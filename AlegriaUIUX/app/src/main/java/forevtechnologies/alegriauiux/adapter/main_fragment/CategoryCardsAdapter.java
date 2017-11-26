package forevtechnologies.alegriauiux.adapter.main_fragment;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
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
import forevtechnologies.alegriauiux.models.CategoryCardModel;

class CategoryCardsAdapter extends RecyclerView.Adapter<CategoryCardsAdapter.CategoryCardViewHolder> {
    private final List<CategoryCardModel> mItems = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    CategoryCardsAdapter(Context context) {
        this.mContext = context;
    }

    public boolean add(CategoryCardModel item) {
        boolean isAdded = mItems.add(item);
        if (isAdded) {
            notifyDataSetChanged();
        }
        return isAdded;
    }

    boolean addAll(Collection<CategoryCardModel> items) {
        boolean isAdded = mItems.addAll(items);
        if (isAdded) {
            notifyDataSetChanged();
        }
        return isAdded;
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public CategoryCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CategoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryCardViewHolder holder, int position) {
        CategoryCardModel item = mItems.get(position);
        holder.tvCategoryTitle.setText(item.getCategoryTitle());
        holder.tvCategorySubtitle.setText(item.getCategorySubtitle());
        holder.tvCategoryRound.setText(item.getCategoryRound());
        holder.ivCategoryPreview.setImageResource(item.getImageResId());
        holder.tvTime.setText(item.getTime());
        holder.tvDayPart.setText(item.getDayPart());

        ((CardView) holder.itemView).setCardBackgroundColor(ContextCompat.getColor(mContext, item.getBackgroundColorResId()));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.ivCategoryPreview.setTransitionName("shared" + String.valueOf(position));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicked(holder.getAdapterPosition(), holder.ivCategoryPreview);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    CategoryCardModel getModelByPos(int pos) {
        return mItems.get(pos);

    }

    interface OnItemClickListener {
        void onItemClicked(int pos, View view);
    }

    class CategoryCardViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCategoryTitle;
        final TextView tvCategorySubtitle;
        final TextView tvCategoryRound;
        final TextView tvTime;
        final TextView tvDayPart;
        ImageView ivCategoryPreview;

        CategoryCardViewHolder(View itemView) {
            super(itemView);
            tvCategoryTitle = (TextView) itemView.findViewById(R.id.tvCategoryTitle);
            tvCategorySubtitle = (TextView) itemView.findViewById(R.id.tvCategorySubtitle);
            tvCategoryRound = (TextView) itemView.findViewById(R.id.tvCategoryRound);
            ivCategoryPreview = (ImageView) itemView.findViewById(R.id.ivCategoryPreview);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDayPart = (TextView) itemView.findViewById(R.id.tvDayPart);
        }
    }
}
