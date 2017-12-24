package forevtechnologies.alegriauiux;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import forevtechnologies.alegriauiux.adapter.DayAdapter;
import forevtechnologies.alegriauiux.adapter.MyEventsDayAdapter;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.models.GetEvents;
import forevtechnologies.alegriauiux.models.MyEventsAthleticModel;
import forevtechnologies.alegriauiux.sharedPreferenceFile.SharedPreferenceStringTags;


/**
 * Created by jojosexbomb69 on 7/12/17.
 */

public class MyEvents extends AppCompatActivity {
    RecyclerView rvAthletics;
    MyEventsDayAdapter dayAdapter;
    ImageView backButton;
    List<MyEventsAthleticModel> items=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    String UID,mEvent,currentEvent;
    DatabaseReference databaseReference;
    SharedPreferences offlineitems;
    List<String> event_item = new ArrayList<>();
    SharedPreferences sp;
    int i;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myevents);

        offlineitems = getSharedPreferences(SharedPreferenceStringTags.USER_CART_DATABASE,MODE_PRIVATE);
        final SharedPreferences.Editor offlineitemsEditor = offlineitems.edit();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UID = user.getUid();
        }
//        sp=getSharedPreferences(SharedPreferenceStringTags.USER_CART_DATABASE,MODE_PRIVATE);
//        Map<String,?> cartItems=sp.getAll();
        Log.w("Act","Running");
        backButton=(ImageView) findViewById(R.id.backButton);
        rvAthletics=findViewById(R.id.myEventsRecycler);



        databaseReference.child("User Data").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    mEvent = String.valueOf(dataSnapshot.getValue());
                   if(!offlineitems.contains(mEvent)){
                       offlineitemsEditor.putString("Event@"+mEvent,mEvent);
                       offlineitemsEditor.commit();
                       Log.d("ADDED",mEvent);
                   }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Failed read value",Toast.LENGTH_SHORT).show();
            }
        });

//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));
//        items.add(new AthleticModel("Lawn", Events.values()[10],23));

        Map<String,?> itemsMap = offlineitems.getAll();
        if(!itemsMap.isEmpty()) {
            for (Map.Entry<String, ?> entry : itemsMap.entrySet()) {
                //   Log.d("map values", entry.getValue().toString());
                currentEvent = entry.getValue().toString();
                items.add(new MyEventsAthleticModel("Lawn", currentEvent, 23));
            }
        }


        dayAdapter=new MyEventsDayAdapter(this);
        dayAdapter.addItems(items);
        rvAthletics.setAdapter(dayAdapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvAthletics.setLayoutManager(linearLayoutManager);
        rvAthletics.setItemAnimator(new DefaultItemAnimator());
        rvAthletics.addItemDecoration(new DividerItemDecoration(this));

    }


    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable mDivider;

        /**
         * Default divider will be used
         */
        public DividerItemDecoration(Context context) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            mDivider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        }

        /**
         * Custom divider will be used
         */
        public DividerItemDecoration(Context context, int resId) {
            mDivider = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
