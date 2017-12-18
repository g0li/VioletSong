package forevtechnologies.alegriauiux.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.CartActivity;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.adapter.DayAdapter;
import forevtechnologies.alegriauiux.adapter.RecyclerItemClickListener;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.CategoryCardModel;
import forevtechnologies.alegriauiux.models.Events;


public class FullInfoTabFragment extends Fragment {

    private static final String EXTRA_CATEGORY_CARD_MODEL = "EXTRA_CATEGORY_CARD_MODEL";
    //    String transitionTag;
    private CategoryCardModel categoryCardModel;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private RecyclerView rvAthletics;
    Intent bundh;
    Intent n;
    TextView tv;
    int cartCount=0;
    Snackbar snackbar;
    Vibrator myVib;
    MediaPlayer mPlayer;
    RelativeLayout badgeLayout;
    public void changeactivity(Activity a, Bundle b)
    {
    }

    public static FullInfoTabFragment newInstance(CategoryCardModel categoryCardModel) {
        FullInfoTabFragment fragment = new FullInfoTabFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_CATEGORY_CARD_MODEL, categoryCardModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryCardModel = getArguments().getParcelable(EXTRA_CATEGORY_CARD_MODEL);
        }
        if (savedInstanceState != null) {
            categoryCardModel = savedInstanceState.getParcelable(EXTRA_CATEGORY_CARD_MODEL);
        }
        setHasOptionsMenu(true);
    }

    RelativeLayout gow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_full_info, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        rvAthletics = (RecyclerView) view.findViewById(R.id.rvAthletics);
        gow=(RelativeLayout)view.findViewById(R.id.gow);
        bundh=new Intent(getActivity(),CartActivity.class);
        myVib=(Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        mPlayer=MediaPlayer.create(view.getContext(),R.raw.open_lighter);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.inflateMenu(R.menu.menu_registration);
        //Testing for cart counter
        Menu m=toolbar.getMenu();
        MenuItem mItem=m.findItem(R.id.badge);
        mItem.setActionView(R.layout.cart_badge);
        mItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(bundh==null){
                    Log.w("Bundh","Empty");
                }
                else{
                    Log.w("Bundh","Not empty");
                }
                bundh.putExtra("actName","Reg");
                startActivity(bundh);
                return true;
            }
        });
//        mItem.setVisible(false);

        View v=mItem.getActionView();
        ImageView imageView=(ImageView)v.findViewById(R.id.cartIcon);
        if(v==null){
            Log.w("View","Null");
        }
        tv=(TextView)v.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText("0");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundh==null){
                    Log.w("Bundh","Empty");
                }
                else{
                    Log.w("Bundh","Not empty");
                }
                bundh.putExtra("actName","Reg");
                startActivity(bundh);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundh==null){
                    Log.w("Bundh","Empty");
                }
                else{
                    Log.w("Bundh","Not empty");
                }
                bundh.putExtra("actName","Reg");
                startActivity(bundh);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.badge:
                    case R.id.cart:
                        //before:i.putStringExtra("TEST","ROSHAN HERE");
                        if(bundh==null){
                            Log.w("Bundh","Empty");
                        }
                        else{
                            Log.w("Bundh","Not empty");
                        }
                        bundh.putExtra("actName","Reg");
                        startActivity(bundh);
                        return true;
                    case R.id.broch:
                        Toast.makeText(getActivity(), "Clear call log", Toast.LENGTH_SHORT).show();
                        return true;

                }
                return  false;
            }
        });
        toolbar.setTitle(categoryCardModel.getCategoryTitle());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        setHasOptionsMenu(true);
        toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), categoryCardModel.getBackgroundColorResId()));
        tv.setTextColor(ContextCompat.getColor(getContext(),categoryCardModel.getBackgroundColorResId()));
        ivPhoto.setImageResource(categoryCardModel.getImageResId());
        List<AthleticModel> items = new ArrayList<>();
        switch(categoryCardModel.getCategoryTitle())
        {
            case"Informal":

                gow.setBackgroundColor(Color.parseColor("#9D50BB"));
                for (int ix=19;ix<=27;ix++) {
                    switch(Events.values()[ix]){

                        case FOA:
                            items.add(new AthleticModel("Lawn",Events.values()[ix], 23));
                            break;

                        case MMA:
                            items.add(new AthleticModel("Lawn",Events.values()[ix], 23));
                            break;

                        case FiP:
                            items.add(new AthleticModel("Lawn",Events.values()[ix], 23));
                            break;

                        case FS:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case TH:
                            items.add(new AthleticModel("Lawn",Events.values()[ix], 23));
                            break;

                        case PhE:
                            items.add(new AthleticModel("Campus",Events.values()[ix], 23));
                            break;

                        case FFF:
                            items.add(new AthleticModel("O-103",Events.values()[ix], 23));
                            break;

                        case PTVR:
                            items.add(new AthleticModel("O-102",Events.values()[ix], 23));
                            break;

                        case FAT:
                            items.add(new AthleticModel("Quad",Events.values()[ix], 23));
                            break;


                    }
                }
                break;



            case"Performing Arts":

                gow.setBackgroundColor(Color.parseColor("#FF6B6B"));
                for (int ix=6;ix<=18;ix++) {
                    switch(Events.values()[ix]){

                        case NRIT:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case DH:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case BBy:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case FoG:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case WoDJ:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case FSG:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case SKT:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case MA:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case RAP:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case DD:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case SoSi:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case OM:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                        case BB:
                            items.add(new AthleticModel("Artificial Lawn",Events.values()[ix], 23));
                            break;

                    }
                }
                break;



            case"Literary Arts":
                gow.setBackgroundColor(Color.parseColor("#FDFC47"));

                for (int ix=82;ix<=87;ix++) {
                    switch(Events.values()[ix]){

                        case ELOC:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                        case QZ:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                        case ESSY:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                        case SB:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                        case EnDe:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                        case PoWr:
                            items.add(new AthleticModel("O-202",Events.values()[ix], 23));
                            break;

                    }
                }
                break;

            case"Fine Arts":
                gow.setBackgroundColor(Color.parseColor("#0b8793"));

                for (int ix=68;ix<=75;ix++) {
                    switch(Events.values()[ix]){

                        case PM:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case CP:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case RM:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case MD:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case FP:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case NA:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case TsP:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;

                        case SkC:
                            items.add(new AthleticModel("O-203",Events.values()[ix], 23));
                            break;
                    }
                }
                break;
            case"Management":
                gow.setBackgroundColor(Color.parseColor("#fcb045"));

                for (int ix=76;ix<=81;ix++) {
                    switch(Events.values()[ix]){

                        case ADMD:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case MQ:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case BM:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case MTH:
                            items.add(new AthleticModel("Campus",Events.values()[ix], 23));
                            break;

                        case CSTD:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case LMS:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                    }
                }
                break;







            case"Sports & Gaming":
                gow.setBackgroundColor(Color.parseColor("#833ab4"));

                for (int ix=45;ix<=67;ix++) {
                    switch(Events.values()[ix]){

                        case FIFA:
                            items.add(new AthleticModel("L2",Events.values()[ix], 23));
                            break;

                        case NFS:
                            items.add(new AthleticModel("L1",Events.values()[ix], 23));
                            break;

                        case MM:
                            items.add(new AthleticModel("O-104",Events.values()[ix], 23));
                            break;

                        case FB:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case BBL:
                            items.add(new AthleticModel("Basketball Court",Events.values()[ix], 23));
                            break;

                        case VB:
                            items.add(new AthleticModel("Volleyball Court",Events.values()[ix], 23));
                            break;

                        case BC:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case TOW:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case TTN:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case ARS:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case CHS:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case FCQ:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case CRMS:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case CRMD:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case KBDI:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case NS:
                            items.add(new AthleticModel("O-302",Events.values()[ix], 23));
                            break;

                        case BDTG:
                            items.add(new AthleticModel("Badminton Ground",Events.values()[ix], 23));
                            break;

                        case BDTB:
                            items.add(new AthleticModel("Badminton Ground",Events.values()[ix], 23));
                            break;

                        case NC:
                            items.add(new AthleticModel("O-303",Events.values()[ix], 23));
                            break;

                        case CSGO:
                            items.add(new AthleticModel("L3",Events.values()[ix], 23));
                            break;

                        case LDO:
                            items.add(new AthleticModel("O-102",Events.values()[ix], 23));
                            break;

                        case VRFN:
                            items.add(new AthleticModel("O-103",Events.values()[ix], 23));
                            break;
                    }
                }
                break;



            case"Technical Events":
                gow.setBackgroundColor(Color.parseColor("#70e1f5"));

                for (int ix=0;ix<=5;ix++) {
                    switch(Events.values()[ix]){

                        case RC:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case TPP:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case MFW:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case CS:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case TR:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case HT:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                    }
                }
                break;





            case"Workshops":
                gow.setBackgroundColor(Color.parseColor("#d32f2f"));

                for (int ix=28;ix<=44;ix++) {
                    switch(Events.values()[ix]){

                        case TD:
                            items.add(new AthleticModel("L2",Events.values()[ix], 23));
                            break;

                        case WD:
                            items.add(new AthleticModel("L1",Events.values()[ix], 23));
                            break;

                        case AA:
                            items.add(new AthleticModel("L1",Events.values()[ix], 23));
                            break;

                        case IOT:
                            items.add(new AthleticModel("O-104",Events.values()[ix], 23));
                            break;

                        case PY:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case DP:
                            items.add(new AthleticModel("Basketball Court",Events.values()[ix], 23));
                            break;

                        case KT:
                            items.add(new AthleticModel("Volleyball Court",Events.values()[ix], 23));
                            break;

                        case FG:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case TW:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case CG:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case ZM:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case SA:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case DJW:
                            items.add(new AthleticModel("Conclave",Events.values()[ix], 23));
                            break;

                        case SSA:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case RC:
                            items.add(new AthleticModel("Gym",Events.values()[ix], 23));
                            break;

                        case TT:
                            items.add(new AthleticModel("Ground",Events.values()[ix], 23));
                            break;

                        case RS:
                            items.add(new AthleticModel("O-302",Events.values()[ix], 23));
                            break;


                    }
                }
                break;
        }



        final DayAdapter dayAdapter = new DayAdapter(getContext());
        dayAdapter.addItems(items);

        rvAthletics.setAdapter(dayAdapter);
        rvAthletics.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        // TODO Handle item click
                        Log.d("ITEMSCENE",String.valueOf(toolbar.getTitle())+" " +position);
                        myVib.vibrate(70);
                        mPlayer.start();
                        if(!bundh.hasExtra("Key "+toolbar.getTitle()+" "+position)) {
                            cartCount++;
                        }
                        tv.setText(String.valueOf(cartCount));
                        bundh.putExtra("Key "+toolbar.getTitle()+" "+position,String.valueOf(toolbar.getTitle())+"@"+position);
                        snackbar=Snackbar
                                .make(view,"Event Added.",Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        cartCount--;
                                        tv.setText(String.valueOf(cartCount));
                                        bundh.removeExtra("Key "+toolbar.getTitle()+" "+position);
                                    }
                                });
                        snackbar.setActionTextColor(getResources().getColor(R.color.white));
                        snackbar.show();
                    }
                })
        );
        rvAthletics.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvAthletics.setItemAnimator(new DefaultItemAnimator());
        rvAthletics.addItemDecoration(new DividerItemDecoration(getContext()));
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_CATEGORY_CARD_MODEL, categoryCardModel);
        super.onSaveInstanceState(outState);
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


    public void initializeIntent(Intent intent){
        n=intent;
    }
}
