import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class C3 {
	
	public static void main(String[] args) throws IOException {
		//���ӷ�����
		Socket socket = new Socket("127.0.0.1",7777);
		System.out.println("client running...");
		
		System.out.println("-----insert your info:");
		//�û��ڿ���̨��������������͵���Ϣ
		BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
		//IO��
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String server = null;
		//����û�����exit��ر�Socket
		while(!"end".equalsIgnoreCase(server =  brConsole.readLine())){
			pw.println(server);
			pw.flush();
			System.out.println("from server say:"+br.readLine());
		}
		//���û�����endʱ����end��Ϣ���͸����������������رյ�ǰSocket
		pw.println(server);
		pw.flush();
		
		pw.close();
		brConsole.close();
		br.close();
		
		socket.close();
	}
}