package netWork.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSend {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        String str = "hello world";
        DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(),
                InetAddress.getByName("127.0.0.1"), 3000);

        System.out.println("UdpSend: ��Ҫ������Ϣ");
        ds.send(dp);
        System.out.println("UdpSend: �ҷ�����Ϣ����");

        Thread.sleep(1000);
        byte[] buf = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buf, 1024);
        System.out.println("UdpSend: ���ڵȴ���Ϣ");
        ds.receive(dp2);
        System.out.println("UdpSend: �ҽ��յ���Ϣ");
        String str2 = new String(dp2.getData(), 0, dp2.getLength()) +
                " from " + dp2.getAddress().getHostAddress() + ":" + dp2.getPort();
        System.out.println(str2);

        ds.close();
    }
}
