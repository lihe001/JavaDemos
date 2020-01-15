package publicPrivateProtected.test2;

package publicPrivateProtected.test1.A;

public class E {
    public void show() {
        //E is not a subclass of A. And E is not in the same package of A.
        //System.out.println(v1);  error, private
        //System.out.println(v2);  error, default
        //System.out.println(v3);
        //System.out.println(v4);
        //showV1();  error, private
        //showV2();  error, default
        //showV3();
        //showV4();

        publicPrivateProtected.test1.A obj = new publicPrivateProtected.test1.A();
        //System.out.println(obj.v1);   error,  private
        //System.out.println(obj.v2);   error,  default
        //System.out.println(obj.v3);   error,  protected ֻ����Ϊ������ܷ���
        System.out.println(obj.v4);

        //obj.showV1();   error,  private
        //obj.showV2();   error,  default
        //obj.showV3();   error   protected ֻ����Ϊ������ܷ���
        obj.showV4();
    }
}
