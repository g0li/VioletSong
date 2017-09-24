package forevtechnologies.alegriauiux.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.R;
import forevtechnologies.alegriauiux.adapter.DayAdapter;
import forevtechnologies.alegriauiux.models.AthleticModel;
import forevtechnologies.alegriauiux.models.CategoryCardModel;
import forevtechnologies.alegriauiux.models.Events;


public class FullInfoTabFragment extends Fragment {

    private static final String EXTRA_CATEGORY_CARD_MODEL = "EXTRA_CATEGORY_CARD_MODEL";
    //    String transitionTag;
    private CategoryCardModel categoryCardModel;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private RecyclerView rvAthletics;

    public static FullInfoTabFragment newInstance(CategoryCardModel categoryCardModel) {
        FullInfoTabFragment fragment = new FullInfoTabFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_CATEGORY_CARD_MODEL, categoryCardModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryCardModel = getArguments().getParcelable(EXTRA_CATEGORY_CARD_MODEL);
        }
        if (savedInstanceState != null) {
            categoryCardModel = savedInstanceState.getParcelable(EXTRA_CATEGORY_CARD_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_info, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        rvAthletics = (RecyclerView) view.findViewById(R.id.rvAthletics);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle(categoryCardModel.getCategoryTitle());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), categoryCardModel.getBackgroundColorResId()));
        ivPhoto.setImageResource(categoryCardModel.getImageResId());
        List<AthleticModel> items = new ArrayList<>();
        switch(categoryCardModel.getCategoryTitle())
        {
            case"Informal":
                for (int ix=24;ix<=31;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Performing Arts":
                for (int ix=13;ix<=23;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Literary Arts":
                for (int ix=88;ix<=92;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Fine Arts":
                for (int ix=70;ix<=78;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Management":
                for (int ix=79;ix<=87;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Sports & Gaming":
                for (int ix=48;ix<=69;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Technical Events":
                for (int ix=0;ix<=12;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
            case"Workshops":
                for (int ix=33;ix<=47;ix++) {
                    items.add(new AthleticModel("Pillai Campus Panvel",Events.values()[ix], 23));
                }
                break;
        }


        DayAdapter dayAdapter = new DayAdapter();
        dayAdapter.addItems(items);

        rvAthletics.setAdapter(dayAdapter);
        rvAthletics.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvAthletics.setItemAnimator(new DefaultItemAnimator());
        rvAthletics.addItemDecoration(new DividerItemDecoration(getContext()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_CATEGORY_CARD_MODEL, categoryCardModel);
        super.onSaveInstanceState(outState);
    }

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable mDivider;

        /**
         * Default divider will be used
         */
        public DividerItemDecoration(Context context) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            mDivider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        }

        /**
         * Custom divider will be used
         */
        public DividerItemDecoration(Context context, int resId) {
            mDivider = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
