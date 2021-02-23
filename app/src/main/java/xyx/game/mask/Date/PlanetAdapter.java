package xyx.game.mask.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetHolder> {

    private Context context;
    private ArrayList<Planet> planets;
    public PlanetAdapter(Context context, ArrayList<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }


    @NonNull
    @Override
    public PlanetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(xyx.game.mask.R.layout.layout_home_item, parent, false);

        return new PlanetHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.setDetails(planet);


    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public void removeItem(int position){
        if (position==-1)return;
       // planets.remove(position);
        notifyItemRemoved(position);
    }

    public void upRead(int position){
        if (position==-1)return;
        planets.get(position).setRead(true);
        notifyDataSetChanged();
    }
}
