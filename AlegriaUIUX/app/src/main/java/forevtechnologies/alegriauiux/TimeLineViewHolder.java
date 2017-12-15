package forevtechnologies.alegriauiux;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {


    public TextView mDate;
    public TextView mMessage;
    public TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        mDate=itemView.findViewById(R.id.text_timeline_date);
        mMessage=itemView.findViewById(R.id.text_timeline_title);
        mTimelineView=itemView.findViewById(R.id.time_marker);
        Log.w("Int viewtype","Val="+viewType);
        mTimelineView.initLine(viewType);
    }
}
