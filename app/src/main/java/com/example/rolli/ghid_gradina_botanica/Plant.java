package com.example.rolli.ghid_gradina_botanica;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;


public class Plant extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    String[] type = { "Alegeti categoria de plante...", "Plante Medicinale", "Plante Ornamentale", "Plante Otravitoare",  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg, View arg1, int position,long id) {
        switch(position)
        {
            case 0:
                break;
            case 1:
                Intent i=new Intent(this,FilterManager.class);
                i.putExtra("plantCategory", "Plante medicinale");
                startActivity(i);
                break;
            case 2:
                Intent ii=new Intent(this,FilterManager.class);
                ii.putExtra("plantCategory", "Plante ornamentale");
                startActivity(ii);
                break;
            case 3:
                Intent iii=new Intent(this,FilterManager.class);
                iii.putExtra("plantCategory", "Plante otravitoare");
                startActivity(iii);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}




