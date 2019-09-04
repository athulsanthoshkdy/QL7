package ql.cev.ql7.Fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ql.cev.ql7.ContactAdapter;
import ql.cev.ql7.ContactListModel;
import ql.cev.ql7.R;



public class ContactFragment extends Fragment{
    private String[] Positions = {
            "CHAIRMAN",
            "VICE CHAIRMAN",
            "GEN. SECRETARY",
            "JOINT SECRETARY",
            "UUC",
            "FINE ARTS SEC.",
            "GEN. CAPTAIN",
            "MAGAZINE EDITOR",
            "",
            "CS",
            "CE",
            "EEE",
            "IT",
            "EC",
            "EI",
            "MCA",
            "",
            "CHAIRMAN",
            "GEN. CONVENOR",
            "TECHNICAL HEAD",
            "CULTURAL HEAD",

    } ;
    private String[] Names = {
            "AKHIL S",
            "ASWATHI P",
            "ADHIL",
            "KEERTHI",
            "AJUMAL K K",
            "VIGNESH DAS K",
            "IRSHAD",
            "HARIPRASAD C M",
            "ASSOCIATION SEC.",
            "ASWIN CHAND",
            "ASHIQUE MOHAMMED",
            "SANAL",
            "AJMAL J K",
            "SOURAV SHAJI",
            "ANOOP",
            "ATHIRA PK",
            "PROGRAM COMMITTEE",
            "AKHIL S",
            "ADHIL",
            "ARJUN NAMBISAN",
            "RAHUL K M",


    } ;
    private String[] PhoneNumber = {
            "9544137060",
            "8547057739",
            "8606832948",
            "8943508248",
            "7736478475",
            "9037259042",
            "7559900615",
            "8714353206",
            "",
            "9496475790",
            "9400118947",
            "7025183264",
            "9745096635",
            "9961854167",
            "9497233246",
            "9562040428",
            "",
            "9544137060",
            "8606832948",
            "9496054088",
            "8943912927",
    } ;



    private ArrayList<ContactListModel> ContactArray = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact,container,false);
        setListData();
        Resources resources=getResources();
        ListView listview =(ListView)view.findViewById(R.id.contact_list_view);
        ContactAdapter contactAdapter=new ContactAdapter(getActivity(),ContactArray,resources);
         listview.setAdapter(contactAdapter);
        return view;
    }

    private void setListData() {
        for (int i=0;i<21;i++){
            final ContactListModel contactListModel=new ContactListModel();
            contactListModel.setPosition(Positions[i]);
            contactListModel.setName(Names[i]);
            contactListModel.setContactNumber(PhoneNumber[i]);
            ContactArray.add(contactListModel);
        }
    }

}
