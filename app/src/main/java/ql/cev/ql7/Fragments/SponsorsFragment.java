package ql.cev.ql7.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ql.cev.ql7.Blog;
import ql.cev.ql7.R;



public class SponsorsFragment extends Fragment {
    private DatabaseReference mdatabase;
    private RecyclerView recyclerView;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sponsors,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
       recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
         mdatabase = FirebaseDatabase.getInstance().getReference().child("Sponsors");


        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, SponsorsFragment.BlogzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Blog, SponsorsFragment.BlogzoneViewHolder>(
                Blog.class,
                R.layout.sponsors_card_items,
                SponsorsFragment.BlogzoneViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(SponsorsFragment.BlogzoneViewHolder viewHolder, final Blog model, int position) {
              viewHolder.setImageUrl(getActivity(), model.getImageUrl());


            }
        };
        recyclerView.setAdapter(FBRA);
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

        public void setImageUrl(Context ctx, String imageUrl) {
            ImageView post_image = mView.findViewById(R.id.post_image);
            Glide.with(post_image.getContext()).load(imageUrl).into(post_image);
        }

        public void setUserName(String userName) {
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        }
    }

}
