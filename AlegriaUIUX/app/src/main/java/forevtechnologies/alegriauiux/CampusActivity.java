package forevtechnologies.alegriauiux;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

public class CampusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        findViewById(R.id.apnegullykamap).setOnTouchListener(new ImageMatrixTouchHandler(getBaseContext()));
    }
}
