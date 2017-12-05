package forevtechnologies.alegriauiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import forevtechnologies.alegriauiux.CartActivity;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.fragments.FullInfoTabFragment;
import forevtechnologies.alegriauiux.models.AthleticModel;



public class DayAdapter extends RecyclerView.Adapter<DayAdapter.AthleticHolder> {
    private final List<AthleticModel> mItems = new ArrayList<>();

    public ArrayList<String> getEventsDataList() {
        return eventsDataList;
    }

    final ArrayList <String> eventsDataList= new ArrayList<String>();
    SharedPreferences.Editor editor;

    private Bundle b=new Bundle();
    int BUNDLE_SIZE=0;
    public static     Context context;

    public void addItems(@NonNull Collection<AthleticModel> items) {
        mItems.addAll(items);
        notifyItemRangeInserted(mItems.size() - items.size() - 1, items.size());
    }
    public DayAdapter(Context c)
    {
        context=c;
        editor=c.getSharedPreferences(c.getString(R.string.shared_preference_cart),Context.MODE_PRIVATE).edit();
    }


    @Override
    public AthleticHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);

        return new AthleticHolder(view);
    }

    @Override
    public void onBindViewHolder(AthleticHolder holder, int position) {
        AthleticModel item = mItems.get(position);
        holder.tvAthleticName.setText(item.getLocation());
        holder.tvCountry.setText(item.getEvents().getEvents());
        holder.tvScore.setText(String.valueOf(item.getScore()));
         if(0<=position && position>=13)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_tech);
        }else if(14<=position && position>=25)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_pa);
        }else if(26<=position && position>=33)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_inf);
        }else if(34<=position && position>=48)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_ws);
        }else if(49<=position && position>=70)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_sng);
        }else if(71<=position && position>=79)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_la);
        }else if(80<=position && position>=86)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_mgmt);
        }else if(87<=position && position>=93)
        {
            holder.ivAthleticFlag.setImageResource(R.drawable.ic_fa);
        }else
         {
             holder.ivAthleticFlag.setImageResource(R.drawable.logo);
         }

        }



    public Bundle getBndl() {
        //b.putStringArrayList("EventName",eventsDataList);
        //tryLog.w("Event bundle",""+eventsDataList.get(0));
        return b;
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class AthleticHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAthleticFlag;
        TextView tvCountry;
        TextView tvAthleticName;
        TextView tvScore;
        int i=0;


        AthleticHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            ivAthleticFlag = (ImageView) itemView.findViewById(R.id.ivAthleticFlag);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
            tvAthleticName = (TextView) itemView.findViewById(R.id.tvAthleticName);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
        }

        @Override
        public void onClick(View v) {
           editor.putString("Key "+i,tvCountry.getText().toString()).commit();
           i++;
            Log.w("event",""+tvCountry.getText().toString());
            Snackbar snackbar = Snackbar
                    .make(v, "Event added", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            eventsDataList.remove(tvCountry.getText().toString());
                        }
                    });
            snackbar.show();
            new FullInfoTabFragment().initializeIntent(new Intent(v.getContext(),CartActivity.class));
        }


    }

    private void pushBndl(ArrayList<String> eventsDataList) {
        b.putStringArrayList("List",eventsDataList);
    }

}
