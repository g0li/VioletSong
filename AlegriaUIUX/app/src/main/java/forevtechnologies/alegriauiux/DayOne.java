package forevtechnologies.alegriauiux;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.ScheduleAdapter;

/**
 * Created by thisi on 29-06-2017.
 */

public class DayOne extends Fragment {
    List<ScheduleDetails> scheduleList = new ArrayList<>();
    RecyclerView recyclerView;
    ScheduleAdapter sAdapter;
    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_schedule,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.scheduleRV);
        sAdapter=new ScheduleAdapter(scheduleList);

        prepareDataList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);
        return  view;
    }

    void prepareDataList()
    {
        ScheduleDetails schedule;
        schedule=new ScheduleDetails("Aarif Ahmed's Birthday","9 July","Sunday","10am","#F44336",R.drawable.arif);
        scheduleList.add(schedule);
        schedule=new ScheduleDetails("Prateek Gupta's Birthday","15 December","Friday","10am","#4DB6AC", R.drawable.pratik);
        scheduleList.add(schedule);
        schedule=new ScheduleDetails("Roshan Singh's Birthday","14 October","Saturday","10am","#7B1FA2", R.drawable.roshan);
        scheduleList.add(schedule);
        schedule=new ScheduleDetails("Shrey Jhakmola's Birthday","30 February","Doomsday","10am","#FBC02D", R.drawable.shrey);
        scheduleList.add(schedule);
        schedule=new ScheduleDetails("Shubhang Rajput's Birthday","11 October","Wednesday","10am","#DD2C00",R.drawable.shubhang);
        scheduleList.add(schedule);
        schedule=new ScheduleDetails("Sooraj Nair's Birthday","4 September","Monday","10am","#8D6E63", R.drawable.sooraj);
        scheduleList.add(schedule);

        sAdapter.notifyDataSetChanged();


    }
}
