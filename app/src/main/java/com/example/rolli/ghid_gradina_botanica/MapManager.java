package com.example.rolli.ghid_gradina_botanica;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MapManager extends AppCompatActivity {

    ImageView imgGradina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        imgGradina = (ImageView) findViewById(R.id.imgGradina);
        imgGradina.setImageResource(R.drawable.harta_gradina_zones);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == event.ACTION_UP) {

            int x = 0;
            int y = 0;

            x = (int) event.getX();
            y = (int) event.getY();

            //zone 1
            if (x > 291 && x < 583) {
                if (y > 295 && y < 512) {
                    Intent i = new Intent(this, ZoneManager.class);
                    i.putExtra("zone", "1");
                    startActivity(i);
                }
            }

            //zone 2
            if (x > 30 && x < 331) {
                if (y > 324 && y < 712) {
                   Intent i = new Intent(this, ZoneManager.class);
                    i.putExtra("zone", "2");
                    startActivity(i);
                }
            }

            //zone 3
            if (x > 322 && x < 654) {
                if (y > 510 && y < 865) {
                    Intent i = new Intent(this, ZoneManager.class);
                    i.putExtra("zone", "3");
                    startActivity(i);
                }
            }

            Log.i( "Tag", x + " " + y);

        }


        return true;
    }
}
