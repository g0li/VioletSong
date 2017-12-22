package forevtechnologies.alegriauiux;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);
        this.setTheme(R.style.AppThemeDark);

        AboutBuilder builder = AboutBuilder.with(this)
                .setAppIcon(R.mipmap.ic_launcher_round)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.ic_launcher_round)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Alegria")
                .setSubTitle("The festival of joy.")
                .setLinksColumnsCount(4)
                .setBrief("The Pillai Group of Institutions exciting inter collegiate event, Alegria - The Festival of Joy, has easily become one of the largest collegiate festivals of India attracting a footfall of over 40,000 people every year.")
                .addGooglePlayStoreLink("")
                .addFacebookLink("128797447271799")
                .addTwitterLink("pillaisalegria")
                .addInstagramLink("pillaisalegria")
                .addYoutubeChannelLink("UCfqP3NEaaXPsgHn8QX3qilw")
                .addEmailLink("alegriathefest@gmail.com")
                .addWebsiteLink("www.alegria.co.in")
                .addLink(R.mipmap.github,"Roshan\nSingh","https://www.github.com/g0li")
                .addLink(R.mipmap.github,"Ziyad\nBhombal","https://www.github.com/bhombalziyad20")
                .addLink(R.mipmap.github,"Abhijay\nNair","https://www.github.com/AbhijayNair")
                .addFiveStarsAction()
                .addMoreFromMeAction("Pillai's Alegria")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("alegriathefest@gmail.com")
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();
        view.setPadding(0,0,0,0);

        ConstraintLayout ylyl=(ConstraintLayout)findViewById(R.id.ylyl);
        ylyl.addView(view);
    }
}
