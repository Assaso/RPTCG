package bullfactory.rptcg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Assaso on 03/06/2017.
 */
public class DbManagerR {

    public static final String TABLE_NAME = "regional";

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

    public DbManagerR(Context context){
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

    public Cursor cargarCursorRegional(){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public Cursor buscarRegionalNO(String numero){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Cursor buscarRegionalNA(String nombre){
        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NA + "=?", new String[]{nombre}, null, null, null);
    }

    public Cursor cargarAgregadoRegional(String numero, String nombre, String calidad, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_NO, numero);
        valores.put(CN_NA, nombre);
        valores.put(CN_QU, calidad);
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Cursor cargarCalidadRegional (String numero, String calidad, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_QU, calidad);
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }

    public Cursor cargarNewRegional (String numero, String total){
        ContentValues valores = new ContentValues();
        valores.put(CN_TO, total);

        db.update(TABLE_NAME, valores, "numero='" + numero + "'", null);

        String[] columnas = new String[]{CN_ID, CN_NO, CN_NA, CN_QU, CN_TO};
        return db.query(TABLE_NAME, columnas, CN_NO + "=?", new String[]{numero}, null, null, null);
    }
}
