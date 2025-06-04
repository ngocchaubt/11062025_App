package com.example.k22411csampleproject.apdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.k22411csampleproject.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;
    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        //nhân bản giao diện theo từng vị trí position mà dữ liệu được duyệt qua
        View item=inflater.inflate(this.resource,null);
        //lúc này: Toàn bộ view nằm trong layout resource(item_advanced_product)
        //sẽ được mô hình hoa ướng đối tượng v đươc quản lý bởi biến item
        //tức l item là tổng tài view
        //như vậy muốn truy suất tới các view con trong n thì phải thông qua item.
        ImageView imgProduct=item.findViewById(R.id.imgProduct);
        TextView txtProductId=item.findViewById(R.id.txtProductId);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        ImageView imgCart = item.findViewById(R.id.imgCart);

        //lấy mô hình hướng đối tuợng được nhân bản ở vị trí position(đối số 1):
        Product p=getItem(position);
        //Rải dữ liệu của Product lên giao diện trong item
        imgProduct.setImageResource(p.getImage_id());
        txtProductId.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"(VNĐ)");
        //xử lý bấm vào nút mua...tính sau

        return item;
    }
}
