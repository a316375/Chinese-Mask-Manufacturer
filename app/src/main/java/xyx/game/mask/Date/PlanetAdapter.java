package xyx.game.mask.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xyx.game.mask.R;

public class PlanetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Planet> planets;
    public PlanetAdapter(Context context, ArrayList<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(xyx.game.mask.R.layout.layout_home_item, parent, false);
        View head = LayoutInflater.from(context).inflate(R.layout.head, parent, false);
        View foot = LayoutInflater.from(context).inflate(R.layout.foot, parent, false);
       if (viewType==VIEW_TYPES.Normal)return new PlanetHolder(view,this );
       if (viewType==VIEW_TYPES.Header)return new HeadHolder(head);
       if (viewType==VIEW_TYPES.Footer)return new FootHolder(foot);
      return null;



    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        switch(viewType) {

            case VIEW_TYPES.Header: // handle row header
                ((HeadHolder)holder).setDetails(showhead);
                break;
            case VIEW_TYPES.Footer: // handle row footer
//                Foot foot=new Foot(size);
//                ((FootHolder)holder).setDetails(foot);
                ((FootHolder)holder).setDetails(size);
                break;
            case VIEW_TYPES.Normal: // handle row item
                Planet planet = planets.get(position);
                ((PlanetHolder)holder).setDetails(planet);
                break;

        }
    }

    private  int size;

    @Override
    public int getItemViewType(int position) {

        if(position==0)
            return VIEW_TYPES.Header;
        else if(position == getItemCount()-1)
            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;
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

    private boolean showhead=false;

    public void addHead(boolean showhead) {
        this.showhead=showhead;
        planets.add(0, null);
        notifyItemInserted(0);
        //刷新下标，不然下标就不连续
       // notifyItemRangeChanged(0, planets.size());
    }


    public void addFoot(int size) {

        this.size=size;
        planets.add(getItemCount(),null);

        notifyItemInserted(getItemCount());
        //刷新下标，不然下标就不连续
      //  notifyItemRangeChanged(getItemCount(), planets.size());

    }
}
