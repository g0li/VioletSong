package forevtechnologies.alegriauiux.utl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

/**
 * Created by thisi on 26-05-2017.
 */

public class Util {

    public static final String URL_STORAGE_REFERENCE = "gs://alefirebase-b6a81.appspot.com";
    public static final String FOLDER_STORAGE_IMG = "images";
    private static final long FRAME_TIME = 10L;

    private Util() {}

    static void postOnAnimation(View view, Runnable action) {
        view.removeCallbacks(action);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.postOnAnimationDelayed(action, FRAME_TIME);
        } else {
            view.postDelayed(action, FRAME_TIME);
        }
    }
    public static void initToast(Context c, String message){
        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();
    }

    public  static boolean verifiyConnection(Context context) {
        boolean connect;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connect = conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected();
        return connect;
    }

    public static String local(String latitudeFinal, String longitudeFinal){
        return "https://maps.googleapis.com/maps/api/staticmap?center="+latitudeFinal+","+longitudeFinal+"&zoom=18&size=280x280&markers=color:red|"+latitudeFinal+","+longitudeFinal;
    }

}