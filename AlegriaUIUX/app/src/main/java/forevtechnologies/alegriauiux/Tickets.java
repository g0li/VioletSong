package forevtechnologies.alegriauiux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.SwipeCardAdapter;
import forevtechnologies.alegriauiux.models.SwipeCardModel;

public class Tickets extends AppCompatActivity {
    RecyclerView recyclerView;
    List<SwipeCardModel> swipeCardModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tickets);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Log.w("DISPLAY","Working");
        swipeCardModels = new ArrayList<>();

        Log.w("DISPLAY","Working");

            findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        SwipeCardModel swipeCardModel1 = new SwipeCardModel();
        swipeCardModel1.setTitle("Dj Chetas X Nina & Malika");
        swipeCardModel1.setDescription("Bollywood Night");
        swipeCardModel1.setPrice("Buy");
        swipeCardModel1.setPhotoUrl(R.drawable.bollywood);
        swipeCardModels.add(swipeCardModel1);



        SwipeCardAdapter swipeCardAdapter = new SwipeCardAdapter(this, swipeCardModels, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                Toast.makeText(Tickets.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(swipeCardAdapter);

    }
}