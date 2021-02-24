package xyx.game.mask.Date;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyx.game.mask.R;

public class HeadHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private View view;
    public HeadHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        txtName=itemView.findViewById(R.id.header);
        // txtName.setText(view.getContext().getResources().getString(R.string.loadSQL));
         txtName.setVisibility(View.GONE);
    }

    public  void setDetails(boolean showhead) {

        if (showhead==true){
            txtName.setText(view.getContext().getResources().getString(R.string.loadSQL));
            txtName.setVisibility(View.VISIBLE);

        }
    }
}
