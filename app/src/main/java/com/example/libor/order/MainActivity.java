package com.example.libor.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button add, minus, addtocart, viewcart;
    Spinner item;
    EditText count, price;
    Intent i;

    int increase, prices, position, amount, counter, initial;
    String[] cart = {"(empty)", "(empty)", "(empty)"};
    String text;
    Boolean buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button)findViewById(R.id.add);
        minus = (Button)findViewById(R.id.minus);
        addtocart = (Button)findViewById(R.id.addcart);
        viewcart = (Button)findViewById(R.id.viewcart);

        item = (Spinner)findViewById(R.id.item);

        count = (EditText)findViewById(R.id.count);
        price = (EditText)findViewById(R.id.price);

        i = new Intent(getApplicationContext(), Main2Activity.class);

        position = 0;
        increase = 0;
        initial = 0;

        buy = false;

        add.setEnabled(false);
        minus.setEnabled(false);

        count.setText("" + 0);
        price.setText("" + 0);
        item();
        view();
        add();
    }
    public void view()
    {
        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(buy == false)
                {
                    Toast.makeText(MainActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(i);
                }

            }
        });
    }
    public void add()
    {
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initial++;

                if(price.getText().toString().equals("0"))
                {
                    Toast.makeText(MainActivity.this, "Please specify an order", Toast.LENGTH_SHORT).show();
                }
                else if(initial == 4)
                {
                    Toast.makeText(MainActivity.this, "Order exceeded!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cart[counter] = "(" + count.getText().toString() + ") " + text + " ₱" + prices + " ► ₱" + String.valueOf(prices*Integer.parseInt(count.getText().toString()));
                    i.putExtra("cart", cart);
                    counter++;
                    Toast.makeText(MainActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    buy = true;
                }

            }
        });
    }

    public void item()
    {
        item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                if(position != 0)
                {
                    price();
                    count();

                    position = 0;
                    increase = 0;

                    text = item.getSelectedItem().toString();

                    count.setText("" + 1);
                    price.setText("" + prices);

                    add.setEnabled(true);
                    minus.setEnabled(true);

                }
                else
                {
                    position = 0;
                    increase = 0;

                    count.setText("" + 0);
                    price.setText("" + 0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void count()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase++;
                count.setText("" + increase);
                amount = prices * increase;
                price.setText("" + amount);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase--;
                count.setText("" + increase);
                amount = prices * increase;
                price.setText("" + amount);
            }
        });
    }

    public void price()
    {
        if(position == 1)
        {
            prices = 50;
        }
        else if(position == 2)
        {
            prices = 80;
        }
        else if(position == 3)
        {
            prices = 70;
        }
        else
        {
            prices = 0;
        }
    }
}
