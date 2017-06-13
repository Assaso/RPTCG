package bullfactory.rptcg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Assaso on 25/06/2016.
 */
public class options extends AppCompatActivity {

    private String dbID;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.options);

        dbID = getIntent().getStringExtra("DEX");

        ImageButton add = (ImageButton) findViewById(R.id.add);
        ImageButton list = (ImageButton) findViewById(R.id.list);
        ImageButton search = (ImageButton) findViewById(R.id.search);

        list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (dbID.equals("N")){
                    Intent intent = new Intent(options.this, Listpk.class);
                    intent.putExtra("DEX", "p");
                    startActivity(intent);
                }

                if (dbID.equals("M")){
                    Intent intent = new Intent(options.this, Listpk.class);
                    intent.putExtra("DEX", "m");
                    startActivity(intent);
                }

                if (dbID.equals("R")){
                    Intent intent = new Intent(options.this, Listpk.class);
                    intent.putExtra("DEX", "r");
                    startActivity(intent);
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbID.equals("N")){
                    Intent intent = new Intent(options.this, Search.class);
                    intent.putExtra("DEX", "p");
                    startActivity(intent);
                }

                if (dbID.equals("M")){
                    Intent intent = new Intent(options.this, Search.class);
                    intent.putExtra("DEX", "m");
                    startActivity(intent);
                }

                if (dbID.equals("R")){
                    Intent intent = new Intent(options.this, Search.class);
                    intent.putExtra("DEX", "r");
                    startActivity(intent);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(options.this, Add.class);
                switch (dbID){
                    case "N":
                        intent.putExtra("DEX", "p");
                        break;
                    case "M":
                        intent.putExtra("DEX", "m");
                        break;
                    case "R":
                        intent.putExtra("DEX", "r");
                        break;
                }
                startActivity(intent);

            }
        });


    }
}
