package hr.alanjagar.smsparing;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import hr.alanjagar.smsparing.data.CarRepository;
import hr.alanjagar.smsparing.data.CityRepository;
import hr.alanjagar.smsparing.data.JsonCityRepository;
import hr.alanjagar.smsparing.data.MockCityRepository;
import hr.alanjagar.smsparing.data.SharedPreferencesCarRepository;
import hr.alanjagar.smsparing.model.Car;
import hr.alanjagar.smsparing.model.City;
import hr.alanjagar.smsparing.model.Zone;
import hr.alanjagar.smsparing.utils.ParkingSmsManager;

public class MainActivity extends AppCompatActivity implements ZoneAdapter.OnItemClickListener{
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @BindView(R.id.ddlCar)
    Spinner ddlCar;

    @BindView(R.id.ddlCity)
    Spinner ddlCity;

    @BindView(R.id.rvZones)
    RecyclerView rvZones;

    @BindView(R.id.tv_activity_main_zone)
    TextView tvZone;

    private CarRepository carRepository;
    private CityRepository cityRepository;
    private ParkingSmsManager parkingSmsManager;

    private String msgToSend = "";
    private String numberForSend = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.carRepository = new SharedPreferencesCarRepository(this);
        this.cityRepository = new JsonCityRepository(this); // new MockCityRepository();
        this.parkingSmsManager = new ParkingSmsManager();

        ButterKnife.bind(this);

        this.fillCars();
        this.fillCities();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.fillCars();
        this.fillCities();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.main_menu_cars){
            Intent i = new Intent(this, CarActivity.class);
            startActivity(i);
        }
        return true;
    }

    private void fillCars(){
        this.ddlCar.setAdapter(new CarAdapter(this, R.layout.car_item, R.id.tv_car_item_title, this.carRepository.getAllCars()));
    }

    private void fillCities(){
        List<City> cities = this.cityRepository.getAllCities();
        if(cities.size() == 0){
            this.tvZone.setVisibility(View.INVISIBLE);
        }
        else{
            this.tvZone.setVisibility(View.VISIBLE);
        }
        this.ddlCity.setAdapter(new CityAdapter(this, R.layout.city_item, R.id.tv_city_item_name, cities));
    }

    @OnItemSelected(R.id.ddlCity)
    public void onCityChange(View view){
        City city = (City) ddlCity.getSelectedItem();
        ZoneAdapter adapter = new ZoneAdapter(city.getZones(), this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rvZones.setLayoutManager(mLayoutManager);
        rvZones.setItemAnimator(new DefaultItemAnimator());
        rvZones.setAdapter(adapter);
    }

    @Override
    public void onZoneClick(Zone zone) {
        if(ddlCar.getSelectedItem() == null){
            this.showCarNotSelectedDialog();
            return;
        }

        City selectedCity = (City) ddlCity.getSelectedItem();
        Car selectedCar = (Car) ddlCar.getSelectedItem();

        payDialog(selectedCar, zone, selectedCity);
    }

    public void payDialog(final Car car, final Zone zone, City city){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String comment = "";
        if(zone.getComment() != null && !zone.getComment().equals("")){
            comment = "(Napomena: " + zone.getComment() + ") ";
        }

        builder.setMessage(String.format("Jeste li sigurni da želite platiti parking za vozilo %s registarskih oznaka %s u mjestu %s. Poruka će biti poslana za zonu \"%s\" %sna broj %s.",
                car.getName(), car.getPlate(), city.getName(), zone.getName(), comment, zone.getSmsNumber()))
                .setTitle("Pošalji poruku?")
                .setPositiveButton("Plati sat vremena", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        msgToSend = car.getPlate();
                        numberForSend = zone.getSmsNumber();

                        sendSMSMessage();
                    }
                })
                .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        if(zone.isSupportsHalfHour())
        {
            builder.setNeutralButton("Plati pola sata",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    msgToSend = zone.getHalfHourPrefix() + car.getPlate() + zone.getHalfHourSuffix();
                    numberForSend = zone.getSmsNumber();

                    sendSMSMessage();
                }
            });
        }


        builder.create().show();
    }

    protected void sendSMSMessage() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
                Log.i("SMS-Parking", "ActivityCompat.shouldShowRequestPermissionRationale returned true");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else{
            parkingSmsManager.pay(msgToSend, numberForSend);
            Toast.makeText(getApplicationContext(), "Poruka poslana.", Toast.LENGTH_LONG).show();
            resetDoDefault();
        }
    }
    public  void  resetDoDefault(){
        msgToSend = "";
        numberForSend = "";
    }

    private void showCarNotSelectedDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Potrebno je odabrati barem jedan automobil.")
                .setTitle("Greška")
                .setPositiveButton("Upravljanje automobilima", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this, CarActivity.class);
                        startActivity(i);
                    }
                });
        builder.create().show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String permissions[], @Nullable int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    parkingSmsManager.pay(msgToSend, numberForSend);
                    resetDoDefault();
                    Toast.makeText(getApplicationContext(), "Poruka poslana.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),"Slanje nije uspijelo molimo pokušajte ponovno", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
