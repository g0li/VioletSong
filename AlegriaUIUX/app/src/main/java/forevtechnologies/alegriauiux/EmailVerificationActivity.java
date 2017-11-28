package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import forevtechnologies.alegriauiux.SignUpActivity;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import android.graphics.Color;


/**
 * Created by ziyad on 22/11/17.
 */

public class EmailVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    public FirebaseAuth mAuth;
    TextView displayInfo;
    Button confirmButton,retryButton;
    ImageButton imgButton;
    KonfettiView konfettiView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);
        mAuth = FirebaseAuth.getInstance();
        displayInfo =findViewById(R.id.displayEmail);
        confirmButton=findViewById(R.id.verifyEmail);
        confirmButton.setOnClickListener(this);
        retryButton=findViewById(R.id.retryButton);
        retryButton.setOnClickListener(this);
        retryButton.setVisibility(View.GONE);
        retryButton.setEnabled(false);
        confirmButton.setEnabled(false);
        imgButton=findViewById(R.id.backButton);
        imgButton.setOnClickListener(this);
        konfettiView=findViewById(R.id.konfettiView);
        konfettiView.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sendEmailLink();
        displayInfo.setText("A link has been sent to: "+mAuth.getCurrentUser().getEmail().toString());

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EmailVerificationActivity.this,LoginActivity.class));

    }

    @Override
    public void onClick(View view) {
        final FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();

        switch (view.getId()){
            case R.id.verifyEmail:
            {   confirmButton.setEnabled(false);
                user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.w("Reload","Reload successful");
                            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                        Log.w("Email","Verified");
                                        Toast.makeText(EmailVerificationActivity.this,"Verified!",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(EmailVerificationActivity.this,MainActivity.class));
                                    }
                                    else{
                                        Log.w("Email","Not verified");
                                        retryButton.setVisibility(View.VISIBLE);
                                        retryButton.setEnabled(true);
                                        confirmButton.setEnabled(true);
                                        Toast.makeText(EmailVerificationActivity.this,"Try again",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Log.w("Reload","Unsuccessful");
                            confirmButton.setEnabled(true);
                        }
                    }
                });

            }
            break;

            case R.id.retryButton:
            {
                sendEmailLink();
            }
            break;
            case R.id.backButton:
            {
             backButtonPressed();
            }
            break;

            case R.id.konfettiView:
            {
                konfettiView.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.RECT, Shape.CIRCLE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                        .stream(300, 5000L);
            }
            break;

            default:
        }
    }

    public void sendEmailLink(){
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.w("Link","Link sent");
                    //Toast.makeText(EmailVerificationActivity.this,"Link sent",Toast.LENGTH_SHORT).show();
                    confirmButton.setEnabled(true);
                }
                else{
                    Log.w("Link","Couldn't send link");
                    //Toast.makeText(EmailVerificationActivity.this,"Couldn't send link",Toast.LENGTH_SHORT).show();
                    retryButton.setVisibility(View.VISIBLE);
                    retryButton.setEnabled(true);

                }
            }
        });
    }

    public void backButtonPressed(){
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
