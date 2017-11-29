package forevtechnologies.alegriateamapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import forevtechnologies.alegriateamapp.adapter.PostFirebaseAdapter;
import forevtechnologies.alegriateamapp.adapter.ClickListenerChatFirebase;
import forevtechnologies.alegriateamapp.model.PostModel;
import forevtechnologies.alegriateamapp.model.UserModel;
import forevtechnologies.alegriateamapp.utl.Util;
import forevtechnologies.alegriateamapp.view.LoginActivity;

public class PostFeeds extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, ClickListenerChatFirebase {
    static final String TAG = PostFeeds.class.getSimpleName();
    static final String CHAT_REFERENCE = "whatshot";

    //Firebase and GoogleApiClient
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mFirebaseDatabaseReference;

    //CLass Model
    private UserModel userModel;

    //Views UI
    private RecyclerView rvListMessage;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageView btSendMessage;
    private EditText edMessage;
    private View contentRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main);
        bindViews();
       mFirebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userModel = new UserModel(mFirebaseUser.getDisplayName(), mFirebaseUser.getPhotoUrl().toString(), mFirebaseUser.getUid() );
        lerMessagensFirebase();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sign_out:
                signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Util.initToast(this,"Google Play Services error.");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonMessage:
                if(!TextUtils.isEmpty(edMessage.getText()))
                sendMessageFirebase();
                break;
        }
    }

    @Override
    public void clickImageChat(View view, int position, String nameUser, String urlPhotoUser, String urlPhotoClick) {
//        Intent intent = new Intent(this,FullScreenImageActivity.class);
//        intent.putExtra("nameUser",nameUser);
//        intent.putExtra("urlPhotoUser",urlPhotoUser);
//        intent.putExtra("urlPhotoClick",urlPhotoClick);
//        startActivity(intent);
    }

    @Override
    public void clickImageMapChat(View view, int position, String latitude, String longitude) {
        String uri = String.format("geo:%s,%s?z=17&q=%s,%s", latitude,longitude,latitude,longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    private void sendMessageFirebase(){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        PostModel model = new PostModel(userModel,edMessage.getText().toString(), Calendar.getInstance().getTime().getTime()+"");
        mFirebaseDatabaseReference.child(CHAT_REFERENCE).push().setValue(model);
        edMessage.setText(null);
        lerMessagensFirebase();
    }


    private void lerMessagensFirebase(){
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final PostFirebaseAdapter firebaseAdapter = new PostFirebaseAdapter(mFirebaseDatabaseReference.child(CHAT_REFERENCE),userModel.getName(),this);
        firebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    rvListMessage.scrollToPosition(positionStart);
                }
            }
        });
        rvListMessage.setLayoutManager(mLinearLayoutManager);
        rvListMessage.setAdapter(firebaseAdapter);
    }

    private void bindViews(){
        contentRoot = findViewById(R.id.contentRoot);
        edMessage = (EditText) findViewById(R.id.editTextMessage);
        btSendMessage = (ImageView)findViewById(R.id.buttonMessage);
        btSendMessage.setOnClickListener(this);

        rvListMessage = (RecyclerView)findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
    }
    private void signOut(){
        mFirebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
