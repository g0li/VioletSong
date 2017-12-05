package forevtechnologies.alegriauiux;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.num.numandroidpagecurleffect.PageCurlView;

/**
 * Created by jojosexbomb69 on 5/12/17.
 */

public class PageCurl extends AppCompatActivity {
    PageCurlView pageCurlView;
    List<Integer> page_id=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_curl);
        pageCurlView=findViewById(R.id.page_curl);
        page_id.add(R.drawable.page1);
        page_id.add(R.drawable.page2);
        page_id.add(R.drawable.page3);
        page_id.add(R.drawable.page4);
        page_id.add(R.drawable.page5);
        pageCurlView.setCurlView(page_id);
        pageCurlView.setCurlSpeed(65);

    }

}
