package forevtechnologies.alegriauiux.adapter;

/**
 * Created by goli on 3/12/17.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
;

import java.util.List;

import com.cipherthinkers.shapeflyer.ShapeFlyer;
import com.intrusoft.library.FrissonView;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders.Any.B;

import forevtechnologies.alegriauiux.CenterLockListener;
import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.RecyclerViewClickListener;
import forevtechnologies.alegriauiux.TicketDialogClass;
import forevtechnologies.alegriauiux.UIHelper;

public class SwipeCardAdapter extends RecyclerView.Adapter<SwipeCardAdapter.MyViewHolder> {
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

    public SwipeCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.row_card_item, parent, false);
        int ROW_MARGIN = 200;
        view.setLayoutParams(new RecyclerView.LayoutParams(UIHelper.getScreenWidth(this.context) - ROW_MARGIN, UIHelper.getContainerHeight(this.context) - ROW_MARGIN));
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        FrissonView imageView = (FrissonView) view.findViewById(R.id.img_product);
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
        MyViewHolder holder = new SwipeCardAdapter.MyViewHolder(view);
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
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int center = UIHelper.getScreenWidth(SwipeCardAdapter.this.context) / 2;
                int padding = 0;
                recyclerView.setPadding(padding, 0, padding, 0);
                recyclerView.setOnScrollListener(new CenterLockListener(center));
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private forevtechnologies.alegriauiux.models.SwipeCardModel chosen;
        TextView txtProductName;
        TextView txtProductDesc;
        TextView txtPrice;
        FrissonView imgProduct;
        View myView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.imgProduct =(FrissonView) itemView.findViewById(R.id.img_product);
            this.txtProductName = (TextView)itemView.findViewById(R.id.txt_product_name);
            this.txtProductDesc = (TextView)itemView.findViewById(R.id.txt_product_desc);
            this.txtPrice = (TextView)itemView.findViewById(R.id.txt_price);
            this.myView=itemView;

        }

        public void setItem(forevtechnologies.alegriauiux.models.SwipeCardModel productItem) {
            this.chosen = productItem;
            this.txtProductName.setText(this.chosen.getTitle());
            this.txtProductDesc.setText(this.chosen.getDescription());
            this.txtPrice.setText(this.chosen.getPrice());

//            if(this.chosen.getPhotoUrl() != null && !this.chosen.getPhotoUrl().equals("")) {
//                ((B)Ion.with(SwipeCardAdapter.this.context).load(this.chosen.getPhotoUrl())).intoImageView(this.imgProduct);
//            }
            this.imgProduct.setImageSource(this.chosen.getPhotoUrl());
//            imgProduct.setBackgroundColor(Color.TRANSPARENT);

        }

        public void onClick(View view) {

            TicketDialogClass tdc = new TicketDialogClass(itemView.getContext());
            tdc.setArtistName(this.txtProductName.getText().toString());
            tdc.show();


        }
    }
}