package com.riseinsteps.androidlearnings;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riseinsteps.androidlearnings.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        list.add("Response with RxJava");
        list.add("Response with Dagger2");
        MainAdapter mainAdapter = new MainAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener(new MainAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                if (pos == 0) {
                    Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                    startActivity(intent);
                } else if (pos == 1) {
                    Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}