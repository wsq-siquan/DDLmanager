import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CFA {
	
	public static void main(String[] args) throws IOException {
		//连接服务器
		Socket socket = new Socket(S3.ip,S3.port);
		System.out.println("client running...");
		
		System.out.println("-----insert your info:");
		//用户在控制台输入向服务器发送的信息
		BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
		//IO流
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String server = null;
		//如果用户输入exit则关闭Socket
		while(!"exit".equalsIgnoreCase(server =  brConsole.readLine())){
			pw.println(server);
			pw.flush();
			System.out.println("from server say:"+br.readLine());
		}
		//当用户输入exit时，把exit信息发送给服务器，服务器关闭当前Socket
		pw.flush();
		
		pw.close();
		brConsole.close();
		br.close();
		
		socket.close();
	}
}