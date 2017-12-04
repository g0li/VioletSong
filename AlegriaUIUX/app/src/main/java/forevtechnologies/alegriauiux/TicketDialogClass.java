package forevtechnologies.alegriauiux;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

    void initUI()
    {
        totalcash=0;
        cancel=(Button)findViewById(R.id.cancel);
        next=(Button)findViewById(R.id.next);
        platinum=(ShoppingView)findViewById(R.id.platinum);
        gold=(ShoppingView)findViewById(R.id.gold);
        student=(ShoppingView)findViewById(R.id.student);
        tots=(TextView) findViewById(R.id.tots);

        cancel.setOnClickListener(this);
        next.setOnClickListener(this);

        platinum.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+1000;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-1000;
                tots.setText(String.valueOf(totalcash));
            }
        });
        gold.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+500;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-500;
                tots.setText(String.valueOf(totalcash));
            }
        });
        student.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                totalcash=totalcash+100;
                tots.setText(String.valueOf(totalcash));
            }

            @Override
            public void onMinusClick(int num) {
                totalcash=totalcash-100;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
        dismiss();
    }

}