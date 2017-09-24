package forevtechnologies.alegriauiux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cipherthinkers.shapeflyer.ShapeFlyer;
import com.google.firebase.auth.FirebaseAuth;


public class Profile extends AppCompatActivity implements View.OnClickListener{
    View backView;
    de.hdodenhof.circleimageview.CircleImageView circUser;
    LinearLayout eventsLayout,feedsLayout;
    TextView name,email;
    public void initUI(){
        backView=findViewById(R.id.backview);
        eventsLayout=(LinearLayout)findViewById(R.id.eventsLayout);
        feedsLayout=(LinearLayout)findViewById(R.id.feedsLayout);
        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        circUser= (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.circUser);
        eventsLayout.setOnClickListener(this);
        feedsLayout.setOnClickListener(this);
        FirebaseAuth auth= FirebaseAuth.getInstance();

        String mName=auth.getCurrentUser().getDisplayName();
        String[] namex=mName.split(" ");
        mName=namex[0]+"\n"+namex[1];

        name.setText(mName);
        email.setText(auth.getCurrentUser().getEmail()) ;

    }

    private ShapeFlyer mShapeFlyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initUI();

    }

    @Override
    protected void onDestroy() {
        if(mShapeFlyer != null){
            mShapeFlyer.release();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

}
