package xyx.game.mask.Date;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyx.game.mask.R;

public class FootHolder extends RecyclerView.ViewHolder  {
    private TextView txtName;
    private  View view;
    public FootHolder(@NonNull View itemView) {
        super(itemView);
        this.view=itemView;

            txtName=itemView.findViewById(R.id.foot);
           // txtName.setText("..fooddssdt...");
        txtName.setVisibility(View.GONE);





    }

    //private Foot foot;
    public void setDetails(int size) {

            if (size<=1){
            txtName.setText(view.getContext().getResources().getString(R.string.zero));
            txtName.setVisibility(View.VISIBLE);

        }
    }
}
