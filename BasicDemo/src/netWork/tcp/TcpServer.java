package netWork.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8001); //פ����8001�˿�
            Socket s = ss.accept();                   //�������ȵ��пͻ�����������
            System.out.println("welcome to the java world");
            InputStream ips = s.getInputStream();     //��������������������
            OutputStream ops = s.getOutputStream();   //�������
            //ͬһ��ͨ��������˵���������ǿͻ��˵�������������˵����������ǿͻ��˵������

            ops.write("Hello, Client!".getBytes());  //���һ�仰���ͻ���


            BufferedReader br = new BufferedReader(new InputStreamReader(ips));
            //�ӿͻ��˶�ȡһ�仰
            System.out.println("Client said: " + br.readLine());


            ips.close();
            ops.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
