package com.android.example.sendersms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);

        sendBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                sendSMS();
            }
        });
    }

    protected void sendSMS(){
        Log.i("Send msg","");
        String phonNum = txtphoneNo.getText().toString();
        String txtSMS = txtMessage.getText().toString();
        if(phonNum.isEmpty() )
            Toast.makeText(getApplicationContext(),"I need a number =)", Toast.LENGTH_LONG).show();
        else if (txtSMS.trim().isEmpty())
                Toast.makeText(getApplicationContext(),"Nothing to say ? I need a message =)", Toast.LENGTH_LONG).show();
            else{
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phonNum,null,txtSMS,null,null);
                Toast.makeText(getApplicationContext(),"SMS Sent", Toast.LENGTH_LONG).show();
                txtMessage.setText("");
                Log.i("Finished sending SMS...","");
            }catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(MainActivity.this,"SMS failed, please try again later", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
