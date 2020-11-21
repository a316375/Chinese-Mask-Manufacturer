package xyx.game.mask.Date;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import xyx.game.mask.M2.Main2Activity;
import xyx.game.mask.R;

public class PlanetHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    public PlanetHolder(final View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.textView1);
        txtDistance = itemView.findViewById(R.id.textView2);
        txtGravity = itemView.findViewById(R.id.textView3);
        txtDiameter = itemView.findViewById(R.id.textView1);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemView.getContext().startActivity(new Intent(itemView.getContext(), Main2Activity.class));
            }
        });
    }

    public void setDetails(Planet planet) {
        txtName.setText(planet.getPlanetName());
        txtDistance.setText(String.format(Locale.US, "Distance from Sun : %d Million KM", planet.getDistanceFromSun()));
        txtGravity.setText(String.format(Locale.US, "Surface Gravity : %d N/kg", planet.getGravity()));
        txtDiameter.setText(String.format(Locale.US, "Diameter : %d KM", planet.getDiameter()));
    }


}
