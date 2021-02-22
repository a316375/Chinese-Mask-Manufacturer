package xyx.game.mask.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import xyx.game.mask.R;
import xyx.game.mask.SettingActivity;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.UIThead;

public class PlanetHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    private Planet planet;
    private PlanetAdapter planetAdapter;
    public PlanetHolder(final View itemView, final PlanetAdapter planetAdapter) {
        super(itemView);
        this.planetAdapter=planetAdapter;
        txtName = itemView.findViewById(R.id.textView0);
        txtDistance = itemView.findViewById(R.id.textView2);
        txtGravity = itemView.findViewById(R.id.textView3);
        txtDiameter = itemView.findViewById(R.id.textView1);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UIThead.runInSubThread(new Runnable() {
                    @Override
                    public void run() {
                        DelViod(planet.getPlanetName(),itemView.getContext());
                    }
                });

                planetAdapter.removeItem(getAdapterPosition());
                Toast.makeText(itemView.getContext(),planet.getInfo(), Toast.LENGTH_LONG).show();
                //itemView.getContext().startActivity(new Intent(itemView.getContext(), Main2Activity.class));
            }
        });
    }

    private void  DelViod(final String id, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        Integer key1 = sharedpreferences.getInt("key1", -1);
        Integer key2 = sharedpreferences.getInt("key2", 0);
        String key3 = sharedpreferences.getString("key3", "");



        if (key1!=-1||key2!=0||key3!=null){
            String leibie="";

            if (key1==0){leibie="Female";}
            if (key1==1){leibie="Male";};


           // FirebaseAuth instance = FirebaseAuth.getInstance();
           // final String uid = instance.getUid();

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference(leibie);//男人/女人
// Example query:
            //myRef.orderByKey().limitToFirst(50).addListenerForSingleValueEvent();
//            db.ref("messages").limitToFirst(1000)
//                    .orderByKey("value");
//        myRef.child("Messge").setValue("122555465");

            myRef.child(id).child("times")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Date date=new Date();
//                    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
//                    sfd.format(new Date());

                    Long value =   dataSnapshot.getValue(Long.class);
                    if (value<=1){myRef.child(id).removeValue();}
                     else {myRef.child(id).child("times").setValue(value-1);}

                   // Toast.makeText(itemView.getContext(),value+"----", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


        }else {
            IntentTool.startActivity((Activity) context , SettingActivity.class);
        }





    }







    public void setDetails(Planet planet) {
        this.planet=planet;
        txtName.setText("Num:"+(planet.getPlanetName()));
//        txtDistance.setText(String.format(Locale.US, "Announced Date: %d ", planet.getDistanceFromSun()));
        txtDistance.setText(String.format(Locale.US, "Announced Date: %s ", getDate2String(planet.getDistanceFromSun(),"yyyy-MM-dd")));
        txtGravity.setText(String.format(Locale.US, "Year of Birth: %d ", planet.getGravity()));
        txtDiameter.setText(String.format(Locale.US, "Remaining Times: %d ", planet.getDiameter()));

    }

    public static String hidePhoneNum(String phone) {
        StringBuilder stringBuilder = new StringBuilder(phone);
        stringBuilder.replace(4, 14, "****");
        return stringBuilder.toString();
    }



    /**
     *
     * @param time  1541569323155
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2018-11-07 13:42:03
     */
    public static String getDate2String(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }


}
