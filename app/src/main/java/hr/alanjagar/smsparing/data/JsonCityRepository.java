package hr.alanjagar.smsparing.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import hr.alanjagar.smsparing.R;
import hr.alanjagar.smsparing.model.Car;
import hr.alanjagar.smsparing.model.City;

/**
 * Created by ajagar on 21.10.2018..
 */

public class JsonCityRepository implements CityRepository {

    private Context context;

    public JsonCityRepository(Context context){

        this.context = context;
    }

    @Override
    public List<City> getAllCities() {
        InputStream is = context.getResources().openRawResource(R.raw.cities);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        if(jsonString == ""){
            return new ArrayList<>();
        }

        Type collectionType = new TypeToken<List<City>>(){}.getType();
        Gson gson = new Gson();
        List<City> cities = gson.fromJson(jsonString, collectionType);
        return cities;
    }
}
