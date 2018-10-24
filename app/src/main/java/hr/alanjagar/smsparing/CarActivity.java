package hr.alanjagar.smsparing;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.alanjagar.smsparing.data.CarRepository;
import hr.alanjagar.smsparing.data.SharedPreferencesCarRepository;
import hr.alanjagar.smsparing.model.Car;

public class CarActivity extends AppCompatActivity implements CarListAdapter.OnItemClickListener, AddCarDialogFragment.OnDialogResult {

    @BindView(R.id.fabAddCar)
    FloatingActionButton fabAddCar;

    @BindView(R.id.rvCars)
    RecyclerView rvCars;

    private CarRepository carRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.carRepository = new SharedPreferencesCarRepository(this);
        this.setCars();
    }

    private void setCars(){
        List<Car> cars = this.carRepository.getAllCars();
        CarListAdapter adapter = new CarListAdapter(cars, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.rvCars.setLayoutManager(mLayoutManager);
        this.rvCars.setItemAnimator(new DefaultItemAnimator());
        this.rvCars.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @OnClick(R.id.fabAddCar)
    public void onAddCarClicked(View view){
        AddCarDialogFragment dialog = new AddCarDialogFragment(this, this, getLayoutInflater());
        dialog.showDialog();
    }

    @Override
    public void onCarDeleteClick(Car car) {
        this.carRepository.removeCar(car.getId());
        Toast.makeText(this, String.format("Automobil %s je uspješno obrisan.", car.getName()), Toast.LENGTH_LONG).show();
        this.setCars();
    }

    @Override
    public void onAddCar(Car car) {
        this.carRepository.addCar(car);
        Toast.makeText(this, "Auto uspješno dodan.", Toast.LENGTH_LONG).show();
        this.setCars();
    }

    @Override
    public void onCancel() {

    }
}
