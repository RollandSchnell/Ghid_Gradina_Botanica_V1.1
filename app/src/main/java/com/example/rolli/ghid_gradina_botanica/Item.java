package com.example.rolli.ghid_gradina_botanica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Item extends AppCompatActivity {


    ImageView imageView;
    TextView text;
    TextView nameText;
    TextView categoryText;
    Bundle bundle;
    DBManager db = new DBManager(this, null, null ,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);


        imageView = (ImageView) findViewById(R.id.imageView);
        bundle = getIntent().getExtras();
        text = (TextView) findViewById(R.id.itemText);
        nameText = (TextView) findViewById(R.id.nameText);
        categoryText = (TextView) findViewById(R.id.categoryText);
        openItem(bundle.getString("item"));

        /*if( bundle.getString("item").equals("Eucalyptus") ) {
            imageView.setImageResource(R.drawable.eucalyptus);
            text = (TextView) findViewById(R.id.itemText);
            text.setText(eucalyptusText);
        } else if( bundle.getString("item").equals("Allium") ) {
            imageView.setImageResource(R.drawable.allium);
            text = (TextView) findViewById(R.id.itemText);
            text.setText(alliumText);
        }*/


    }


    public void openItem(String item) {


        int notFound = 0;
        db.createList();
        ArrayList<ItemModel> list = db.getItemModel();
        String[] names = new String[list.size()];

        for(int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getItemName();
            Log.i("tag", list.get(i).getItemName());
        }


        for(int i = 0;i < names.length; i++) {
            if(names[i].equals(item)) {
                String name = "R.drawable." + list.get(i).getItemName();
                //imageView.setImageResource(Integer.parseInt(name));
                imageView.setImageResource(getResources().getIdentifier(list.get(i).getItemImg(), "drawable", getPackageName()));
                text.setText(list.get(i).getItemText());
                String nameString = "Nume: " + list.get(i).getItemName();
                String categoryString = "Categorie: " + list.get(i).getItemCategory();
                nameText.setText(nameString);
                categoryText.setText(categoryString);
                //Log.i("TAG", "found item:" + list.get(i).getItemName() + "int:" + Integer.parseInt(name));
            } else {
                notFound++;
            }
        }

        if(notFound == names.length) {
            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.not_found);
        }

    }
}

