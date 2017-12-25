package forevtechnologies.alegriauiux;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.vansuita.gaussianblur.GaussianBlur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import forevtechnologies.alegriauiux.adapter.DayAdapter;
import forevtechnologies.alegriauiux.adapter.MyEventsDayAdapter;
import forevtechnologies.alegriauiux.adapter.RecyclerItemClickListener;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.models.GetEvents;
import forevtechnologies.alegriauiux.models.MyEventsAthleticModel;
import forevtechnologies.alegriauiux.sharedPreferenceFile.SharedPreferenceStringTags;
import jp.wasabeef.blurry.Blurry;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static forevtechnologies.alegriauiux.adapter.DayAdapter.context;


/**
 * Created by jojosexbomb69 on 7/12/17.
 */

public class MyEvents extends AppCompatActivity {
    RecyclerView rvAthletics;
    MyEventsDayAdapter dayAdapter;
    ImageView backButton;
    List<MyEventsAthleticModel> items;
    FirebaseDatabase firebaseDatabase;
    String UID,currentEvent;
    DatabaseReference databaseReference;
    SharedPreferences offlineitems;
    List<String> itemPos = new ArrayList<>();
    List<String> EVENTDATA = new ArrayList<>();
    public final static int WIDTH=1000;
    Dialog QRCODE_DISPLAY;
    Button btn_close;
    ImageView qr_image;
    int i = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myevents);
        offlineitems = getSharedPreferences(SharedPreferenceStringTags.USER_CART_DATABASE,MODE_PRIVATE);
        final SharedPreferences.Editor offlineitemsEditor = offlineitems.edit();
        items=new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UID = user.getUid();
        }

        Log.w("Act","Running");
        backButton=(ImageView) findViewById(R.id.backButton);
        rvAthletics=(RecyclerView) findViewById(R.id.myEventsRecycler);
        dayAdapter = new MyEventsDayAdapter(getBaseContext());



        databaseReference.child("User Data").child(UID).addValueEventListener(new ValueEventListener() {
            @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
          for(DataSnapshot child : dataSnapshot.getChildren()){
              String mEvent = child.getValue().toString();
              items.add(new MyEventsAthleticModel("Lawn",mEvent, 23));
              itemPos.add(mEvent);
          }
          dayAdapter.addItems(items);
          rvAthletics.setAdapter(dayAdapter);
          rvAthletics.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
              @Override
              public void onItemClick(View view, int position) {
                  String DATA = items.get(position).getEvents();
                  try {
                      Bitmap bitmap = encodeAsBitmap(DATA);
                      CustomDialog(bitmap);

                  } catch (WriterException e) {
                      e.printStackTrace();
                  }
              }
              Bitmap encodeAsBitmap(String str) throws WriterException {
                  BitMatrix result;
                  try {
                      result = new MultiFormatWriter().encode(str,
                              BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
                  } catch (IllegalArgumentException iae) {
                      // Unsupported format
                      return null;
                  }
                  int w = result.getWidth();
                  int h = result.getHeight();
                  int[] pixels = new int[w * h];
                  for (int y = 0; y < h; y++) {
                      int offset = y * w;
                      for (int x = 0; x < w; x++) {
                          pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                      }
                  }
                  Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                  bitmap.setPixels(pixels, 0,1000, 0, 0, w, h);
                  return bitmap;
              }
          }));

               // Toast.makeText(getBaseContext(),String.valueOf(itemPos.size()),Toast.LENGTH_SHORT).show();
//               for(String events : itemPos){
//                   items.add(new MyEventsAthleticModel("Lawn",events,23));
////                   Toast.makeText(getBaseContext(),events,Toast.LENGTH_SHORT).show();
//               }


      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Log.w("Cancelled:","True");
      }
  });

//      Map<String,?> itemsMap = offlineitems.getAll();
//              for (Map.Entry<String, ?> entry : itemsMap.entrySet()) {
//                  if (!itemsMap.isEmpty()) {
//                      currentEvent = entry.getValue().toString();
//                      if(currentEvent.equals("CART_EXISTS"))
//                          continue;
//                      Toast.makeText(getBaseContext(),currentEvent,Toast.LENGTH_SHORT).show();
//                     // items.add(new MyEventsAthleticModel("Lawn",currentEvent, 23));
//                      itemPos.add(currentEvent);
//              }
//          }

        rvAthletics.setAdapter(dayAdapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        rvAthletics.setLayoutManager(linearLayoutManager);
        rvAthletics.setItemAnimator(new DefaultItemAnimator());
        rvAthletics.addItemDecoration(new DividerItemDecoration(this));



    }

    public void CustomDialog(Bitmap bitmap){
            QRCODE_DISPLAY = new Dialog(MyEvents.this);
            QRCODE_DISPLAY.setContentView(R.layout.qr_dialog);
            QRCODE_DISPLAY.setTitle("QR CODE");

            btn_close = QRCODE_DISPLAY.findViewById(R.id.close_btn);
            qr_image = QRCODE_DISPLAY.findViewById(R.id.image_qr);
            btn_close.setEnabled(true);
            qr_image.setImageBitmap(bitmap);
            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QRCODE_DISPLAY.cancel();
//                    finish();
                }
            });
            QRCODE_DISPLAY.show();
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
