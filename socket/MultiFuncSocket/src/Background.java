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

import org.omg.CORBA.StringHolder;
 

public class Background {
	public static Socket socket;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		int i=0;
		ServerSocket server = new ServerSocket(7100);
		System.out.println("server running...");
		while (true) {
			
			socket = server.accept();
			System.out.println("accept"+i);
			func();
			socket.close();
			i++;
		}
	}
	
	public static void func() throws IOException, SQLException, ClassNotFoundException {
		
		Connection conn = null;
        String sql;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost/t1?"
                + "user=root&password=1234&useUnicode=true&characterEncoding=UTF8";
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
        
        
        
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    	String fromcli;
		fromcli = br.readLine();
		String[] str= fromcli.split("#");
		if(str[0].equals("login")) {
			String name = str[1];
			String passw = str[2];
			
			sql = "SELECT * FROM socketdb.user_pw where username='"+name+"';";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            //String tString = "user";
            if(rs.next()) {
                if(name.equals(rs.getString(1))) {

                	if(passw.equals(rs.getString(2))) {
                		System.out.println("loginsucess");
                		pw.println("loginsucess");
                		pw.flush();
                	} else {
                		System.out.println("incorrectpassword");
                		pw.println("incorrectpassword");
                		pw.flush();
                		
                	}                	
            	}
            	
            } else {
            	System.out.println("user does not exist");
            	pw.println("user does not exist");
        		pw.flush();
            }
			System.out.println("login");
			
			
		} else if (str[0].equals("register")) {			
			String name = str[1];
			String passw = str[2];
	        sql = "SELECT * FROM socketdb.user_pw where username='"+name+"';";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
           
            if(rs.next()) {
                if(name.equals(rs.getString(1))) {
//                	return "Login Success";
//                else return "incorrect password";
            	System.out.println("already has this user");
            	pw.println("already has this user");
        		pw.flush();
            	}
            	
            } else {
            	sql = "insert socketdb.user_pw(username,passw) values('"+name+"','"+passw+"');";
            	int result = stmt.executeUpdate(sql);
            	System.out.println("sucess"+ result);
            	pw.println("successregister");
        		pw.flush();
            }
			System.out.println("register");
			
		} else if(str[0].equals("query")) {
			
			sql = "SELECT * FROM socketdb.detail_data where ename = \""+str[1]+"\" order by ddl asc;";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            //PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(rs.next()) {
               
            	System.out.println("1\n");
            	String shuchu =rs.getString(1) + "#" + rs.getString(2)+"#" +rs.getString(3)+"#" +rs.getString(4);
            	System.out
                        .println(shuchu);// 入如果返回的是int类型可以用getInt()
            	
            	pw.println(shuchu);
            	pw.flush();
            }
           //pw.println("query sucess");
           //pw.flush();
			
			System.out.println("query");
		}else if(str[0].equals("query2")) {
			
			sql = "SELECT * FROM socketdb.detail_data where ename = \""+str[1]+"\";";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            //PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(rs.next()) {
               
            	System.out.println("1\n");
            	String shuchu =rs.getString(1) + "#" + rs.getString(2)+"#" +rs.getString(3)+"#" +rs.getString(4);
            	System.out
                        .println(shuchu);// 入如果返回的是int类型可以用getInt()
            	
            	pw.println(shuchu);
            	pw.flush();
            }
           //pw.println("query sucess");
           //pw.flush();
			
			System.out.println("query2");
		} else if (str[0].equals("add")) {
			
			String name = str[1];
			String detail = str[2];
			String startdate = str[3];
			String ddl = str[4];
	        sql = "SELECT * FROM socketdb.detail_data where ename='"+name+"' and detail ='"+detail+"';";
	        System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
           
            if(rs.next()) {
                if(name.equals(rs.getString(1))) {
//                	return "Login Success";
//                else return "incorrect password";
            	System.out.println("already has this item");
            	pw.println("already has this item");
            	pw.flush();
            	}
            	
            } else {
            	sql = "insert socketdb.detail_data(ename,detail,startdate,ddl) values('"+name+"','"+detail+"','"+startdate+"','"+ddl+"');";
            	int result = stmt.executeUpdate(sql);
            	System.out.println("sucess_add"+ result);
            	pw.println("sucessadd"+result);
            	pw.flush();
            }
			
			System.out.println("add");
		} else if (str[0].equals("update")) {
			
			String name = str[1];
			String detail = str[2];
			String startdate = str[3];
			String ddl = str[4];
			String ndetail = str[5];
			String nstartdate = str[6];
			String nddl = str[7];
			
			sql = "SELECT * FROM socketdb.detail_data where ename='"+name+"' and detail ='"+ndetail+"'and startdate='"+nstartdate+"' and ddl = '"+nddl+"';";
			System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
           
            if(rs.next()) {
                if(name.equals(rs.getString(1))) {
//                	return "Login Success";
//                else return "incorrect password";
            	System.out.println("already has this item");
            	pw.println("already has this item");
            	pw.flush();
            	}
            	
            } else {
            	sql = "UPDATE socketdb.detail_data SET detail='"+ndetail+"',startdate='"+nstartdate+"',ddl='"+nddl+"' WHERE ename='"+name+"' and detail ='"+detail+"';";
    			int result = stmt.executeUpdate(sql);
    			System.out.println("sucess_update"+ result);
            	pw.println("sucessupate"+result);
            	pw.flush();
            }
			
			System.out.println("update");
		} else if(str[0].equals("delete")) {
			String name = str[1];
			String detail = str[2];
			sql = "delete from socketdb.detail_data where ename='"+name+"' and detail='"+detail+"';";
			int result = stmt.executeUpdate(sql);
			System.out.println("sucess_delete"+ result);
        	pw.println("sucessdelete"+result);
        	pw.flush();
			
		}
		pw.close();
		br.close();
	}
	
}
