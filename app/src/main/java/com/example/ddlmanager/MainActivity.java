package com.example.ddlmanager;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.sql.Connection;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static List<Map<String, String>> data;
    public static ListView listView;
    public static SimpleAdapter simpleAdapter;
    public String sc;
    public List<ItemData> itemDatas;
    public MyAdapter myAdapter;
    public String username;
    public String password;
    public TextView imageButton;
    public int ioc=1;
    final String STATICACTION = "com.example.ddlmanager.staticreceiver";
    private SensorManager mSensorManager = null;
    private Sensor mMagneticSensor, mAccelerometerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        listView.setDivider(null);
        imageButton = (TextView) findViewById(R.id.add_b);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mMagneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        mSensorManager.registerListener(mSensorEventListener, mMagneticSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mSensorEventListener, mAccelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.unregisterListener(mSensorEventListener);



        data = new ArrayList<>();
        itemDatas =new ArrayList<>();


        final Bundle bundle = this.getIntent().getExtras();
         username = bundle.getString("username");
         //password = bundle.getString("password");
//        String username = "";
//        String password = "";
        sc = "query2#"+username;
        queryfDB();

        TextView sortb = (TextView)findViewById(R.id.sort_b);
        sortb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDatas.clear();
                data.clear();
                sc = "query#"+username;
                queryfDB();
            }
        });

            //trydb(username,password);
//        } catch (IOException e) {
//            Log.i("nowsq","no socket");
//            e.printStackTrace();
//        }
        Log.i("nowsq","socket-sucess");
        simpleAdapter = new SimpleAdapter(MainActivity.this, data, R.layout.item,
                new String[]{"detail", "std","edd"}, new int[]{R.id.detail, R.id.std,R.id.edd});
         myAdapter= new MyAdapter(MainActivity.this,itemDatas,4);
       // ioc++;







        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,add.class);
                Bundle bundle = new Bundle();
                bundle.putString("type","add");
                bundle.putString("username",username);
                //bundle.putString("password",password);
                bundle.putString("detail","");
                bundle.putString("std","");
                bundle.putString("edd","");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //showdetail();
                Intent intent = new Intent(MainActivity.this,add.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type","update");
                bundle1.putString("username",data.get(i).get("name"));
                bundle1.putString("detail",data.get(i).get("detail"));
                bundle1.putString("std",data.get(i).get("std"));
                bundle1.putString("edd",data.get(i).get("edd"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }

    public void queryfDB() {
        new Thread(){
            @Override
            public void run()
            {
                //你的socket代码放这里
                Socket socket = null;
                try {

                    //data = new ArrayList<>();
                    /*String[] get= {"t1#2016-01-01#2017-01-01","t2#2016-01-01#2017-01-01","t3#2016-01-01#2017-01-01","t4#2016-01-01#2017-01-01","t5#2016-01-01#2017-01-01"};
                    for(int i=0;i<5;++i) {
                        String[] str= get[i].split("#");
                        Log.i("nowsq",get[i]);
                        ItemData temp1 = new ItemData(str[0],str[1],str[2]);
                        itemDatas.add(temp1);

                        Map<String, String> temp = new LinkedHashMap<>();
                        temp.put("name","name_String");
                        //temp.put("pw",str[1]);
                        temp.put("detail",str[0]);
                        temp.put("std",str[1]);
                        temp.put("edd",str[2]);
                        data.add(temp);

                        if (i==0) { //widget notification
                            Intent intent = new Intent(STATICACTION);
                            Bundle bundleB = new Bundle();
                            bundleB.putInt("per",temp1.calprog());
                            bundleB.putString("detail",str[0]);
                            bundleB.putString("std",str[1]);
                            bundleB.putString("edd",str[2]);
                            //String ttt = String.valueOf(temp1.calprog())+"%";
                            //bundleB.putString("percent",ttt);
                            intent.putExtras(bundleB);
                            sendBroadcast(intent);
                            //bro();

                            Log.i("nowsq","wid success");
                        }
                    }*/

                    InetAddress seradd = InetAddress.getByName("192.168.191.1");
                    socket = new Socket(seradd, 7100);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //String sc = n+"#"+p;
                    pw.println(sc);
                    pw.flush();

                    //String get;
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    int i=1;
                    String get;
                    while((get=br.readLine()) != null) {
                        String[] str= get.split("#");
                        Log.i("nowsq",get);
                        ItemData temp1 = new ItemData(str[1],str[2],str[3]);
                        itemDatas.add(temp1);
                        Map<String, String> temp = new LinkedHashMap<>();
                        temp.put("name",str[0]);
                        //temp.put("pw",str[1]);
                        temp.put("detail",str[1]);
                        temp.put("std",str[2]);
                        temp.put("edd",str[3]);
                        data.add(temp);
                        Log.i("nowsq","add_success");
                        if (i==1) { //widget notification
                            Intent intent = new Intent(STATICACTION);
                            Bundle bundleB = new Bundle();
                            bundleB.putInt("per",temp1.calprog());
                            bundleB.putString("detail",str[1]);
                            bundleB.putString("std",str[2]);
                            bundleB.putString("edd",str[3]);
                            //String ttt = String.valueOf(temp1.calprog())+"%";
                            //bundleB.putString("percent",ttt);
                            intent.putExtras(bundleB);
                            sendBroadcast(intent);
                            //bro();
                            i++;
                            Log.i("nowsq","wid success");
                        }

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
                        Log.i("nowsq","setadapter");

                        listView.setAdapter(myAdapter);
                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","adapter done");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();
    }

    public void trydb(String n, String p){

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
                    while((get=br.readLine()) != null) {
                        String[] str= get.split("#");
                        Log.i("nowsq",get);
                        ItemData temp1 = new ItemData(str[2],str[3],str[4]);
                        itemDatas.add(temp1);
                        Map<String, String> temp = new LinkedHashMap<>();
                        temp.put("name",str[0]);
                        temp.put("pw",str[1]);
                        temp.put("detail",str[2]);
                        temp.put("std",str[3]);
                        temp.put("edd",str[4]);
                        data.add(temp);
                        Log.i("nowsq","add_success");
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
                        Log.i("nowsq","setadapter");

                        listView.setAdapter(myAdapter);
                        //listView.setAdapter(simpleAdapter);
                        Log.i("nowsq","adapter done");
                        //t1.setText(s2);
                    }
                });



            }
        }.start();

    }








    private float newRotationDegree = 0;


    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        float[] accValues = null;
        float[] magValues = null;
        long lastShakeTime = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    accValues = event.values;
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    magValues = event.values;
                    break;
                default:
                    break;
            }
//            if (accValues != null && magValues != null) {
//                float[] R = new float[9];
//                float[] values = new float[3];
//                SensorManager.getRotationMatrix(R, null, accValues, magValues);
//                SensorManager.getOrientation(R, values);
//                newRotationDegree = (float) Math.toDegrees(values[0]) + 180;
//            }

            int value = 17;
            float x = 0;
            if (accValues != null) {
                x = accValues[0];
                float y = accValues[1];
                float z = accValues[2];
                if (Math.abs(x) >= value || Math.abs(y) >= value || Math.abs(z-10) >= value) {
                    if (lastShakeTime == 0) {
                        lastShakeTime = System.currentTimeMillis();
                        shakeResult();
                    } else {
                        if (System.currentTimeMillis() - lastShakeTime > 5000) {
                            shakeResult();
                        }
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    private void  shakeResult() {
        numb.addi();
        myAdapter= new MyAdapter(MainActivity.this,itemDatas,numb.geti());
        listView.setAdapter(myAdapter);

        Toast.makeText(MainActivity.this,"shake",Toast.LENGTH_SHORT).show();


    }


}
