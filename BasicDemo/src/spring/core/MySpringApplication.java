package spring.core;


import spring.core.annotations.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MySpringApplication {

    public static boolean ENABLE_LOG = true;
    private List<Object> beans;
    private Object[] proxyBeans;
    private CommandLineRunner runner;

    public static void run(Class main) {
        MySpringApplication app = new MySpringApplication();
        try {
            //����Bean����
            app.createBeans(main);

            //��������Bean�ϵ�Before��After����
            app.aop();

            //�����е�Autowire��Ա������ֵ
            app.di();

            //����Bean�������еķ����������Postע�⣬��ִ�и÷���
            app.post();
            log("My Spring init successfully............");
            if (app.runner != null) {
                app.runner.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ����main�࣬ȥ������Ӧ��Bean
     *
     * @param main
     * @return
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     */
    private Stream<Class> loadClasses(Class main) throws MalformedURLException, ClassNotFoundException {
        URL resource = main.getResource("");
        File baseDir = new File(resource.getFile());

        //��ż��ص�Ŀ¼����
        Queue<File> dirs = new LinkedList<>();
        dirs.add(baseDir);

        int offset = main.getResource("/").getPath().length();

        Stream.Builder<Class> classesBuilder = Stream.builder();

        while (!dirs.isEmpty()) {
            //ȡ��һ��Ŀ¼
            File tmp = dirs.poll();
            //������Ŀ¼��������Ŀ¼�������е�class
            for (File f : tmp.listFiles()) {
                if (f.isDirectory()) {
                    dirs.add(f);
                } else {
                    if (f.getName().endsWith(".class")) {
                        String clsName = f.toURI().toURL().getPath().substring(offset).replaceAll("/", ".").replace(".class", "");
                        //�������еĽӿ�
                        if (!clsName.contains("edu.ecnu.core")) {
                            Class cls = main.getClassLoader().loadClass(clsName);
                            log("load class: " + cls.getName());
                            classesBuilder.accept(cls);
                        }
                    }
                }
            }
        }
        return classesBuilder.build();
    }

    private void createBeans(Class main) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, MalformedURLException, ClassNotFoundException {
        //��ȡ���зǽӿڵ���, �����й���, ֻ���´���Component�Ķ���
        beans = loadClasses(main).filter(cls ->
                Arrays.stream(cls.getAnnotations()).anyMatch(a -> a instanceof Component)
        ).map(cls -> {
            try {
                return cls.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.toList());

        //���µ�һ������Componentע���, ����CommandLineRunner�ӿڵĶ���, ��Ϊrunner
        runner = (CommandLineRunner) beans.stream().filter(bean ->
                Arrays.stream(bean.getClass().getInterfaces()).anyMatch(i -> i.equals(CommandLineRunner.class))
        ).findFirst().orElse(null);
    }

    private void aop() {
        Stream.Builder<MyAop> myAopsBuilder = Stream.builder();

        //�������е�Bean
        for (Object bean : beans) {
            //�鿴�Ƿ���Aspectע��
            boolean isAspect = Arrays.stream(bean.getClass().getAnnotations()).anyMatch(a -> (a instanceof Aspect));
            if (isAspect) {
                //������Bean���еķ���
                for (Method m : bean.getClass().getMethods()) {
                    for (Annotation a : m.getAnnotations()) {
                        String pointCut = null;
                        AdviceEnum advice = null;
                        if (a instanceof Before) {
                            pointCut = ((Before) a).value();
                            advice = AdviceEnum.BEFORE;
                        }
                        if (a instanceof After) {
                            pointCut = ((After) a).value();
                            advice = AdviceEnum.AFTER;
                        }
                        if (pointCut != null) {
                            //��ע���е������ͷ������ֿ�
                            int sep = pointCut.lastIndexOf(".");
                            String targetClass = pointCut.substring(0, sep);
                            String targetMethod = pointCut.substring(sep + 1);
                            myAopsBuilder.accept(new MyAop(targetClass, targetMethod, bean, advice, m));
                        }
                    }
                }
            }
        }

        Map<String, Map<String, List<MyAop>>> clsMethodAopMapping = myAopsBuilder.build().collect(Collectors.groupingBy(MyAop::getTarget,
                Collectors.groupingBy(MyAop::getTargetMethod)));

        proxyBeans = new Object[beans.size()];
        for (int i = 0; i < beans.size(); i++) {
            Object bean = beans.get(i);
            String clsName = bean.getClass().getName();
            if (clsMethodAopMapping.containsKey(clsName)) {
                Class[] interfaces = bean.getClass().getInterfaces();
                if (interfaces.length > 0) {

                    Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                            interfaces, (proxy, method, args) -> {
                                String methodName = method.getName();
                                List<MyAop> aops = clsMethodAopMapping.get(clsName).get(methodName);
                                runAop(aops, AdviceEnum.BEFORE, method, args);
                                Object res = method.invoke(bean, args);
                                runAop(aops, AdviceEnum.AFTER, method, args);
                                return res;
                            });
                    proxyBeans[i] = proxyInstance;
                }
            }
        }

    }

    private void runAop(List<MyAop> aops, AdviceEnum advice, Method m, Object... args) {
        if (aops != null) {
            aops.stream().filter(a -> a.getAdvice() == advice).forEach(aop -> {
                try {
                    aop.getMethod().invoke(aop.getAspect(), m, args);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new IllegalStateException(e);
                }
            });
        }
    }

    private void di() throws IllegalArgumentException, IllegalAccessException {
        //�������е�Bean
        for (Object bean : beans) {
            //��ȡÿ��Bean�ĳ�Ա����
            for (Field f : bean.getClass().getDeclaredFields()) {
                //�����Ա�����Ǵ�Autowiredע��
                if (Arrays.stream(f.getAnnotations()).anyMatch(a -> (a instanceof Autowired))) {
                    Class fCls = f.getType();
                    Object requiredBean;
                    //����ǽӿڣ��͸���Bean�ĸ���ӿ��ж����������
                    if (fCls.isInterface()) {
                        requiredBean = getRequiredInterfaceBean(fCls);
                    } else {
                        //������ǽӿڣ�ֱ�Ӹ������
                        requiredBean = getRequiredBean(fCls);
                    }
                    //�����ȡ����ɹ����͸���Ա������ֵ
                    if (requiredBean != null) {
                        f.setAccessible(true);
                        f.set(bean, requiredBean);
                        log(String.format("Field %s has annotation Autowired, execute di, %s",
                                f.toString(), requiredBean
                        ));
                    }
                }
            }
        }
    }

    private void post() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        log("Start Post Processes:");
        for (Object bean : beans) {
            for (Method m : bean.getClass().getMethods()) {
                if (Arrays.stream(m.getAnnotations()).anyMatch(a -> (a instanceof PostConstruct))) {
                    m.setAccessible(true);
                    m.invoke(bean);
                }
            }
        }
    }

    private Object getRequiredInterfaceBean(Class cls) {
        for (int i = 0; i < beans.size(); i++) {
            Object bean = beans.get(i);
            for (Class beanI : bean.getClass().getInterfaces()) {
                if (beanI.equals(cls)) {
                    return proxyBeans[i] != null ? proxyBeans[i] : bean;
                }
            }
        }
        return null;
    }

    private Object getRequiredBean(Class cls) {
        return beans.stream().filter(bean -> bean.getClass().equals(cls))
                .findFirst().orElse(null);
    }

    private static void log(Object msg) {
        if (ENABLE_LOG) {
            System.out.println(msg);
        }
    }
}
