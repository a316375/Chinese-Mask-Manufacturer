package xyx.game.mask.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import xyx.game.mask.Date.Planet;
import xyx.game.mask.Date.PlanetAdapter;
import xyx.game.mask.R;
import xyx.game.mask.SettingActivity;
import xyx.game.mask.Tool.IntentTool;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private PlanetAdapter adapter;
    private ArrayList<Planet> planetArrayList;

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

        recyclerView=root.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        planetArrayList = new ArrayList<>();
        adapter = new PlanetAdapter(root.getContext(), planetArrayList);
        recyclerView.setAdapter(adapter);
        createListData();

        upData();



        return root;
    }

    private void upData() {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        Integer key1 = sharedpreferences.getInt("key1", -1);


        String leibie = null;
        if (key1==-1){
            IntentTool.startActivity(getActivity(), SettingActivity.class);
            getActivity().finish();
            return;
        }
        if (key1==0)leibie="Female";
        if (key1==1)leibie="Male";


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(leibie);//男人

        myRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!=null)
                {
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    int i=0;
                    for (DataSnapshot ds:children){

                        if (i>=50)break;
                        String key = ds.getKey();
                        Integer id = Integer.valueOf(ds.child("id").getValue(String.class));
                        Integer times = ds.child("times").getValue(Integer.class);
                        Integer year = ds.child("year").getValue(Integer.class);
                        Log.v("--Tag", key+"--"+i);

//                        if (new Random().nextInt(3)==1){
                            Planet   planet = new Planet(key, id, year, times);
                            planetArrayList.add(planet);

                            i++;
//                        }


                    }
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
