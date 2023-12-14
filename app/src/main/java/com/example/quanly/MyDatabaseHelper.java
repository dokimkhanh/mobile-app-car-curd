package com.example.quanly;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QL_VanTai.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "QLOTO";

    private static final String COLUMN_BKX = "bienkiemsoat";
    private static final String COLUMN_TENXE = "tenxe";
    private static final String COLUMN_TENCHUXE = "tenchuxe";
    private static final String COLUMN_LOAIXE = "loaixe";
    private static final String COLUMN_HANGXE = "hangxe";
    private static final String COLUMN_NAMSANXUAT = "namsanxuat";

    private Context _context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this._context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_BKX + " TEXT PRIMARY KEY, " +
                COLUMN_TENXE + " TEXT, " +
                COLUMN_TENCHUXE + " TEXT, " +
                COLUMN_LOAIXE + " TEXT, " +
                COLUMN_HANGXE + " TEXT, " +
                COLUMN_NAMSANXUAT + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Oto> getAllData() {

        List<Oto> dataList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Oto oto = new Oto();
                oto.setBks(cursor.getString(cursor.getColumnIndex(COLUMN_BKX)));
                oto.setTenxe(cursor.getString(cursor.getColumnIndex(COLUMN_TENXE)));
                oto.setTenchuxe(cursor.getString(cursor.getColumnIndex(COLUMN_TENCHUXE)));
                oto.setLoaixe(cursor.getString(cursor.getColumnIndex(COLUMN_TENCHUXE)));
                oto.setHangxe(cursor.getString(cursor.getColumnIndex(COLUMN_HANGXE)));
                oto.setNamsanxuat(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NAMSANXUAT))));

                // Adding hangHoa to list
                dataList.add(oto);
            } while (cursor.moveToNext());
        }

        // close the cursor and return the list
        cursor.close();
        return dataList;
    }


    public void AddData(Oto oto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BKX, oto.getBks());
        cv.put(COLUMN_TENXE, oto.getTenxe());
        cv.put(COLUMN_TENCHUXE, oto.getTenchuxe());
        cv.put(COLUMN_LOAIXE, oto.getLoaixe());
        cv.put(COLUMN_HANGXE, oto.getHangxe());
        cv.put(COLUMN_NAMSANXUAT, oto.getNamsanxuat());

        long res = db.insert(TABLE_NAME, null, cv);
        if (res != -1) {
            Toast.makeText(_context, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(_context, "Không thể thêm dữ liệu", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void UpdateData(String bksOld, String bks, String tenxe, String tenchuxe, String loaixe, String hangxe, int namsx) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BKX, bks);
        cv.put(COLUMN_TENXE, tenxe);
        cv.put(COLUMN_TENCHUXE, tenchuxe);
        cv.put(COLUMN_LOAIXE, loaixe);
        cv.put(COLUMN_HANGXE, hangxe);
        cv.put(COLUMN_NAMSANXUAT, namsx);
        long result = db.update(TABLE_NAME, cv, COLUMN_BKX + " =?", new String[]{bksOld});

        if (result == -1) {
            Toast.makeText(_context, "Lỗi!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(_context, "Cập nhật dữ liệu thành công!!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    void deleteData(String bks) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, COLUMN_BKX + "=?", new String[]{bks});
        if (res == -1) {
            Toast.makeText(_context, "Không thể xoá bản ghi này", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(_context, "Xoá thành công bản ghi", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME);
            Toast.makeText(_context, "Đã xoá thành công tất cả bản ghi", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(_context, "Không thể xoá bản ghi", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    @SuppressLint("Range")
    Oto getDataById(String bks) {
        SQLiteDatabase db = this.getReadableDatabase();
        Oto model = new Oto();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_BKX + " =?", new String[]{bks});
            cursor.moveToFirst();
            if (cursor != null && cursor.moveToFirst()) {

                model.setBks(cursor.getString(cursor.getColumnIndex(COLUMN_BKX)));
                model.setTenxe(cursor.getString(cursor.getColumnIndex(COLUMN_TENXE)));
                model.setTenchuxe(cursor.getString(cursor.getColumnIndex(COLUMN_TENCHUXE)));
                model.setLoaixe(cursor.getString(cursor.getColumnIndex(COLUMN_LOAIXE)));
                model.setHangxe(cursor.getString(cursor.getColumnIndex(COLUMN_HANGXE)));
                model.setNamsanxuat(cursor.getInt(cursor.getColumnIndex(COLUMN_NAMSANXUAT)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }
}
