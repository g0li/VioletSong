package forevtechnologies.alegriauiux;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;

/**
 * Created by thisi on 24-05-2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<PostDetails> postList;
    public PostAdapter(List<PostDetails> postList){
        this.postList=postList;
    }
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hot_list, parent, false);

        return new PostAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PostAdapter.MyViewHolder holder, int position) {
        PostDetails postDetails = postList.get(position);
        holder.userName.setText(postDetails.getUserName());
        holder.userTeam.setText(postDetails.getUserTeam());
        holder.timestamp.setText(postDetails.getTimestamp());
        holder.post.setText(postDetails.getPost());
        switch (postDetails.getUserTeam())
        {
            case "Developer":
                holder.teamcolor.setBackgroundColor(Color.BLACK);
                holder.userTeam.setTextColor(Color.WHITE);
                break;
            default:
                holder.teamcolor.setBackgroundColor(Color.CYAN);
                holder.userTeam.setTextColor(Color.DKGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {
        public TextView userName,userTeam,timestamp;
        public LinearLayout teamcolor;
        public ExpandableTextView post;
        public MyViewHolder(View view) {
            super(view);
            userName=(TextView)view.findViewById(R.id.textview_name);
            userTeam=(TextView)view.findViewById(R.id.textview_team);
            timestamp=(TextView)view.findViewById(R.id.textview_timestamp);
            teamcolor=(LinearLayout) view.findViewById(R.id.teamcolor);
            post=(ExpandableTextView)view.findViewById(R.id.exptextview_post);
                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        post.toggle();
                    }
                });
                post.setAnimationDuration(1000L);
                post.setInterpolator(new OvershootInterpolator());
                post.setExpandInterpolator(new OvershootInterpolator());
                post.setCollapseInterpolator(new OvershootInterpolator());
            post.setOnExpandListener(new ExpandableTextView.OnExpandListener()
            {
                @Override
                public void onExpand(final ExpandableTextView view)
                {
                    Log.d("whatshot", "ExpandableTextView expanded");
                }

                @Override
                public void onCollapse(final ExpandableTextView view)
                {
                    Log.d("whatshot", "ExpandableTextView collapsed");
                }
            });
        }
    }
}
