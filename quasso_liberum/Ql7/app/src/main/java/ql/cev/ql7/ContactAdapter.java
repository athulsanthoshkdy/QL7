package ql.cev.ql7;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class ContactAdapter extends BaseAdapter{
    private Context context;
    private static LayoutInflater inflater=null;
    private ArrayList data;
    private Resources res;
    private ContactListModel tempValues=null;
    int i=0;
    public ContactAdapter(Context a, ArrayList d, Resources resLocal) {
        context = a;
        data=d;
        res = resLocal;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{

        public TextView Position;
        public TextView Name;
        public TextView ContactNumber;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.contact_items, null);

            holder = new ViewHolder();
            holder.Position = (TextView) vi.findViewById(R.id.position);
            holder.Name=(TextView)vi.findViewById(R.id.name);
            holder.ContactNumber=(TextView)vi.findViewById(R.id.contact_no);

            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.Position.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ContactListModel ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.Position.setText( tempValues.getPosition() );
            holder.Name.setText( tempValues.getName() );
            holder.ContactNumber.setText(tempValues.getContactNumber());

            /******** Set Item Click Listner for LayoutInflater for each row *******/


        }
        return vi;

    }


}
