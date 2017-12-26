package forevtechnologies.alegriauiux;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import es.dmoral.toasty.Toasty;
import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.TicketCartModel;
import in.shadowfax.proswipebutton.ProSwipeButton;

import static android.view.View.GONE;

public class SelectPaymentActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    LinearLayout offlineLin, offlineLin1, onlineLin, onlineLin1;
    TextView offlineTextView,offlineTextView1, onlineTextView, onlineTextView1;
    RadioButton offlineRadio, onlineRadio;
    CardView offlineCardView, onlineCardView;
    ProSwipeButton payButton;
    NiceSpinner spinner;
    Intent payz;
    FirebaseUser currentUser;
    Intent toSendForward;

    public void initUI()
    {

        passDataForward();
        payButton=(ProSwipeButton)findViewById(R.id.payButton);
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        currentUser.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    checkUserAuth(currentUser);
                }
                else{
                    Toast.makeText(SelectPaymentActivity.this,"Connection failed \n Please try again",Toast.LENGTH_LONG).show();
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
        spinner.setOnItemSelectedListener(this);

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
                        if(toSendForward.hasExtra("METHOD")){
                            startActivity(toSendForward);
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

    private void checkUserAuth(FirebaseUser user) {
        if(user.isAnonymous()){
            Toast.makeText(this, "\"Please link your account with Google in the Profile page.\"", Toast.LENGTH_SHORT).show();
            payButton.setEnabled(false);
        }
        else{
            switch (user.getProviders().get(0)){
                case "google.com":
                    payButton.setEnabled(true);
                    break;
                case "password":
                    Toast.makeText(SelectPaymentActivity.this,"Please link your account with Google in the Profile page.",Toast.LENGTH_LONG).show();
                    payButton.setEnabled(false);
                    break;
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Payment");
        setContentView(R.layout.activity_select_payment);
        toSendForward=new Intent(SelectPaymentActivity.this,CheckoutActivity.class);
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
                spinner.setVisibility(View.VISIBLE);
                activityChange("Online");
                break;
            case 1:
                onlineRadio.setChecked(false);
                offlineRadio.setChecked(true);
                offlineLin.setBackgroundColor(R.color.holo_green_light);
                onlineLin.setBackgroundColor(Color.TRANSPARENT);
                payButton.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.w("Item selected","Pos"+position);
        switch (position){
            case 0:
                Log.w("Item:","NetBanking");
//                payz=new Intent(SelectPaymentActivity.this,SelectPaymentActivity.class);
                toSendForward.putExtra("METHOD","NetBanking");
                break;
            case 1:
                Log.w("Item:","DebitCard");
//                payz=new Intent(SelectPaymentActivity.this,SelectPaymentActivity.class);
                toSendForward.putExtra("METHOD","DebitCard");
                break;
            case 2:
                Log.w("Item:","Paytm");
//                payz=new Intent(SelectPaymentActivity.this,SelectPaymentActivity.class);
                toSendForward.putExtra("METHOD","Paytm");
                break;
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void passDataForward() {
        Intent i=getIntent();
        Bundle b=i.getExtras();
        if(b.isEmpty()){
            Log.w("Empty:","TRUE");
        }
        switch (i.getStringExtra("DATA_FROM")){
            case "CART":
                if(b.getString("DATA_TYPE").equals("Reg")){
                    handleRegDataFromCart(b);
                }
                else if(b.getString("DATA_TYPE").equals("Tickets")){
                    handleTicketDataFromCart(b);
                }
                break;
            case "FORM":
                if(b.getString("DATA_TYPE").equals("Reg")){
                    handleRegDataFromForm(b);
                }
                else if(b.getString("DATA_TYPE").equals("Tickets")){
                    handleTicketDataFromForm(b);
                }
                break;
        }

    }

    private void handleTicketDataFromForm(Bundle b) {
        Bundle bundh=new Bundle();
        bundh.putParcelableArrayList("TICKET_DATA",b.getParcelableArrayList("TICKET_DATA"));
        bundh.putInt("TICKET_PRICE",b.getInt("TICKET_PRICE"));
        bundh.putString("DATA_TYPE",b.getString("DATA_TYPE"));
        toSendForward.putExtras(bundh);
    }

    private void handleRegDataFromForm(Bundle b) {
        Bundle bundh=new Bundle();
        ArrayList<CartModel> items=new ArrayList<>();
        items=b.getParcelableArrayList("REG_DATA");
        bundh.putParcelableArrayList("REG_DATA",b.getParcelableArrayList("REG_DATA"));
        bundh.putInt("REG_PRICE",b.getInt("REG_PRICE"));
        bundh.putString("DATA_TYPE",b.getString("DATA_TYPE"));
        toSendForward.putExtras(bundh);
    }

    private void handleTicketDataFromCart(Bundle b) {
        Bundle bundh=new Bundle();
        bundh.putParcelableArrayList("TICKET_DATA",b.getParcelableArrayList("TICKET_DATA"));
        bundh.putInt("TICKET_PRICE",b.getInt("TICKET_PRICE"));
        bundh.putString("DATA_TYPE",b.getString("DATA_TYPE"));
        toSendForward.putExtras(bundh);
    }

    private void handleRegDataFromCart(Bundle b) {
        Bundle bundh=new Bundle();
        ArrayList<CartModel> items=new ArrayList<>();
        items=b.getParcelableArrayList("REG_DATA");
        bundh.putParcelableArrayList("REG_DATA",items);
        bundh.putInt("REG_PRICE",b.getInt("REG_PRICE"));
        bundh.putString("DATA_TYPE",b.getString("DATA_TYPE"));
        toSendForward.putExtras(bundh);
    }

}
