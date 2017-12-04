package forevtechnologies.alegriauiux.adapter;

/**
 * Created by goli on 3/12/17.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
;

import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders.Any.B;
import java.util.List;

import forevtechnologies.alegriauiux.CenterLockListener;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.RecyclerViewClickListener;
<<<<<<< HEAD
import forevtechnologies.alegriauiux.UIHelper;

public class SwipeCardAdapter extends RecyclerView.Adapter<SwipeCardAdapter.MyViewHolder> {
=======
import forevtechnologies.alegriauiux.TicketDialogClass;
import forevtechnologies.alegriauiux.Tickets;
import forevtechnologies.alegriauiux.UIHelper;

import static forevtechnologies.alegriauiux.UIHelper.getScreenWidth;

public class SwipeCardAdapter extends Adapter<SwipeCardAdapter.MyViewHolder> {
>>>>>>> master
    private LayoutInflater inflater;
    private List<forevtechnologies.alegriauiux.models.SwipeCardModel> data;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    public SwipeCardAdapter(Context context, List<forevtechnologies.alegriauiux.models.SwipeCardModel> data, RecyclerViewClickListener mitemListener) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.itemListener = mitemListener;
    }

    public int getItemCount() {
        return this.data.size();
    }

<<<<<<< HEAD
    public SwipeCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.row_card_item, parent, false);
        int ROW_MARGIN = 200;
        view.setLayoutParams(new RecyclerView.LayoutParams(UIHelper.getScreenWidth(this.context) - ROW_MARGIN, UIHelper.getContainerHeight(this.context) - ROW_MARGIN));
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
=======
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.row_card_item, parent, false);
        int ROW_MARGIN = 200;
        view.setLayoutParams(new LayoutParams(getScreenWidth(this.context) - ROW_MARGIN, UIHelper.getContainerHeight(this.context) - ROW_MARGIN));
        MarginLayoutParams marginParams = new MarginLayoutParams(view.getLayoutParams());
>>>>>>> master
        ImageView imageView = (ImageView)view.findViewById(R.id.img_product);
        imageView.getLayoutParams().height = UIHelper.getContainerHeight(this.context) / 2;
        if(viewType == 0) {
            marginParams.setMargins(ROW_MARGIN / 4, 0, 0, 0);
        } else if(viewType == 1) {
            marginParams.setMargins(ROW_MARGIN / 4, 0, ROW_MARGIN / 4, 0);
        } else {
            marginParams.setMargins(ROW_MARGIN / 4, 0, 0, 0);
        }

        android.widget.RelativeLayout.LayoutParams layoutParams = new android.widget.RelativeLayout.LayoutParams(marginParams);
        view.setLayoutParams(layoutParams);
<<<<<<< HEAD
        MyViewHolder holder = new SwipeCardAdapter.MyViewHolder(view);
=======
        MyViewHolder holder = new MyViewHolder(view);
>>>>>>> master
        return holder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        forevtechnologies.alegriauiux.models.SwipeCardModel current = (forevtechnologies.alegriauiux.models.SwipeCardModel)this.data.get(position);
        holder.setItem(current);
    }

    public int getItemViewType(int position) {
        return position == 0?0:(position + 1 == this.data.size()?1:2);
    }

    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
<<<<<<< HEAD
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int center = UIHelper.getScreenWidth(SwipeCardAdapter.this.context) / 2;
=======
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int center = getScreenWidth(SwipeCardAdapter.this.context) / 2;
>>>>>>> master
                int padding = 0;
                recyclerView.setPadding(padding, 0, padding, 0);
                recyclerView.setOnScrollListener(new CenterLockListener(center));
            }
        });
    }

<<<<<<< HEAD
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
=======
    class MyViewHolder extends ViewHolder implements OnClickListener {
>>>>>>> master
        private forevtechnologies.alegriauiux.models.SwipeCardModel chosen;
        TextView txtProductName;
        TextView txtProductDesc;
        TextView txtPrice;
        ImageView imgProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.imgProduct = (ImageView)itemView.findViewById(R.id.img_product);
            this.txtProductName = (TextView)itemView.findViewById(R.id.txt_product_name);
            this.txtProductDesc = (TextView)itemView.findViewById(R.id.txt_product_desc);
            this.txtPrice = (TextView)itemView.findViewById(R.id.txt_price);
        }

        public void setItem(forevtechnologies.alegriauiux.models.SwipeCardModel productItem) {
            this.chosen = productItem;
            this.txtProductName.setText(this.chosen.getTitle());
            this.txtProductDesc.setText(this.chosen.getDescription());
            this.txtPrice.setText(this.chosen.getPrice());
            if(this.chosen.getPhotoUrl() != null && !this.chosen.getPhotoUrl().equals("")) {
                ((B)Ion.with(SwipeCardAdapter.this.context).load(this.chosen.getPhotoUrl())).intoImageView(this.imgProduct);
            }

        }

        public void onClick(View view) {
<<<<<<< HEAD
            Toast.makeText(context, "Clicked "+ getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
=======

                TicketDialogClass tdc = new TicketDialogClass(itemView.getContext());
                tdc.show();


            }
        }
}
>>>>>>> master
