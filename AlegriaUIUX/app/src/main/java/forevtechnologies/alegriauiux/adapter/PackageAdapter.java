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


//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final PackageItem item = getItem(position);
//        ViewHolder holder;
//        if (convertView == null) {
//            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = li.inflate(R.layout.package_row, parent, false);
//            holder = new ViewHolder();
//            holder.ivImage = (ImageView) convertView.findViewById(R.id.example_row_iv_image);
//            holder.tvTitle = (TextView) convertView.findViewById(R.id.example_row_tv_title);
//            holder.tvDescription = (TextView) convertView.findViewById(R.id.example_row_tv_description);
//            holder.bAction1 = (Button) convertView.findViewById(R.id.example_row_b_action_1);
//            holder.bAction2 = (Button) convertView.findViewById(R.id.example_row_b_action_2);
//            holder.bAction3 = (Button) convertView.findViewById(R.id.example_row_b_action_3);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        ((SwipeListView)parent).recycle(convertView, position);
//
//        holder.ivImage.setImageDrawable(item.getIcon());
//        holder.tvTitle.setText(item.getName());
//        holder.tvDescription.setText(item.getPackageName());
//
//
//        holder.bAction1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = context.getPackageManager().getLaunchIntentForPackage(item.getPackageName());
//                if (intent != null) {
//                    context.startActivity(intent);
//                } else {
//                    Toast.makeText(context, R.string.cantOpen, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        holder.bAction2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPlayStoreInstalled()) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("market://details?id=" + item.getPackageName())));
//                } else {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("http://play.google.com/store/apps/details?id=" + item.getPackageName())));
//                }
//            }
//        });
//
//        holder.bAction3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri packageUri = Uri.parse("package:" + item.getPackageName());
//                Intent uninstallIntent;
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                    uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
//                } else {
//                    uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
//                }
//                context.startActivity(uninstallIntent);
//            }
//        });
//
//
//        return convertView;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView uName,uPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            uName=(TextView)itemView.findViewById(R.id.cartItemName);
            uPrice=(TextView)itemView.findViewById(R.id.cartItemPrice);


        }
    }


}