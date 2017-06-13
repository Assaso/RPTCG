package bullfactory.rptcg;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Assaso on 07/06/2017.
 */
public class CursorAdapter extends android.widget.CursorAdapter{

    private String dbID, con, NO, NA, PK, QA, TO;

    public CursorAdapter(Context context, Cursor cursor, String dbID){
        super(context, cursor, 0);
        this.dbID = dbID;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.adapter, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView tvNO = (TextView) view.findViewById(R.id.tvno);
        TextView tvNA = (TextView) view.findViewById(R.id.tvna);
        TextView tvTO = (TextView) view.findViewById(R.id.tvto);
        ImageView ivSP = (ImageView) view.findViewById(R.id.ivsp);
        RelativeLayout rla = (RelativeLayout) view.findViewById(R.id.rla);

        NO = cursor.getString(cursor.getColumnIndexOrThrow("numero"));
        NA = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        TO = cursor.getString(cursor.getColumnIndexOrThrow("total"));
        int NOv = Integer.parseInt(NO);
        if (NOv <= 9){
            con = "00" + NOv;
        }
        if (NOv >= 10 && NOv <= 99){
            con = "0" + NOv;
        }
        if (NOv >= 100){
            con = "" + NOv;
        }
        PK = dbID + con;

        //cuando se carque una raresa se tendra que poner el valor de string del nombre del fondo
        String tqa = cursor.getString(cursor.getColumnIndexOrThrow("calidad"));
        if (tqa.equals("")){
            QA = "q00";
        }else{
            QA = tqa;
        }

        tvNO.setText(NO);
        tvNA.setText(NA);
        tvTO.setText(TO);
        int iID = context.getResources().getIdentifier(PK, "drawable", context.getPackageName());
        ivSP.setImageResource(iID);
        int bID = context.getResources().getIdentifier(QA, "drawable", context.getPackageName());
        rla.setBackgroundResource(bID);

    }
}
