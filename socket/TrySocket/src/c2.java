
/**
 * 
 */


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;


public class c2 {

	
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			//�ͻ���socketָ���������ĵ�ַ�Ͷ˿ں�
			socket = new Socket("10.11.6.86", 4000);
			System.out.println("Socket=" + socket);
			//ͬ������ԭ��һ��
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())));
			
				String line = br.readLine();
				System.out.println(line);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("close......");
				br.close();
				pw.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
