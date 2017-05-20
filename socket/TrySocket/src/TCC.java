import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCC {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("client start");
		Socket puts = new Socket("127.0.0.1",9870);
		new Thread(new TPC(puts)).start();
		Socket gets = new Socket("127.0.0.1",9870);
		new Thread(new TGC(gets)).start();
	}
}
class TGC extends Thread {
	 Socket s = null;
	 BufferedReader br;
	public TGC(Socket s) throws IOException {
		// TODO Auto-generated constructor stub
		System.out.println("get thread start...");
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));		
	}
	public void run() {
		try {
			String get= null;
			while(true) {
				get = br.readLine();
				System.out.println("get from server :" + get);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class TPC extends Thread {
	 Socket s = null;
	 PrintWriter pw;
	public TPC(Socket s) throws IOException {
		// TODO Auto-generated constructor stub
		System.out.println("put thread start...");
		this.s = s;
		pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));	
	}
	public void run() {
		try {
			String put= null;
			while(true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("put to server :");
				put = sc.nextLine();
				pw.println(put);
				pw.flush();
				//sc.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

