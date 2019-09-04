package ql.cev.ql7;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ql.cev.ql7.Fragments.AboutFragment;
import ql.cev.ql7.Fragments.ContactFragment;
import ql.cev.ql7.Fragments.HomeFragment;
import ql.cev.ql7.Fragments.SponsorsFragment;

public class MainActivity extends AppCompatActivity {

   // private TextView mTextMessage;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       HomeFragment homeFragment=new HomeFragment();
       FragmentManager manager=getFragmentManager();
       manager.beginTransaction().replace(R.id.frame_layout,homeFragment,homeFragment.getTag()).commit();

       // mTextMessage = (TextView) findViewById(R.id.message);
       BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

   }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    //  mTextMessage.setText(R.string.title_home);
                    HomeFragment homeFragment=new HomeFragment();
                    FragmentManager manager=getFragmentManager();
                    manager.beginTransaction().replace(R.id.frame_layout,homeFragment,homeFragment.getTag()).commit();
                    return true;
                case R.id.sponsor:
                    SponsorsFragment sponsorsFragment=new SponsorsFragment();
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.frame_layout,sponsorsFragment,sponsorsFragment.getTag()).commit();
                    return true;
                case R.id.contact:
                    ContactFragment fragmentContact=new ContactFragment();
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.frame_layout,fragmentContact,fragmentContact.getTag()).commit();
                    return true;
                case R.id.about:
                    AboutFragment fragmentAbout=new AboutFragment();
                    manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.frame_layout,fragmentAbout,fragmentAbout.getTag()).commit();
                    return true;
            }
            return false;
        }
    };


}
