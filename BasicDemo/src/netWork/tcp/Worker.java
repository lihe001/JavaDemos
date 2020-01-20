package netWork.tcp;

import java.io.*;
import java.net.Socket;

class Worker implements Runnable {
    Socket s;

    public Worker(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            System.out.println("������Ա�Ѿ�����");
            InputStream ips = s.getInputStream();
            OutputStream ops = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(ips));
            DataOutputStream dos = new DataOutputStream(ops);
            while (true) {
                String strWord = br.readLine();
                System.out.println("client said:" + strWord + ":" + strWord.length());
                if (strWord.equalsIgnoreCase("quit"))
                    break;
                String strEcho = strWord + " 666";
                // dos.writeBytes(strWord +"---->"+ strEcho +"\r\n");
                System.out.println("server said:" + strWord + "---->" + strEcho);
                dos.writeBytes(strWord + "---->" + strEcho + System.getProperty("line.separator"));
            }
            br.close();
            // �رհ�װ�࣬���Զ��رհ�װ��������װ�ĵײ��ࡣ���Բ��õ���ips.close()
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
