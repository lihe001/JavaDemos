package exceptionExemple;

import java.io.IOException;

public class Son extends Father {
    public void f1() throws IOException {
        //子类重写方法，
        //所抛出的异常不能超出父类规定的范围
    }
}

