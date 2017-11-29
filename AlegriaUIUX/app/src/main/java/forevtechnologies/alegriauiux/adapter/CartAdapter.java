package forevtechnologies.alegriauiux.adapter;

import android.content.Context;
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
import forevtechnologies.alegriauiux.models.Events;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private final List<AthleticModel> cartItem = new ArrayList<>();
    private Bundle b = new Bundle();
    int BUNDLE_SIZE = 0;
    public static Context context;


    public CartAdapter(Context c) {
        context = c;
    }


    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {
    AthleticModel model=cartItem.get(position);

    switch (model.getEvents().getEvents())
    {
    case Events.
    }
    holder.cName.setText();

    }



    public Bundle getBndl() {
        return b;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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