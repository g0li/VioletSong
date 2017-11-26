package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.dd.CircularProgressButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import forevtechnologies.alegriauiux.SignUpActivity;

/**
 * Created by ziyad on 22/11/17.
 */

public class EmailVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    ActionProcessButton cpb;
    TextView displayInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);
        mAuth = SignUpActivity.getFirebaseAuth();
        cpb = (ActionProcessButton) findViewById(R.id.verifyAccount);
        cpb.setOnClickListener(this);
        displayInfo = (TextView) findViewById(R.id.displayEmail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cpb.setProgress(0);
        cpb.setEnabled(false);
        FirebaseUser currentUser = SignUpActivity.getFirebaseUser();
        if (currentUser!=null && currentUser.isEmailVerified()){
            Toast.makeText(EmailVerificationActivity.this,"Email already verified",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EmailVerificationActivity.this,MainActivity.class));
        }
        else{
            sendEmailVerification(currentUser);
            displayInfo.setText("A verification link has been sent to "+currentUser.getEmail().toString()+"\n Tap on link and press the button below to complete verification");
            cpb.setEnabled(true);
        }


    }

    public void checkEmailVerified(){
        FirebaseUser usr=SignUpActivity.getFirebaseUser();
        usr.reload();
        if(usr.isEmailVerified()){
            cpb.setProgress(100);
            Log.w("Verfication","Email verified");
            startActivity(new Intent(EmailVerificationActivity.this,MainActivity.class));
        }
        else{
            cpb.setProgress(-1);
            Log.w("Verification","Email not verified");
            Toast.makeText(EmailVerificationActivity.this,"Email not verified",Toast.LENGTH_SHORT).show();
            cpb.setProgress(0);
        }

    }

    public void sendEmailVerification(FirebaseUser usr){
        usr.reload();
        usr.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.w("Link","Link sent");
                    Toast.makeText(EmailVerificationActivity.this,"Link sent",Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.w("Link","Link not sent");
                    Toast.makeText(EmailVerificationActivity.this,"Couldn't send link",Toast.LENGTH_SHORT).show();                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.verifyAccount:
                cpb.setProgress(50);
                checkEmailVerified();
                break;
            default:
                break;
        }
    }
}
