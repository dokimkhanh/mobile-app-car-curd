package com.example.quanly;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class MyAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<Oto> list;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);

        TextView textIndex = rowView.findViewById(R.id.textIndex);
        TextView textBks = rowView.findViewById(R.id.textBks);
        TextView texTenxe = rowView.findViewById(R.id.textTenXe);
        TextView textChuXe = rowView.findViewById(R.id.textChuXe);
        TextView textLoaiXe = rowView.findViewById(R.id.textLoaiXe);
        TextView textHangXe = rowView.findViewById(R.id.textHangXe);
        TextView textNamSx = rowView.findViewById(R.id.textNamSx);
        LinearLayout mainLayout = rowView.findViewById(R.id.mainLayout);

        // Lấy dữ liệu từ đối tượng HangHoaEntity tại vị trí position
        Oto hangHoa = list.get(position);

        // Hiển thị dữ liệu lên TextViews
        textIndex.setText(position + 1 + "");
        textBks.setText("BKS: " + hangHoa.getBks());
        texTenxe.setText("Tên xe: " + hangHoa.getTenxe());
        textChuXe.setText("Chủ xe: " + hangHoa.getTenchuxe());
        textLoaiXe.setText("Loại xe: " + hangHoa.getLoaixe() + "");
        textHangXe.setText("Hãng xe: " + hangHoa.getHangxe() + "");
        textNamSx.setText("NSX: " + hangHoa.getNamsanxuat() + "");

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("bks",list.get(position).getBks());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
