package forevtechnologies.alegriauiux.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import forevtechnologies.alegriauiux.ContactDetails;
import forevtechnologies.alegriauiux.R;

/**
 * Created by thisi on 13-05-2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private List<ContactDetails> contactList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName,contactTeam,contactEvent,contactNumber;

        public MyViewHolder(View view) {
            super(view);
            contactName=(TextView)view.findViewById(R.id.contactName);
            contactTeam=(TextView)view.findViewById(R.id.teamName);
            contactEvent=(TextView)view.findViewById(R.id.eventName);
            contactNumber=(TextView)view.findViewById(R.id.contactNumber);
        }
    }
    public ContactAdapter(List<ContactDetails> contactList){
        this.contactList=contactList;
    }
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.MyViewHolder holder, int position) {
        ContactDetails contactDetails = contactList.get(position);
        holder.contactName.setText(contactDetails.getName());
        holder.contactTeam.setText(contactDetails.getTeam());
        holder.contactNumber.setText(contactDetails.getNumber());
        holder.contactEvent.setText(contactDetails.getEvent());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


}
