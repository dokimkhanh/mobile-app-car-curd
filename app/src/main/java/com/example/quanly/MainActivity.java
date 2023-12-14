package com.example.quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fbutton_add, fbtn_deleteAll;
    ListView myListView;
    List<Oto> myListData = new ArrayList<Oto>();
    MyAdapter myAdapter;
    ImageView imgView_data;
    TextView textView_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();

        MyDatabaseHelper myDB = new MyDatabaseHelper(this);
        myListData = myDB.getAllData();

        if (myListData.size() < 1) {
            imgView_data.setVisibility(View.VISIBLE);
            textView_data.setVisibility(View.VISIBLE);
        }

        myAdapter = new MyAdapter(this, 1, this.myListData);
        myListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


        fbutton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        fbtn_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn muốn xoá tất cẩ??");
                builder.setPositiveButton("Xác nhận xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDB.deleteAllData();
                        myAdapter.notifyDataSetChanged();
                        finish();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });

    }

    private void map() {
        fbutton_add = findViewById(R.id.fbtn_add);
        myListView = findViewById(R.id.listData);
        fbtn_deleteAll = findViewById(R.id.fbtn_deleteAll);
        textView_data = findViewById(R.id.textViewData);
        imgView_data = findViewById(R.id.imageViewData);
    }
}