package netWork.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 8001); //��Ҫ������ȿ���

            //ͬһ��ͨ��������˵���������ǿͻ��˵�������������˵����������ǿͻ��˵������
            InputStream ips = s.getInputStream();    //����ͨ����������
            BufferedReader brNet = new BufferedReader(new InputStreamReader(ips));

            OutputStream ops = s.getOutputStream();  //����ͨ���������
            DataOutputStream dos = new DataOutputStream(ops);

            BufferedReader brKey = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String strWord = brKey.readLine();
                if (strWord.equalsIgnoreCase("quit")) {
                    break;
                } else {
                    System.out.println("I want to send: " + strWord);
                    dos.writeBytes(strWord + System.getProperty("line.separator"));

                    System.out.println("Server said: " + brNet.readLine());
                }

            }

            dos.close();
            brNet.close();
            brKey.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
