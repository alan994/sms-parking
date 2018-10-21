package hr.alanjagar.smsparing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.alanjagar.smsparing.model.Car;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private List<Car> cars;
    private OnItemClickListener listener;

    public CarListAdapter(List<Car> cars, CarListAdapter.OnItemClickListener listener) {
        this.cars = cars;
        this.listener = listener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_item, parent, false);

        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.bind(car, this.listener);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_car_list_item_name)
        public TextView tvName;

        @BindView(R.id.iv_car_list_item_delete_action)
        public ImageView ivDeleteAction;

        private Car car;

        public CarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Car car, final OnItemClickListener listener) {
            this.car = car;
            this.tvName.setText(String.format("%s, %s", this.car.getName(), this.car.getPlate()));
            this.ivDeleteAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCarDeleteClick(car);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onCarDeleteClick(Car car);
    }
}
