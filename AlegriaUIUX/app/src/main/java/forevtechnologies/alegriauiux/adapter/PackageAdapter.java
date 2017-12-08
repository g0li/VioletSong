package forevtechnologies.alegriauiux.adapter;

/**
 * Created by goli on 5/12/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.PriceMapper;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.models.CartModel;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {

    private List<CartModel> cartItem = new ArrayList<>();
    private Context context;

    public PackageAdapter(Context context, List<CartModel> data) {
        this.context = context;
        this.cartItem = data;
    }


    public String getItem(int position) {
        return String.valueOf(cartItem.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CartModel cartModel= cartItem.get(i);
        viewHolder.uName.setText(cartModel.getName());
        viewHolder.uName.setText(String.valueOf(PriceMapper.getPrice(cartModel.getName())));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cartItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView uName,uPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            uName=(TextView)itemView.findViewById(R.id.cartItemName);
            uPrice=(TextView)itemView.findViewById(R.id.cartItemPrice);


        }
    }


}