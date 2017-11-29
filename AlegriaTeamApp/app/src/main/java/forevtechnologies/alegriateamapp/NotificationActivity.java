package forevtechnologies.alegriateamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationActivity extends AppCompatActivity {
    private FirebaseMessaging fm;
    private AtomicInteger msgId;
    EditText headEditText,bodyEditText;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        msgId = new AtomicInteger();
        fm = FirebaseMessaging.getInstance();
        headEditText=(EditText)findViewById(R.id.headerNotify);
        bodyEditText=(EditText)findViewById(R.id.bodyNotify);
        sendButton=(Button)findViewById(R.id.sendNotification);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String head=headEditText.getText().toString(),body=bodyEditText.getText().toString();
                sendNotification(head,body);
            }
        });
    }
    public void sendNotification(String header,String body)
    {
        fm.send(new RemoteMessage.Builder("778306226656@gcm.googleapis.com")
                .setMessageId(Integer.toString(msgId.incrementAndGet()))
                .addData("my_message", header)
                .addData("my_action",body)
                .addData("my_body",body)
                .build());
    }
}
