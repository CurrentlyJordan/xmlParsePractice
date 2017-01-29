package nyc.c4q.jordansmith.practicexmlparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import nyc.c4q.jordansmith.practicexmlparser.Model.Item;
import nyc.c4q.jordansmith.practicexmlparser.Model.Rss;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> itemList;
    public static final String TAG = MainActivity.class.getSimpleName();
    String copyright;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.park_event_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ParkEventAdapter());
        lookForThings();


    }

    private void lookForThings() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.nycgovparks.org/")
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ParksService parkService = retrofit.create(ParksService.class);
        Call<Rss> httpRequest = parkService.getParkEvents();
        httpRequest.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                Log.d(TAG, "success " + response.body().toString());
                try {
                    if (response.isSuccessful()) {
                        Rss parkApiResponse = response.body();
                            itemList = parkApiResponse.getChannel().getItem();
                        copyright = parkApiResponse.getChannel().getCopyright();
                            ParkEventAdapter adapter = (ParkEventAdapter) recyclerView.getAdapter();
                            adapter.setData(itemList);
                            recyclerView.setAdapter(adapter);
                            Log.d("Tag", "my size is " + adapter.getItemCount());


                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.d("failure", "no connection");


                Toast.makeText(getApplicationContext(), "Error getting park data", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
