package dev.rk.listme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dev.rk.listme.R;
import dev.rk.listme.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;
    private LayoutInflater inflater;
    private int layoutId;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);

        this.context = context;
        this.products = objects;
        inflater = LayoutInflater.from(context);
        layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product product = products.get(position);
        View view = convertView == null ? inflater.inflate(this.layoutId, parent, false) : convertView;
        ((TextView)view.findViewById(R.id.idText)).setText(String.valueOf(product.id));
        ((TextView)view.findViewById(R.id.priceText)).setText(String.valueOf(product.price));
        ((ImageView)view.findViewById(R.id.productImage)).setImageResource(product.imgId);
        ((TextView)view.findViewById(R.id.description)).setText(String.valueOf(product.description));

        return  view;
    }
}

