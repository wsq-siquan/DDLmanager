package com.example.ddlmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class add extends AppCompatActivity {
    public EditText detail,std,edd;
    public Button add,delete;
    public String d="hhh",s="2016-01-01",e="2016-03-01";
    public  String outputS;
    public String type,username,odetail,ostd,oedd;
    public int flag=0;
    public TextView addback,ndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle bundle = this.getIntent().getExtras();
         type = bundle.getString("type");
         username = bundle.getString("username");
        odetail = bundle.getString("detail");
        ostd = bundle.getString("std");
        oedd =bundle.getString("edd");
        //String password = bundle.getString("password");

        detail = (EditText)findViewById(R.id.detail_add);
        std = (EditText)findViewById(R.id.std_add);
        edd = (EditText)findViewById(R.id.edd_add);
        add = (Button)findViewById(R.id.add_to_db);
        delete= (Button)findViewById(R.id.delete_b);
        ndelete = (TextView)findViewById(R.id.new_delete);

        if (type.equals("add")) {
            add.setText("add");
            delete.setText("CLEAR");
           ndelete.setVisibility(View.INVISIBLE);
        } else if(type.equals("update")) {

            detail.setText(odetail);
            std.setText(ostd);
            edd.setText(oedd);
            add.setText("update");
            //detail.setText();
            delete.setText("Clear");
           //    delete.setVisibility(View.VISIBLE);
        }
        d= detail.getText().toString();
        s=std.getText().toString();
        e=edd.getText().toString();
        //outputS = "insert t1.t3_data(ename,pw,detail,ddl,startdate) values('"+ username+"','"+password+"','"+d+"','"+s+"','"+e+"'); ";


        ndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d= detail.getText().toString();
                s=std.getText().toString();
                e=edd.getText().toString();
                flag=0;
                outputS = "delete#"+username+"#"+odetail;
                deletefDB();
                while(flag==0) {}
                if(flag==1) {
                    Intent intent = new Intent(add.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username",username);
                    //bundle.putString("password",mPasswordView.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("add")) {
                    d= detail.getText().toString();
                    s=std.getText().toString();
                    e=edd.getText().toString();
                    flag=0;
                    outputS = "add#"+username+"#"+d+"#"+s+"#"+e;
                    add2db();
                    while(flag==0) {}
                    if(flag==1) {
                        Intent intent = new Intent(add.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        //bundle.putString("password",mPasswordView.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else if(type.equals("update")) {
                    d= detail.getText().toString();
                    s=std.getText().toString();
                    e=edd.getText().toString();
                    flag=0;
                    outputS = "update#"+username+"#"+ odetail+"#"+ostd+"#"+oedd+"#"+d+"#"+s+"#"+e;
                    update2db();
                    while(flag==0) {}
                    if(flag==1) {
                        Intent intent = new Intent(add.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        //bundle.putString("password",mPasswordView.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    detail.setText("");
                    std.setText("");
                    edd.setText("");


            }
        });

        addback = (TextView)findViewById(R.id.add_back);
        addback.setText("<");
        addback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                //bundle.putString("password",mPasswordView.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void update2db() {
        new Thread(){
            @Override
            public void run()
            {
                //你的socket代码放这里
                Socket socket = null;
                try {
                    InetAddress seradd = InetAddress.getByName("192.168.191.1");
                    socket = new Socket(seradd, 7100);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //String sc = n+"#"+p;
                    pw.println(outputS);
                    pw.flush();

                    String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    get = br.readLine();
                    if(get.equals("sucessupate1")) {
                        flag = 1;
                    }else {
                        flag =2;
                    }

                    br.close();
                    pw.close();
                    Log.i("nowsq",get);
                    Log.i("nowsq","close");
                } catch (Exception e) {
                    Log.e("nowsq", "来自服务器的数据");
                    //show.setText("fail2");
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //更新UI
                        Log.i("nowsq","update1");

                        if(flag==1) {
                            Toast.makeText(add.this,"update success",Toast.LENGTH_SHORT).show();
                        } else if(flag==2){
                            Toast.makeText(add.this,"update failed",Toast.LENGTH_SHORT).show();
                        }

                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","update2");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();
    }
    public void deletefDB() {
        new Thread(){
            @Override
            public void run()
            {
                //你的socket代码放这里
                Socket socket = null;
                try {
                    InetAddress seradd = InetAddress.getByName("192.168.191.1");
                    socket = new Socket(seradd, 7100);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //String sc = n+"#"+p;
                    pw.println(outputS);
                    pw.flush();

                    String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    get = br.readLine();
                    if(get.equals("sucessdelete1")) {
                        flag = 1;
                    }else {
                        flag =2;
                    }

                    br.close();
                    pw.close();
                    Log.i("nowsq",get);
                    Log.i("nowsq","close");
                } catch (Exception e) {
                    Log.e("nowsq", "来自服务器的数据");
                    //show.setText("fail2");
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //更新UI
                        Log.i("nowsq","delete1");

                        if(flag==1) {
                            Toast.makeText(add.this,"delete success",Toast.LENGTH_SHORT).show();
                        } else if(flag==2){
                            Toast.makeText(add.this,"delete failed",Toast.LENGTH_SHORT).show();
                        }

                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","delete2");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();
    }

    public void add2db() {

        new Thread(){
            @Override
            public void run()
            {
                //你的socket代码放这里
                Socket socket = null;
                try {
                    InetAddress seradd = InetAddress.getByName("192.168.191.1");
                    socket = new Socket(seradd, 7100);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //String sc = n+"#"+p;
                    pw.println(outputS);
                    pw.flush();

                    String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    get = br.readLine();
                    if(get.equals("sucessadd1")) {
                        flag = 1;
                    }else {
                        flag =2;
                    }

                    br.close();
                    pw.close();
                    Log.i("nowsq",get);
                    Log.i("nowsq","close");
                } catch (Exception e) {
                    Log.e("nowsq", "来自服务器的数据");
                    //show.setText("fail2");
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //更新UI
                        Log.i("nowsq","add1");

                        if(flag==1) {
                            Toast.makeText(add.this,"add success",Toast.LENGTH_SHORT).show();
                        } else if(flag==2){
                            Toast.makeText(add.this,"add failed",Toast.LENGTH_SHORT).show();
                        }

                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","add2");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();

    }


}
