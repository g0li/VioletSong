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




//        for (int i=0;i<=EventsPaisa.values().length;i++)
//        {
//            while (i<=13)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("50");
//                }
//            }
//            while (i<=38 && i>13)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("100");
//                }
//            }
//            while (i<=54 && i>38)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("150");
//                }
//            }
//            while (i<=66&& i> 54)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("200");
//                }
//            }
//            while (i<=70 && i>66)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("250");
//                }
//            }
//            while (i<=78& i>70)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("300");
//                }
//            }
//            while (i==79)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("350");
//                }
//            }
//            while (i==80||i==81)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("500");
//                }
//            }
//            while (i==82)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("600");
//                }
//            }
//            while (i<=85&& i >82)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("1000");
//                }
//            }
//            while (i==86)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("2500");
//                }
//            }
//            while (i==87)
//            {
//                if(EventsPaisa.values()[i].equals(cartItem.get(position)))
//                {
//                    holder.cPrice.setText("FREE");
//                }
//            }
//
//        }

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