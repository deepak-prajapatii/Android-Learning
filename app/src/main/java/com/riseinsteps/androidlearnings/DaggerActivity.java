package com.riseinsteps.androidlearnings;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riseinsteps.androidlearnings.adapter.DaggerAdapter;
import com.riseinsteps.androidlearnings.daggercomponent.DaggerApi;
import com.riseinsteps.androidlearnings.model.Model;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DaggerActivity extends AppCompatActivity {
    private static final String TAG = "DaggerActivity";

    private TextView page, perPage, total, totalPages, URL, text;
    private RecyclerView recyclerView;
    private DaggerAdapter daggerAdapter;

    @Inject
    Retrofit retrofit;

    private DaggerApi daggerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        initComponents();

        ((BaseApplication) getApplication()).getNetworkAppComponent().injectActivity(this);
        daggerApi = retrofit.create(DaggerApi.class);
        daggerApi.getModel().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DaggerActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                page.setText("Page: " + response.body().getPage().toString());
                perPage.setText("Per Page: " + response.body().getPer_page().toString());
                total.setText("Total: " + response.body().getTotal().toString());
                totalPages.setText("Total Pages: " + response.body().getTotal_pages().toString());
                URL.setText("Url: " + response.body().getSupport().getUrl());
                text.setText("Text: " + response.body().getSupport().getText());

                daggerAdapter = new DaggerAdapter(getApplicationContext(), response.body().getData());
                recyclerView.setAdapter(daggerAdapter);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: Run");
            }
        });
    }

    private void initComponents() {
        page = findViewById(R.id.dagger_page);
        perPage = findViewById(R.id.dagger_per_page);
        total = findViewById(R.id.dagger_total);
        totalPages = findViewById(R.id.dagger_total_pages);
        URL = findViewById(R.id.dagger_url);
        text = findViewById(R.id.dagger_text);
        recyclerView = findViewById(R.id.dagger_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}