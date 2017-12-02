package forevtechnologies.alegriauiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.PriceMapper;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private final List<CartModel> cartItem = new ArrayList<>();
    private Bundle bundle;
    public static Context context;


    public CartAdapter(Context c) {

        context = c;

    }

    public void addItems(@NonNull Collection<CartModel> items) {
        cartItem.addAll(items);
        notifyItemRangeInserted(cartItem.size() - items.size() - 1, items.size());
    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {
    CartModel model=cartItem.get(position);
    holder.cName.setText(model.getName());
    holder.cPrice.setText(String.valueOf(PriceMapper.getPrice(model.getName())));




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



class CartHolder extends RecyclerView.ViewHolder {
    TextView cName,cPrice;
    public CartHolder(View itemView) {
        super(itemView);
        cName=(TextView)itemView.findViewById(R.id.cartItemName);
        cPrice=(TextView)itemView.findViewById(R.id.cartItemPrice);

    }

}
}