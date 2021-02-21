package xyx.game.mask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.varunjohn1990.iosdialogs4android.IOSDialog;
import com.varunjohn1990.iosdialogs4android.IOSDialogButton;
import com.varunjohn1990.iosdialogs4android.IOSDialogMultiOptionsListeners;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyx.game.mask.Obj.A_Obj;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.TimeSave;
import xyx.game.mask.Tool.UIAlertDialog;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Publish my marriage proposal to the server", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                showDialog();
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

    private void showDialog() {
        List<IOSDialogButton> iosDialogButtons = new ArrayList<>();
        iosDialogButtons.add(new IOSDialogButton(1, "Show 10 Times-(Free)", false, IOSDialogButton.TYPE_POSITIVE));
        iosDialogButtons.add(new IOSDialogButton(2, "Show 100 Times-(AD Reward)"));
        iosDialogButtons.add(new IOSDialogButton(3, "Show 1000 Times-(Pay Money)", false, IOSDialogButton.TYPE_NEGATIVE));

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
                                Toast.makeText(MainActivity.this, "Show 10 Times-(Free)", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                sendToShow(100);
                                Toast.makeText(MainActivity.this, "Show 100 Times-(AD reward)", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                sendToShow(1000);
//                                for (int i = 0; i <10000; i++) {
//                                    TestViod(i);
//                                }
                                Toast.makeText(MainActivity.this, "Show 1000 Times-(Pay Money)", Toast.LENGTH_SHORT).show();
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
            String email = String.valueOf(TimeSave.Start_Zieo_Time());
            String uid = instance.getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(leibie);//男人/女人

//        myRef.child("Messge").setValue("122555465");
            A_Obj a_obj=new A_Obj(key3,i,email,key2,key1);
            myRef.child(uid+i).setValue(a_obj);

        }else {
            IntentTool.startActivity(MainActivity.this,SettingActivity.class);
        }





    }



    private void sendToShow(int i) {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        Integer key1 = sharedpreferences.getInt("key1", -1);
        Integer key2 = sharedpreferences.getInt("key2", 0);
        String key3 = sharedpreferences.getString("key3", "");



        if (key1!=-1||key2!=0||key3!=null){
            String leibie="";
            if (key1==0){leibie="Male";};
            if (key1==1){leibie="Female";}


            FirebaseAuth instance = FirebaseAuth.getInstance();
            String email = String.valueOf(TimeSave.Start_Zieo_Time());
            String uid = instance.getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(leibie);//男人/女人

//        myRef.child("Messge").setValue("122555465");
            A_Obj a_obj=new A_Obj(key3,i,email,key2,key1);
            myRef.child(uid).setValue(a_obj);

        }else {
            IntentTool.startActivity(MainActivity.this,SettingActivity.class);
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
}
