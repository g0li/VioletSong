package forevtechnologies.alegriauiux;

/**
 * Created by thisi on 13-05-2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import forevtechnologies.alegriauiux.adapter.ContactAdapter;

public class ContactsActivity extends AppCompatActivity {
    private List<ContactDetails> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rcv);

        cAdapter = new ContactAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

        prepareContactData();
    }

    private void prepareContactData() {
        ContactDetails contact;
        contact =new ContactDetails("Roshan Singh","Gaming","+918169777677","Developer");
        contactList.add(contact);

        contact =new ContactDetails("Shrey Jhakmola","Hackathon","+919762065479","Developer");
        contactList.add(contact);

        contact =new ContactDetails("Akash Dobhal","Dota 2 1vs1","+91XXXXXXXXXX","Developer");
        contactList.add(contact);

        contact =new ContactDetails("Arif Ahmed","Text to Glory","+917578898960","Graphics");
        contactList.add(contact);

        contact =new ContactDetails("Shubhang Rajput","L1+L2","+919699149429","Graphics");
        contactList.add(contact);

        cAdapter.notifyDataSetChanged();

    }
}