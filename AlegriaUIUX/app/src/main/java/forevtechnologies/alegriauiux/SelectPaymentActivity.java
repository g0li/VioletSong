package forevtechnologies.alegriauiux;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class SelectPaymentActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout offlineLin, offlineLin1, onlineLin, onlineLin1;
    TextView offlineTextView,offlineTextView1, onlineTextView, onlineTextView1;
    RadioButton offlineRadio, onlineRadio;
    CardView offlineCardView, onlineCardView;
    ProSwipeButton payButton;
    protected void initUI()
    {
        payButton=(ProSwipeButton)findViewById(R.id.payButton);

        offlineLin=(LinearLayout)findViewById(R.id.offlineLin);
        offlineLin1=(LinearLayout)findViewById(R.id.offlineLin1);
        onlineLin=(LinearLayout)findViewById(R.id.onlineLin);
        onlineLin1=(LinearLayout)findViewById(R.id.onlineLin1);

        offlineTextView=(TextView) findViewById(R.id.offlineTextView);
        offlineTextView1=(TextView) findViewById(R.id.offlineTextView1);
        onlineTextView=(TextView) findViewById(R.id.onlineTextView);
        onlineTextView1=(TextView) findViewById(R.id.onlineTextView1);

        offlineRadio=(RadioButton) findViewById(R.id.offlineRadioButton);
        onlineRadio=(RadioButton) findViewById(R.id.onlineRadio);

        offlineCardView=(CardView) findViewById(R.id.offlineCardView);
        onlineCardView=(CardView) findViewById(R.id.onlineCardView);
        
        offlineLin.setOnClickListener(this);
        offlineLin1.setOnClickListener(this);
        onlineLin.setOnClickListener(this);
        onlineLin1.setOnClickListener(this);
        
        offlineTextView.setOnClickListener(this);
        offlineTextView1.setOnClickListener(this);
        
        onlineTextView.setOnClickListener(this);
        onlineTextView1.setOnClickListener(this);
        
        offlineRadio.setOnClickListener(this);
        onlineRadio.setOnClickListener(this);
        
        offlineCardView.setOnClickListener(this);
        onlineCardView.setOnClickListener(this);

        payButton.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        payButton.showResultIcon(true); // false if task failed
                    }
                }, 2000);
                payButton.updateBackground();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        //startActivity(new Intent(SelectPaymentActivity.this,SelectPaymentActivity.class));
                    }
                }, 5000);

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);
        initUI();
        
    }

    @SuppressLint("ResourceAsColor")
    protected void buttonSceneDecider(int a)
    {
        switch (a) {
            case 0:
                onlineRadio.setChecked(true);
                offlineRadio.setChecked(false);
                onlineLin.setBackgroundColor(R.color.holo_green_light);
                offlineLin.setBackgroundColor(Color.TRANSPARENT);
                payButton.setVisibility(View.VISIBLE);
                activityChange("Online");
                break;
            case 1:
                onlineRadio.setChecked(false);
                offlineRadio.setChecked(true);
                offlineLin.setBackgroundColor(R.color.holo_green_light);
                onlineLin.setBackgroundColor(Color.TRANSPARENT);
                payButton.setVisibility(View.VISIBLE);
                activityChange("Offline");
                break;

        }
    }
    protected void activityChange(String paymentMethod)
    {
        Log.d("activityChange",paymentMethod);
        //startActivity(new Intent(SelectPaymentActivity.this,PaymentActivity.class).putExtra("Crit",paymentMethod));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.offlineLin:
            case R.id.offlineLin1:
            case R.id.offlineCardView:
            case R.id.offlineTextView:
            case R.id.offlineTextView1:
            case R.id.offlineRadioButton:
                buttonSceneDecider(1);
                break;
            case R.id.onlineLin:
            case R.id.onlineLin1:
            case R.id.onlineCardView:
            case R.id.onlineTextView:
            case R.id.onlineTextView1:
            case R.id.onlineRadio:
                buttonSceneDecider(0);
                break;
                
        }
    }
}
