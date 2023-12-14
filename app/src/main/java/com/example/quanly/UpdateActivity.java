package com.example.quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    private EditText editBks_update, editTenXe_update, editTenChuXe_update, editLoaiXe_update, editHangXe_update, editNamSanXuat_update;
    private Button btn_update, btn_delete;
    private FloatingActionButton btn_back;
    private String _bks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        map();
        MyDatabaseHelper db = new MyDatabaseHelper(UpdateActivity.this);

        if (getIntent().hasExtra("bks")) {
            _bks = getIntent().getStringExtra("bks");
            Oto model = db.getDataById(_bks);

            editBks_update.setText(model.getBks());
            editTenXe_update.setText(model.getTenxe());
            editTenChuXe_update.setText(model.getTenchuxe());
            editLoaiXe_update.setText(String.valueOf(model.getLoaixe()));
            editHangXe_update.setText(String.valueOf(model.getHangxe()));
            editNamSanXuat_update.setText(String.valueOf(model.getNamsanxuat()));

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String bks = editBks_update.getText().toString().trim();
                    String tenXe = editTenXe_update.getText().toString();
                    String tenChuXe = editTenChuXe_update.getText().toString();
                    String loaiXe = editLoaiXe_update.getText().toString();
                    String hangXe = editHangXe_update.getText().toString();

                    int namSanXuat = (editNamSanXuat_update.getText().toString().trim().isEmpty()) ? 2000 : Integer.parseInt(editNamSanXuat_update.getText().toString().trim());

                    if (bks.isEmpty() || tenXe.trim().isEmpty() || tenChuXe.trim().isEmpty() || loaiXe.trim().isEmpty() || hangXe.trim().isEmpty()) {
                        Toast.makeText(UpdateActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (db.getDataById(bks).getBks() != null) {
                        Toast.makeText(UpdateActivity.this, "Xe này đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!bks.equalsIgnoreCase(_bks)) { //Neu ma truyen vao va ma hien tai !=
                        if (db.getDataById(bks).getBks() != null) {
                            Toast.makeText(UpdateActivity.this, "Biển này đã có trong cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    db.UpdateData(_bks, bks, tenXe, tenChuXe, loaiXe, hangXe, namSanXuat);
                }
            });
        } else {
            Toast.makeText(this, "Không có thông tin", Toast.LENGTH_SHORT).show();
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void map() {
        editBks_update = findViewById(R.id.edit_bks_update);
        editTenXe_update = findViewById(R.id.edit_tenxe_update);
        editTenChuXe_update = findViewById(R.id.edit_tenchuxe_update);
        editLoaiXe_update = findViewById(R.id.edit_loaixe_update);
        editHangXe_update = findViewById(R.id.edit_hangxe_update);
        editNamSanXuat_update = findViewById(R.id.edit_namsanxuat_update);

        btn_update = findViewById(R.id.btn_update);
        btn_back = findViewById(R.id.fbtn_back);
        btn_delete = findViewById(R.id.btn_delete);
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc chắn muốn xoá?");
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper db = new MyDatabaseHelper(UpdateActivity.this);
                db.deleteData(_bks);
                finish();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}