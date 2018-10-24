package hr.alanjagar.smsparing;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.alanjagar.smsparing.model.Car;

/**
 * Created by ajagar on 20.10.2018..
 */

public class CarAdapter extends ArrayAdapter<Car> {

    LayoutInflater flater;
    private List<Car> data;

    public CarAdapter(Activity context, int resouceId, int textviewId, List<Car> list){
        super(context,resouceId,textviewId, list);
        this.data = list;
        flater = context.getLayoutInflater();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car car = getItem(position);

        View carView = flater.inflate(R.layout.car_item,null,true);

        TextView tvTitle = (TextView) carView.findViewById(R.id.tv_car_item_title);
        tvTitle.setText(car.getName());

        TextView tvplate = (TextView) carView.findViewById(R.id.tv_car_item_plate);
        tvplate.setText(car.getPlate().toUpperCase());

        if(position == this.data.size() -1){
            View devider = carView.findViewById(R.id.v_car_item_devider);
            devider.setVisibility(View.GONE);
        }

        return carView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Car car = getItem(position);

        View carView = flater.inflate(R.layout.car_selected_item,null,true);

        TextView tvTitle = (TextView) carView.findViewById(R.id.tv_car_selected_item_title);
        tvTitle.setText(car.getName());

        TextView tvplate = (TextView) carView.findViewById(R.id.tv_car_selected_item_plate);
        tvplate.setText(car.getPlate().toUpperCase());

        return carView;

    }
}