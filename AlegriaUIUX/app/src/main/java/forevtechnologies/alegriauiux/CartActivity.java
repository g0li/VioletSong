package forevtechnologies.alegriauiux;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.CartAdapter;
import forevtechnologies.alegriauiux.models.CartModel;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Button buttonX = (Button) findViewById(R.id.checkout);
        Intent intent = getIntent();
        b = getIntent().getExtras();
        List<CartModel> items = new ArrayList<>();
        List<String> eventNames;
        eventNames=b.getStringArrayList("EventName");
        //Log.w("Event Bundle received",""+eventNames.get(0));
        for(int n=0;n<eventNames.size();n++){
            items.add(new CartModel(eventNames.get(n)));
        }
        if(!items.isEmpty()){
            Log.w("Size","items not empty");
        }
        else{
            Log.w("Size","items empty");
        }
        /*
        Log.d("TEST",b.getString("TEST"));//this line
        for (int i = 0; i <=b.size(); i++) {
            items.add(new CartModel(eventNames.get(i)));
        }
         */

        CartAdapter cartAdapter = new CartAdapter(getApplicationContext());
        cartAdapter.addItems(items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reg_events);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CartActivity.DividerItemDecoration(getBaseContext()));
    }


    @Override
    public void onClick(View view) {

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
