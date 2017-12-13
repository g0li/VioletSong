package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cipherthinkers.shapeflyer.ShapeFlyer;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import forevtechnologies.alegriauiux.LoginActivity;
import forevtechnologies.alegriauiux.model.Person;


public class Profile extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    View backView;
    de.hdodenhof.circleimageview.CircleImageView circUser;
    LinearLayout eventsLayout,feedsLayout;
    TextView name,email;
    TextView events,eventsTitle;
    Button gbutton;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";
    Person person;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    TextView signInInfo;
    int[] icons={R.drawable.iccon1,R.drawable.iccon2,R.drawable.iccon3};// icon pool
    private ShapeFlyer mShapeFlyer;

    public void initUI(){
        backView=findViewById(R.id.backview);
        eventsLayout=(LinearLayout)findViewById(R.id.eventsLayout);
        feedsLayout=(LinearLayout)findViewById(R.id.feedsLayout);
        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        events=findViewById(R.id.events);
        eventsTitle=findViewById(R.id.eventsTitle);
        circUser= (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.circUser);
        circUser.setImageDrawable(getDrawable(getIcon()));//generating random icon from pool
        gbutton=(Button)findViewById(R.id.gsigninForEmail);
        signInInfo=findViewById(R.id.signInInfo);
        gbutton.setOnClickListener(this);
        eventsLayout.setOnClickListener(this);
        events.setOnClickListener(this);
        events.setText("0");
        eventsTitle.setOnClickListener(this);
        feedsLayout.setOnClickListener(this);
        mShapeFlyer= findViewById(R.id.shape_flyer);
        mShapeFlyer.setOnClickListener(this);
        if(user.isAnonymous()){
            gbutton.setVisibility(View.VISIBLE);
            name.setText("Guest user");
            email.setText(auth.getCurrentUser().getEmail());
        }
        else{
            switch (user.getProviders().get(0)){
                case "google.com":{
                    Log.w("Provider:",""+user.getProviders().get(0));
                    signInInfo.setVisibility(View.GONE);
                    gbutton.setVisibility(View.GONE);
                    String mName=auth.getCurrentUser().getDisplayName();
                    String[] namex=mName.split(" ");
                    mName=namex[0]+"\n"+namex[1];
                    name.setText(mName);
                    email.setText(auth.getCurrentUser().getEmail());
                }
                    break;
                case "password":{
                    Log.w("Provider:",""+user.getProviders().get(0));
                    gbutton.setVisibility(View.VISIBLE);
                    String mName=auth.getCurrentUser().getEmail();
                    String[] namex=mName.split("@");
                    mName=namex[0];
                    name.setText(mName);
                    email.setText(auth.getCurrentUser().getEmail());
                }
                    break;
                default:
                    break;

            }
        }
        
        //google sign in

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    //function to generate random icon index
    public int getIcon(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        return icons[randomNum];
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                //firebaseAuthWithGoogle(account);
                AuthCredential credential=GoogleAuthProvider.getCredential(account.getIdToken(),null);
                auth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Profile.this,"Linked with google",Toast.LENGTH_SHORT).show();
                            Log.w("Link","Linked with google successfully");
                            gbutton.setVisibility(View.GONE);
                            changeFieldValues();
                        }
                        else{
                            Toast.makeText(Profile.this,"Couldn't link with google \n Or using invalid account",Toast.LENGTH_SHORT).show();
                            Log.w("Link","Couldn't link with google");

                        }

                    }
                });
            } else {
            }
        }
    }

   /* private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //ask for user password

                            AuthCredential credential= EmailAuthProvider.getCredential(user.getEmail(),user.);
                            user.linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Log.w("Linking","Link with Google successful");
                                        user=task.getResult().getUser();
                                    }
                                    else{
                                        Log.w("Linking","Failed to link with google");
                                        Toast.makeText(Profile.this,"Failed to link account",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Log.d(TAG,"Authentication failed.");

                        }

                    }
                });
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        initUI();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
        switch (v.getId()){
            case R.id.gsigninForEmail:
                signIn();
                break;
            case R.id.events:
            case R.id.eventsTitle:
            case R.id.eventsLayout:
                startActivity(new Intent(Profile.this,MyEvents.class));
                break;
            case R.id.shape_flyer:
                finish();
                break;
            default:
                break;
        }
    }

    public void changeFieldValues(){
        user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.w("Reload:","Successful");
                    name.setText(user.getDisplayName());
                    email.setText(user.getEmail());
//                    circUser.setImageURI(user.getPhotoUrl());
                }
                else{
                    Log.w("Reload","Unsuccessful");
                }
            }
        });
    }

}
