import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CFA {
	
	public static void main(String[] args) throws IOException {
		//���ӷ�����
		Socket socket = new Socket(S3.ip,S3.port);
		System.out.println("client running...");
		
		System.out.println("-----insert your info:");
		//�û��ڿ���̨��������������͵���Ϣ
		BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
		//IO��
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String server = null;
		//����û�����exit��ر�Socket
		while(!"exit".equalsIgnoreCase(server =  brConsole.readLine())){
			pw.println(server);
			pw.flush();
			System.out.println("from server say:"+br.readLine());
		}
		//���û�����exitʱ����exit��Ϣ���͸����������������رյ�ǰSocket
		pw.flush();
		
		pw.close();
		brConsole.close();
		br.close();
		
		socket.close();
	}
}