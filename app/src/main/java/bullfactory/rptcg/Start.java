package bullfactory.rptcg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Assaso on 11/06/2017.
 */
public class Start extends AppCompatActivity {

    private Button sB;
    private ProgressBar pB;
    private TextView tV;
    private DbManagerN dbmn;
    private DbManagerM dbmm;
    private DbManagerR dbmr;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        sB = (Button) findViewById(R.id.sb);
        pB = (ProgressBar) findViewById(R.id.pb);
        tV = (TextView) findViewById(R.id.textView);

        dbmn = new DbManagerN(this);
        dbmm = new DbManagerM(this);
        dbmr = new DbManagerR(this);

        sB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tno = "001";
                if (dbmm.tablaExiste(tno)){
                    Intent intent = new Intent(Start.this, Principal.class);
                    startActivity(intent);
                }else{
                    new CreearTablasTaskR().execute();
                    sB.setClickable(false);
                }
            }
        });
    }

    private class CreearTablasTaskR extends AsyncTask<Void, Integer, Void> {

        int progress;

        @Override
        protected void onPreExecute(){
            progress = 0;
            pB.setVisibility(View.VISIBLE);
            tV.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Creeando datos de ingreso esto puede tardar varios minutos...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids){

            int com = 0;

            for (int i = 0; i < 802; i++){
                int j = i +1;
                String con = "";
                if (j <= 9) {
                    con = "00" + j;
                }

                if (j >= 10 && j <= 99){
                    con = "0" + j;
                }

                if (j >= 100){
                    con = "" + j;
                }
                dbmn.insertar(con, "", "", "");

                com = com + 1;
                switch (com){
                    case (43):
                        publishProgress(5);
                        break;
                    case (86):
                        publishProgress(10);
                        break;
                    case (129):
                        publishProgress(15);
                        break;
                    case (172):
                        publishProgress(20);
                        break;
                    case (215):
                        publishProgress(25);
                        break;
                    case (258):
                        publishProgress(30);
                        break;
                    case (301):
                        publishProgress(35);
                        break;
                    case (344):
                        publishProgress(40);
                        break;
                    case (387):
                        publishProgress(45);
                        break;
                    case (430):
                        publishProgress(50);
                        break;
                    case (473):
                        publishProgress(55);
                        break;
                    case (516):
                        publishProgress(60);
                        break;
                    case (559):
                        publishProgress(65);
                        break;
                    case (602):
                        publishProgress(70);
                        break;
                    case (645):
                        publishProgress(75);
                        break;
                    case (688):
                        publishProgress(80);
                        break;
                    case (731):
                        publishProgress(85);
                        break;
                    case (774):
                        publishProgress(90);
                        break;
                }
            }

            for (int i = 0; i < 18; i++){
                int j = i +1;
                String con = "";
                if (j <= 9) {
                    con = "00" + j;
                }

                if (j >= 10 && j <= 99){
                    con = "0" + j;
                }

                if (j >= 100){
                    con = "" + j;
                }
                dbmr.insertar(con, "", "", "");

                com = com + 1;
                switch (com) {
                    case (817):
                        publishProgress(95);
                        break;
                }
            }


            for (int i = 0; i < 50; i++){
                int j = i +1;
                String con = "";
                if (j <= 9) {
                    con = "00" + j;
                }

                if (j >= 10 && j <= 99){
                    con = "0" + j;
                }

                if (j >= 100){
                    con = "" + j;
                }
                dbmm.insertar(con, "", "", ""
                );

                com = com + 1;
                switch (com){
                    case (870):
                        publishProgress(100);
                        break;
                }
            }
            return null;
        }

        protected void onProgressUpdate(Integer... values){
            pB.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid){
            pB.setVisibility(View.INVISIBLE);
            tV.setVisibility(View.INVISIBLE);
            sB.setClickable(true);
            Intent intent = new Intent(Start.this, Principal.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Finalizado...", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO CREAR MENU PRINCIPAL
}
