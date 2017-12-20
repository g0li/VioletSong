package forevtechnologies.alegriauiux;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.angmarch.views.NiceSpinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class SelectPaymentActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout offlineLin, offlineLin1, onlineLin, onlineLin1;
    TextView offlineTextView,offlineTextView1, onlineTextView, onlineTextView1;
    RadioButton offlineRadio, onlineRadio;
    CardView offlineCardView, onlineCardView;
    ProSwipeButton payButton;
    NiceSpinner spinner;
    protected void initUI()
    {
        payButton=(ProSwipeButton)findViewById(R.id.payButton);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        final FirebaseUser user=mAuth.getCurrentUser();
        user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.w("Reload","successful");

                    if(user.isAnonymous()){
                        payButton.setEnabled(false);
                        payButton.setFocusableInTouchMode(false);
                        //add alert box to redirect to profile
                    }
                    else{
                        switch (user.getProviders().get(0)){
                            case "google.com":
                                break;
                            case "password":
                                if(!user.isEmailVerified()){
                                    payButton.setEnabled(false);
                                    payButton.setFocusableInTouchMode(false);
                                }
                                break;
                        }
                    }

                }
                else{
                    Log.w("Reload","unsuccessful");
                    Toast.makeText(SelectPaymentActivity.this,"Try checking your internet connection.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

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

        spinner=(NiceSpinner)findViewById(R.id.paymentSpinner);
        List<String> payMethod= new LinkedList<>(Arrays.asList("Net Banking","Debit Card", "Paytm"));
        spinner.attachDataSource(payMethod);
        final Intent payz =new Intent(SelectPaymentActivity.this,SelectPaymentActivity.class);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                payz.putExtra("method",adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                        if(payz.hasExtra("method")){
                            startActivity(payz);
                        }
                        else
                        {
                            Toast.makeText(SelectPaymentActivity.this, "Select a method", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 5000);

            }
        });
        
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

    @Override
    public void finish() {
        super.finish();
    }
}
