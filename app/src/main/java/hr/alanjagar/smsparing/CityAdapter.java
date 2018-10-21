package hr.alanjagar.smsparing;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.alanjagar.smsparing.model.Car;
import hr.alanjagar.smsparing.model.City;

/**
 * Created by ajagar on 20.10.2018..
 */

public class CityAdapter extends ArrayAdapter<City> {

    LayoutInflater flater;

    public CityAdapter(Activity context, int resouceId, int textviewId, List<City> list){
        super(context,resouceId,textviewId, list);
        flater = context.getLayoutInflater();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return renderView(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return renderView(position);
    }

    @NonNull
    private View renderView(int position) {
        City city = getItem(position);

        View cityView = flater.inflate(R.layout.city_item,null,true);

        TextView tvName = (TextView) cityView.findViewById(R.id.tv_city_item_name);
        tvName.setText(city.getName());

        return cityView;
    }
}