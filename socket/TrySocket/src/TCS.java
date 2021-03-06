import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.swing.text.AbstractDocument.BranchElement;


public class TCS {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9870);
		System.out.println("server start...");
		
		Socket s = ss.accept();
		new Thread(new TG(s)).start();
		s = ss.accept();
		new Thread(new TP(s)).start();
		
	}
}


 class TG extends Thread {
	 Socket s = null;
	 BufferedReader br;
	public TG(Socket s) throws IOException {
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
				System.out.println("get from client :" + get);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
 
 class TP extends Thread {
	 Socket s = null;
	 PrintWriter pw;
	public TP(Socket s) throws IOException {
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
				System.out.println("put to client :");
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

