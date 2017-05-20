import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

public class CS {
	public static void main(String[] args) throws IOException {
		//�Ƚ���һ��server socket
		ServerSocket ss = new ServerSocket(10987);
		System.out.println("server running");
		
		//�ٽ���һ��socket
		Socket s = ss.accept();
		//���ǵõ��ͻ��˴�������Ϣ��reader
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//���Ǵӷ����д��ȥ��Ϣ��writer
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		
		//��socket����accept����������ֱ���ͻ���������
		//s = ss.accept();
		String msg = null;
		String send = null;
		
		while(true) {
			msg = br.readLine();
			System.out.println("from c :" + msg);
			System.out.println("i say :");
			Scanner sc = new Scanner(System.in);
			send = sc.nextLine();
			pw.println(send);
			pw.flush();
			if(msg.equals("exit")) break;
		}
		s.close();
		ss.close();
		System.out.println("socket close");
		
	}
}