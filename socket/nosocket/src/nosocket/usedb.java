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
		//①启动服务器
		ServerSocket server = new ServerSocket(port);
		System.out.println("server running...");
		//②监听Socket
		socket = server.accept();
		System.out.println("accept");
		//③IO流
		//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		//pw.println("55555");
		//pw.flush();
		//String client = null;
		//如果接收到客户端的exit信号则关闭Socket
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
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost/t1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
        
        
		
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
            
            String[] str= input.split("#");
    		System.out.println(str[0]);
    		System.out.println(str[1]);
    		
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "create table if not exists Email_password\n" +
                    "(\n" +
                    "email varchar(255),\n" +
                    "password varchar(255),\n" +
                    "PRIMARY KEY(email)\n" +
                    ")";
            
            int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            if (result != -1) {
                System.out.println("创建数据表成功");
                
                sql = "select * from Email_password where email = \"" + str[0]+" \" ";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                
                if(rs.next()) {
                    if(str[1].equals(rs.getString(2)))
                    	return "Login Success";
                    else return "incorrect password";
                	
                	//System.out
                            //.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
                } else {
                	sql = "insert into Email_password(email,password) values(\"" +str[0] +"\",\"" +str[1] +"\")";
                	System.out.println(sql);
                	result = stmt.executeUpdate(sql);
                	return "Sign IN";
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
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
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost/t1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
        
        
		
 
        try {
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	String nandp;
    		nandp = br.readLine();
    		String[] str= nandp.split("#");
    		
        	
        	
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
             //com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
            
    		
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "create table if not exists t3_data(ename varchar(255), pw varchar(255),  detail varchar(255),ddl date, startdate date"
                    +");";
            
            int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            if (result != -1) {
                System.out.println("创建数据表成功");
                
                sql = "SELECT * FROM t1.t3_data where ename = \""+str[0]+"\"  and pw=\""+ str[1] +"\";";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                while(rs.next()) {
                   
                	System.out.println("1\n");
                	String shuchu =rs.getString(1) + "#" + rs.getString(2)+"#" +rs.getString(3)+"#" +rs.getString(4)+"#" +rs.getString(5);
                	System.out
                            .println(shuchu);// 入如果返回的是int类型可以用getInt()
                	
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
            System.out.println("MySQL操作错误");
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