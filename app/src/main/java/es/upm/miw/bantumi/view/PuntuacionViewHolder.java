package es.upm.miw.bantumi.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.database.Puntuacion;

public class PuntuacionViewHolder extends RecyclerView.ViewHolder {
    TextView tvDate;
    TextView tvWinnerName;
    TextView tvWinnerSeeds;

    private PuntuacionViewHolder(View itemView) {
        super(itemView);
        this.tvDate = itemView.findViewById(R.id.tvResultDate);
        this.tvWinnerName = itemView.findViewById(R.id.tvResultWinner);
        this.tvWinnerSeeds = itemView.findViewById(R.id.tvResultWinnerSeedNumber);
    }

    static PuntuacionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_best_result_item, parent, false);
        return new PuntuacionViewHolder(view);
    }

    public void bind(Puntuacion puntuacion) {
        this.tvDate.setText(puntuacion.getGameTime());
        this.tvWinnerName.setText(puntuacion.getWinnerName());
        this.tvWinnerSeeds.setText(String.valueOf(puntuacion.getWinnerSeedStorage()));
    }

}
