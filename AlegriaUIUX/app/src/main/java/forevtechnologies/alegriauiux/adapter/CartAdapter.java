    package forevtechnologies.alegriauiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public final List<CartModel> cartItem = new ArrayList<>();
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
    public void onBindViewHolder(CartHolder holder, final int position) {
    CartModel model=cartItem.get(position);
    holder.cName.setText(model.getName());
    holder.cPrice.setText(String.valueOf(PriceMapper.getPrice(model.getName())));

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