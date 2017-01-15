package com.example.rolli.ghid_gradina_botanica;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class FilterManager extends AppCompatActivity {

    ListView listView ;
    String plantCategory;
    TextView plantText;
    CustomListAdapter listAdapter;
    DBManager db = new DBManager(this, null, null ,1);
    ArrayList<ItemModel> list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_plants);

        String plantCategoryLocal = new String();
        intent =  new Intent(this, Item.class);
        int counter = 0;
        int size = 0;

        db.createList();
        list = db.getItemModel();

        plantText = (TextView) findViewById(R.id.plantText);
        listView = (ListView) findViewById(R.id.itemList);
        Bundle extras = getIntent().getExtras();
        plantCategory = extras.getString("plantCategory");

        plantText.setText(plantCategory);


        Log.i("tag", Integer.toString(list.size()));


        if (plantCategory.equals("Plante medicinale")) {
            plantCategoryLocal = "medicinala";
        } else if(plantCategory.equals("Plante ornamentale")) {
            plantCategoryLocal = "ornamentala";
        } else if(plantCategory.equals("Plante otravitoare")) {
            plantCategoryLocal = "toxica";
        }

        for(int i = 0; i < list.size(); i++ ) {
            if(list.get(i).getItemCategory().equals(plantCategoryLocal)) {
                size ++;
            }
        }

        String[] values = new String[size];
        String[] imgid = new String[size];

        for(int i = 0; i < list.size(); i++ ) {
            if(list.get(i).getItemCategory().equals(plantCategoryLocal)) {
                Log.i("tag", plantCategoryLocal);
                values[counter] = list.get(i).getItemName();
                imgid[counter] = list.get(i).getItemImg();
                counter ++;
            }
        }


        listAdapter = new CustomListAdapter(this , values , imgid);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                String  itemValue = (String) listView.getItemAtPosition(position);

                intent.putExtra("item", itemValue);
                startActivity(intent);

            }

        });

    }


    public class CustomListAdapter extends ArrayAdapter <String> {

        private final Activity context;
        private final String[] itemname;
        private final String[] imgid;

        public CustomListAdapter(Activity context, String[] itemname, String[] imgid) {
            super(context, R.layout.list_item_view, itemname);

            this.context = context;
            this.itemname = itemname;
            this.imgid = imgid;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.list_item_view, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
            extratxt.setTextColor(Color.WHITE);
            txtTitle.setText(itemname[position]);
            imageView.setImageResource(getResources().getIdentifier(imgid[position], "drawable", getPackageName()));
            extratxt.setText("Click pentru detalii despre " + itemname[position]);
            return rowView;

        }

    }


}
