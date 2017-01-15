package com.example.rolli.ghid_gradina_botanica;


import android.content.Context;
import android.content.Intent;

public class QRManager {

    public Intent sendItem(Context context, String item) {

        Intent intent = new Intent(context, Item.class);
        intent.putExtra("item", item);

        return intent;
    }
}
