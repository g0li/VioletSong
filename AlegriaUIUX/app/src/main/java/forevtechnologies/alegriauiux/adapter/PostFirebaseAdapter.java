package forevtechnologies.alegriauiux.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import at.blogc.android.views.ExpandableTextView;
import forevtechnologies.alegriauiux.adapter.CircleTransform;
import forevtechnologies.alegriauiux.adapter.ClickListenerChatFirebase;
import forevtechnologies.alegriauiux.model.PostModel;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.model.PostModel;


/**
 * Created by Alessandro Barreto on 23/06/2016.
 */
public class PostFirebaseAdapter extends FirebaseRecyclerAdapter<PostModel,PostFirebaseAdapter.MyChatViewHolder> {

    private static final int RIGHT_MSG = 0;
    private static final int LEFT_MSG = 1;
    private static final int RIGHT_MSG_IMG = 2;
    private static final int LEFT_MSG_IMG = 3;

    private ClickListenerChatFirebase mClickListenerChatFirebase;

    private String nameUser,message,team;



    public PostFirebaseAdapter(DatabaseReference ref, String nameUser,String Message,String Team) {
        super(PostModel.class, R.layout.item_message_left, MyChatViewHolder.class, ref);
        this.nameUser = nameUser;
        this.message=Message;
        this.team=Team;
        this.mClickListenerChatFirebase = mClickListenerChatFirebase;
    }
    public PostFirebaseAdapter(DatabaseReference ref, String nameUser) {
        super(PostModel.class, R.layout.item_message_left, MyChatViewHolder.class, ref);
        this.nameUser = nameUser;
    }
    public PostFirebaseAdapter(DatabaseReference ref) {
        super(PostModel.class, R.layout.item_message_left, MyChatViewHolder.class, ref);
    }

    @Override
    public MyChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RIGHT_MSG){
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_message_right,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == LEFT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == RIGHT_MSG_IMG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right_img,parent,false);
            return new MyChatViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left_img,parent,false);
            return new MyChatViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        PostModel model = getItem(position);
              return RIGHT_MSG_IMG;
             }

    @Override
    protected void populateViewHolder(MyChatViewHolder viewHolder, PostModel model, int position) {
        viewHolder.setIvUser(model.getUserModel().getPhoto_profile());
        viewHolder.setTvName(model.getUserModel().getName());
        viewHolder.setTxtMessage(model.getMessage());
        viewHolder.setTvTimestamp(model.getTimeStamp());
        viewHolder.setTvTeam(team);

    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout teamColor;
        TextView tvTimestamp,tvTeam,tvName;
        ExpandableTextView txtMessage;
        ImageView ivUser;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.textview_name);
            txtMessage = (ExpandableTextView) itemView.findViewById(R.id.exptextview_post);
            ivUser = (ImageView)itemView.findViewById(R.id.imageView2);
            tvTimestamp = (TextView)itemView.findViewById(R.id.textview_timestamp);
            tvTeam=(TextView)itemView.findViewById(R.id.textview_team);
            teamColor=(LinearLayout)itemView.findViewById(R.id.teamcolor);
            txtMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtMessage.toggle();
                }
            });
            txtMessage.setAnimationDuration(1000L);
            txtMessage.setInterpolator(new OvershootInterpolator());
            txtMessage.setExpandInterpolator(new OvershootInterpolator());
            txtMessage.setCollapseInterpolator(new OvershootInterpolator());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            PostModel model = getItem(position);

        }

        public void setTvName(String s)
        {
            tvName.setText(s);
        }
        public void setTxtMessage(String mxssage){

            txtMessage.setText(mxssage);
        }

        public void setIvUser(String urlPhotoUser){
            if (ivUser == null)return;
            Glide.with(ivUser.getContext()).load(urlPhotoUser).centerCrop().transform(new CircleTransform(ivUser.getContext())).override(40,40).into(ivUser);
        }

        public void setTvTimestamp(String timestamp){
            if (tvTimestamp == null)return;
            tvTimestamp.setText(converteTimestamp(timestamp));
        }
        public void setTvTeam(String team)
        { if (txtMessage == null)return;


        }



    }

    private CharSequence converteTimestamp(String mileSegundos){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(mileSegundos), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

}
