package bullfactory.rptcg;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Assaso on 09/06/2017.
 */
public class Add extends AppCompatActivity {

    private EditText etRNO, etRNA;
    private Spinner sQA;
    private Button btADD;
    private ListView lvADD;
    private ArrayAdapter aaS;
    private String[] calidad;
    private DbManagerN dbmn;
    private DbManagerM dbmm;
    private DbManagerR dbmr;
    private String sQAT, sQAV, noV, naV, dbID, message, qua, total, totS;
    private Cursor cursor, cursor1;
    private CursorAdapter ca;
    private int sQAI, bQAI, tot;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.addpk);



        etRNO = (EditText) findViewById(R.id.etrno);
        etRNA = (EditText) findViewById(R.id.etrna);
        btADD = (Button) findViewById(R.id.btadd);
        lvADD = (ListView) findViewById(R.id.lvadd);
        sQA = (Spinner) findViewById(R.id.sqa);
        calidad = new String[]{"Common", "Reverse", "Holo", "Special", "Full art", "Rainbow full art", "Secret"};
        aaS = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, calidad);
        sQA.setAdapter(aaS);


        dbmn = new DbManagerN(this);
        dbmm = new DbManagerM(this);
        dbmr = new DbManagerR(this);


        btADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sQAT = sQA.getSelectedItem().toString();
                if (!etRNO.getText().toString().equals("") || !etRNA.getText().toString().equals("") || !sQAT.equals("")){
                    switch (sQAT){
                        case "Common":
                            sQAV = "q01";
                            sQAI = 1;
                            break;
                        case "Reverse":
                            sQAV = "q02";
                            sQAI = 2;
                            break;
                        case "Holo":
                            sQAV = "q03";
                            sQAI = 3;
                            break;
                        case "Special":
                            sQAV = "q04";
                            sQAI = 4;
                            break;
                        case "Full art":
                            sQAV = "q05";
                            sQAI = 5;
                            break;
                        case "Rainbow full art":
                            sQAV = "q06";
                            sQAI = 6;
                            break;
                        case "Secret":
                            sQAV = "q07";
                            sQAI = 7;
                            break;
                    }

                    new AgregarTask().execute();
                }
            }
        });
    }

    private class AgregarTask extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute(){
            message = "Terminado...";
            dbID = getIntent().getStringExtra("DEX");
            noV = etRNO.getText().toString();
            naV = etRNA.getText().toString();
            Toast.makeText(getApplicationContext(), "Sobre escribiendo...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            switch (dbID){
                case "p":
                    cursor1 = dbmn.buscarNacionalNO(noV);
                    if (cursor1.moveToFirst()) {
                        qua = cursor1.getString(cursor1.getColumnIndex("calidad"));
                    }
                        if (qua.equals("")) {
                            total = "1";
                        cursor = dbmn.cargarAgregadoNacional(noV, naV, sQAV, total);
                        if (cursor.moveToFirst()) {
                            ca = new CursorAdapter(Add.this, cursor, dbID);
                        }
                    }else{
                            if (cursor1.moveToFirst()){
                                totS = cursor1.getString(cursor1.getColumnIndex("total"));
                                tot = Integer.parseInt(totS);
                            }
                        switch (qua){
                            case "q01":
                                bQAI = 1;
                                break;
                            case "q02":
                                bQAI = 2;
                                break;
                            case "q03":
                                bQAI = 3;
                                break;
                            case "q04":
                                bQAI = 4;
                                break;
                            case "q05":
                                bQAI = 5;
                                break;
                            case "q06":
                                bQAI = 6;
                                break;
                            case "q07":
                                bQAI = 7;
                                break;
                        }

                        if (bQAI >= sQAI){
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor1 = dbmn.cargarNewNacional(noV, totS);
                            ca = new CursorAdapter(Add.this, cursor1, dbID);
                            message = "NO HA MEJORADO LA CALIDAD";
                        }else{
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor = dbmn.cargarCalidadNacional(noV, sQAV, totS);
                            ca = new CursorAdapter(Add.this, cursor, dbID);
                        }
                    }
                    break;
                case "m":
                    cursor1 = dbmm.buscarMegaNO(noV);
                    if (cursor1.moveToFirst()) {
                        qua = cursor1.getString(cursor1.getColumnIndex("calidad"));
                    }
                    if (qua.equals("")) {
                    cursor = dbmm.cargarAgregadoMega(noV, naV, sQAV, total);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Add.this, cursor, dbID);
                    }
                    }else {
                        if (cursor1.moveToFirst()) {
                            totS = cursor1.getString(cursor1.getColumnIndex("total"));
                            tot = Integer.parseInt(totS);
                        }
                        switch (qua) {
                            case "q01":
                                bQAI = 1;
                                break;
                            case "q02":
                                bQAI = 2;
                                break;
                            case "q03":
                                bQAI = 3;
                                break;
                            case "q04":
                                bQAI = 4;
                                break;
                            case "q05":
                                bQAI = 5;
                                break;
                            case "q06":
                                bQAI = 6;
                                break;
                            case "q07":
                                bQAI = 7;
                                break;
                        }

                        if (bQAI >= sQAI) {
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor1 = dbmm.cargarNewMega(noV, totS);
                            ca = new CursorAdapter(Add.this, cursor1, dbID);
                            message = "NO HA MEJORADO LA CALIDAD";
                        } else {
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor = dbmm.cargarCalidadMega(noV, sQAV, totS);
                            ca = new CursorAdapter(Add.this, cursor, dbID);
                        }
                    }
                    break;
                case "r":
                    cursor1 = dbmr.buscarRegionalNO(noV);
                    if (cursor1.moveToFirst()) {
                        qua = cursor1.getString(cursor1.getColumnIndex("calidad"));
                    }
                    if (qua.equals("")) {
                    cursor = dbmr.cargarAgregadoRegional(noV, naV, sQAV, total);
                    if (cursor.moveToFirst()){
                        ca = new CursorAdapter(Add.this, cursor, dbID);
                    }
                    }else {
                        if (cursor1.moveToFirst()) {
                            totS = cursor1.getString(cursor1.getColumnIndex("total"));
                            tot = Integer.parseInt(totS);
                        }
                        switch (qua) {
                            case "q01":
                                bQAI = 1;
                                break;
                            case "q02":
                                bQAI = 2;
                                break;
                            case "q03":
                                bQAI = 3;
                                break;
                            case "q04":
                                bQAI = 4;
                                break;
                            case "q05":
                                bQAI = 5;
                                break;
                            case "q06":
                                bQAI = 6;
                                break;
                            case "q07":
                                bQAI = 7;
                                break;
                        }

                        if (bQAI >= sQAI) {
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor1 = dbmr.cargarNewRegional(noV, totS);
                            ca = new CursorAdapter(Add.this, cursor1, dbID);
                            message = "NO HA MEJORADO LA CALIDAD";
                        } else {
                            tot = tot + 1;
                            totS = "" + tot;
                            cursor = dbmr.cargarCalidadRegional(noV, sQAV, totS);
                            ca = new CursorAdapter(Add.this, cursor, dbID);
                        }
                    }
                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            if(ca != null){
                lvADD.setAdapter(ca);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "No se encontro el registro...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
