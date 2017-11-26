package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import at.blogc.android.views.ExpandableTextView;
import butterknife.ButterKnife;
import forevtechnologies.alegriauiux.adapter.PostFirebaseAdapter;
import forevtechnologies.alegriauiux.adapter.ClickListenerChatFirebase;
import forevtechnologies.alegriauiux.model.PostModel;
import forevtechnologies.alegriauiux.model.UserModel;
import forevtechnologies.alegriauiux.utl.Util;
import lumenghz.com.pullrefresh.PullToRefreshView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by thisi on 12-05-2017.
 */

public class WHATSHOT extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private static final int IMAGE_GALLERY_REQUEST = 1;
    private static final int IMAGE_CAMERA_REQUEST = 2;
    private static final int PLACE_PICKER_REQUEST = 3;

    static final String TAG = MainActivity.class.getSimpleName();
    static final String CHAT_REFERENCE = "whatshot";

    //Firebase and GoogleApiClient
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mFirebaseDatabaseReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    PullToRefreshView mPullToRefresh;

    //CLass Model
    private boolean mIsRefreshing;
    private static final int REFRESH_DELAY = 4500;

    //Views UI
    private RecyclerView rvListMessage;
    private LinearLayoutManager mLinearLayoutManager;

    private View contentRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_whatshot,container,false);
        bindViews( view);
        mFirebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        lerMessagensFirebase();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        ButterKnife.bind(this, view);

        return view;

    }

    private void initRefreshView() {
        mPullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lerMessagensFirebase();
                        mPullToRefresh.setRefreshing(false);

                    }
                }, REFRESH_DELAY);
            }
        });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Util.initToast(getActivity(),"Google Play Services error.");
    }





    private void lerMessagensFirebase(){

        rvListMessage.getRecycledViewPool().clear();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final PostFirebaseAdapter firebaseAdapter = new PostFirebaseAdapter(mFirebaseDatabaseReference.child(CHAT_REFERENCE));
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



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshView();

        mPullToRefresh.post(new Runnable() {
            @Override
            public void run() {
//           lerMessagensFirebase();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void bindViews(View v){
        contentRoot = v.findViewById(R.id.contentRoot);
        rvListMessage = (RecyclerView)v.findViewById(R.id.messageRecyclerView);
        rvListMessage = (RecyclerView)v.findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mPullToRefresh=(PullToRefreshView) v.findViewById(R.id.pull_to_refresh);
    }
}
