package forevtechnologies.alegriauiux;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Franav on 12/21/2017.
 */

public class ExitApplication {
    public static void Exit(Context c){
        Intent intent= new Intent(c,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT",true);
        c.startActivity(intent);
    }
}
