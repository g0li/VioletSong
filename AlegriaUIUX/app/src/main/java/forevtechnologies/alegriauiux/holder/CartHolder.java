package forevtechnologies.alegriauiux.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.SwipeableUltimateRecyclerview;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import forevtechnologies.alegriauiux.R;

/**
 * Created by jojosexbomb69 on 5/12/17.
 */

public class CartHolder extends UltimateRecyclerviewViewHolder {
    public TextView cName,cPrice;
    public CartHolder(View itemView) {
        super(itemView);
        cName=(TextView)itemView.findViewById(R.id.cartItemName);
        cPrice=(TextView)itemView.findViewById(R.id.cartItemPrice);

    }

}
