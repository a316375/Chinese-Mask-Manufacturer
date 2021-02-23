package xyx.game.mask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

;import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.sql.Time;
import java.util.Map;

import xyx.game.mask.Obj.User;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.MonthYearPickerDialog;
import xyx.game.mask.Tool.TimeSave;

public class SettingActivity extends AppCompatActivity {


    private Button click;
    private EditText editText;
    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        click = (Button) findViewById(R.id.click);
        editText = (EditText) findViewById(R.id.EditText);

        //加载动画资源文件
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


        final RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioGroup);
//        //第一种获得单选按钮值的方法
//        //为radioGroup设置一个监听器:setOnCheckedChanged()
//        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radbtn = (RadioButton) findViewById(checkedId);
//                Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" + radbtn.getText(), Toast.LENGTH_LONG).show();
//            }
//        });
        Button btnchange = (Button) findViewById(R.id.start);
       //为radioGroup设置一个监听器:setOnCheckedChanged()

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click.getText().length()!=4){

                    click.startAnimation(shake); //给组件播放动画效果
                    return;}

                if (editText.getText().length()<5){
                    editText.startAnimation(shake);
                    return;
                }




                int all=0;
                for (int i = 0; i < radgroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) radgroup.getChildAt(i);
                    if (rd.isChecked()) {
                        all++;


                        sendToFirebase(i);
//                        Toast.makeText(getApplicationContext(), i+"点击提交按钮,获取你选择的是:" +
//                                rd.getText()+
//                                ((Button)findViewById(R.id.click)).getText()+((EditText)findViewById(R.id.EditText)).getText(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                if (all==0){
                    findViewById(R.id.radioGroup).startAnimation(shake);
                }
            }
        });


        ((EditText)findViewById(R.id.EditText)).setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});

        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        Integer key1 = sharedpreferences.getInt("key1", -1);
        Integer key2 = sharedpreferences.getInt("key2", 0);
        String key3 = sharedpreferences.getString("key3", "");

        if (key1!=-1){
            Log.v("--",key1.toString());
             if (key1==0)radgroup.check(R.id.btnMan);
             if (key1==1)radgroup.check(R.id.btnWoman);

        }
        if (key2!=0){
            click.setText(key2.toString());
        }
        if (key3!=null){
            editText.setText(key3);
        }








    }

    //储存数据发送给数据库
    private void sendToFirebase(final int i) {

        FirebaseAuth instance = FirebaseAuth.getInstance();
        String email = instance.getCurrentUser().getEmail();
        String uid = instance.getUid();
        User user=new User(i,
                Integer.valueOf(click.getText().toString()),
                editText.getText().toString(),TimeSave.Start_Zieo_Time(),email);
        long time = TimeSave.Start_Zieo_Time();
       // Toast.makeText(getApplicationContext(),email+uid+"\n"+time,Toast.LENGTH_LONG).show();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");//男人
        myRef.child(uid).setValue(user);
        myRef.child(uid).child("time").setValue(ServerValue.TIMESTAMP);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("key1", i);
                editor.putInt("key2", Integer.valueOf(click.getText().toString()));
                editor.putString("key3", editText.getText().toString());
                editor.commit();

            }
        }).start();







        IntentTool.startActivity(SettingActivity.this,MainActivity.class);
        finish();






    }

    public void showTimePickerDialog(View v) {
        MonthYearPickerDialog newFragment = new MonthYearPickerDialog((Button)findViewById(R.id.click));
        newFragment.show(getSupportFragmentManager(), "DatePicker");

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {//返回键
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }
}