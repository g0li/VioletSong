package forevtechnologies.alegriauiux;

/**
 * Created by goli on 3/12/17.
 */

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class UIHelper {
    public UIHelper() {
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        return height;
    }

    public static int getContainerHeight(Context context) {
        int statusBarHeight = (int)Math.ceil((double)(25.0F * context.getResources().getDisplayMetrics().density));
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(16843499, tv, true);
        int actionBarHeight = context.getResources().getDimensionPixelSize(tv.resourceId);
        int height = getScreenHeight(context) - actionBarHeight - statusBarHeight;
        return height;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }
}
