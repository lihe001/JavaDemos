package aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class Main {


    public static void initXml() {
        XmlReader.readXml("C:\\Users\\lihe1\\JavaDemos\\BasicDemo\\src\\aop\\aops.xml");
        ResourceListener.addListener("C:\\Users\\lihe1\\JavaDemos\\BasicDemo\\src\\aop");
    }

    public static void main(String[] args) throws Exception {
        Main.initXml();

        Person action = new PersonImpl();
        ProxyHandler mh = new ProxyHandler(action);
        ClassLoader cl = Main.class.getClassLoader();
        Class<?> proxyClass = Proxy.getProxyClass(cl, new Class<?>[]{Person.class});
        Person proxy = (Person) proxyClass.getConstructor(new Class[]{InvocationHandler.class}).
                newInstance(new Object[]{mh});

        while (true) {
            proxy.eat();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
