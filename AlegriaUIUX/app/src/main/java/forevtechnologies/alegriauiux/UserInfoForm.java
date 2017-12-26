package forevtechnologies.alegriauiux;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.TicketCartModel;

/**
 * Created by ABHIJAY on 21-12-2017.
 */

public class UserInfoForm extends Activity implements View.OnClickListener {
    SharedPreferences sp;
    Button proceed;
    TextInputEditText user_name,user_phone,user_email;
    SharedPreferences.Editor editor;
    Intent toSendForward;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_form);
        sp = getSharedPreferences("USER_DATA",MODE_PRIVATE);
        editor = sp.edit();
        passDataForward();
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_phone = findViewById(R.id.user_phone);
        proceed = findViewById(R.id.info_proceed);
        proceed.setOnClickListener(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(!user.isAnonymous())
        {
               switch(user.getProviders().get(0)){

                   case "google.com":   user_name.setText(user.getDisplayName());
                                        user_name.setEnabled(false);
                                        break;
                   case "password":

               }
        }


        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.college_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner.attachDataSource(dataset);

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.info_proceed :
               if(user_email.getText().toString().equals("") && user_phone.getText().toString().equals("")){
                   Toast.makeText(getBaseContext(),"Please enter the data",Toast.LENGTH_LONG).show();
                }
                else
               {
                  editor.putString("NAME",user_name.getText().toString());
                  Log.w("SPNAME","Name entered");
                  editor.putString("EMAIL",user_email.getText().toString());
                  Log.w("SPEMAIL","Email entered");
                  editor.putString("PHONE",user_phone.getText().toString());
                  Log.w("SPPHONE","Phone entered");
                  editor.commit();
                  startActivity(toSendForward);
                  finish();
               }

       }
    }
    private void passDataForward() {
        Intent i=getIntent();
        Bundle b=i.getExtras();
        if(b.isEmpty()){
            Log.w("Empty:","TRUE");
        }

        if(b.getString("DATA_TYPE").equals("Reg")){
            ArrayList<CartModel> items=new ArrayList<>();
            items=b.getParcelableArrayList("REG_DATA");
            int totalPrice=b.getInt("REG_PRICE");
            toSendForward=new Intent(this,SelectPaymentActivity.class);
            Bundle newBundle= new Bundle();
            newBundle.putParcelableArrayList("REG_DATA",items);
            newBundle.putInt("REG_PRICE",totalPrice);
            newBundle.putString("DATA_TYPE","Reg");
            toSendForward.putExtra("DATA_FROM","FORM");
            toSendForward.putExtras(newBundle);
        }

        else if(b.getString("DATA_TYPE").equals("Tickets")){
            ArrayList<TicketCartModel> tItems=new ArrayList<>();
            tItems=b.getParcelableArrayList("TICKET_DATA");
            int totalPrice=b.getInt("TICKET_PRICE");
            toSendForward=new Intent(this,SelectPaymentActivity.class);
            Bundle newBundle= new Bundle();
            newBundle.putParcelableArrayList("TICKET_DATA",tItems);
            newBundle.putInt("TICKET_PRICE",totalPrice);
            newBundle.putString("DATA_TYPE","Tickets");
            toSendForward.putExtra("DATA_FROM","FORM");
            toSendForward.putExtras(newBundle);


        }
    }
}
