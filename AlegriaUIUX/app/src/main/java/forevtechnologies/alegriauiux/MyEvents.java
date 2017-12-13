package forevtechnologies.alegriauiux;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.DayAdapter;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.Events;


/**
 * Created by jojosexbomb69 on 7/12/17.
 */

public class MyEvents extends AppCompatActivity {
    RecyclerView rvAthletics;
    DayAdapter dayAdapter;
    ImageView backButton;
    List<AthleticModel> items=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myevents);
        Log.w("Act","Running");
        backButton=(ImageView) findViewById(R.id.backButton);
        rvAthletics=findViewById(R.id.myEventsRecycler);
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        items.add(new AthleticModel("Lawn", Events.values()[10],23));
        dayAdapter=new DayAdapter(this);
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
