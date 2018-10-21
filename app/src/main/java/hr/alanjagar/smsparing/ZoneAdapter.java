package hr.alanjagar.smsparing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.alanjagar.smsparing.model.Zone;

/**
 * Created by ajagar on 20.10.2018..
 */

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder> {

    private List<Zone> zones;
    private OnItemClickListener listener;

    public ZoneAdapter(List<Zone> zones, OnItemClickListener listener) {
        this.zones = zones;
        this.listener = listener;
    }

    @Override
    public ZoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zone_item, parent, false);

        return new ZoneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ZoneViewHolder holder, int position) {
        Zone zone = zones.get(position);
        holder.bind(zone, this.listener);
    }

    @Override
    public int getItemCount() {
        return zones.size();
    }

    public class ZoneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_zone_item_name)
        public TextView name;

        @BindView(R.id.tv_zone_item_number)
        public TextView number;
        private Zone zone;

        public ZoneViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Zone zone, final OnItemClickListener listener) {
            this.zone = zone;
            this.name.setText(zone.getName());
            this.number.setText(zone.getSmsNumber());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onZoneClick(zone);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onZoneClick(Zone zone);
    }
}