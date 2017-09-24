package forevtechnologies.alegriauiux;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {
    private ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    TextView day,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        day=(TextView)findViewById(R.id.day);
        date=(TextView)findViewById(R.id.date);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);

            }
        });
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                    day();
            }
        });
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle b=new Bundle();
            switch (position)
            {
                case 0:
                    return new DayOne();
                case 1:
                    date.setText("");
                    day.setText("");
                    date.setText("06 AUG");
                    day.setText("DAY 02");
                    return new DayOne();
                case 2:
                    date.setText("");
                    day.setText("");
                    date.setText("07 AUG");
                    day.setText("DAY 03");
                    return new DayOne();
                case 3:
                    date.setText("");
                    day.setText("");
                    date.setText("08 AUG");
                    day.setText("DAY 04");
                    return new DayOne();
                case 4:
                    date.setText("");
                    day.setText("");
                    date.setText("09 AUG");
                    day.setText("DAY 05");
                    return new DayOne();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }
void day()
{
    switch(mViewPager.getCurrentItem())
    {
        case 0:
            date.setText("");
            day.setText("");
            date.setText("05 AUG");
            day.setText("DAY 01");
            break;

        case 1:
            date.setText("");
            day.setText("");
            date.setText("06 AUG");
            day.setText("DAY 02");
            break;

        case 2:
            date.setText("");
            day.setText("");
            date.setText("07 AUG");
            day.setText("DAY 03");
            break;

        case 3:
            date.setText("");
            day.setText("");
            date.setText("08 AUG");
            day.setText("DAY 04");
            break;

        case 4:
            date.setText("");
            day.setText("");
            date.setText("09 AUG");
            day.setText("DAY 05");
            break;

    }
}

}

