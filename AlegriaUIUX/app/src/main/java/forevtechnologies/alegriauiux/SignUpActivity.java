package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    private ActionProcessButton cpb;

    private EditText uEmail,uPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uEmail=(EditText)findViewById(R.id.user_email);
        uPassword=(EditText)findViewById(R.id.user_password);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cpb.setProgress(0);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }

    }

    public void eSignUp(String email,String password){
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
                            cpb.setProgress(100);
                            //insert delay here
                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Failed to create account",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            cpb.setProgress(-1);
                            cpb.setProgress(0);
                        }

                        // ...
                    }
                }); }
       catch(Exception e){
           Log.w(TAG,"Unable to create user",e);
           cpb.setProgress(-1);
           //insert delay here
           cpb.setProgress(0);
           Toast.makeText(SignUpActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
       }
    }
    @Override
    public void onClick(View v) {
        int i=v.getId();
        cpb.setProgress(0);


            {

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
                    cpb.setProgress(50);
                    eSignUp(uEmail.getText().toString(), LoginActivity.MD5(uPassword.getText().toString()));
                }
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

}
