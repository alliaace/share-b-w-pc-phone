package com.example.sheikhhamza.com_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button b;
    String str,add;
    Button img;
    TextView st;
    ImageView ss;
    Socket s;
    comunicator c;
    EditText msg;
    ImageButton send;
    ListView listv;
    msg_adapter listAdapter;
    boolean com_bound = false;
    AsyncTask a;
    boolean stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
        st = (TextView) findViewById(R.id.status);
        c = new comunicator();
        msg = (EditText) findViewById(R.id.msg);
        send = (ImageButton) findViewById(R.id.send);
        listv = (ListView) findViewById(R.id.textStream);
       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st.setText("connecting... ");
                    try{
                        c.connection("192.168.10.15",10);

                    }catch(Exception e){
                        st.setText(e.toString());
                    }

            }
        });*/


    }

    @Override
    public void onStart() {
        super.onStart();
        Intent i = new Intent(this, comunicator.class);
        bindService(i, scon, Context.BIND_AUTO_CREATE);
        ArrayList<String> str = new ArrayList<String>();
        listAdapter = new msg_adapter(this, str);
        listv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listv.setStackFromBottom(true);
        listv.setAdapter(listAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("address");
        final EditText in= new EditText(this);
        in.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        builder.setView(in);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                add=in.getText().toString();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                add="192.168.10.15";
            }
        });
        builder.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindService(scon);
        com_bound = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        stop = true;
    }

    void list(String msg) {
        Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
        listAdapter.add(msg);
        listAdapter.notifyDataSetChanged();
    }

    private ServiceConnection scon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            comunicator.binder b = (comunicator.binder) service;
            c = b.getService();
            com_bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            com_bound = false;
        }
    };

    void sendMethod(View v) {
        new AsyncTask<Void,Void,Void>(){
        @Override
        protected Void doInBackground(Void...a) {
    try {
        s=c.connection(add, 10);
        Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
        c.send(msg.getText().toString().getBytes(), msg.getText().length(), s);
        listAdapter.add(msg.getText().toString());
        while((str=new String(c.recieve(s)))!=null){
            listAdapter.add( str );
        }

    } catch (Exception e) {
        st.setText(e.toString());
    }
    return null;
}
            @Override
            protected void onPostExecute(Void aVoid) {
            try{
                s.close();
                s=null;
                st.setText("finished");
            } catch (Exception e) {
                st.setText(e.toString());
            }
            }
        }.execute();
    }
}
