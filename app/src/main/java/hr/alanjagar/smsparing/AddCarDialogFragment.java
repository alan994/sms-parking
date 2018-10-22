package hr.alanjagar.smsparing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.alanjagar.smsparing.model.Car;

public class AddCarDialogFragment {

    @BindView(R.id.etCarName)
    public EditText etCarName;

    @BindView(R.id.etCarPlate)
    public EditText etCarPlate;

    public OnDialogResult dialogResultListener;
    private Context context;
    private LayoutInflater layoutInflater;

    public AddCarDialogFragment(OnDialogResult dialogResultListener, Context context, LayoutInflater layoutInflater) {
        this.dialogResultListener = dialogResultListener;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    public void showDialog() {
        View view = this.layoutInflater.inflate(R.layout.dialog_add_car, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Dodaj auto")
                .setView(view)
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = etCarName.getText().toString();
                        String plate = etCarPlate.getText().toString();

                        Car car = new Car();
                        car.setName(name);
                        car.setPlate(plate.toUpperCase());

                        dialogResultListener.onAddCar(car);
                    }
                })
                .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogResultListener.onCancel();
                    }
                });
        builder.create().show();
    }


    public interface OnDialogResult {
        void onAddCar(Car car);
        void onCancel();
    }
}
