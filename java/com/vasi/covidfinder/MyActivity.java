package com.vasi.covidfinder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] quarantine_centers = getResources().getStringArray(R.array.quarantine_centers);
        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item,R.id.labels,quarantine_centers));

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView)view).getText().toString();

                Intent i = new Intent(getApplicationContext(),SingleListItem.class);
                i.putExtra("product",product);
                startActivity(i);

            }
        });
    }
}
