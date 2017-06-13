package bullfactory.rptcg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Assaso on 28/07/2016.
 */
public class DbManagerM {

    public static final String TABLE_NAME = "mega";

    public static final String CN_ID = "_id";
    public static final String CN_NO = "numero";
    public static final String CN_NA = "nombre";
    public static final String CN_QU = "calidad";
    public static final String CN_TO = "total";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NO + " text not null,"
            + CN_NA + " text,"
            + CN_QU + " text,"
            + CN_TO + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManagerM(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar (String numero, String nombre, String calidad, String total){
        db.insert(TABLE_NAME, null, generarContentValues(numero, nombre, calidad, total));
    }

    private ContentValues generarContentValues(String numero, String nombre, String calidad, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_NO, numero);
        valores.put(CN_NA, nombre);
        valores.put(CN_QU, calidad);
        valores.put(CN_TO, total);

        return valores;
    }

    public Cursor cargarCursorMega(){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public Cursor buscarMegaNO(String numero){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Cursor buscarMegaNA(String nombre){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NA + "=?", new String[]{nombre}, null, null, null);
    }

    public Cursor cargarAgregadoMega(String numero, String nombre, String calidad, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_NO, numero);
        valores.put(CN_NA, nombre);
        valores.put(CN_QU, calidad);
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Cursor cargarCalidadMega (String numero, String calidad, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_QU, calidad);
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Boolean tablaExiste(String testNO){
        Boolean exist;
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};

            Cursor c = db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{testNO}, null, null, null);
        if (c.getCount() <= 0){
            exist = false;
        }else {
            exist = true;
        }

        return exist;
    }

    public Cursor cargarNewMega (String numero, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }
}
