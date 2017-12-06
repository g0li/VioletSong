package forevtechnologies.alegriauiux;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.wangyuwei.shoppoing.ShoppingView;

/**
 * Created by goli on 3/12/17.
 */
public class TicketDialogClass extends Dialog implements
        View.OnClickListener{
    static int totalcash;
    public Activity c;
    public Dialog d;
    private Button cancel,next;
    private ShoppingView platinum,gold,student;
    private TextView tots;
    private Intent intent;
    private int numGold, numPlatinum, numStudent;
    private String artistName;

    void initUI()
    {
        totalcash=0;
        numGold=numPlatinum=numStudent=0;
        cancel=(Button)findViewById(R.id.cancel);
        next=(Button)findViewById(R.id.next);
        platinum=(ShoppingView)findViewById(R.id.platinum);
        gold=(ShoppingView)findViewById(R.id.gold);
        student=(ShoppingView)findViewById(R.id.student);
        tots=(TextView) findViewById(R.id.tots);
        intent=new Intent(getContext(),CartActivity.class);
        cancel.setOnClickListener(this);
        next.setOnClickListener(this);

        platinum.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+1000;
                numPlatinum++;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-1000;
                if(numPlatinum!=0){numPlatinum--;}
                tots.setText(String.valueOf(totalcash));
            }
        });
        gold.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+500;
                numGold++;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-500;
                if(numGold!=0){numGold--;}
                tots.setText(String.valueOf(totalcash));
            }
        });
        student.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+100;
                numStudent++;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-100;
                if(numStudent!=0){numStudent--;}
                tots.setText(String.valueOf(totalcash));
            }
        });

    }
    public TicketDialogClass(@NonNull Context context) {
        super(context);

    }
    public TicketDialogClass(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TicketDialogClass(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ticketer);
        initUI();

    }

    public void setArtistName(String name){
        this.artistName=name;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case(R.id.next):
                intent.putExtra("actName","Tickets");
                intent.putExtra("artistName",this.artistName);
                intent.putExtra("stuPass",numStudent);
                intent.putExtra("goldPass",numGold);
                intent.putExtra("plaPass",numPlatinum);
                v.getContext().startActivity(intent);
                break;
        }
        dismiss();
    }

}