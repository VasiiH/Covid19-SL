package com.vasi.covidfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.annotation.Nullable;


public class SingleListItem extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.setContentView(R.layout.single_list_item_view);

        TextView txtProduct = (TextView)findViewById(R.id.center_label);
        Intent i = getIntent();
        String product = i.getStringExtra("product");
        txtProduct.setText(product);


    }
}
