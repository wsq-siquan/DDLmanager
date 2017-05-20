import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Tryclient {
	public static void main(String[] args) throws Exception, IOException {
		
		String get;
		
		Socket socket = new Socket("127.0.0.1",7100);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("login#wsq#pw");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();		
		socket.close();
		
		
		socket = new Socket("127.0.0.1",7100);
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("register#wsq#pw");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();
		socket.close();
		
		socket = new Socket("127.0.0.1",7100);
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("query#user");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();
		socket.close();
		
		socket = new Socket("127.0.0.1",7100);
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("add#user#detail#2016-10-10#2016-12-25");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();
		socket.close();
		
		socket = new Socket("127.0.0.1",7100);
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("update#user#detail#detail2#2016-10-10#2016-12-25");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();
		socket.close();
		
		socket = new Socket("127.0.0.1",7100);
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println("delete#user#hw4");
		pw.flush();
		get=br.readLine();
		System.out.println(get);
		br.close();
		pw.close();
		socket.close();
	}
}
