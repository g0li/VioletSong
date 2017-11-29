package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import forevtechnologies.alegriauiux.adapter.DayAdapter;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle b;

    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Button buttonX = (Button)findViewById(R.id.checkout);
        b=getIntent().getExtras();
        arrayList=new ArrayList<String>();
        for(int i=0;i<b.size();i++)
        {
            arrayList.add(String.valueOf(b.get("Key "+i)));
        }


    }


    @Override
    public void onClick(View view) {

    }
}
