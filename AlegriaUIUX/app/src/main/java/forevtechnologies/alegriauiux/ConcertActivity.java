package forevtechnologies.alegriauiux;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import forevtechnologies.alegriauiux.pager.GlazyFragmentPagerAdapter;
import forevtechnologies.alegriauiux.pager.GlazyViewPager;
import forevtechnologies.alegriauiux.transformers.GlazyPagerTransformer;
import forevtechnologies.alegriauiux.views.GlazyImageView;

public class ConcertActivity extends AppCompatActivity {
    private GlazyViewPager mPager;
    private GlazyFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_concert);
        setTitle("Concerts");

        mPager = (GlazyViewPager) findViewById(R.id.pager);
        mPagerAdapter = new GlazyFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        String desc_nucleya= "One of the early proponents of dubstep in India, Udyan Sagar aka Nucleya has emerged as one of the most exciting, experimental and engaging music producers in the Indian scene in five short years. " +
                "He made his mark by focusing on sounds and textures that are Indian to the ear but international in appeal." +
                "From opening for Skrillex and Major Lazer to playing a short set at a small pub in the posh parts of Delhi and playing on the main stage at NH7 Weekender - the growth of Nucleya as a brand and the overwhelming " +
                "popularity it has received is extraordinary." +
                " From playing organiser-promoter-DJ at gigs in farmhouses in the outskirts of Ahmedabad to performing to crowds of 50,000+ people at music festivals, the bomb that is Nucleya has clearly detonated." +
                "This year we will have this dynamic artist making the crowds groove to his tunes playing at Alegria 2017.";

        String desc_loststories="Lost stories is a DJ duo consisting of Prayag Mehta and Rishab Joshi best known for their official remixes for Alan Walker \"Faded\" and One Republic \"Wherever I Go\". " +
                "Their incredible journey has given new-age music a contemporary and quirky twist. " +
                "They started off and rose to prominence when their single \"False Promises\" was released on Tiësto's Black Hole Recordings. " +
                "This music sensation with a massive fan following and great potential, are making India proud globally. " +
                "Mumbai Mirror in their article published on 29th August 2012 stated “Their edgy music has groovy basslines, is melodic, energetic yet moody outros and has dreamy breakdowns with a hint of tech elements are their signature “progressive trance” production sound." +
                "If one listens minutely, one will also notice a subtle touch of Indian classical; adding uniqueness to the complete experience.”" +
                "They have performed at multiple music festivals such as Tomorrowland, Mysteryland Marenostrum, Sunburn, Supersonic and many more." +
                "They've been ranked #52 on DJ Mag Top 100 2016 and in all their glory are geared up to set the city on fire with their upcoming performance at Pillai's Alegria 2017.\n";

        String desc_quintino="The Dutch DJ and music producer was discovered by music mastermind Laidback Luke at a young age." +
                "Quintino’s precision and unrivalled ability behind the turntables has made him one of the leading forces in today’s global music scene." +
                "Quintino who was ranked 86 in the top 100   DJ's of 2014 by DJ mag has taken a huge leap from 86th to the 32nd position in 2016." +
                "He has had collaborations with world famous DJs like DJ Laidback Luke and DJ Tiesta, Alvaro and Hardwell." +
                "Today he has around 200 international gigs a year and is a headliner of some of the biggest music festivals around the world." +
                "He has been remixing for the likes of Galantis, Major Lazer and more." +
                "Known for his energetic stage presence Quintino is all set to rock it at Alegria 2017.\n";

        String desc_terimiko="Teri Miko is a leading international DJ, producer & remixer of Ukrainian descent." +
                "Known for her highly energetic DJ sets, she did her first headline act at the age of 20 years." +
                "She has been one of the leading torchbearers of trap, a genre filled with a hard attitude that can be felt in the sound of the brass, triangle, triplet hi hats, loud kicks and snappy snares." +
                "Consisting of hard hitting trap beats infused with electronic sounds, her every onstage performance is a testament to her passion." +
                "Miko’s weekly radio show, LED Sessions is broadcast in more than 35 countries in North America, Europe and Asia." +
                "Her tour diary continues to be full with performances all around the globe, spinning at world’s most exclusive VIP nightclubs, festivals like VH1 Supersonic, SunBurn and playing for the finest dance music brands." +
                "Her next stop is our very own Alegria-The festival of joy!\n" ;

        mPagerAdapter.addCardItem(
                new GlazyCard()
                        .withTitle("NUCLEYA")
                        .withSubTitle("DJ/PRODUCER")
                        .withDescription(desc_nucleya.toUpperCase())
                        .withImageRes(R.drawable.slider_nucleya)
                        .withImageCutType(GlazyImageView.ImageCutType.WAVE)
                        .withImageCutHeightDP(40)
        );

        mPagerAdapter.addCardItem(
                new GlazyCard()
                        .withTitle("LOST STORIES")
                        .withSubTitle("DJ DUO/PRODUCER")
                        .withDescription(desc_loststories.toUpperCase())
                        .withImageRes(R.drawable.slider_loststories)
                        .withImageCutType(GlazyImageView.ImageCutType.WAVE)
                        .withImageCutHeightDP(40)
        );

        mPagerAdapter.addCardItem(
                new GlazyCard()
                        .withTitle("QUINTINO")
                        .withSubTitle("PRODUCER")
                        .withDescription(desc_quintino.toUpperCase())
                        .withImageRes(R.drawable.slider_quintino)
                        .withImageCutType(GlazyImageView.ImageCutType.WAVE)
                        .withImageCutHeightDP(40)
        );

        mPagerAdapter.addCardItem(
                new GlazyCard()
                        .withTitle("TERI MIKO")
                        .withSubTitle("DJ/PRODUCER")
                        .withDescription(desc_terimiko.toUpperCase())
                        .withImageRes(R.drawable.slider_terimiko)
                        .withImageCutType(GlazyImageView.ImageCutType.WAVE)
                        .withImageCutHeightDP(40)
        );
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageMargin(Utils.dpToPx(getApplicationContext(), 25));
        mPager.setPageTransformer(false, new GlazyPagerTransformer());
    }
}
