import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        //����һ��ServerSocket,���ڼ����ͻ���socket����������
        ServerSocket ss=new ServerSocket(30000);
        //����ѭ�����Ͻ������Կͻ��˵�����,��������Ҳ��Ӧ����һ��Socket
        while(true){
            Socket s=ss.accept();
            OutputStream os=s.getOutputStream();
            os.write("���ã����յ��˷�����������ף����n".getBytes("utf-8"));
            os.close();
            s.close();
    }

    }
}
