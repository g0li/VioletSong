package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import forevtechnologies.alegriauiux.LoginActivity;
import forevtechnologies.alegriauiux.model.Person;


public class Profile extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    View backView;
    de.hdodenhof.circleimageview.CircleImageView circUser;
    LinearLayout eventsLayout,feedsLayout;
    TextView name,email;
    Button gbutton;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";
    Person person;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();


    public void initUI(){
        backView=findViewById(R.id.backview);
        eventsLayout=(LinearLayout)findViewById(R.id.eventsLayout);
        feedsLayout=(LinearLayout)findViewById(R.id.feedsLayout);
        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        circUser= (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.circUser);
        gbutton=(Button)findViewById(R.id.gsigninForEmail);
        gbutton.setOnClickListener(this);
        eventsLayout.setOnClickListener(this);
        feedsLayout.setOnClickListener(this);

        if(user.isAnonymous()){
            gbutton.setVisibility(View.VISIBLE);
            name.setText("Guest user");
            email.setText(auth.getCurrentUser().getEmail());
        }
        else{

            String provider =user.getProviders().get(0).toString().toLowerCase();

            Log.w("Provider","Provider: "+provider);
            if(provider.equals("password")){
                gbutton.setVisibility(View.VISIBLE);
                Log.w("Auth","User signed in using password");
            }
            else{
                gbutton.setVisibility(View.INVISIBLE);
                Log.w("Auth","User hasn't signed in with email");
            }


            try{
                String mName=auth.getCurrentUser().getDisplayName();
                String[] namex=mName.split(" ");
                mName=namex[0]+"\n"+namex[1];
                name.setText(mName);
                email.setText(auth.getCurrentUser().getEmail());
            }
            catch(Exception e){
                String mName=auth.getCurrentUser().getEmail();
                String[] namex=mName.split("@");
                mName=namex[0];
                name.setText(mName);
                email.setText(auth.getCurrentUser().getEmail());
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
                            Toast.makeText(Profile.this,"Couldn't link with google",Toast.LENGTH_SHORT).show();
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

    private ShapeFlyer mShapeFlyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            default:
                break;
        }
    }

    public void changeFieldValues(){
        user.reload();
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());
        circUser.setImageURI(user.getPhotoUrl());
    }

}
