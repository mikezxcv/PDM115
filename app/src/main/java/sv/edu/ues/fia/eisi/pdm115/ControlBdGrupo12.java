package sv.edu.ues.fia.eisi.pdm115;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ControlBdGrupo12 {
    private static final String[]camposRol = new String []
            {"ID_ROL","NOMBRE_ROL"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBdGrupo12(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "procesosGrupo12.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE ROL (\n" +
                        "   ID_ROL INTEGER not null,\n" +
                        "   NOMBRE_ROL CHAR(50),\n" +
                        "    primary key (ID_ROL)\n" +
                        ")");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }

    public void insertar(Rol rol){
        ContentValues roles = new ContentValues();
        roles.put("ID_ROL", rol.getID_ROL());
        roles.put("NOMBRE_ROL", rol.getNOMBRE_ROL());
        db.insert("ROL", null, roles);
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        return false;
    }

    public List<String> llenar_lv(){
        List<String> lista = new ArrayList<String>();
        db = DBHelper.getWritableDatabase();
        String q = "SELECT * FROM ROL";
        Cursor registros = db.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1));
            }while(registros.moveToNext());
        }
        return lista;

    }

    public String llenarBDCarnet(){
        // Tabla ROL
        final String[] ROL_ID_ROL = {"1","2","3"};
        final String[] ROL_NOMBRE_ROL = {"Director","Jefe de Sistemas","Secretario"};

        // Tabla

        abrir();
        db.execSQL("DELETE FROM ROL");

        Rol rol = new Rol();
        for(int i=0;i<3;i++){
            rol.setNOMBRE_ROL(ROL_NOMBRE_ROL[i]);
            rol.setID_ROL(ROL_ID_ROL[i]);
            insertar(rol);
        }
        cerrar();
        return "Guardo Correctamente";
    }
}
