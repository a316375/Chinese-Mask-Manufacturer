package xyx.game.mask.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.TimeZone;

import xyx.game.mask.Date.Planet;
import xyx.game.mask.Date.PlanetAdapter;
import xyx.game.mask.GreenDao.DaoSession;
import xyx.game.mask.GreenDao.GreenDaoApplication;
import xyx.game.mask.GreenDao.Users;
import xyx.game.mask.GreenDao.UsersDao;
import xyx.game.mask.Obj.A_Obj;
import xyx.game.mask.Obj.Load;
import xyx.game.mask.Obj.Today;
import xyx.game.mask.R;
import xyx.game.mask.SettingActivity;
import xyx.game.mask.Tool.IntentTool;
import xyx.game.mask.Tool.TimeSave;
import xyx.game.mask.Tool.UIThead;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private PlanetAdapter adapter;
    private ArrayList<Planet> planetArrayList;
    private DatabaseReference myRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        OpenSQL();

        recyclerView= root.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        planetArrayList = new ArrayList<>();
        adapter = new PlanetAdapter(root.getContext(), planetArrayList);
        recyclerView.setAdapter(adapter);
        createListData();


       // checkSetting();
        checkSetting();




        return root;
    }

















    String leibie = null;
    private void checkSetting(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        Integer key1 = sharedpreferences.getInt("key1", -1);



        if (key1==-1){
            IntentTool.startActivity(getActivity(), SettingActivity.class);
            getActivity().finish();
            return;
        }
        if (key1==0)leibie="Female";
        if (key1==1)leibie="Male";

        checkToday();

    }
    private void upData() {
//        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
//        Integer key1 = sharedpreferences.getInt("key1", -1);
//
//
//        String leibie = null;
//        if (key1==-1){
//            IntentTool.startActivity(getActivity(), SettingActivity.class);
//            getActivity().finish();
//            return;
//        }
//        if (key1==0)leibie="Female";
//        if (key1==1)leibie="Male";



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //男人
        myRef = database.getReference(leibie);

        //只能读取20条
        myRef.orderByKey().limitToFirst(20).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!=null)
                {


                    Iterable<DataSnapshot> children = snapshot.getChildren();



                    adapter.addHead(false);//网络请求的不显示head

                    for (DataSnapshot ds:children){
//                        Log.v("--tuxue--",ds.toString());
//                        Log.v("--tuxue2--",ds.getValue().toString());
//                        Log.v("--tuxue3--",ds.getKey().toString());


                        A_Obj mvalue = ds.getValue(A_Obj.class);
//                        Log.v("--tuxue4--",mvalue.toString());
                        String dsKey = ds.getKey();



//                        final String key = ds.getKey();
//
//                        Long id = Long.valueOf(ds.child("id").getValue(Long.class));
//                        final Integer times = ds.child("times").getValue(Integer.class);
//                        Integer year = ds.child("year").getValue(Integer.class);
//                        String info = ds.child("info").getValue(String.class);
//                        Integer gender=ds.child("gender").getValue(Integer.class);

//                        UIThead.runInSubThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                if (times<=1){myRef.child(key).removeValue();}
//                                else
//                                { myRef.child(key).child("times").setValue(times-1);}
//                            }
//                        });
                        if (mvalue.getTimes()<=1){
                            myRef.child(dsKey).removeValue();}
                        else
                        { myRef.child(dsKey).child("times").setValue(mvalue.getTimes()-1);}


//                        if (new Random().nextInt(3)==1){
                            Planet   planet = new Planet(dsKey, mvalue.getId(), mvalue.getYear(), mvalue.getTimes(),mvalue.getInfo(),false);
                            planetArrayList.add(planet);
                         //String id, int Gender, int Year, String info, long time,   boolean isread
                            add(new Users(dsKey,mvalue.getGender(),mvalue.getYear(),mvalue.getInfo(),mvalue.getId(),false));



//                        }


                    }

                    adapter.addFoot(planetArrayList.size());
                    adapter.notifyDataSetChanged();

//                    for(DataSnapshot ds : snapshot.getChildren()) {
//                        String key = ds.getKey();
//                        Integer id = Integer.valueOf(ds.child("id").getValue(String.class));
//                        Integer times = ds.child("times").getValue(Integer.class);
//                        Integer year = ds.child("year").getValue(Integer.class);
//                        Log.v("--Tag", key);
//                        Planet   planet = new Planet("Uranus", id, year, times);
//                        planetArrayList.add(planet);
//
//
//                    }
                   // adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private UsersDao usersDao;
    private void OpenSQL(){
        DaoSession daoSession = ((GreenDaoApplication)getActivity().getApplication()).getDaoSession();
        usersDao = daoSession.getUsersDao();
    }

    //保存起来
    private void add(final Users users) {
       // Users users=new Users();
        UIThead.runInSubThread(new Runnable() {
            @Override
            public void run() {
                usersDao.insert(users);
            }
        });

    }

    //清除历史
    private void del_All(){
        usersDao.deleteAll();
    }

    private void loadFromSQL(){
        adapter.addHead(true);
        ArrayList<Users> allCategory = getAllCategory();
       // List<Users> joes = usersDao.queryBuilder().list();

       // Log.v("----",joes.toString()+joes.size());
        for (Users users:allCategory){
//            Planet   planet = new Planet(key, id, year, times,info);
            Planet   planet = new Planet(users.getId(), users.getTime(), users.getYear(), 0,users.getInfo(),users.getIsread());
            planetArrayList.add(planet);
        }

        adapter.addFoot(planetArrayList.size());

        adapter.notifyDataSetChanged();
    }

    public ArrayList<Users> getAllCategory () {

        return new ArrayList<>(usersDao .loadAll());
    }

    private  ProgressDialog progressDialog;
    //检查网络时间
    private void checkToday() {

        progressDialog= new ProgressDialog(getContext());
        progressDialog.setMessage(getActivity().getResources().getString(R.string.waitting));
        progressDialog.show();
        progressDialog.setCancelable(false);



        UIThead.runInSubThread(new Runnable() {
            long time=0;
            @Override
            public void run() {
                try {
                    time = getTime();

                    Log.v("---","Today------"+time);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("---","Today-Fail-----"+e.toString());
                }
                finally {
                    if (time==0)return;
                    upToday(time);
                }
            }
        });



    }

    //核查今天是否刷新过
    private void upToday(final Long time) {

        //储存今天日期：
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences =getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putLong("today", time);
                editor.commit();

            }
        }).start();


        SharedPreferences sharedpreferences =getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        long today = sharedpreferences.getLong("today", 0);
        if (today!=0){
            if (TimeSave.isSameDay(today,time,TimeZone.getDefault())){
                UIThead.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                        loadFromSQL();
                        progressDialog.dismiss();
                    }
                });

                }

        }else {
            startLoad(time);}



    }

    private void startLoad(final Long time) {
        FirebaseAuth instance = FirebaseAuth.getInstance();
        //String email = instance.getCurrentUser().getEmail();
        final String uid = instance.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Today");//today
        myRef.child(uid).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                if (snapshot.getValue()==null){
                    //未注册过的用户
                    Today today=new Today();
                    myRef.child(uid).setValue(today);
                    myRef.child(uid).child("load").setValue(ServerValue.TIMESTAMP);
                    upData();
                }else {
                    Today value = snapshot.getValue(Today.class);
                    Long load = value.getLoad();
//                    if (time!=load){
//                        Log.v("----","Now is"+time);
//                        Log.v("----","But is"+load);
//                    }
                    boolean sameDay = TimeSave.isSameDay(time, load, TimeZone.getDefault());
                    Log.v("----","SameDay="+sameDay);
                    if (!sameDay){
                        del_All();
                        myRef.child(uid).child("load").setValue(ServerValue.TIMESTAMP);//刷新记录
                        upData();
                    }else {
                        //去加载本地数据库
                        loadFromSQL();
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.setMessage("Error Cause Database");
            }
        });
    }

    private long getTime() throws Exception {

        String url = "https://time.is/Unix_time_now";
        // File file = new File(getClass().getClassLoader().getResource(url).getFile());
        // Document doc=Jsoup.parse(file, "UTF-8", url);
        Document doc = Jsoup.connect(url).get();
        //  Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);//原文作废
        String[] tags = new String[] {
                "div[id=time_section]",
                "div[id=clock0_bg]"
        };
        // Log.v("---","doc------"+doc);

        Elements elements= doc.select(tags[0]);
        for (int i = 0; i <tags.length; i++) {
            elements = elements.select(tags[i]);
        }
        return Long.parseLong(elements.text() + "000");
    }













    private void createListData() {
//        Planet planet = new Planet("Earth", 150, 10, 12750);
//        planetArrayList.add(planet);
//        planet = new Planet("Jupiter", 778, 26, 143000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mars", 228, 4, 6800);
//        planetArrayList.add(planet);
//        planet = new Planet("Pluto", 5900, 1, 2320);
//        planetArrayList.add(planet);
//        planet = new Planet("Venus", 108, 9, 12750);
//        planetArrayList.add(planet);
//        planet = new Planet("Saturn", 1429, 11, 120000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mercury", 58, 4, 4900);
//        planetArrayList.add(planet);
//        planet = new Planet("Neptune", 4500, 12, 50500);
//        planetArrayList.add(planet);
//        planet = new Planet("Uranus", 2870, 9, 52400);
//        planetArrayList.add(planet);
//        planet = new Planet("Jupiter", 778, 26, 143000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mars", 228, 4, 6800);
//        planetArrayList.add(planet);
//        planet = new Planet("Pluto", 5900, 1, 2320);
//        planetArrayList.add(planet);
//        planet = new Planet("Venus", 108, 9, 12750);
//        planetArrayList.add(planet);
//        planet = new Planet("Saturn", 1429, 11, 120000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mercury", 58, 4, 4900);
//        planetArrayList.add(planet);
//        planet = new Planet("Neptune", 4500, 12, 50500);
//        planetArrayList.add(planet);
//        planet = new Planet("Uranus", 2870, 9, 52400);
//        planetArrayList.add(planet);
//
//        adapter.notifyDataSetChanged();
    }












}
