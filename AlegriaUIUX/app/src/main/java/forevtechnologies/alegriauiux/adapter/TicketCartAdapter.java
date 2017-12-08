package forevtechnologies.alegriauiux.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.models.TicketCartModel;


public class TicketCartAdapter extends RecyclerView.Adapter<TicketCartAdapter.TicketCartHolder> {
    public final List<TicketCartModel> cartItem = new ArrayList<>();
    private Bundle bundle;
    public static Context context;


    public TicketCartAdapter(Context c) {

        context = c;

    }

    public void addItems(@NonNull Collection<TicketCartModel> items) {
        cartItem.addAll(items);
        notifyItemRangeInserted(cartItem.size() - items.size() - 1, items.size());
    }

    @Override
    public TicketCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new TicketCartHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketCartHolder holder, final int position) {
        TicketCartModel model=cartItem.get(position);
        holder.cName.setText(model.getName());
        holder.cPrice.setText(String.valueOf(model.getPrice()));
    }




    @Override
    public int getItemCount() {
        return cartItem.size();
    }



    class TicketCartHolder extends RecyclerView.ViewHolder {
        TextView cName,cPrice;
        public TicketCartHolder(View itemView) {
            super(itemView);
            cName=(TextView)itemView.findViewById(R.id.cartItemName);
            cPrice=(TextView)itemView.findViewById(R.id.cartItemPrice);

        }

    }
}