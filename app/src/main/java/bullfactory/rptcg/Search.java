package bullfactory.rptcg;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Assaso on 08/06/2017.
 */
public class Search extends AppCompatActivity{

    private EditText etNO, etNA;
    private Button btNO, btNA;
    private ListView lvS;
    private Cursor cursor;
    private CursorAdapter ca;
    private DbManagerN dbmn;
    private DbManagerM dbmm;
    private DbManagerR dbmr;
    private String dbID, no, na;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.search);


        lvS = (ListView) findViewById(R.id.slv);
        etNO = (EditText) findViewById(R.id.etno);
        etNA = (EditText) findViewById(R.id.etna);
        btNO = (Button) findViewById(R.id.btno);
        btNA = (Button) findViewById(R.id.btna);
        dbmn = new DbManagerN(Search.this);
        dbmm = new DbManagerM(Search.this);
        dbmr = new DbManagerR(Search.this);

        btNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   new BuscarNumeroTask().execute();
                }
        });

        btNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNA.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Debes agregar un nombre, la primera letra siempre mayuscula", Toast.LENGTH_SHORT).show();
                }else {
                    new BuscarNombreTask().execute();
                }
            }
        });
    }

    private class BuscarNumeroTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            dbID = getIntent().getStringExtra("DEX");
            no = etNO.getText().toString();
            Toast.makeText(getApplicationContext(), "Creeando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            switch (dbID){
                case "p":
                    cursor = dbmn.buscarNacionalNO(no);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }
                    break;
                case "m":
                    cursor = dbmm.buscarMegaNO(no);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }
                    break;
                case "r":
                    cursor = dbmr.buscarRegionalNO(no);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }
                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            if (ca != null) {
                lvS.setAdapter(ca);
                Toast.makeText(getApplicationContext(), "Finalizado...", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Valor no encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class BuscarNombreTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            dbID = getIntent().getStringExtra("DEX");
            na = etNA.getText().toString();
            Toast.makeText(getApplicationContext(), "Creeando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            switch (dbID){
                case "p":
                    cursor = dbmn.buscarNacionalNA(na);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }
                    break;
                case "m":
                    cursor = dbmm.buscarMegaNA(na);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }
                    break;
                case "r":
                    cursor = dbmr.buscarRegionalNA(na);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Search.this, cursor, dbID);
                    }


                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            if (ca != null) {
                lvS.setAdapter(ca);
                Toast.makeText(getApplicationContext(), "Finalizado...", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Valor no encontrado", Toast.LENGTH_SHORT).show();
            }
            }
    }
}
