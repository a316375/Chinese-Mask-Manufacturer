package xyx.game.mask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.varunjohn1990.iosdialogs4android.IOSDialog;
import com.varunjohn1990.iosdialogs4android.IOSDialogButton;
import com.varunjohn1990.iosdialogs4android.IOSDialogMultiOptionsListeners;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import xyx.game.mask.Obj.A_Obj;
import xyx.game.mask.Obj.Num;
import xyx.game.mask.Obj.Today;
import xyx.game.mask.Obj.User;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.TimeSave;
import xyx.game.mask.Tool.UIAlertDialog;
import xyx.game.mask.Tool.UIThead;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;


    private int Time = 1000*3;//周期时间
    private Timer timer = new Timer();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载动画资源文件


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
//        fab.startAnimation(shake);
        /**
         * 方式二：采用timer及TimerTask结合的方法
         */
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fab.startAnimation(shakeAnimation(2));
            }
        };
        timer.schedule(timerTask,
                1000,//延迟1秒执行
                Time);//周期时间



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Publish my marriage proposal to the server", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                //fab.clearAnimation();
                timer.cancel();

                checksend();
//              if (click==2) showDialog();
//              if (click==3) showDialog();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
        String currentDateTimeString =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        navUsername.setText(navUsername.getText().toString()+"\n@"+currentDateTimeString);



        SharedPreferences sharedpreferences = getBaseContext().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        Integer key1 = sharedpreferences.getInt("key1", -1);
        Integer key2 = sharedpreferences.getInt("key2", 0);
        String key3 = sharedpreferences.getString("key3", "");



        if (key1!=-1||key2!=0||key3!=null){ StartIN();}else {
            IntentTool.startActivity(MainActivity.this,SettingActivity.class);
            finish();}

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = database.getReference("Male");//男人
//    DatabaseReference myRef2 = database.getReference("Female");
//    myRef2.removeValue();


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Male");//男人
//        DatabaseReference myRef2 = database.getReference("Female");
////        myRef.child("Messge").setValue("122555465");
//        A_Obj a_obj=new A_Obj("QQ:12345322",10,"122555465",1991,1);
//        myRef.child("Messge").child("ID123").setValue(a_obj);
//        myRef.child("Messge").child("ID124").setValue(a_obj);
//        myRef.child("Messge").child("ID125").setValue(a_obj);
//        myRef.child("Messge").child("ID126").setValue(a_obj);
//
//        myRef2.child("Messge").child("ID125").setValue(a_obj);
//        myRef2.child("Messge").child("ID126").setValue(a_obj);

//        myRef2.child("Messge").child("ID125").removeValue();//移除


        //更新
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("times",5);//前面的字是child後面的字是要修改的value值
//        myRef2.child("Messge").child("ID126").updateChildren(childUpdates);


//        // Read from the database
//        myRef.child("Messge").child("ID126").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//              //  String value = dataSnapshot.getValue(String.class);
//                A_Obj post = dataSnapshot.getValue(A_Obj.class);
//                Log.d("TAG", post.getID()+"--Value is: " + post.toString());
//                Log.d("TAG", "Value is: " + dataSnapshot.getValue().toString());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException());
//            }
//        });



    }





    private void showDialog2(){
        new IOSDialog.Builder(MainActivity.this)
                .title("Post My Message").message(getResources().getString(R.string.stop_today)).build().show();
    }




    private void showDialog() {


        List<IOSDialogButton> iosDialogButtons = new ArrayList<>();
        iosDialogButtons.add(new IOSDialogButton(1, "Show 10 Times-(Free)", false, IOSDialogButton.TYPE_POSITIVE));
        iosDialogButtons.add(new IOSDialogButton(2, "Show 20 Times-(AD Reward)"));
        iosDialogButtons.add(new IOSDialogButton(3, "Show 100 Times-(Pay Money)", false, IOSDialogButton.TYPE_NEGATIVE));

        new IOSDialog.Builder(MainActivity.this)
                .title("Post My Message")              // String or String Resource ID
                .message(R.string.dialogmessge)  // String or String Resource ID
                .multiOptions(true)                // Set this true other it will not work
                .multiOptionsListeners(new IOSDialogMultiOptionsListeners() {
                    @Override
                    public void onClick(IOSDialog iosDialog, IOSDialogButton iosDialogButton) {
                        iosDialog.dismiss();

                        switch (iosDialogButton.getId()) {
                            case 1:

                                sendToShow(10);
                               // Toast.makeText(MainActivity.this, "Show 10 Times-(Free)", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                //sendToShow(20);
                                 Toast.makeText(MainActivity.this, "wait it", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                               // sendToShow(100);
//                                for (int i = 0; i <100; i++) {
//                                    TestViod(i);
//                                }
                               Toast.makeText(MainActivity.this, "wait it", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .iosDialogButtonList(iosDialogButtons)
                .build()
                .show();
    }


    private void  TestViod(int i){
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        Integer key1 = sharedpreferences.getInt("key1", -1);
        Integer key2 = sharedpreferences.getInt("key2", 0);
        String key3 = sharedpreferences.getString("key3", "");



        if (key1!=-1||key2!=0||key3!=null){
            String leibie="";
            if (key1==0){leibie="Male";};
            if (key1==1){leibie="Female";}


            FirebaseAuth instance = FirebaseAuth.getInstance();
            Long id = Long.valueOf(TimeSave.Start_Zieo_Time());
            String uid = instance.getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(leibie);//男人/女人

//        myRef.child("Messge").setValue("122555465");
            A_Obj a_obj=new A_Obj(key3,i,id,key2,key1);
            myRef.child(uid+i).setValue(a_obj);
            myRef.child(uid+i).child("id").setValue(ServerValue.TIMESTAMP);

        }else {
            IntentTool.startActivity(MainActivity.this,SettingActivity.class);
        }





    }


    private void checksend(){


        SharedPreferences sharedpreferences =getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        final long today = sharedpreferences.getLong("today", 0);
        if (today==0)return;



        FirebaseAuth instance = FirebaseAuth.getInstance();
        //String email = instance.getCurrentUser().getEmail();
        final String uid = instance.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Today");//Today
        myRef.child(uid).child("send").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()==null){
                    //myRef.child(uid).child("send").setValue(ServerValue.TIMESTAMP);
                    showDialog();
                }else {
                    Long value = snapshot.getValue(Long.class);
                    boolean sameDay = TimeSave.isSameDay(today, value, TimeZone.getDefault());
                    if (sameDay){ showDialog2();fab.setVisibility(View.GONE);return;}
                    showDialog();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




    private void StartIN() {
        FirebaseAuth instance = FirebaseAuth.getInstance();
        //String email = instance.getCurrentUser().getEmail();
        final String uid = instance.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");//男人
        ( (DatabaseReference) myRef.child(uid)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    Log.v("Tag---",dataSnapshot.toString());
                    // int followersCount = dataSnapshot.getValue(Integer.class);
                    final User user=dataSnapshot.getValue(User.class);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("key1", user.getGender());
                            editor.putInt("key2", user.getYear());
                            editor.putString("key3", user.getString());
                            editor.commit();

                        }
                    }).start();






                    //put value in view;
                } else  {
//                    IntentTool.startActivity(MainActivity.this,SettingActivity.class);
//                    finish();
                    //put 0 in view
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }









    private void sendToShow(final int i) {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        final Integer key1 = sharedpreferences.getInt("key1", -1);
        final Integer key2 = sharedpreferences.getInt("key2", 0);
        final String key3 = sharedpreferences.getString("key3", "");



        if (key1!=-1||key2!=0||key3!=null){
            String leibie="";
            if (key1==0){leibie="Male";};
            if (key1==1){leibie="Female";}


            FirebaseAuth instance = FirebaseAuth.getInstance();
            final Long id = Long.valueOf(TimeSave.Start_Zieo_Time());

            final String uid =  instance.getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference(leibie);//男人/女人




            final DatabaseReference myRef2 = database.getReference("Today");//Today
            myRef2.child(uid).child("send").setValue(ServerValue.TIMESTAMP);





            final DatabaseReference num = database.getReference(leibie+"Num");//拿到排队号码

             //num.setValue(new Num((long) 0));

            num.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Num value = snapshot.getValue(Num.class);
                    if (value.getNum()>=Long.MAX_VALUE/10)value.setNum(1L);
                    num.setValue(new Num(value.getNum()+1));
                    A_Obj a_obj=new A_Obj(key3,i,id,key2,key1);
                    //Log.v("-------",String.valueOf(value));
                    myRef.child(String.valueOf(value.getNum())+uid.substring(0,8)).setValue(a_obj);
                    myRef.child(String.valueOf(value.getNum())+uid.substring(0,8)).child("id").setValue(ServerValue.TIMESTAMP);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



//        myRef.child("Messge").setValue("122555465");


        }else {
            IntentTool.startActivity(MainActivity.this,SettingActivity.class);
            finish();
        }





    }

// ...




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                IntentTool.startActivity(MainActivity.this,SettingActivity.class);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }







    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

}
