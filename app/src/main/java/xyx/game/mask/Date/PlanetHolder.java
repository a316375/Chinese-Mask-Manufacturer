package xyx.game.mask.Date;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.varunjohn1990.iosdialogs4android.IOSDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import xyx.game.mask.GreenDao.DaoSession;
import xyx.game.mask.GreenDao.GreenDaoApplication;
import xyx.game.mask.GreenDao.Users;
import xyx.game.mask.GreenDao.UsersDao;
import xyx.game.mask.R;
import xyx.game.mask.SettingActivity;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.UIThead;

public class PlanetHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    private ImageView imageView;
    private Planet planet;
    private PlanetAdapter planetAdapter;


    private UsersDao usersDao;





    public PlanetHolder(final View itemView, final PlanetAdapter planetAdapter ) {
        super(itemView);







        this.planetAdapter=planetAdapter;


        txtName = itemView.findViewById(R.id.textView0);
        txtDistance = itemView.findViewById(R.id.textView2);
        txtGravity = itemView.findViewById(R.id.textView3);
        txtDiameter = itemView.findViewById(R.id.textView1);
        imageView=itemView.findViewById(R.id.imageView);



        DaoSession daoSession = ((GreenDaoApplication)((Activity)itemView.getContext()).getApplication()).getDaoSession();
        usersDao = daoSession.getUsersDao();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                planetAdapter.upRead(getAdapterPosition());

//
//                UIThead.runInSubThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        DelViod(planet.getPlanetName(),itemView.getContext());
//                    }
//                });

                UIThead.runInSubThread(new Runnable() {
                    @Override
                    public void run() {

                        Users user = usersDao.queryBuilder() .where(UsersDao.Properties.Id.eq(planet.getPlanetName())).build().unique();
                        if (user==null)return;
                        user.setIsread(true);
                        Log.v("------",user.getId());
                        //String id, int Gender, int Year, String info, long time, boolean isread
                    usersDao.update(new Users(user.getId(),user.getGender(),user.getYear(),user.getInfo(),user.getTime(),true));
                   //usersDao.save(user);
                    }
                });



                new IOSDialog.Builder(itemView.getContext())
                        .title(itemView.getResources().getString(R.string.About_Me))              // String or String Resource ID
                        .message(Genger(planet.getDiameter())+":"+planet.getGravity()+"\n"+planet.getInfo())  // String or String Resource ID
                        .positiveButtonText("Yeah, Copy")  // String or String Resource ID
                        .negativeButtonText("Close")   // String or String Resource ID
                        .positiveClickListener(new IOSDialog.Listener() {
                            @Override
                            public void onClick(IOSDialog iosDialog) {
                                iosDialog.dismiss();
                                //获取剪贴板管理器：
                                ClipboardManager cm = (ClipboardManager) itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                 // 创建普通字符型ClipData
                                ClipData mClipData = ClipData.newPlainText("Label", planet.getInfo());
                                  // 将ClipData内容放到系统剪贴板里。
                                cm.setPrimaryClip(mClipData);
                                Toast.makeText(itemView.getContext(), "Copy Success", Toast.LENGTH_SHORT).show();
                            }
                        }).negativeClickListener(new IOSDialog.Listener() {
                    @Override
                    public void onClick(IOSDialog iosDialog) {
                        iosDialog.dismiss();
                       // Toast.makeText(context, ":(", Toast.LENGTH_SHORT).show();
                    }
                })
                        .build()
                        .show();




                //imageView.setImageResource(R.mipmap.m1);

                //planetAdapter.removeItem(getAdapterPosition());
                //Toast.makeText(itemView.getContext(),planet.getInfo(), Toast.LENGTH_LONG).show();
                //itemView.getContext().startActivity(new Intent(itemView.getContext(), Main2Activity.class));
            }
        });

    }

    private String Genger(int i){
        if (i==0)return itemView.getResources().getString(R.string.Male);
        return itemView.getResources().getString(R.string.Female);
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
        if (planet.isRead())imageView.setImageResource(R.mipmap.m1);else imageView.setImageResource(R.mipmap.m2);
        if (planet.isRead())txtDiameter.setVisibility(View.GONE);









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
