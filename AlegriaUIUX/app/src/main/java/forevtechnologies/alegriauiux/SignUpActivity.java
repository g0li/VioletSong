package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import forevtechnologies.alegriauiux.model.Person;
import forevtechnologies.alegriauiux.LoginActivity;

/**
 * Created by ziyad on 21/11/17.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "GoogleActivity";
    public static Person person;
    private FirebaseAuth mAuth;
    private ProgressBar pBar;
    private EditText uEmail,uPassword;
    private Button sButton;
    private LinearLayout linearLayout;
    private ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sButton=findViewById(R.id.signUpButton);
        sButton.setOnClickListener(this);
        imageButton=findViewById(R.id.backButton);
        imageButton.setOnClickListener(this);
        pBar=(ProgressBar)findViewById(R.id.progressBarSignUp);
        pBar.setMax(100);
        linearLayout=findViewById(R.id.signUpActivity);
        uEmail=(EditText)findViewById(R.id.user_email);
        uPassword=(EditText)findViewById(R.id.user_password);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }

    }

    public void eSignUp(String email,String password){
        linearLayout.setAlpha(0.5f);
        setInputs(false);
       pBar.setVisibility(View.VISIBLE);
       pBar.setProgress(50);
        try
       {mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String mUserName=user.getDisplayName();
                            String mUserEmail=user.getEmail();
                            Uri mUserPhoto=user.getPhotoUrl();

                            person=new Person();
                            person.setName(mUserName);
                            person.setEmail(mUserEmail);
                            person.setPhoto(mUserPhoto);
                            //insert delay here
                            pBar.setProgress(0);
                            pBar.setVisibility(View.GONE);
                            linearLayout.setAlpha(1);
                            startActivity(new Intent(SignUpActivity.this,EmailVerificationActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            setInputs(true);
                            pBar.setProgress(0);
                            pBar.setVisibility(View.GONE);
                            linearLayout.setAlpha(1);
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Failed to create account",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                }); }
       catch(Exception e){
           Log.w(TAG,"Unable to create user",e);
           Toast.makeText(SignUpActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
       }
    }
    @Override
    public void onClick(View v) {
        int i=v.getId();


        if(i==R.id.signUpButton){

                if(uEmail.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Email cannot be left empty",Toast.LENGTH_SHORT).show();
                }
                else if(uPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Password cannot be left empty",Toast.LENGTH_SHORT).show();
                }
                else if(uPassword.getText().toString().length()<8){
                    Toast.makeText(SignUpActivity.this,"Password must have at least 8 characters",Toast.LENGTH_SHORT).show();
                }
                if(!(uEmail.getText().toString().isEmpty()) && !(uPassword.getText().toString().isEmpty()) && !(uPassword.getText().toString().length()<8)) {
                    eSignUp(uEmail.getText().toString(), LoginActivity.MD5(uPassword.getText().toString()));
                }
            }
            else if(i==R.id.backButton){
                //startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            finish();
        }

    }
        public static FirebaseUser getFirebaseUser(){
            FirebaseAuth auth=FirebaseAuth.getInstance();
            FirebaseUser user=auth.getCurrentUser();
            return user;
        }

        public static FirebaseAuth getFirebaseAuth(){
            FirebaseAuth auth=FirebaseAuth.getInstance();
            return auth;
        }

        public void setInputs(boolean val){
            sButton.setEnabled(val);
            uPassword.setEnabled(val);
            uPassword.setFocusableInTouchMode(val);
            uEmail.setEnabled(val);
            uEmail.setFocusableInTouchMode(val);
        }

    @Override
    public void finish() {
        super.finish();
    }
}
