package forevtechnologies.alegriauiux.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.models.AthleticModel;


public class DayAdapter extends RecyclerView.Adapter<DayAdapter.AthleticHolder> {
    private final List<AthleticModel> mItems = new ArrayList<>();
    private Bundle b=new Bundle();
    int BUNDLE_SIZE=0;

    public void addItems(@NonNull Collection<AthleticModel> items) {
        mItems.addAll(items);
        notifyItemRangeInserted(mItems.size() - items.size() - 1, items.size());
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
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



    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class AthleticHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAthleticFlag;
        TextView tvCountry;
        TextView tvAthleticName;
        TextView tvScore;


        AthleticHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivAthleticFlag = (ImageView) itemView.findViewById(R.id.ivAthleticFlag);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
            tvAthleticName = (TextView) itemView.findViewById(R.id.tvAthleticName);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
        }
        public Bundle getBndl() {
            return b;
        }

        @Override
        public void onClick(View v) {
            ArrayList <String> eventsDataList= new ArrayList<String>();
            eventsDataList.add(tvCountry.getText().toString());
            PushData(eventsDataList);        }
    }

    protected void PushData( ArrayList <String> datalist)
    {
        for(int i=0;i<datalist.size();i++)
        {
            b.putString("Key "+i,datalist.get(i));
            BUNDLE_SIZE=i+1;
        }

    }
}
