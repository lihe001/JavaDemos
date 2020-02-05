package spring.mySpring.di;


import spring.core.annotations.Component;

@Component
public class HelloWorld {

    public String hello() {
        return "Hello world";
    }
}
