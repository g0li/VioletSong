package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nguyenhoanglam.imagepicker.view.ProgressWheel;

import java.nio.charset.Charset;

import forevtechnologies.alegriauiux.model.Person;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    public static Person person;
    public FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private EditText uEmail,uPassword;
    private ActionProcessButton esignin;
    private TextView guestTextView;
    private FloatingActionButton fab;
    private ProgressBar pBar;
    private CoordinatorLayout coordinatorLayout;
    private FirebaseUser currentUser;
    private Button googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            if(currentUser.isAnonymous()){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            else{
                Log.w("provider",""+currentUser.getProviders().get(0).toString());
                switch (currentUser.getProviders().get(0)){
                    case "password":
                    {
                        if(currentUser.isEmailVerified()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else{
                            startActivity(new Intent(LoginActivity.this,EmailVerificationActivity.class));
                        }
                    }
                    break;
                    case "google.com":
                    {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                    break;

                    default:
                        break;
                }
            }
        }
        else{

        }
        //listeners
        findViewById(R.id.GsignInLAyout).setOnClickListener(this);
        findViewById(R.id.Gpic).setOnClickListener(this);
        googleButton=findViewById(R.id.Gpic);
        //findViewById(R.id.GsignInButton).setOnClickListener(this);
        findViewById(R.id.ESignIn).setOnClickListener(this);
        findViewById(R.id.GuestSignIn).setOnClickListener(this);
        findViewById(R.id.fab).setOnClickListener(this);
        pBar=(ProgressBar)findViewById(R.id.progressBarLogin);
        pBar.setMax(100);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.content_login_id);
        //end listeners
        //get username and password
        uEmail=(EditText)findViewById(R.id.user_email);
        uPassword=(EditText)findViewById(R.id.user_password);
        //bind textview and floating action button
        guestTextView=(TextView)findViewById(R.id.GuestSignIn);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        //bind circular progress bars
        esignin=(ActionProcessButton)findViewById(R.id.ESignIn);

        uEmail.setEnabled(true);
        uEmail.setFocusableInTouchMode(true);
        uPassword.setEnabled(true);
        uPassword.setFocusableInTouchMode(true);
        esignin.setEnabled(true);
        //set enabled for google sign in button
        guestTextView.setEnabled(true);
        fab.setEnabled(true);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        esignin.setProgress(0);

    }
    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String mUserName=user.getDisplayName();
                            String mUserEmail=user.getEmail();
                            Uri mUserPhoto=user.getPhotoUrl();

                            person=new Person();
                            person.setName(mUserName);
                            person.setEmail(mUserEmail);
                            person.setPhoto(mUserPhoto);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Log.d(TAG,"Authentication failed.");

                            setInputs(R.id.Gpic,true);

                        }

                    }
                });
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uEmail.setEnabled(true);
        uEmail.setFocusableInTouchMode(true);
        uPassword.setEnabled(true);
        uPassword.setFocusableInTouchMode(true);
        esignin.setEnabled(true);
        //set enabled for google sign in button
        guestTextView.setEnabled(true);
        fab.setEnabled(true);
        esignin.setProgress(0);
        googleButton.setEnabled(true);
        googleButton.setFocusableInTouchMode(true);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        setInputs(R.id.Gpic,true);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    public void eSignIn(final String email, String password){

        try{mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                Log.d("LoginAcitivity", "signInWithEmail:success");
                                esignin.setProgress(100);
                                FirebaseUser user = mAuth.getCurrentUser();
                                String mUserName=user.getDisplayName();
                                String mUserEmail=user.getEmail();
                                Uri mUserPhoto=user.getPhotoUrl();

                                person=new Person();
                                person.setName(mUserName);
                                person.setEmail(mUserEmail);
                                person.setPhoto(mUserPhoto);

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,EmailVerificationActivity.class));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginAcitivity", "signInWithEmail:failure", task.getException());
                            esignin.setProgress(-1);
                            setInputs(R.id.ESignIn,true);
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            esignin.setProgress(0);
                        }

                        // ...
                    }
                }); }
        catch (Exception e){
            Log.w("Email sign in","Failed to sign in",e);
            esignin.setProgress(-1);
            setInputs(R.id.ESignIn,true);
            Toast.makeText(LoginActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
            esignin.setProgress(0);
        }
    }

   /* public void eSignUp(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    */

    public void guestSignIn(){
        coordinatorLayout.setAlpha(0.5f);
        pBar.setVisibility(View.VISIBLE);
        pBar.setProgress(50);
        try{mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Anonymous sign in", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String mUserName=user.getDisplayName();
                            String mUserEmail=user.getEmail();
                            Uri mUserPhoto=user.getPhotoUrl();

                            person=new Person();
                            person.setName(mUserName);
                            person.setEmail(mUserEmail);
                            person.setPhoto(mUserPhoto);
                            pBar.setProgress(0);
                            pBar.setVisibility(View.GONE);
                            coordinatorLayout.setAlpha(1);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Anonymous sign in", "signInAnonymously:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            pBar.setProgress(0);
                            pBar.setVisibility(View.GONE);
                            setInputs(R.id.GuestSignIn,true);
                            coordinatorLayout.setAlpha(1);
                            //updateUI(null);
                        }

                        // ...
                    }
                });}
        catch (Exception e){
            Log.w("Anonymous sign in","Failed to sign in",e);
            setInputs(R.id.GuestSignIn,true);
            Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
        }

    }

    public void setInputs(int i,Boolean val){
        uEmail.setEnabled(val);
        uEmail.setFocusableInTouchMode(val);
        uPassword.setEnabled(val);
        uPassword.setFocusableInTouchMode(val);
        esignin.setEnabled(val);
        //set enabled for google sign in button
        guestTextView.setEnabled(val);
        fab.setEnabled(val);
        googleButton.setEnabled(val);

        findViewById(i).setEnabled(true);
    }


    //function for encryption
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes(Charset.forName("UTF-8")));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        }
        catch (java.security.NoSuchAlgorithmException e) {

        }
        return null;
    }



    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.GuestSignIn) {
            //put on screen progress bar
            setInputs(i,false);
            guestSignIn();
        }
        else if (i == R.id.fab) {
            //eSignUp(uEmail.getText().toString(),uPassword.getText().toString());
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }
        else if (i == R.id.GsignInLAyout) {
            signIn();
        }
        else if (i == R.id.Gpic) {
            setInputs(i,false);
            signIn();
        }
        else if (i == R.id.ESignIn) {
            if (uEmail.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Email cannot be left empty", Toast.LENGTH_SHORT).show();
            } else if (uPassword.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Password cannot be left empty", Toast.LENGTH_SHORT).show();
            } else if (uPassword.getText().toString().length() < 8) {
                Toast.makeText(LoginActivity.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
            }
            //place function here for email auth
            if (!(uEmail.getText().toString().isEmpty()) && !(uPassword.getText().toString().isEmpty()) && !(uPassword.getText().toString().length() < 8)) {
                setInputs(i,false);
                esignin.setProgress(50);
                eSignIn(uEmail.getText().toString(), MD5(uPassword.getText().toString()));
            }

        }

    }

    public FirebaseUser getFirebaseUser(){
        //FirebaseAuth auth=FirebaseAuth.getInstance();
        //FirebaseUser user=auth.getCurrentUser();
        return mAuth.getCurrentUser();
    }

    public FirebaseAuth getFirebaseAuth(){
        //FirebaseAuth auth=FirebaseAuth.getInstance();
        return mAuth;
    }



}