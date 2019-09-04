package ql.cev.ql7;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;



public class QuassoLiberum extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

}
