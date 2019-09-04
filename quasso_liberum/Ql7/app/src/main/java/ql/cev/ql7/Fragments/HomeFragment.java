package ql.cev.ql7.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import ql.cev.ql7.CevCornerActivity;
import ql.cev.ql7.CompetitionActivity;
import ql.cev.ql7.CostumGridAdapter;
import ql.cev.ql7.ProshowActivity;
import ql.cev.ql7.R;
import ql.cev.ql7.ResultActivity;
import ql.cev.ql7.ScheduleActivity;
import ql.cev.ql7.WorkshopActivity;



public class HomeFragment extends Fragment {
    private ImageView imageView;
    private GridView grid;
    private TextView counDown;
    private String[] web = {
            "COMPETITIONS",
            "WORKSHOPS",
            "PROSHOWS",
            "SCHEDULE",
            "CEV CORNER",
            "RESULTS"


    } ;
    private int[] imageId = {
            R.drawable.competition,
            R.drawable.workshop,
            R.drawable.proshow,
            R.drawable.schedule,
            R.drawable.cevcorner,
            R.drawable.result
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
       // CostumGridAdapter adapter = new CostumGridAdapter(getActivity(), web, imageId);
     grid=(GridView)view.findViewById(R.id.grid);
     imageView=(ImageView)view.findViewById(R.id.ql);
     counDown=(TextView)view.findViewById(R.id.counter);
        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();
        end_calendar.set(2018, 1, 22);
        long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
        long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
        long total_millis = (end_millis - start_millis); //total time in milliseconds

       grid.setAdapter(new CostumGridAdapter(getActivity(),web,imageId));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getActivity(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                if (position==0){
                    Intent intent=new Intent(getActivity(), CompetitionActivity.class);
                    startActivity(intent);
                }
                if (position==1){
                    Intent intent=new Intent(getActivity(), WorkshopActivity.class);
                    startActivity(intent);
                }
                if (position==2){
                    Intent intent=new Intent(getActivity(), ProshowActivity.class);
                    startActivity(intent);
                }
                if (position==3){
                    Intent intent=new Intent(getActivity(), ScheduleActivity.class);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent=new Intent(getActivity(), CevCornerActivity.class);
                    startActivity(intent);
                }
                if (position==5){
                    Intent intent=new Intent(getActivity(), ResultActivity.class);
                    startActivity(intent);
                }

            }
        });
        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                counDown.setText(days + " Days : "+ hours + " Hours : " + minutes + " Minutes : " + seconds+" Seconds"); //You can compute the millisUntilFinished on hours/minutes/seconds
            }

            @Override
            public void onFinish() {
                counDown.setText("Break Free....CEV!");
            }
        };
        cdt.start();

        return view;
    }
}
