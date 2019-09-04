package ql.cev.ql7.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ql.cev.ql7.R;



public class AboutFragment extends Fragment {
    private ImageView athul;
    private ImageView junaid,rithwick;
    TextView website,facebook,instagram;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about,container,false);

        facebook=(TextView)view.findViewById(R.id.facebook);
        instagram=(TextView)view.findViewById(R.id.instagram);
        website=(TextView)view.findViewById(R.id.website);
        athul=(ImageView) view.findViewById(R.id.athul);
        junaid=(ImageView) view.findViewById(R.id.junaid);
        rithwick=(ImageView) view.findViewById(R.id.rithwick);
        if (website != null) {
            website.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (facebook != null) {
            facebook.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (instagram != null) {
            instagram.setMovementMethod(LinkMovementMethod.getInstance());
        }
        Glide.with(athul.getContext())
                .load(R.drawable.athul_photo)
                .into(athul);
        Glide.with(junaid.getContext())
                .load(R.drawable.junaid_photo)
                .into(junaid);
        Glide.with(rithwick.getContext())
                .load(R.drawable.rithwick_photo)
                .into(rithwick);

        return view;
    }
}
