package ql.cev.ql7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_result);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("result");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, CompetitionActivity.BlogzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Blog, CompetitionActivity.BlogzoneViewHolder>(
                Blog.class,
                R.layout.card_items_result,
                CompetitionActivity.BlogzoneViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(CompetitionActivity.BlogzoneViewHolder viewHolder, final Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());



            }
        };
        recyclerView.setAdapter(FBRA);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    public static class BlogzoneViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogzoneViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title) {
            TextView post_title = mView.findViewById(R.id.event_name);
            post_title.setText(title);
            //post_title.setText();
        }
        public void setDesc(String desc) {
            TextView post_desc = mView.findViewById(R.id.result_date_time);
            post_desc.setText(desc);
        }
    }
}

