package bullfactory.rptcg;

/**
 * Created by Assaso on 25/06/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    //primero se declaran los componentes que estaran en la pantalla

    Button national, mega, regional;
    ImageButton exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //aqui se asigna un componente en la pantalla a cada variable

        national = (Button)findViewById(R.id.national);
        mega = (Button)findViewById(R.id.mega);
        regional = (Button)findViewById(R.id.regional);
        exit = (ImageButton)findViewById(R.id.exit_principal);

        national.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, options.class);
                intent.putExtra("DEX", "N");
                startActivity(intent);
            }
        });

        mega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, options.class);
                intent.putExtra("DEX", "M");
                startActivity(intent);
            }
        });

        regional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, options.class);
                intent.putExtra("DEX", "R");
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

}
