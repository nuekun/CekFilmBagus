package com.nue.cekfilmbagus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nue.cekfilmbagus.adapter.AdapterFilm;
import com.nue.cekfilmbagus.api.ApiInterface;
import com.nue.cekfilmbagus.api.MovieResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public  static  String BASE_URL = "https://api.themoviedb.org";
    public  static  int page = 1;
    public  static  String api_key = "9666591218413e9ec0a8f66fea70f674";
    public  static  String language = "en-US";
    public  static  String category = "popular";
    ArrayList<HashMap<String, String>> list_data;
    private RecyclerView recFilm;
    private LinearLayoutManager linearLayoutManager;
//    TextView txtJudul1,txtJudul2,txtKeterangan1,txtKeterangan2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recFilm = findViewById(R.id.RecMain);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recFilm.setLayoutManager(llm);


//        txtJudul1=findViewById(R.id.judul1);
//        txtKeterangan1=findViewById(R.id.keterangan1);
//        txtJudul2=findViewById(R.id.judul2);
//        txtKeterangan2=findViewById(R.id.keterangan2);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResult> call = myInterface.ListOfMovie(category,api_key,language,page);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult daftarJudul = response.body();
                List<MovieResult.ResultsBean> Hasil = daftarJudul.getResults();
                list_data = new ArrayList<HashMap<String, String>>();

                for (int a = 0; a < Hasil.size(); a++) {
                    MovieResult.ResultsBean film = Hasil.get(a);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("judul", film.getTitle());
                    map.put("keterangan", film.getOverview());
                    map.put("tanggal", film.getRelease_date());
                    map.put("gambar", film.getPoster_path());
                    list_data.add(map);
                }

                    AdapterFilm adapter = new AdapterFilm(MainActivity.this, list_data);
                    recFilm.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                t.printStackTrace();
            }
        });




    }
}

