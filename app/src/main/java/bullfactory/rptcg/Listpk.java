package bullfactory.rptcg;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Assaso on 07/06/2017.
 */
public class Listpk extends AppCompatActivity {

    private ListView list;
    private String dbID;
    private DbManagerN dbmn;
    private DbManagerM dbmm;
    private DbManagerR dbmr;
    private Cursor cursor;
    private CursorAdapter ca;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.list);


        dbID = getIntent().getStringExtra("DEX");

        list = (ListView) findViewById(R.id.lv);

        if (dbID.equals("p")){
            dbmn = new DbManagerN(this);
            new cargarTaskn().execute();
        }

        if (dbID.equals("m")){
            dbmm = new DbManagerM(this);
            new cargarTaskm().execute();
        }

        if (dbID.equals("r")){
            dbmr = new DbManagerR(this);
            new cargarTaskr().execute();
        }

    }

    private class cargarTaskm extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            cursor = dbmm.cargarCursorMega();
            ca = new CursorAdapter(Listpk.this, cursor, dbID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            list.setAdapter(ca);
            Toast.makeText(getApplicationContext(), "Terminando...", Toast.LENGTH_SHORT).show();
        }
    }

    private class cargarTaskn extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            cursor = dbmn.cargarCursorNacional();
            ca = new CursorAdapter(Listpk.this, cursor, dbID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            list.setAdapter(ca);
            Toast.makeText(getApplicationContext(), "Terminando...", Toast.LENGTH_SHORT).show();
        }
    }

    private class cargarTaskr extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            cursor = dbmr.cargarCursorRegional();
            ca = new CursorAdapter(Listpk.this, cursor, dbID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            list.setAdapter(ca);
            Toast.makeText(getApplicationContext(), "Terminando...", Toast.LENGTH_SHORT).show();
        }
    }
}
