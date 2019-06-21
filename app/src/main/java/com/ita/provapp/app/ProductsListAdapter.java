package com.ita.provapp.app;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ita.provapp.common.json.Product;

import java.util.ArrayList;

public class ProductsListAdapter implements ListAdapter {
    private ArrayList<Product> arrayList;
    private Context context;
    public ProductsListAdapter(Context context, ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.product_list_element, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView name = convertView.findViewById(R.id.name);
            TextView price = convertView.findViewById(R.id.price);
            TextView description = convertView.findViewById(R.id.description);
            name.setText(product.getName());
            price.setText(String.valueOf(product.getPrice()));
            description.setText(product.getDescription());
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
