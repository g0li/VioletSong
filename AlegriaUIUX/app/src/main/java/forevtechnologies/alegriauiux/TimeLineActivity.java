package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dd.processbutton.iml.ActionProcessButton;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.RecyclerItemClickListener;
import forevtechnologies.alegriauiux.models.DayWiseEvents;
import forevtechnologies.alegriauiux.models.EventDay;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.models.OrderStatus;
import forevtechnologies.alegriauiux.models.Orientation;
import forevtechnologies.alegriauiux.models.TimeLineModel;

import forevtechnologies.alegriauiux.sharedPreferenceFile.SharedPreferenceStringTags;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;
//    shared preference to check which event user needs notifications for
//    String USER_NOTIFICATIONS="USER_NOTIFICATION";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public EventDay eDay;
    CardView cardView;
    ImageView eventPicture;
    int itemPosition;
    TextView eventName,eventLocation,eventDate;
    ActionProcessButton processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        sp=getSharedPreferences(SharedPreferenceStringTags.USER_NOTIFICATIONS,MODE_PRIVATE);
        editor=sp.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mOrientation = (Orientation) Orientation.HORIZONTAL;
        mWithLinePadding = true;

        setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.horizontal_timeline) : getResources().getString(R.string.vertical_timeline));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        cardView=findViewById(R.id.eventDescription);
        processButton=findViewById(R.id.notifyButton);
        processButton.setProgress(0);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sp.contains(mDataList.get(itemPosition).getMessage())){
                    editor.putString(mDataList.get(itemPosition).getMessage(),mDataList.get(itemPosition).getMessage());
                    editor.commit();
                    Log.w("Notify:","Added");
                    Log.w("Data:",mDataList.get(itemPosition).getMessage());
                }
                else{
                    Log.w("Notify:","Added");
                    Log.w("Data:",mDataList.get(itemPosition).getMessage());
                }

                processButton.setProgress(50);
                processButton.setProgress(100);
                processButton.setEnabled(false);
            }

        });
        cardView.setVisibility(View.GONE);

        initView();
    }

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        }
        else {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
    }

    private void initView() {
        //set day here using remote config if possible or else use integers
        eDay=EventDay.DAY1;
        setDataListItems(eDay);
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Log.w("Position:",String.valueOf(position));
                itemPosition=position;
                changeCardDescription(position,eDay);
            }
        }));
    }

    private void setDataListItems(EventDay day){
        switch (day){
            case DAY1: //set data for day1

                List<TimeLineModel>eventList=new ArrayList<>();
                //adding objects to list
                for(int i=0;i<=17;i++){
                    eventList.add(new TimeLineModel());
                }

                //setting values for objects in list
                for(int i=0;i<=17;i++){
                    //event name
                    eventList.get(i).setMessage(DayWiseEvents.values()[i].getEvents());
                    //event date displayed in yyyy-MM-dd Hh:Mm
                    eventList.get(i).setDate("2018-06-02 8:00");
                    //event drawable
                    eventList.get(i).setPicture(
                            getResources()
                            .getIdentifier
                            (DayWiseEvents.values()[i].toString().toLowerCase(),
                                    "drawable",
                                    getPackageName()
                            )
                    );
                    //location of event displayed in description card
                    eventList.get(i).setLocation("O-303");
                    /*Info regarding the event at given time
                    and place displayed in description card */
                    eventList.get(i).setTime("This event will take place in "
                                            +eventList.get(i).getLocation()+
                                            " at "+eventList.get(i).getDate());
                }
                for(int i=0;i<=17;i++){
                    eventList.get(i).setStatus(OrderStatus.ACTIVE);
                }
                mDataList.addAll(eventList);

                break;
            case DAY2: //set data for day2

                break;
            case DAY3: //set data for day3

                break;
            case DAY4: //set data for day4

                break;
            case DAY5: //set data for day5

                break;
            default:
                break;

        }

//        mDataList.add(new TimeLineModel(Events.values()[12].getEvents(), "2017-02-12 08:00", OrderStatus.INACTIVE));
//        mDataList.add(new TimeLineModel("Courier is out to delivery your order", "2017-02-12 08:00", OrderStatus.ACTIVE));
//        mDataList.add(new TimeLineModel("Item has reached courier facility at New Delhi", "2017-02-11 21:00", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Item has been given to the courier", "2017-02-11 18:00", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Item is packed and will dispatch soon", "2017-02-11 09:30", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Order is being readied for dispatch", "2017-02-11 08:00", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Order processing initiated", "2017-02-10 15:00", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Order confirmed by seller", "2017-02-10 14:30", OrderStatus.COMPLETED));
//        mDataList.add(new TimeLineModel("Order placed successfully", "2017-02-10 14:00", OrderStatus.COMPLETED));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if(mOrientation!=null)
            savedInstanceState.putSerializable("ORIENTATION", mOrientation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("ORIENTATION")) {
                mOrientation = (Orientation) savedInstanceState.getSerializable("ORIENTATION");
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void changeCardDescription(int position,EventDay day){
        TimeLineModel m;
        switch (day){
            case DAY1:
                    m=mDataList.get(position);
                    changeDescriptionModel(m.getPicture(),m.getMessage(),m.getLocation(),m.getTime());
                break;
            case DAY2:

                break;
            case DAY3:

                break;
            case DAY4:

                break;
            case DAY5:

                break;
            default:
        }

    }

    public void changeDescriptionModel(int eventPictureId, String eventName, String eventLocation, String eventTime){
        cardView.setVisibility(View.VISIBLE);
        if(sp.contains(eventName)){
            processButton.setProgress(100);
            processButton.setEnabled(false);
            processButton.setText("Already Added");
        }
        else{
            processButton.setProgress(0);
            processButton.setEnabled(true);
        }
        this.eventName=findViewById(R.id.eventName);
        this.eventName.setText(eventName);
        this.eventLocation=findViewById(R.id.eventLocation);
        this.eventLocation.setText(eventLocation);
        this.eventDate=findViewById(R.id.eventTime);
        this.eventDate.setText(eventTime);
        eventPicture=findViewById(R.id.eventPicture);
        eventPicture.setImageResource(eventPictureId);
    }
}
