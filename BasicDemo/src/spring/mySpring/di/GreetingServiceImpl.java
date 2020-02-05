package spring.mySpring.di;


import spring.core.annotations.Autowired;
import spring.core.annotations.Component;
import spring.core.annotations.PostConstruct;

@Component
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    HelloWorld helloWorld;

    @PostConstruct
    public void post() {
        System.out.println("Greeting Service Impl is ready: " + helloWorld.hello());
    }

    @Override
    public void greet() {
        System.out.println("Simple greeting");
    }

}
