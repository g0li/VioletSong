package forevtechnologies.alegriauiux.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import forevtechnologies.alegriauiux.ContactDetails;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.ScheduleDetails;

/**
 * Created by thisi on 13-05-2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {
    private List<ScheduleDetails> contactList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName,eventDate,eventDay,eventTime;
        public CircleImageView eventIcon;
        public ImageView eventColor;

        public MyViewHolder(View view) {
            super(view);
            eventName=(TextView)view.findViewById(R.id.eventName);
            eventDate=(TextView)view.findViewById(R.id.eventDate);
            eventDay=(TextView)view.findViewById(R.id.eventDay);
            eventTime=(TextView)view.findViewById(R.id.eventTime);
            eventIcon=(CircleImageView) view.findViewById(R.id.eventIcon);
            eventColor=(ImageView) view.findViewById(R.id.eventColor);

        }
    }
    public ScheduleAdapter(List<ScheduleDetails> contactList){
        this.contactList=contactList;
    }
    @Override
    public ScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scheduler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.MyViewHolder holder, int position) {
        ScheduleDetails contactDetails = contactList.get(position);
        holder.eventName.setText(contactDetails.getName());
        holder.eventDate.setText(contactDetails.getDate());
        holder.eventDay.setText(contactDetails.getDay());
        holder.eventTime.setText(contactDetails.getTime());
        holder.eventColor.setBackgroundColor(Color.parseColor(contactDetails.getColor()));
        holder.eventIcon.setImageResource(contactDetails.getIcon());
    }

    @Override
    public int getItemCount() {return 6;}


}
