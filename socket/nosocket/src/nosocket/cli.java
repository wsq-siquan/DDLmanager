package nosocket;

import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class cli{
	public static void main(String[] args) throws IOException {
	Socket socket = new Socket("127.0.0.1",7100);
	BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	pw.println("wsq#pw");
	pw.flush();
	
	String get,send;
	//get = br.readLine();
	while((get=br.readLine()) != null) {
		//Scanner sc = new Scanner(System.in);
		//System.out.println("i send :");
		//send = sc.nextLine();
		//pw.println(send);
		//pw.flush();
		//get = br.readLine();
		//if(get ==null) break;
		//System.out.println("i read : " + get);
		String[] str= get.split("#");
		System.out.println(str[0]);
		System.out.println(str[1]);System.out.println(str[2]);System.out.println(str[3]);System.out.println(str[4]);
		
		//if(send.equals("exit")) break;
	}
	br.close();
	pw.close();
	System.out.print("end\n");
	}
}
