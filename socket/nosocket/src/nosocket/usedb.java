package nosocket;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
 

public class usedb {
	public static int port = 7100;
	//public static String ip = "192.168.191.1";
	public static Socket socket;
	
	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		//������������
		ServerSocket server = new ServerSocket(port);
		System.out.println("server running...");
		//�ڼ���Socket
		socket = server.accept();
		System.out.println("accept");
		//��IO��
		//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		//pw.println("55555");
		//pw.flush();
		//String client = null;
		//������յ��ͻ��˵�exit�ź���ر�Socket
		//client = br.readLine();
//		int i=0;
//		while(true){
//			i++;
//			System.out.println(i);
//			if(client != null) {
//				System.out.println("from client say:"+client);			
//				pw.println(excsql(client));
//				pw.flush();
//				Thread.sleep(5000);
//				client = br.readLine();
//			} else {
//				pw.println("no message");
//				Thread.sleep(5000);
//				client = br.readLine();
//			}			
//		}
		
		trydb();
	}
	public static String excsql(String input) throws SQLException {
		Connection conn = null;
        String sql;
        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding
        // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
        // �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
        String url = "jdbc:mysql://localhost/t1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
        
        
		
 
        try {
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or��
            // new com.mysql.jdbc.Driver();
            
            String[] str= input.split("#");
    		System.out.println(str[0]);
    		System.out.println(str[1]);
    		
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = DriverManager.getConnection(url);
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
            sql = "create table if not exists Email_password\n" +
                    "(\n" +
                    "email varchar(255),\n" +
                    "password varchar(255),\n" +
                    "PRIMARY KEY(email)\n" +
                    ")";
            
            int result = stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
            if (result != -1) {
                System.out.println("�������ݱ�ɹ�");
                
                sql = "select * from Email_password where email = \"" + str[0]+" \" ";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
                
                if(rs.next()) {
                    if(str[1].equals(rs.getString(2)))
                    	return "Login Success";
                    else return "incorrect password";
                	
                	//System.out
                            //.println(rs.getString(1) + "\t" + rs.getString(2));// ��������ص���int���Ϳ�����getInt()
                } else {
                	sql = "insert into Email_password(email,password) values(\"" +str[0] +"\",\"" +str[1] +"\")";
                	System.out.println(sql);
                	result = stmt.executeUpdate(sql);
                	return "Sign IN";
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
		//return false;
		return "fail";
	}
	
	
	public static void trydb() {
		Connection conn = null;
        String sql;
        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding
        // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
        // �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
        String url = "jdbc:mysql://localhost/t1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
        
        
		
 
        try {
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	String nandp;
    		nandp = br.readLine();
    		String[] str= nandp.split("#");
    		
        	
        	
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
            // or:
             //com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or��
            // new com.mysql.jdbc.Driver();
            
    		
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = DriverManager.getConnection(url);
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
            sql = "create table if not exists t3_data(ename varchar(255), pw varchar(255),  detail varchar(255),ddl date, startdate date"
                    +");";
            
            int result = stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
            if (result != -1) {
                System.out.println("�������ݱ�ɹ�");
                
                sql = "SELECT * FROM t1.t3_data where ename = \""+str[0]+"\"  and pw=\""+ str[1] +"\";";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                while(rs.next()) {
                   
                	System.out.println("1\n");
                	String shuchu =rs.getString(1) + "#" + rs.getString(2)+"#" +rs.getString(3)+"#" +rs.getString(4)+"#" +rs.getString(5);
                	System.out
                            .println(shuchu);// ��������ص���int���Ϳ�����getInt()
                	
                	pw.println(shuchu);
                	pw.flush();
                	
                	
                	//sql = "insert into Email_password(email,password) values(\"" + "ssssssss" +"\",\"" +"wsq_pw" +"\")";
                	//System.out.println(sql);
                	//result = stmt.executeUpdate(sql);
                	//return "Sign IN";
                	//System.out.println("2\n");
                	//System.out.println("end\n");
                }
                pw.close();
                br.close();
            }
        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	System.out.println("end\n");
            //conn.close();
        }
		//return false;
	}
	
	
	
}