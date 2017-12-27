package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fujiyuu75.sequent.Sequent;

/**
 * Created by thisi on 12-05-2017.
 */

public class HOUSE extends Fragment implements View.OnClickListener {
    CardView profileCardView,brochureCardView,eventsCardView,infoCardView,concertCardView,chatCardView,campusCardView;
    TextView profileTextView,brochureTextView,eventsTextView,infoTextView,concertTextView,chatTextView,campusTextView;
    ImageView profileImageView,brochureImageView,eventsImageView,infoImageView,concertImageView,chatImageView,campusImageView;
    LinearLayout profileLayout,brochureLayout,eventsLayout,infoLayout,concertLayout,chatLayout,campusLayout;
    TableLayout TX,TX1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initui(view);
        Sequent.origin(TX).anim(getActivity(), R.anim.bounce_in).start();
        Sequent.origin(TX1).anim(getActivity(), R.anim.bounce_in).start();



    return view;
    }

    void initui(View v)
    {
        profileCardView=(CardView)v.findViewById(R.id.profileCardView);
        profileLayout=(LinearLayout)v.findViewById(R.id.profileLinearLayout);
        profileImageView=(ImageView)v.findViewById(R.id.profileImageView);
        profileTextView=(TextView)v.findViewById(R.id.profileTextView);

        brochureCardView=(CardView)v.findViewById(R.id.brochureCardView);
        brochureLayout=(LinearLayout)v.findViewById(R.id.brochureLinearLayout);
        brochureImageView=(ImageView)v.findViewById(R.id.brochureImageView);
        brochureTextView=(TextView)v.findViewById(R.id.brochureTextView);

        eventsCardView=(CardView)v.findViewById(R.id.eventsCardView);
        eventsLayout=(LinearLayout)v.findViewById(R.id.eventsLayout);
        eventsImageView=(ImageView)v.findViewById(R.id.eventsImageView);
        eventsTextView=(TextView)v.findViewById(R.id.eventsTextView);

        infoCardView=(CardView)v.findViewById(R.id.infoCardView);
        infoLayout=(LinearLayout)v.findViewById(R.id.infoLayout);
        infoImageView=(ImageView)v.findViewById(R.id.infoImageView);
        infoTextView=(TextView)v.findViewById(R.id.infoTextView);

        concertCardView=(CardView)v.findViewById(R.id.concertCardView);
        concertLayout=(LinearLayout)v.findViewById(R.id.concertLayout);
        concertImageView=(ImageView)v.findViewById(R.id.concertImageView);
        concertTextView=(TextView)v.findViewById(R.id.concertTextView);

        chatCardView=(CardView)v.findViewById(R.id.campusCardView);
        chatLayout=(LinearLayout)v.findViewById(R.id.campusLayout);
        chatImageView=(ImageView)v.findViewById(R.id.campusImageView);
        chatTextView=(TextView)v.findViewById(R.id.campusTextView);

        campusCardView=(CardView)v.findViewById(R.id.chatCardView);
        campusLayout=(LinearLayout)v.findViewById(R.id.chatLayout);
        campusImageView=(ImageView)v.findViewById(R.id.chatImageView);
        campusTextView=(TextView)v.findViewById(R.id.chatTextView);

        campusCardView=(CardView)v.findViewById(R.id.campusCardView);
        campusLayout=(LinearLayout)v.findViewById(R.id.campusLayout);
        campusImageView=(ImageView)v.findViewById(R.id.campusImageView);
        campusTextView=(TextView)v.findViewById(R.id.campusTextView);


        TX=(TableLayout) v.findViewById(R.id.TX);
        TX1=(TableLayout) v.findViewById(R.id.TX1);

        profileCardView.setOnClickListener(this);
        profileLayout.setOnClickListener(this);
        profileImageView.setOnClickListener(this);
        profileTextView.setOnClickListener(this);

        brochureCardView.setOnClickListener(this);
        brochureLayout.setOnClickListener(this);
        brochureImageView.setOnClickListener(this);
        brochureTextView.setOnClickListener(this);

        eventsCardView.setOnClickListener(this);
        eventsLayout.setOnClickListener(this);
        eventsImageView.setOnClickListener(this);
        eventsTextView.setOnClickListener(this);

        infoCardView.setOnClickListener(this);
        infoLayout.setOnClickListener(this);
        infoImageView.setOnClickListener(this);
        infoTextView.setOnClickListener(this);

        concertCardView.setOnClickListener(this);
        concertLayout.setOnClickListener(this);
        concertImageView.setOnClickListener(this);
        concertTextView.setOnClickListener(this);

        campusCardView.setOnClickListener(this);
        campusLayout.setOnClickListener(this);
        campusImageView.setOnClickListener(this);
        campusTextView.setOnClickListener(this);

        campusCardView.setOnClickListener(this);
        campusLayout.setOnClickListener(this);
        campusImageView.setOnClickListener(this);
        campusTextView.setOnClickListener(this);

        chatCardView.setOnClickListener(this);
        chatLayout.setOnClickListener(this);
        chatImageView.setOnClickListener(this);
        chatTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId())
    {
        case R.id.profileCardView:
        case R.id.profileLinearLayout:
        case R.id.profileImageView:
        case R.id.profileTextView:
            getActivity().overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
            startActivity(new Intent(getActivity(),Profile.class));
            break;
        case R.id.campusCardView:
        case R.id.campusLayout:
        case R.id.campusImageView:
        case R.id.campusTextView:
//            getActivity().overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
//            startActivity(new Intent(getActivity(),TimeLineActivity.class));
            Toast.makeText(getContext(),"We're sorry. \n The schedule hasn't been finalised yet.",Toast.LENGTH_LONG).show();
            break;
        case R.id.eventsCardView:
        case R.id.eventsLayout:
        case R.id.eventsImageView:
        case R.id.eventsTextView:
            startActivity(new Intent(getActivity(), EventsActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            getActivity().overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
            break;
        case R.id.infoCardView:
        case R.id.infoLayout:
        case R.id.infoImageView:
        case R.id.infoTextView:
            startActivity(new Intent(getActivity(), InfoActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            getActivity().overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
            break;
        case R.id.concertCardView:
        case R.id.concertLayout:
        case R.id.concertImageView:
        case R.id.concertTextView:
            startActivity(new Intent(getActivity(), Tickets.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            getActivity().overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
            break;
        case R.id.chatCardView:
        case R.id.chatLayout:
        case R.id.chatImageView:
        case R.id.chatTextView:
            break;


    }
    }

}