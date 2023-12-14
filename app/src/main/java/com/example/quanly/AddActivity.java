package com.example.quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {
    private EditText editBks, editTenXe, editTenChuXe, editLoaiXe, editHangXe, editNamSanXuat;
    private Button btn_add;
    private FloatingActionButton fbtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        map();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                String bks = editBks.getText().toString().trim();
                String tenXe = editTenXe.getText().toString();
                String tenChuXe = editTenChuXe.getText().toString();
                String loaiXe = editLoaiXe.getText().toString();
                String hangXe = editHangXe.getText().toString();

                int namSanXuat = (editNamSanXuat.getText().toString().trim().isEmpty()) ? 2000 : Integer.parseInt(editNamSanXuat.getText().toString().trim());

                if (bks.isEmpty() || tenXe.trim().isEmpty() || tenChuXe.trim().isEmpty() || loaiXe.trim().isEmpty() || hangXe.trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (myDB.getDataById(bks).getBks() != null) {
                    Toast.makeText(AddActivity.this, "Xe này đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }

                myDB.AddData(new Oto(bks, tenXe, tenChuXe, loaiXe, hangXe, namSanXuat));
            }
        });

        fbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void map() {
        editBks = findViewById(R.id.edit_bks);
        editTenXe = findViewById(R.id.edit_tenxe);
        editTenChuXe = findViewById(R.id.edit_tenchuxe);
        editLoaiXe = findViewById(R.id.edit_loaixe);
        editHangXe = findViewById(R.id.edit_hangxe);
        editNamSanXuat = findViewById(R.id.edit_namsanxuat);

        btn_add = findViewById(R.id.btn_them);
        fbtn_back = findViewById(R.id.fbtn_back);
    }
}