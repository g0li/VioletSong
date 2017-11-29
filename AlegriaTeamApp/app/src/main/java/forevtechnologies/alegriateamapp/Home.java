package forevtechnologies.alegriateamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button postButton,notificationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        postButton=(Button)findViewById(R.id.postButton);
        notificationButton=(Button)findViewById(R.id.notificationButton);
        postButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.postButton:
                startActivity(new Intent(Home.this,PostFeeds.class));
                break;
            case R.id.notificationButton:
                startActivity(new Intent(Home.this,NotificationActivity.class));
                break;
        }
    }
}
