package com.example.rolli.ghid_gradina_botanica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String strResult;
    final Activity activity = this;
    DBManager db = new DBManager(this, null, null, 1);
    ImageView plant, qr_scanner, contact, stiati_ca, feedback, harta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        plant=(ImageView)findViewById(R.id.plant);
        qr_scanner=(ImageView)findViewById(R.id.qr_scanner);
        contact=(ImageView) findViewById(R.id.contact);
        stiati_ca=(ImageView) findViewById(R.id.stiati_ca);
        feedback=(ImageView) findViewById(R.id.feedback);
        harta=(ImageView) findViewById(R.id.harta);

        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Plant.class);
                startActivity(i);
            }
        });
        qr_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setPrompt("Scanning");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ContactManager.class);
                startActivity(i);
            }
        });
        stiati_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), StiaticaManager.class);
                startActivity(i);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FeedbackManager.class);
                startActivity(i);
            }
        });
        harta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MapManager.class);
                startActivity(i);
            }
        });


        initDB();
        //addItem();
        //deleteElements();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        strResult = result.getContents();


        if( result != null ) {

            if( result.getContents() == null ) {
                Toast.makeText(this, "You canceled the scan", Toast.LENGTH_SHORT).show();

            } else {
                QRManager man = new QRManager();
                Intent intent = man.sendItem(this, strResult);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void addItem() {

        //ItemModel model = new ItemModel("Allium", "allium", "This is Allium text", "Ornamental");
        ItemModel model = new ItemModel("lalea", "lalea", "Tulipa, în mod obişnuit denumită  lalea, prezintă aproximativ 150 de specii de plante cu floribulboase din familia Liliaceae. Sortimentul originar de specii cuprinde Europa de Sud, Africa de Nord şi Asia , din Anatolia şi Iran la vest – nord-est de China.\n" +
                "Lalelele pot să crească chiar pe anotimp friguros şi cu zăpadă multă. Plantele au de obicei, 2 – 6 frunze, cu unele specii care au până la 12 frunze. ", "ornamentala");
        db.addProduct(model);

        model = new ItemModel("palmier", "palmier", "Plantele din grupa palmierilor se caracterizeaza printr-o tulpina cilindrica, neramificata, adesea inalta, dar uneori foarte scurta, terminata cu un grup de frunze.\n" +
                "\n" +
                "\n" +
                "Pe tulpina se observa cicatricele si resturile de la frunzele uscate si cazute sau taiate.Frunzele deosebit de mari pot fi penate (Phoenix) sau palmate, in evantai (Chamaerops).", "ornamentala");
        db.addProduct(model);

        model = new ItemModel("aloe vera", "aloe_vera", "Aloe vera este o specie de plante suculente care este probabil originară din nordul Africii. Extractele de A. vera sunt folosite pe scară largă în cometica și medicina alternativa, susținându-se că au proprietăți de reîntinerire, vindecare sau analgezice.Există doar câteva dovezi preliminare că extractele de A. vera ar putea fi utile în tratamentul diabetului și în cazul nivelului ridicat de lipide în organismul uman.\n", "medicinala");
        db.addProduct(model);

        model = new ItemModel("dalhia", "dalhia", "Aloe vera este o specie de plante suculente care este probabil originară din nordul Africii. Extractele de A. vera sunt folosite pe scară largă în cometica și medicina alternativa, susținându-se că au proprietăți de reîntinerire, vindecare sau analgezice.Există doar câteva dovezi preliminare că extractele de A. vera ar putea fi utile în tratamentul diabetului și în cazul nivelului ridicat de lipide în organismul uman.\n" +
                "\n" +
                "\n", "ornamentala");
        db.addProduct(model);

        model = new ItemModel("feriga comuna", "feriga_comuna", "Aloe vera este o specie de plante suculente care este probabil originară din nordul Africii. Extractele de A. vera sunt folosite pe scară largă în cometica și medicina alternativa, susținându-se că au proprietăți de reîntinerire, vindecare sau analgezice.Există doar câteva dovezi preliminare că extractele de A. vera ar putea fi utile în tratamentul diabetului și în cazul nivelului ridicat de lipide în organismul uman.", "medicinala");
        db.addProduct(model);

        model = new ItemModel("nufar", "nufar", "Nuferii sunt plante perene(rar putand fii si plante anuale) care trăiesc în bălți,lacuri,ape curgătoare. Ele au tulpini lungi (rizomi) înfipte în mâl.Frunzele se pot clasifica în două tipuri: un tip de frunze subacvatice și frunzele plutitoare de la suprafața apei cu camere cu aer, în formă de inimă și cu un pețiol lung. Florile sunt frecvent aromate, au petalele de culori diferite așezate pe mai multe rânduri.", "acvatica");
        db.addProduct(model);

        model = new ItemModel("cactus", "cactus", "Aloe vera este o specie de plante suculente care este probabil originară din nordul Africii. Extractele de A. vera sunt folosite pe scară largă în cometica și medicina alternativa, susținându-se că au proprietăți de reîntinerire, vindecare sau analgezice.Există doar câteva dovezi preliminare că extractele de A. vera ar putea fi utile în tratamentul diabetului și în cazul nivelului ridicat de lipide în organismul uman.", "spinoasa");
        db.addProduct(model);

        model = new ItemModel("galbenele", "galbenele", "Gălbenelele sunt originare din zona mediteraneană, cresc spontan sau cultivate ca plante decorative, în gradini, pe lângă zidurile caselor, poteci, drumuri sau prin parcuri,  şi sunt răspândite şi recunoscute în toata Europa încă din Evul Mediu.\n" +
                "\n" +
                "În trecut era numită şi \"floarea ploilor\" deoarece atunci când nu se deschidea era sigur că vin ploile..", "medicinala");
        db.addProduct(model);

        model = new ItemModel("tisa", "tisa", "Tisa este un arbore care poate ajunge până la 6-15 m în înălțime și 1,5-6 m în diametru. Se aseamănă cu bradul, de care se deosebește prin frunzele de o culoare mai verde, iar pe fața inferioară verzi palide. Ele sunt dispuse pectinat pe ramurile laterale. Spre deosebire de celelalte conifere, tisa este o planta dioica.Cu excepția arilului roșu, toate organele plantei sunt toxice, în popor, spunându-se că până și umbra tisei este otrăvitoare. Toxicitatea arborelui este determinată de un alcaloid numit taxină și de un glicozid - taxacatina și este dovedit că gradul de toxicitate a frunzelor variază în funcție de anotimp, iarna acestea fiind mai toxice decât în timpul verii.", "toxica");
        db.addProduct(model);
    }

    public void deleteElements() {

        db.deleteElements();
    }

    public void initDB() {

        db.createList();
        ArrayList<ItemModel> list = db.getItemModel();
        if(list.size() == 0) {
            Log.i("tag", "empty");
            addItem();
        }
    }



}
