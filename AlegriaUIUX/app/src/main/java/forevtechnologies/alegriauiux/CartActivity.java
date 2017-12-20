package forevtechnologies.alegriauiux;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.CartAdapter;
import forevtechnologies.alegriauiux.adapter.TicketCartAdapter;
import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.models.TicketCartModel;

public class CartActivity extends AppCompatActivity implements View.OnClickListener{
    Intent b;
    private int totalPrice=0;
    Button checkOutButton;
    CartAdapter cartAdapter;
    TextView textView;

    public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cart);
        setTitle("Cart");
        checkOutButton=(Button)findViewById(R.id.checkout);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(
                        getApplicationContext().getPackageName()+".cartPrice", Context.MODE_PRIVATE);
                prefs.edit().putInt("totalPrice",totalPrice).apply();
                startActivity(new Intent(CartActivity.this,SelectPaymentActivity.class));
            }
        });
        textView=findViewById(R.id.price_display);
        b=getIntent();
        if(b==null){
            Log.w("Bundle","Empty");
        }
        final List<CartModel> items=new ArrayList<>(88);
        final List<TicketCartModel> tItems=new ArrayList<>();
        List<Object> keyNames=new ArrayList<>(88);
        Bundle bundle;
        int numStudent,numPlatinum,numGold;
        String artistName;

        Log.w("Calling Activity",""+b.getStringExtra("actName"));

        //---> for calling activity equals Tickets


        if(b.getStringExtra("actName").equals("Tickets")){ //if calling activity is Ticket activity
            numStudent=b.getIntExtra("stuPass",0); //100
            numPlatinum=b.getIntExtra("plaPass",0); //1000
            numGold=b.getIntExtra("goldPass",0); //500
            artistName=b.getStringExtra("artistName");
            tItems.add(new TicketCartModel(artistName+"(Student)",numStudent*100));
            tItems.add(new TicketCartModel(artistName+"(Gold)",numGold*500));
            tItems.add(new TicketCartModel(artistName+"(Platinum)",numPlatinum*1000));


            final TicketCartAdapter ticketCartAdapter= new TicketCartAdapter(getApplicationContext());
            ticketCartAdapter.addItems(tItems);
            recyclerView=findViewById(R.id.reg_events);
            recyclerView.setAdapter(ticketCartAdapter);


            for(TicketCartModel model: tItems){
                totalPrice+=model.getPrice();
                Log.w("Price:||",""+totalPrice);
            }
            textView.setText("Rs. "+totalPrice+"/-");
            if(totalPrice>0){
                checkOutButton.setEnabled(true);
                checkOutButton.setFocusableInTouchMode(true);
            }
            else if(totalPrice<=0){
                checkOutButton.setEnabled(false);
                checkOutButton.setFocusableInTouchMode(false);
            }


            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                    final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                    if (direction == ItemTouchHelper.LEFT) {    //if swipe left

                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this); //alert for confirm to delete
                        builder.setMessage("Are you sure to delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                totalPrice-=ticketCartAdapter.cartItem.get(position).getPrice();
                                if(totalPrice<=0){
                                    checkOutButton.setEnabled(false);
                                    checkOutButton.setFocusableInTouchMode(false);
                                }
                                ticketCartAdapter.cartItem.remove(position);
                                ticketCartAdapter.notifyItemRemoved(position);
                                ticketCartAdapter.notifyItemRangeChanged(position, ticketCartAdapter.getItemCount());
                                textView.setText("Rs. "+totalPrice+"/-");
                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkOutButton.setEnabled(true);
                                checkOutButton.setFocusableInTouchMode(true);
                                ticketCartAdapter.notifyItemRemoved(position+1);
                                ticketCartAdapter.notifyItemRangeChanged(position, ticketCartAdapter.getItemCount());
                                return;
                            }
                        }).show();  //show alert dialog
                    }
                    if (direction == ItemTouchHelper.RIGHT) {    //if swipe right

                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this); //alert for confirm to delete
                        builder.setMessage("Are you sure to delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                totalPrice-=ticketCartAdapter.cartItem.get(position).getPrice();
                                if(totalPrice<=0){
                                    checkOutButton.setEnabled(false);
                                    checkOutButton.setFocusableInTouchMode(false);
                                }
                                ticketCartAdapter.cartItem.remove(position);
                                ticketCartAdapter.notifyItemRemoved(position);
                                ticketCartAdapter.notifyItemRangeChanged(position, ticketCartAdapter.getItemCount());
                                textView.setText("Rs. "+totalPrice+"/-");
                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkOutButton.setEnabled(true);
                                checkOutButton.setFocusableInTouchMode(true);
                                ticketCartAdapter.notifyItemRemoved(position+1);
                                ticketCartAdapter.notifyItemRangeChanged(position, ticketCartAdapter.getItemCount());
                                return;
                            }
                        }).show();  //show alert dialog
                    }
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView); //set swipe to recylcerview

        }



        // --> for calling activity equals Registration/ FullInfoTabFragment
        else if(b.getStringExtra("actName").equals("Reg")){ //if calling activity is FullInfoTabFragment
            /*
        function to get all extras from intent
        */
            bundle = b.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    if (!key.equals("actName")){
                        Object value = bundle.get(key);
                        keyNames.add(bundle.get(key));
                        Log.d("CartExtras", String.format("%s %s (%s)", key,
                                value.toString(), value.getClass().getName()));
                    }
                }
            }

            for(Object name :keyNames){
                String[] splitter=String.valueOf(name).split("@");
                int position=Integer.parseInt(splitter[1]);
                Integer.parseInt(splitter[1]);
                switch (splitter[0]){
                    case "Informal":
                        switch (position){
                            case 0:
                                items.add(new CartModel(Events.FOA.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.MMA.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.FiP.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.FS.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.TH.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.PhE.getEvents()));
                                break;
                            case 6:
                                items.add(new CartModel(Events.FFF.getEvents()));
                                break;
                            case 7:
                                items.add(new CartModel(Events.PTVR.getEvents()));
                                break;
                            case 8:
                                items.add(new CartModel(Events.FAT.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Performing Arts":
                        switch (position){
                            case 0:
                                items.add(new CartModel(Events.NRIT.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.DH.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.BBy.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.FoG.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.WoDJ.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.FSG.getEvents()));
                                break;
                            case 6:
                                items.add(new CartModel(Events.SKT.getEvents()));
                                break;
                            case 7:
                                items.add(new CartModel(Events.MA.getEvents()));
                                break;
                            case 8:
                                items.add(new CartModel(Events.RAP.getEvents()));
                                break;
                            case 9:
                                items.add(new CartModel(Events.DD.getEvents()));
                                break;
                            case 10:
                                items.add(new CartModel(Events.SoSi.getEvents()));
                                break;
                            case 11:
                                items.add(new CartModel(Events.OM.getEvents()));
                                break;
                            case 12:
                                items.add(new CartModel(Events.BB.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Literary Arts":
                        switch (position) {
                            case 0:
                                items.add(new CartModel(Events.ELOC.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.QZ.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.ESSY.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.SB.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Management":
                        switch (position) {
                            case 0:
                                items.add(new CartModel(Events.ADMD.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.MQ.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.BM.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.MTH.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Sports & Gaming":
                        switch (position){
                            case 0:
                                items.add(new CartModel(Events.FIFA.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.NFS.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.MM.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.FB.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.BBL.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.VB.getEvents()));
                                break;
                            case 6:
                                items.add(new CartModel(Events.BC.getEvents()));
                                break;
                            case 7:
                                items.add(new CartModel(Events.TOW.getEvents()));
                                break;
                            case 8:
                                items.add(new CartModel(Events.TTN.getEvents()));
                                break;
                            case 9:
                                items.add(new CartModel(Events.ARS.getEvents()));
                                break;
                            case 10:
                                items.add(new CartModel(Events.CHS.getEvents()));
                                break;
                            case 11:
                                items.add(new CartModel(Events.FCQ.getEvents()));
                                break;
                            case 12:
                                items.add(new CartModel(Events.CRMS.getEvents()));
                                break;
                            case 13:
                                items.add(new CartModel(Events.CRMD.getEvents()));
                                break;
                            case 14:
                                items.add(new CartModel(Events.KBDI.getEvents()));
                                break;
                            case 15:
                                items.add(new CartModel(Events.NS.getEvents()));
                                break;
                            case 16:
                                items.add(new CartModel(Events.BDTG.getEvents()));
                                break;
                            case 17:
                                items.add(new CartModel(Events.BDTB.getEvents()));
                                break;
                            case 18:
                                items.add(new CartModel(Events.NC.getEvents()));
                                break;
                            case 19:
                                items.add(new CartModel(Events.CSGO.getEvents()));
                                break;
                            default:
                                items.add(new CartModel(Events.AA.getEvents()));
                                break;
                        }
                        break;
                    case "Fine Arts":
                        switch (position){
                            case 0:
                                items.add(new CartModel(Events.PM.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.CP.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.RM.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.MD.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.FP.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.NA.getEvents()));
                                break;
                            case 6:
                                items.add(new CartModel(Events.AA.getEvents()));
                                break;
                            default:
                                items.add(new CartModel(Events.AA.getEvents()));
                                break;
                        }
                        break;
                    case "Technical Events":
                        switch (position){
                            case 0:
                                items.add(new CartModel(Events.RC.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.TPP.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.MFW.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.CS.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.TR.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.HT.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Workshops":
                        switch(position){
                            case 0:
                                items.add(new CartModel(Events.TD.getEvents()));
                                break;
                            case 1:
                                items.add(new CartModel(Events.WD.getEvents()));
                                break;
                            case 2:
                                items.add(new CartModel(Events.AA.getEvents()));
                                break;
                            case 3:
                                items.add(new CartModel(Events.IOT.getEvents()));
                                break;
                            case 4:
                                items.add(new CartModel(Events.PY.getEvents()));
                                break;
                            case 5:
                                items.add(new CartModel(Events.DP.getEvents()));
                                break;
                            case 6:
                                items.add(new CartModel(Events.KT.getEvents()));
                                break;
                            case 7:
                                items.add(new CartModel(Events.FG.getEvents()));
                                break;
                            case 8:
                                items.add(new CartModel(Events.TW.getEvents()));
                                break;
                            case 9:
                                items.add(new CartModel(Events.CG.getEvents()));
                                break;
                            case 10:
                                items.add(new CartModel(Events.ZM.getEvents()));
                                break;
                            case 11:
                                items.add(new CartModel(Events.SA.getEvents()));
                                break;
                            case 12:
                                items.add(new CartModel(Events.DJW.getEvents()));
                                break;
                            case 13:
                                items.add(new CartModel(Events.SSA.getEvents()));
                                break;
                            case 14:
                                items.add(new CartModel(Events.RCB.getEvents()));
                                break;
                            case 15:
                                items.add(new CartModel(Events.TT.getEvents()));
                                break;
                            case 16:
                                items.add(new CartModel(Events.PH.getEvents()));
                                break;
                            case 17:
                                items.add(new CartModel(Events.RS.getEvents()));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }


            recyclerView=findViewById(R.id.reg_events);
            cartAdapter = new CartAdapter(getApplicationContext());
            cartAdapter.addItems(items);
            recyclerView.setAdapter(cartAdapter);



            for(CartModel model: items){
                totalPrice+=PriceMapper.getPrice(model.getName());
                Log.w("Price:||",""+totalPrice);
            }
            textView.setText("Rs. "+totalPrice+"/-");
            if(totalPrice>0){
                checkOutButton.setEnabled(true);
                checkOutButton.setFocusableInTouchMode(true);
            }
            else if(totalPrice<=0){
                checkOutButton.setEnabled(false);
                checkOutButton.setFocusableInTouchMode(false);
            }

            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                    final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                    if (direction == ItemTouchHelper.LEFT) {    //if swipe left

                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this); //alert for confirm to delete
                        builder.setMessage("Are you sure to delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                totalPrice-=PriceMapper.getPrice(cartAdapter.cartItem.get(position).getName());
                                if(totalPrice<=0){
                                    checkOutButton.setEnabled(false);
                                    checkOutButton.setFocusableInTouchMode(false);
                                }
                                cartAdapter.cartItem.remove(position);
                                cartAdapter.notifyItemRemoved(position);
                                cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());
                                textView.setText("Rs. "+totalPrice+"/-");
                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkOutButton.setEnabled(true);
                                checkOutButton.setFocusableInTouchMode(true);
                                cartAdapter.notifyItemRemoved(position+1);
                                cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());
                                return;
                            }
                        }).show();  //show alert dialog
                    }
                    if (direction == ItemTouchHelper.RIGHT) {    //if swipe right

                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this); //alert for confirm to delete
                        builder.setMessage("Are you sure to delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                totalPrice-=PriceMapper.getPrice(cartAdapter.cartItem.get(position).getName());
                                if(totalPrice<=0){
                                    checkOutButton.setEnabled(false);
                                    checkOutButton.setFocusableInTouchMode(false);
                                }
                                cartAdapter.cartItem.remove(position);
                                cartAdapter.notifyItemRemoved(position);
                                cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());
                                textView.setText("Rs. "+totalPrice+"/-");
                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkOutButton.setEnabled(true);
                                checkOutButton.setFocusableInTouchMode(true);
                                cartAdapter.notifyItemRemoved(position+1);
                                cartAdapter.notifyItemRangeChanged(position, cartAdapter.getItemCount());
                                return;
                            }
                        }).show();  //show alert dialog
                    }
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView); //set swipe to recylcerview

        }

        LinearLayoutManager mLayoutManager=new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext()));
        recyclerView.setNestedScrollingEnabled(false);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("Running","true");
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