package forevtechnologies.alegriauiux;

/**
 * Created by goli on 3/12/17.
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;

public class CenterLockListener extends OnScrollListener {
    private boolean mAutoSet = true;
    private int mCenterPivot;

    public CenterLockListener(int center) {
        this.mCenterPivot = center;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager lm = (LinearLayoutManager)recyclerView.getLayoutManager();
        if(this.mCenterPivot == 0) {
            this.mCenterPivot = lm.getOrientation() == 0?recyclerView.getLeft() + recyclerView.getRight():recyclerView.getTop() + recyclerView.getBottom();
        }

        if(!this.mAutoSet && newState == 0) {
            View view = this.findCenterView(lm);
            int viewCenter = lm.getOrientation() == 0?(view.getLeft() + view.getRight()) / 2:(view.getTop() + view.getBottom()) / 2;
            int scrollNeeded = viewCenter - this.mCenterPivot;
            if(lm.getOrientation() == 0) {
                recyclerView.smoothScrollBy(scrollNeeded, 0);
            } else {
                recyclerView.smoothScrollBy(0, scrollNeeded);
            }

            this.mAutoSet = true;
        }

        if(newState == 1 || newState == 2) {
            this.mAutoSet = false;
        }

    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    private View findCenterView(LinearLayoutManager lm) {
        int minDistance = 0;
        View view = null;
        View returnView = null;
        boolean notFound = true;

        for(int i = lm.findFirstVisibleItemPosition(); i <= lm.findLastVisibleItemPosition() && notFound; ++i) {
            view = lm.findViewByPosition(i);
            int center = lm.getOrientation() == 0?(view.getLeft() + view.getRight()) / 2:(view.getTop() + view.getBottom()) / 2;
            int leastDifference = Math.abs(this.mCenterPivot - center);
            if(leastDifference > minDistance && i != lm.findFirstVisibleItemPosition()) {
                notFound = false;
            } else {
                minDistance = leastDifference;
                returnView = view;
            }
        }

        return returnView;
    }
}
