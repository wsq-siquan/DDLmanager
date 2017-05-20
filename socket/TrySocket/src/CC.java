import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CC {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket  s =new Socket("127.0.0.1", 10987);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		String get,send;
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("i send :");
			send = sc.nextLine();
			pw.println(send);
			pw.flush();
			get = br.readLine();
			System.out.println("i read : " + get);
			if(send.equals("exit")) break;
		}
		s.close();
		System.out.println("socket close");
		
	}
}
