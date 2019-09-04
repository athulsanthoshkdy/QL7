package ql.cev.ql7;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CevCornerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthlistener;
    private String current_user="";
    private String post_user;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cev_corner);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);



        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.cevcorner));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
       // Toast.makeText(getApplicationContext(),post_user,Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(getApplicationContext(),"Click around your image to DELETE", Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.nice_button_enabled);
        toast.show();

        mdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();


        if (mCurrentUser != null) {
            current_user= mCurrentUser.getEmail();
        }
        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(CevCornerActivity.this, RegisterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };


        mdatabase = FirebaseDatabase.getInstance().getReference().child("cevcorner");


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
        FirebaseRecyclerAdapter<Blog, CevCornerActivity.BlogzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Blog, CevCornerActivity.BlogzoneViewHolder>(
                Blog.class,
                R.layout.activity_corner_items,

                CevCornerActivity.BlogzoneViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(final CevCornerActivity.BlogzoneViewHolder viewHolder, final Blog model,final int position) {
                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());

                viewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                viewHolder.setUserName(model.getUsername());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        post_user=model.getEmail();

                        //  String user = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.post_user)).getText().toString();
                        // Toast.makeText(getApplicationContext(),post_user,Toast.LENGTH_SHORT).show();
                        if (post_user.equals(current_user)){
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CevCornerActivity.this);

                            // Setting Dialog Title
                            alertDialog.setTitle("DELETE");

                            // Setting Dialog Message
                            alertDialog.setMessage("Are you sure want to delete this post ?");

                            // Setting Icon to Dialog
                            alertDialog.setIcon(R.drawable.delete);

                            // Setting Positive "Yes" Button
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                    mdatabase.child(post_key).removeValue();
                                    // Write your code here to invoke YES event
                                    Toast toast = Toast.makeText(getApplicationContext(),"Post deleted", Toast.LENGTH_SHORT);
                                    View view = toast.getView();
                                    view.setBackgroundResource(R.drawable.nice_button_enabled);
                                    toast.show();
                                }
                            });

                            // Setting Negative "NO" Button
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,	int which) {
                                    // Write your code here to invoke NO event
                                    //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });

                            // Showing Alert Message
                            alertDialog.show();

                        }


                    }
                });


            }
        };
        recyclerView.setAdapter(FBRA);

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void PostMyPhoto(View view) {

        Intent in = new Intent(CevCornerActivity.this, PostActivity.class);
        in.putExtra("name", "cevcorner");
        startActivity(in);
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
            ImageView post_image = mView.findViewById(R.id.post_my_image);
            Glide.with(post_image.getContext()).load(imageUrl).into(post_image);
        }

        public void setUserName(String userName) {
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        }
    }

}

