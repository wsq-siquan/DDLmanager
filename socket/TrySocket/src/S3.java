import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class S3 {
	public static int port = 3333;
	public static String ip = "127.0.0.1";
	
	public static void main(String[] args) throws IOException {
		//������������
		ServerSocket server = new ServerSocket(port);
		System.out.println("server running...");
		//�ڼ���Socket
		Socket socket = server.accept();
		//��IO��
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		String client = null;
		//������յ��ͻ��˵�exit�ź���ر�Socket
		while(!"exit".equalsIgnoreCase(client = br.readLine())){
			System.out.println("from client say:"+client);
			pw.println("server get your info :" +client);
			pw.flush();
		}
				
		br.close();
		pw.close();
		
		socket.close();
		server.close();
	}
}