package proxy;

//目标对象
class SubjectImpl implements Subject {
    public void request() {
        System.out.println("I am dealing the request.");
    }
}


