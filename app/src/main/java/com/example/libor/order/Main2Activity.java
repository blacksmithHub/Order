package com.example.libor.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    String[] cart;
    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cart = getIntent().getExtras().getStringArray("cart");

        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cart);

        listView = (ListView) findViewById(R.id.orderlist);
        listView.setAdapter(adapter);
    }

}
