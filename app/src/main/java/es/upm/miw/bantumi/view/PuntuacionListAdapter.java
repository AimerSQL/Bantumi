package es.upm.miw.bantumi.view;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.upm.miw.bantumi.database.Puntuacion;

public class PuntuacionListAdapter extends ListAdapter<Puntuacion, PuntuacionViewHolder> {
    public PuntuacionListAdapter(@NonNull DiffUtil.ItemCallback<Puntuacion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PuntuacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PuntuacionViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PuntuacionViewHolder holder, int position) {
        Puntuacion current = getItem(position);
        holder.bind(current);
    }

    public static class PuntuacionDiff extends DiffUtil.ItemCallback<Puntuacion> {

        @Override
        public boolean areItemsTheSame(@NonNull Puntuacion oldItem, @NonNull Puntuacion newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Puntuacion oldItem, @NonNull Puntuacion newItem) {
            return oldItem.getWinnerName().equals(newItem.getWinnerName());
        }
    }
}
