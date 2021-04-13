package com.riseinsteps.androidlearnings;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riseinsteps.androidlearnings.adapter.RxJavaAdapter;
import com.riseinsteps.androidlearnings.model.Model;
import com.riseinsteps.androidlearnings.roomdb.RoomRepository;
import com.riseinsteps.androidlearnings.rxjava.RetrofitClient;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaActivity";
    private TextView page, perPage, total, totalPages, URL, text;
    private RecyclerView recyclerView;
    private RxJavaAdapter rxJavaAdapter;
    private Subscription subscription;
    private RoomRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        initComponents();

        fetchDataFromApi();

    }

    private void initComponents() {
        page = findViewById(R.id.rx_page);
        perPage = findViewById(R.id.rx_per_page);
        total = findViewById(R.id.rx_total);
        totalPages = findViewById(R.id.rx_total_pages);
        URL = findViewById(R.id.rx_url);
        text = findViewById(R.id.rx_text);
        recyclerView = findViewById(R.id.rx_java_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchDataFromApi() {
        subscription = RetrofitClient.getInstance()
                .getModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Model>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Run");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Run");
                    }

                    @Override
                    public void onNext(Model model) {
                        Log.d(TAG, "onNext: Run");
                        page.setText("Page: " + model.getPage().toString());
                        perPage.setText("Per Page: " + model.getPer_page().toString());
                        total.setText("Total: " + model.getTotal().toString());
                        totalPages.setText("Total Pages: " + model.getTotal_pages().toString());
                        URL.setText("Url: " + model.getSupport().getUrl());
                        text.setText("Text: " + model.getSupport().getText());
                        rxJavaAdapter = new RxJavaAdapter(getApplicationContext(), model.getData());
                        recyclerView.setAdapter(rxJavaAdapter);

                        BackgroundThread backgroundThread = new BackgroundThread(model);
                        new Thread(backgroundThread).start();
                        Toast.makeText(RxJavaActivity.this, "Saving data locally(in Room)", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    private class BackgroundThread implements Runnable {
        private Model model;

        public BackgroundThread(Model model) {
            this.model = model;
        }

        @Override
        public void run() {

            repository = new RoomRepository(getApplication());
            repository.insert(model);

            Log.d(TAG, "Display data via Room: " + repository.getModel().getData().get(2).getFirst_name());
        }
    }
}