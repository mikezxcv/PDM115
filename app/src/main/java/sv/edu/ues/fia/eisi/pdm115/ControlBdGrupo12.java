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
    private static final String[]camposSOLICITUDDIFERIDO = new String []
            {"IDDIFERIDO","ID_DETALLEALUMNOSEVALUADOS", "FECHASOLICITUDDIFERIDO", "ESTADODIFERIDO", "FECHADIFERIDO", "NOTADIFERIDO", "OBSERVACIONESDIFERIDO", "MATERIADIFERIDO", "MOTIVODIFERIDO", "HORADIFERIDO"};
    private static final String[]camposArea = new String []
            {"ID_AREA","ID_ROL","NOMBRE_AREA"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBdGrupo12(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public String[] materiasPrimeraRevision() {
        String[] lista = {};
        return lista;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "procesosGrupo12_6.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE AREA (ID_AREA INTEGER not null,ID_ROL INTEGER, NOMBRE_AREA CHAR(50) not null, primary key (ID_AREA))");
                db.execSQL("CREATE TABLE ROL (\n" +
                        "   ID_ROL INTEGER not null,\n" +
                        "   NOMBRE_ROL CHAR(50),\n" +
                        "    primary key (ID_ROL)\n" +
                        ")");

                db.execSQL("CREATE TABLE SOLICITUDDIFERIDO  (\n" +
                        "   IDDIFERIDO           INTEGER                         not null,\n" +
                        "   ID_DETALLEALUMNOSEVALUADOS INTEGER,\n" +
                        "   FECHASOLICITUDDIFERIDO DATE,\n" +
                        "   ESTADODIFERIDO       SMALLINT,\n" +
                        "   FECHADIFERIDO        DATE,\n" +
                        "   NOTADIFERIDO         INTEGER,\n" +
                        "   OBSERVACIONESDIFERIDO CHAR(100),\n" +
                        "   MATERIADIFERIDO      CHAR(20),\n" +
                        "   MOTIVODIFERIDO       CHAR(100),\n" +
                        "   HORADIFERIDO         DATE,\n" +
                        "   primary key (IDDIFERIDO)\n" +
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
        return;
    }

    public void insertar(RolTabla rol){
        ContentValues roles = new ContentValues();
        roles.put("ID_ROL", rol.getID_ROL());
        roles.put("NOMBRE_ROL", rol.getNOMBRE_ROL());
        db.insert("ROL", null, roles);
    }

    public void insertar(AreaTabla area){
        ContentValues roles = new ContentValues();
        roles.put("ID_AREA", area.getID_AREA());
        roles.put("ID_ROL", area.getID_ROL());
        roles.put("NOMBRE_AREA", area.getNOMBRE_AREA());
        db.insert("AREA", null, roles);
    }

    public void insertar(SolicitudDiferidoTabla solDiferido){
        ContentValues roles = new ContentValues();
        roles.put("IDDIFERIDO", solDiferido.getIDDIFERIDO());
        roles.put("ID_DETALLEALUMNOSEVALUADOS", solDiferido.getID_DETALLEALUMNOSEVALUADOS());
        roles.put("FECHASOLICITUDDIFERIDO", solDiferido.getFECHADIFERIDO());
        roles.put("ESTADODIFERIDO", solDiferido.getESTADODIFERIDO());
        roles.put("FECHADIFERIDO", solDiferido.getFECHADIFERIDO());
        roles.put("NOTADIFERIDO", solDiferido.getNOTADIFERIDO());
        roles.put("OBSERVACIONESDIFERIDO", solDiferido.getOBSERVACIONESDIFERIDO());
        roles.put("MATERIADIFERIDO", solDiferido.getMATERIADIFERIDO());
        roles.put("MOTIVODIFERIDO", solDiferido.getMOTIVODIFERIDO());
        roles.put("HORADIFERIDO", solDiferido.getHORADIFERIDO());
        db.insert("SOLICITUDDIFERIDO", null, roles);
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

    public String llenarBDCarnet() {
        // Tabla ROL
        final String[] ROL_ID_ROL = {"1", "2", "3"};
        final String[] ROL_NOMBRE_ROL = {"Director", "Jefe de Sistemas", "Secretario"};

        // Tabla Area
        final String[] ID_AREA = {"1"};
        final String[] ID_ROL = {"1"};
        final String[] NOMBRE_AREA = {"Area 1"};

        // TABLA SolicitudDiferido
        // Formato Fecha YYYY-MM-DD HH:MM:SS
        final String[] SolicitudDiferidoTabla_IDDIFERIDO = {"11","22","33"};
        final String[] ID_DETALLEALUMNOSEVALUADOS = {"1","2","3"};
        final String[] FECHASOLICITUDDIFERIDO = {"2020-04-04","2020-04-05","2020-04-06"};
        // 0 (falso) y 1 (verdadero)
        final String[] ESTADODIFERIDO = {"0","0","0"};
        final String[] FECHADIFERIDO = {"","",""};
        final String[] NOTADIFERIDO = {"","",""};
        final String[] OBSERVACIONESDIFERIDO = {"","",""};
        final String[] MATERIADIFERIDO = {"MAT115","FIR115","IEC115"};
        final String[] MOTIVODIFERIDO = {"Motivo1", "Motivo2", "Motivo3"};
        final String[] HORADIFERIDO = {"","",""};

        abrir();
        db.execSQL("DELETE FROM ROL");
        db.execSQL("DELETE FROM AREA");
        db.execSQL("DELETE FROM SOLICITUDDIFERIDO");



        RolTabla rol = new RolTabla();
        for (int i = 0; i < 3; i++) {
            rol.setNOMBRE_ROL(ROL_NOMBRE_ROL[i]);
            rol.setID_ROL(ROL_ID_ROL[i]);
            insertar(rol);
        }

        AreaTabla area = new AreaTabla();
        for (int i = 0; i < 1; i++) {
            area.setID_AREA(ID_AREA[i]);
            area.setID_ROL(ID_ROL[i]);
            area.setNOMBRE_AREA(NOMBRE_AREA[i]);
            insertar(area);

        }

        SolicitudDiferidoTabla solDiferido  = new SolicitudDiferidoTabla();
        for(int i=0;i<3;i++) {
            solDiferido.setID_DETALLEALUMNOSEVALUADOS(ID_DETALLEALUMNOSEVALUADOS[i]);
            solDiferido.setFECHASOLICITUDDIFERIDO(FECHASOLICITUDDIFERIDO[i]);
            solDiferido.setESTADODIFERIDO(ESTADODIFERIDO[i]);
            solDiferido.setFECHADIFERIDO(FECHADIFERIDO[i]);
            solDiferido.setNOTADIFERIDO(NOTADIFERIDO[i]);
            solDiferido.setOBSERVACIONESDIFERIDO(OBSERVACIONESDIFERIDO[i]);
            solDiferido.setMATERIADIFERIDO(MATERIADIFERIDO[i]);
            solDiferido.setMOTIVODIFERIDO(MOTIVODIFERIDO[i]);
            solDiferido.setHORADIFERIDO(HORADIFERIDO[i]);

            insertar(solDiferido);
        }

        cerrar();
        return "Registros insertados";
    }
}
