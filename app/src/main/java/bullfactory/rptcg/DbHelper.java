package bullfactory.rptcg;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by Assaso on 27/07/2016.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "registro";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DbManagerR.CREATE_TABLE);
        db.execSQL(DbManagerN.CREATE_TABLE);
        db.execSQL(DbManagerM.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}


