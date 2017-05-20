package com.example.ddlmanager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public  String sc;
    public int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText)findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//
//                    return true;
//                }
//                return false;
//            }
//        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button re = (Button)findViewById(R.id.register_b);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();


            }
        });
//
//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);

        re.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,pw;
                name = mEmailView.getText().toString();
                pw =  mPasswordView.getText().toString();
                sc = "register#"+name+"#"+ pw;
                flag=0;
                register();
                while(flag==0) {
                }
                if(flag==1) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username",mEmailView.getText().toString());
                    bundle.putString("password",mPasswordView.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


    }

    public void attemptLogin() {
        String name,pw;
        name = mEmailView.getText().toString();
        pw =  mPasswordView.getText().toString();
        sc = "login#"+name+"#"+ pw;
        flag=0;
        login();
        //flag=1;
        while(flag==0) {
        }
            if(flag==1) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",mEmailView.getText().toString());
                bundle.putString("password",mPasswordView.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

    }

    public void login() {

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
                    pw.println(sc);
                    pw.flush();

                    String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    get = br.readLine();
                    if(get.equals("loginsucess")) {
                        flag = 1;
                    }else {
                        flag =2;
                    }

                    br.close();
                    pw.close();
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
                        Log.i("nowsq","login1");

                        if(flag==1) {
                            Toast.makeText(LoginActivity.this,"login success",Toast.LENGTH_SHORT).show();
                        } else if(flag==2){
                            Toast.makeText(LoginActivity.this,"login failed",Toast.LENGTH_SHORT).show();
                        }

                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","login2");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();
    }
    public void register() {
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
                    pw.println(sc);
                    pw.flush();

                    String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    get = br.readLine();
                    if(get.equals("successregister")) {
                        flag = 1;
                    }else {
                        flag =2;
                    }

                    br.close();
                    pw.close();
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
                        Log.i("nowsq","register1");

                        if(flag==1) {
                            Toast.makeText(LoginActivity.this,"register success",Toast.LENGTH_SHORT).show();
                        } else if(flag==2){
                            Toast.makeText(LoginActivity.this,"register failed",Toast.LENGTH_SHORT).show();
                        }

                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","register2");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();
    }
}


