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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.CollegeCodes;
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
    NiceSpinner niceSpinner,categorySpinner;
    List<String>dataset,category,eng,acs,mng,jr,arch;
    List<String> currentDataset=new ArrayList<>();
    Map<String,String> lookupTable=new HashMap<>();
    int flag;
    boolean containsCollege=false;
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


        niceSpinner = (NiceSpinner) findViewById(R.id.college_spinner);
        niceSpinner.setTextColor(getResources().getColor(R.color.white));
        createLists();

        categorySpinner = findViewById(R.id.college_category_spinner);
        categorySpinner.setTextColor(getResources().getColor(R.color.white));
        category=new ArrayList<String>();
        category.add("Engineering.");
        category.add("Arts,Science,Commerce.");
        category.add("Architecture.");
        category.add("Management.");
        category.add("Junior Colleges.");

        categorySpinner.attachDataSource(category);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    Log.w("Data:","->"+category.get(i));
                    switch (i){
                        case 0:
                            niceSpinner.attachDataSource(eng);
                            flag=i;
                            niceSpinner.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            niceSpinner.attachDataSource(acs);
                            flag=i;
                            niceSpinner.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            niceSpinner.attachDataSource(arch);
                            flag=i;
                            niceSpinner.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            niceSpinner.attachDataSource(mng);
                            flag=i;
                            niceSpinner.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            niceSpinner.attachDataSource(jr);
                            flag=i;
                            niceSpinner.setVisibility(View.VISIBLE);
                            break;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(UserInfoForm.this,"Nothing Selected",Toast.LENGTH_LONG).show();
            }
        });
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    switch (flag){
                        case 0:
                            currentDataset=eng;
                            break;
                        case 1:
                            currentDataset=acs;
                            break;
                        case 2:
                            currentDataset=arch;
                            break;
                        case 3:
                            currentDataset=mng;
                            break;
                        case 4:
                            currentDataset=jr;
                            break;
                    }
                    sp.edit().putString("COLLEGE_CODE",lookupTable.get(currentDataset.get(i))).commit();
                    containsCollege=true;
                    Log.w("Code added:",lookupTable.get(currentDataset.get(i)));

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void createLists() {
        eng=new ArrayList<>();
        acs=new ArrayList<>();
        mng=new ArrayList<>();
        arch=new ArrayList<>();
        jr=new ArrayList<>();
        for(CollegeCodes c : CollegeCodes.values()){
            lookupTable.put(c.getName(),c.toString());
        }

        for(int i=0;i<=58;i++){

            eng.add(CollegeCodes.values()[i].getName());
        }
        for(int i=60;i<=208;i++){

            acs.add(CollegeCodes.values()[i].getName());
        }
        for(int i=210;i<=258;i++){

            mng.add(CollegeCodes.values()[i].getName());
        }
        for(int i=260;i<=280;i++){

            arch.add(CollegeCodes.values()[i].getName());
        }
        for(int i=282;i<=328;i++){

            jr.add(CollegeCodes.values()[i].getName());
        }

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.info_proceed :
               if(user_email.getText().toString().equals("") || user_phone.getText().toString().equals("") || !containsCollege){
                   Toast.makeText(getBaseContext(),"Please enter the required data.",Toast.LENGTH_LONG).show();

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
