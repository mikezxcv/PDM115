package sv.edu.ues.fia.eisi.pdm115;
import android.app.VoiceInteractor;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.docente.Locales;


// Import Cris
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
// Import Cris

public class ControlBdGrupo12 {
    private static final String[]camposRol = new String []
            {"ID_ROL","NOMBRE_ROL"};
    private static final String[]camposSOLICITUDDIFERIDO = new String []
            {"IDDIFERIDO","ID_DETALLEALUMNOSEVALUADOS", "FECHASOLICITUDDIFERIDO", "ESTADODIFERIDO", "FECHADIFERIDO", "NOTADIFERIDO", "OBSERVACIONESDIFERIDO", "MATERIADIFERIDO", "MOTIVODIFERIDO", "HORADIFERIDO"};
    private static final String[]camposArea = new String []
            {"ID_AREA","ID_ROL","NOMBRE_AREA"};
    private static final String[]camposRepetido = new String []
            {"rep.IDREPETIDO",
                    "rep.ID_DETALLEALUMNOSEVALUADOS",
                    "rep.FECHASOLICITUDREPETIDO",
                    "rep.ESTADOREPETIDO",
                    "rep.FECHAREPETIDO",
                    "rep.HORAREPETIDO",
                    "rep.NOTADESPUESREPETIDO",
                    "rep.NOTAANTESREPETIDO",
                    "rep.OBSERVACIONES",
                    "rep.MATERIA",
                    "rep.LOCAL"
            };
    private static final String[]camposDiferido = new String []
            {"dif.IDDIFERIDO",
                    "dif.ID_DETALLEALUMNOSEVALUADOS",
                    "dif.FECHASOLICITUDDIFERIDO",
                    "dif.ESTADODIFERIDO",
                    "dif.FECHADIFERIDO",
                    "dif.NOTADIFERIDO",
                    "dif.OBSERVACIONESDIFERIDO",
                    "dif.MATERIADIFERIDO",
                    "dif.IDLOCAL",
                    "dif.MOTIVODIFERIDO",
                    "dif.HORADIFERIDO"
            };

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBdGrupo12(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    public static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "procesosGrupo12_25.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                /*db.execSQL("CREATE TABLE AREA (ID_AREA INTEGER not null,ID_ROL INTEGER, NOMBRE_AREA CHAR(50) not null, primary key (ID_AREA))");
                db.execSQL("CREATE TABLE ROL (\n" +
                        "   ID_ROL INTEGER not null,\n" +
                        "   NOMBRE_ROL CHAR(50),\n" +
                        "    primary key (ID_ROL)\n" +
                        ")"); */

                //creacion de tablas

                db.execSQL("CREATE TABLE ACCESOUSUARIO  (\n" +
                        "   USUARIO              VARCHAR(15)                     not null,\n" +
                        "   ID_OPCION            VARCHAR(3)                         not null,\n" +
                        "   primary key (USUARIO, ID_OPCION)\n" +
                        ");");
                db.execSQL("CREATE TABLE AREA  (\n" +
                        "   ID_AREA              INTEGER                         not null,\n" +
                        "   ID_ROL               INTEGER,\n" +
                        "   NOMBRE_AREA          CHAR(50)                        not null,\n" +
                        "   primary key (ID_AREA)\n" +
                        ");");
                db.execSQL("CREATE TABLE CICLO  (\n" +
                        "   IDCICLO              CHAR(6)                         not null,\n" +
                        "   FECHADESDE           DATE                            not null,\n" +
                        "   FECHAHASTA           DATE                            not null,\n" +
                        "   primary key (IDCICLO)\n" +
                        ");");
                db.execSQL("CREATE TABLE DETALLEALUMNOSEVALUADOS  (\n" +
                        "   ASISTIO              SMALLINT                        not null,\n" +
                        "   NOTAEVALUACION       INTEGER(4),\n" +
                        "   FECHA_PUBLICACION    DATE,\n" +
                        "   FECHA_LIMITE         DATE,\n" +
                        "   ID_DETALLEALUMNOSEVALUADOS INTEGER                         not null,\n" +
                        "   CARNET               CHAR(7),\n" +
                        "   IDREPETIDO           INTEGER,\n" +
                        "   IDDIFERIDO           INTEGER,\n" +
                        "   IDDOCENTE            CHAR(10),\n" +
                        "   IDPRIMERREVISION     CHAR(10),\n" +
                        "   IDEVALUACION         CHAR(10),\n" +
                        "   primary key (ID_DETALLEALUMNOSEVALUADOS)\n" +
                        ");");
                db.execSQL("CREATE TABLE DOCENTE  (\n" +
                        "   IDDOCENTE            CHAR(10)                        not null,\n" +
                        "   IDTIPODOCENTECICLO   CHAR(2),\n" +
                        "   IDESCUELA            CHAR(10),\n" +
                        "   IDASIGNATURA         CHAR(10),\n" +
                        "   IDCICLO              CHAR(6),\n" +
                        "   USUARIO              VARCHAR(7),\n" +
                        "   ID_OPCION            CHAR(3),\n" +
                        "   NOMBREDOCENTE        VARCHAR(50)                    not null,\n" +
                        "   APELLIDODOCENTE      VARCHAR(50)                    not null,\n" +
                        "   ID_ROL               INTEGER                   ,\n" +
                        "   primary key (IDDOCENTE)\n" +
                        ");");
                db.execSQL("CREATE TABLE DOCENTESSEGUNDAREV  (\n" +
                        "   IDDOCENTE            CHAR(10)                        not null,\n" +
                        "   IDSEGUNDAREVICION    INTEGER                         not null,\n" +
                        "   primary key (IDDOCENTE, IDSEGUNDAREVICION)\n" +
                        ");");
                db.execSQL("CREATE TABLE ENCARGADODEIMPRESIONES  (\n" +
                        "   IDENCARGADO          CHAR(10)                        not null,\n" +
                        "   IDSOLICITUDIMPRESION CHAR(10),\n" +
                        "   USUARIO              VARCHAR(7),\n" +
                        "   ID_OPCION            CHAR(3),\n" +
                        "   NOMBREENCARGADO      VARCHAR(50)                    not null,\n" +
                        "   APELLIDOENCARGADO    VARCHAR(50)                    not null,\n" +
                        "   ESTADOIMPRESION      SMALLINT,\n" +
                        "   DESCRIPCION_SOLICITUD CHAR(150),\n" +
                        "    primary key (IDENCARGADO)\n" +
                        ");");
                db.execSQL("CREATE TABLE ESCUELA  (\n" +
                        "   IDESCUELA            CHAR(10)                        not null,\n" +
                        "   ID_AREA              INTEGER,\n" +
                        "   NOMBREESCUELA        VARCHAR(50)                    not null,\n" +
                        "   FACULTAD             VARCHAR(50)                    not null,\n" +
                        "  primary key (IDESCUELA)\n" +
                        ");");
                db.execSQL("CREATE TABLE ESTUDIANTE  (\n" +
                        "   CARNET               CHAR(7)                         not null,\n" +
                        "   USUARIO              VARCHAR(7),\n" +
                        "   ID_OPCION            CHAR(3),\n" +
                        "   NOMBREESTUDIANTE     VARCHAR(50)                    not null,\n" +
                        "   APELLIDOESTUDIANTE   VARCHAR(50)                    not null,\n" +
                        "   CARRERA              VARCHAR(50),\n" +
                        "   CONTRA               CHAR(50),\n" +
                        "   primary key (CARNET)\n" +
                        ");");
                db.execSQL("CREATE TABLE EVALUACION  (\n" +
                        "   IDEVALUACION         INTEGER                        not null,\n" +
                        "   IDTIPOEVAL           CHAR(2),\n" +
                        "   NOMBREEVALUACION     VARCHAR(50)                    not null,\n" +
                        "   FECHAEVALUACION      DATE                            not null,\n" +
                        "   IDASIGNATURA      CHAR(20)                            not null,\n" +
                        "   primary key (IDEVALUACION)\n" +
                        ");");
                db.execSQL("CREATE TABLE LOCAL  (\n" +
                        "   IDLOCAL              CHAR(10)                        not null,\n" +
                        "   NOMBRELOCAL          VARCHAR(50)                    not null,\n" +
                        "   UBICACION            VARCHAR(50),\n" +
                        "   primary key (IDLOCAL)\n" +
                        ");");
                db.execSQL("CREATE TABLE MATERIA  (\n" +
                        "   IDASIGNATURA         CHAR(10)                        not null,\n" +
                        "   NOMBREASIGNATURA     VARCHAR(50)                    not null,\n" +
                        "    primary key (IDASIGNATURA)\n" +
                        ");");
                db.execSQL("CREATE TABLE MATERIACICLO  (\n" +
                        "   IDASIGNATURA         CHAR(10)                        not null,\n" +
                        "   IDCICLO              CHAR(6)                         not null,\n" +
                        "  primary key (IDASIGNATURA, IDCICLO)\n" +
                        ");");
                db.execSQL("CREATE TABLE OPCIONCRUD  (\n" +
                        "   ID_OPCION            CHAR(3)                         not null,\n" +
                        "   DESOPCION            VARCHAR(30)                    not null,\n" +
                        "   NUMCRUD              INTEGER                         not null,\n" +
                        "   primary key (ID_OPCION)\n" +
                        ");");
                db.execSQL("CREATE TABLE PRIMERREVISION  (\n" +
                        "   IDPRIMERREVISION     INTEGER                        not null,\n" +
                        "   IDLOCAL              CHAR(10),\n" +
                        "   IDDOCENTE            CHAR(10)                        not null,\n" +
                        "   ID_DETALLEALUMNOSEVALUADOS INTEGER,\n" +
                        "   FECHASOLICITUDPRIMERAREV DATE,\n" +
                        "   ESTADOPRIMERAREV     CHAR(10),\n" +
                        "   FECHAPRIMERAREV      DATE,\n" +
                        "   HORAPRIMERAREV       INTEGER,\n" +
                        "   NOTAANTESPRIMERAREV  float(4),\n" +
                        "   NOTADESPUESPRIMERAREV float(4),\n" +
                        "   OBSERVACIONESPRIMERAREV VARCHAR(30),\n" +
                        " primary key (IDPRIMERREVISION)\n" +
                        ");");
                db.execSQL("CREATE TABLE REPETIDO  (\n" +
                        "   IDREPETIDO           INTEGER                         not null,\n" +
                        "   ID_DETALLEALUMNOSEVALUADOS INTEGER,\n" +
                        "   FECHASOLICITUDREPETIDO DATE,\n" +
                        "   ESTADOREPETIDO       CHAR(50),\n" +
                        "   FECHAREPETIDO        DATE,\n" +
                        "   HORAREPETIDO         TIME,\n" +
                        "   NOTADESPUESREPETIDO  FLOAT,\n" +
                        "   NOTAANTESREPETIDO    FLOAT,\n" +
                        "   OBSERVACIONES        CHAR(100),\n" +
                        "   MATERIA              CHAR(100),\n" +
                        "   LOCAL                CHAR(50),\n" +
                        "   primary key (IDREPETIDO)\n" +
                        ");\n");
                db.execSQL("CREATE TABLE ROL  (\n" +
                        "   ID_ROL               INTEGER                         not null,\n" +
                        "   NOMBRE_ROL           CHAR(50),\n" +
                        "    primary key (ID_ROL)\n" +
                        ");\n");
               db.execSQL("CREATE TABLE [SEGUNDAREVICION] (\n" +
                       "[IDSEGUNDAREVICION] INTEGER  PRIMARY KEY NOT NULL,\n" +
                       "[FECHASOLICITUDSEGUNDAREVICION] DATE  default null,\n" +
                       "[ESTADOSEGUNDAREVICION] VARCHAR(12)  default null,\n" +
                       "[FECHASEGUNDAREVICION] DATE  default null,\n" +
                       "[HORASEGUNDAREVICION] VARCHAR(5)  default null,\n" +
                       "[NOTADESPUESSEGUNDAREVICION] FLOAT  default null,\n" +
                       "[OBSERVACIONESSEGUNDAREVICION] CHAR(150)  default null,\n" +
                       "[MATERIASEGUNDAREVICION] CHAR(50)  default null,\n" +
                       "[MOTIVOSSEGUNDAREVICION] CHAR(150)  default null,\n" +
                       "[IDPRIMERAREVISION] VARCHAR(5)  UNIQUE default null,\n" +
                       "[IDLOCAL] CHAR(20)   default null\n" +
                       ");");
                db.execSQL("CREATE TABLE SOLICITUDDIFERIDO  (\n" +
                        "   IDDIFERIDO           INTEGER                         not null,\n" +
                        "   ID_DETALLEALUMNOSEVALUADOS INTEGER,\n" +
                        "   FECHASOLICITUDDIFERIDO DATE,\n" +
                        "   ESTADODIFERIDO       CHAR(20),\n" +
                        "   FECHADIFERIDO        DATE,\n" +
                        "   NOTADIFERIDO         FLOAT,\n" +
                        "   OBSERVACIONESDIFERIDO CHAR(100),\n" +
                        "   MATERIADIFERIDO      CHAR(20),\n" +
                        "   IDLOCAL      CHAR(20),\n" +
                        "   MOTIVODIFERIDO       CHAR(100),\n" +
                        "   HORADIFERIDO         TIME,\n" +
                        "   primary key (IDDIFERIDO)\n" +
                        ");");
                db.execSQL("CREATE TABLE SOLICITUDIMPRESION  (\n" +
                        "   IDSOLICITUDIMPRESION CHAR(10)                        not null,\n" +
                        "   IDDOCENTE            CHAR(10),\n" +
                        "   CANTIDADEXAMENES     INTEGER                         not null,\n" +
                        "   ESTADOAPROBACION     SMALLINT,\n" +
                        "   HOJASEMPAQUE         SMALLINT,\n" +
                        "  primary key (IDSOLICITUDIMPRESION)\n" +
                        ");");
                db.execSQL("CREATE TABLE TIPODOCENTE  (\n" +
                        "   IDTIPODOCENTECICLO   CHAR(2)                         not null,\n" +
                        "   NOMTIPODOCENTECICLO  VARCHAR(20)                    not null,\n" +
                        "    primary key (IDTIPODOCENTECICLO)\n" +
                        ");");
                db.execSQL("CREATE TABLE TIPOEVALUACION  (\n" +
                        "   IDTIPOEVAL           CHAR(2)                         not null,\n" +
                        "   NOMTIPOEVAL          VARCHAR(20)                    not null,\n" +
                        "    primary key (IDTIPOEVAL)\n" +
                        ");");
                db.execSQL("CREATE TABLE USUARIO  (\n" +
                        "   USUARIO              VARCHAR(15)                     not null,\n" +
                        "   NOMBRE_USUARIO       VARCHAR(256)                   not null,\n" +
                        "   CONTRASENA       VARCHAR(256)                   NOT null,\n" +
                        "   primary key (USUARIO)\n" +
                        ");");
                //fin creacion de tablas



                //insertar datos de prueba\

                db.execSQL("INSERT INTO `area` (`ID_AREA`, `ID_ROL`, `NOMBRE_AREA`) VALUES\n" +
                        "\t(25, 25, 'AREA DE BASE DE DATOS');");
                db.execSQL("INSERT INTO `ciclo` (`IDCICLO`, `FECHADESDE`, `FECHAHASTA`) VALUES\n" +
                        "\t('01-20', '2020-06-07', '2020-07-07');");
                db.execSQL("INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "\t(1, 1, 8, '2020-06-07', '2020-06-12', 'MP16001', NULL, 11, '1', NULL, 1);");
                db.execSQL("  INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "    (2, 1, 8, '2020-06-07', '2020-06-12', 'MP16001', NULL, 22, '2', NULL, 2);");
                db.execSQL("INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "\t(3, 1, 8, '2020-06-07', '2020-06-12', 'MP16001', NULL, 11, '1', NULL, 3);");
                db.execSQL("  INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "    (4, 1, 8, '2020-06-07', '2020-06-12', 'MP16001', NULL, 22, '2', NULL, 4);");


                db.execSQL("INSERT INTO `usuario` (`USUARIO`, `NOMBRE_USUARIO`, `CONTRASENA`) VALUES\n" +
                        "\t('DOCENTE', 'JUAN RAMOS','PASS1'),\n" +
                        "\t('ESTUDIANTE', 'MIGUEL PEREZ','PASS2');\n");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('ESTUDIANTE','1');");

                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(1, '1', 'PARCIAL 1', '2020-12-12','DSI115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(2, '1', 'PARCIAL 2', '2020-12-12','DSI115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(3, '1', 'PARCIAL 1', '2020-12-12','MAT115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(4, '1', 'PARCIAL 2', '2020-12-12','MAT115');");


                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(1, '01', '01', 'DSI115', '01-20', 'DOCENTE', '1', 'JUAN', 'RAMOS',1);");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(2, '01', '01', 'MAT115', '01-20', 'DOCENTE', '1', 'RUDY', 'RAMOS',1);");


                db.execSQL("INSERT INTO `escuela` (`IDESCUELA`, `ID_AREA`, `NOMBREESCUELA`, `FACULTAD`) VALUES\n" +
                        "\t('01', 1, 'ESCUELA DE SISTEMAS INFORMATICOS', 'INGENIERIA Y ARQUITECTURA');");
                db.execSQL("INSERT INTO `estudiante` (`CARNET`, `USUARIO`, `ID_OPCION`, `NOMBREESTUDIANTE`, `APELLIDOESTUDIANTE`, `CARRERA`, `CONTRA`) VALUES\n" +
                        "\t('MP16001', 'DOCENTE', '1', 'MIGUEL', 'PEREZ', 'SISTEMAS', 'SISTEMAS');");
                db.execSQL("INSERT INTO `local` (`IDLOCAL`, `NOMBRELOCAL`, `UBICACION`) VALUES\n" +
                        "\t('01', 'B-21', 'PLANTA MEDIA');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('DSI115', 'DISENO DE SISTEMAS 1');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('MAT115', 'MATEMATICAS 1');");
                db.execSQL("INSERT INTO `materiaciclo` (`IDASIGNATURA`, `IDCICLO`) VALUES\n" +
                        "\t('DSI115', '01-20');");
                db.execSQL("INSERT INTO `opcioncrud` (`ID_OPCION`, `DESOPCION`, `NUMCRUD`) VALUES\n" +
                        "\t('1', 'VER SOLICITUD', 1);");




                db.execSQL("INSERT INTO `primerrevision` (`IDPRIMERREVISION`, `IDLOCAL`, `IDDOCENTE`, `ID_DETALLEALUMNOSEVALUADOS`, `FECHASOLICITUDPRIMERAREV`, `ESTADOPRIMERAREV`, `FECHAPRIMERAREV`, `HORAPRIMERAREV`, `NOTAANTESPRIMERAREV`, `NOTADESPUESPRIMERAREV`, `OBSERVACIONESPRIMERAREV`) VALUES\n" +
                        "\t(1, NULL, '1', '1', '2020-06-07', NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO `primerrevision` (`IDPRIMERREVISION`, `IDLOCAL`, `IDDOCENTE`, `ID_DETALLEALUMNOSEVALUADOS`, `FECHASOLICITUDPRIMERAREV`, `ESTADOPRIMERAREV`, `FECHAPRIMERAREV`, `HORAPRIMERAREV`, `NOTAANTESPRIMERAREV`, `NOTADESPUESPRIMERAREV`, `OBSERVACIONESPRIMERAREV`) VALUES\n" +
                        "\t(2, NULL, '2', '2', '2020-06-07', NULL, NULL, NULL, NULL, NULL, NULL);");


                db.execSQL("INSERT INTO `rol` (`ID_ROL`, `NOMBRE_ROL`) VALUES\n" +
                        "\t(25, 'ADMINISTRADOR');");
                db.execSQL("INSERT INTO `tipodocente` (`IDTIPODOCENTECICLO`, `NOMTIPODOCENTECICLO`) VALUES\n" +
                        "\t('01', 'NOMBRE TIPO DOCENTE');");

                db.execSQL("  INSERT INTO `segundarevicion` (`IDSEGUNDAREVICION`, `FECHASOLICITUDSEGUNDAREVICION`, `ESTADOSEGUNDAREVICION`, `FECHASEGUNDAREVICION`, `HORASEGUNDAREVICION`, `NOTADESPUESSEGUNDAREVICION`, `OBSERVACIONESSEGUNDAREVICION`, `MATERIASEGUNDAREVICION`, `MOTIVOSSEGUNDAREVICION`, `IDPRIMERAREVISION`) VALUES\n" +
                        "\t(1, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1'),\n" +
                        "\t(2, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2');");
                // Llenados CS17049


                db.execSQL("INSERT INTO REPETIDO VALUES(1, 1,'2000-04-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(2, 1,'2000-04-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(3, 1,'2000-04-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(4, 1,'2000-04-07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                // Fin Llenados CS17049

                //FIN DATOS DE PRUEBA


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

//METODOS NECESARIOS MP16001------------------------------------------------
    //datos primera revision

    public String consultarCantidadSolicitudesPrimeraRevision(){
        long contador=0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return String.valueOf(contador);
    }

    public String[]  alumnosPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  nombreEstudiantePrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(1);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }



    public String[] materiasPrimeraRevision(){
        String [] materias= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String materia= datos.getString(2);
                materias[contador]= materia;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return materias;

    }
    public String[]  nombreEvaluacionPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(3);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  fechaSolicitudPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(4);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  idPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesPrimeraRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(5);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    //fin datos primera reivision

    //funciones crud(insert,update,delete,create) de la clase primera revision
    public String actualizar(PrimeraRevision primeraRevision){
        String[] id = {primeraRevision.getIdPrimeraRevision()};

        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("ESTADOPRIMERAREV", primeraRevision.getEstadoPrimeraRevision());
        cv.put("FECHAPRIMERAREV", primeraRevision.getFechaPrimeraRevision());
        cv.put("HORAPRIMERAREV", primeraRevision.getHoraPrimerarevision());
        cv.put("IDLOCAL", primeraRevision.getIdLocal());
        //dar revision
        cv.put("NOTAANTESPRIMERAREV", primeraRevision.getNotaAntesPrimeraRevision());
        cv.put("NOTADESPUESPRIMERAREV", primeraRevision.getNotaDespuesPrimeraRevision());

        cv.put("OBSERVACIONESPRIMERAREV", primeraRevision.getObservacionesPrimeraRevision());

        db.update("primerrevision", cv, "IDPRIMERREVISION = ? ",id);
        return "Registro Actualizado Correctamente";
    }
    public String actualizar1R(PrimeraRevision primeraRevision){
        String[] id = {primeraRevision.getIdPrimeraRevision()};

        ContentValues cv = new ContentValues();

        //dar revision
        cv.put("NOTAANTESPRIMERAREV", primeraRevision.getNotaAntesPrimeraRevision());
        cv.put("NOTADESPUESPRIMERAREV", primeraRevision.getNotaDespuesPrimeraRevision());


        db.update("primerrevision", cv, "IDPRIMERREVISION = ? ",id);
        return "Registro Actualizado Correctamente";
    }
    public String eliminar(PrimeraRevision primeraRevision){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("primerrevision", "IDPRIMERREVISION= '"+primeraRevision.getIdPrimeraRevision()+"'", null);



        regAfectados+=contador;
        return regAfectados;
    }


    //fin funciones crud primera revision

    //datos segunda revision

    public String consultarCantidadSolicitudesSegundaRevision(){
        long contador=0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return String.valueOf(contador);
    }

    public String[]  alumnosSegundaRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  nombreEstudianteSegundaRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
                "FROM segundarevicion AS seg\n" +
                "JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON eva.IDEVALUACION=det.IDEVALUACION\n",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(1);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }



    public String[] materiasSegundaRevision(){
        String [] materias= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String materia= datos.getString(2);
                materias[contador]= materia;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return materias;

    }
    public String[]  nombreEvaluacionSegundaRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(3);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  fechaSolicitudSegundaRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(4);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  fechaPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(5);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  idSegundaRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("\n" +
                "SELECT det.carnet,nombreestudiante, DOCENTE.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
                "                FROM segundarevicion AS seg\n" +
                "                JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "                JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "                JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "                JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "                JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(6);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  notaInicial(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, docente.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
                "FROM segundarevicion AS seg\n" +
                "JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON eva.IDEVALUACION=det.IDEVALUACION\n",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(7);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  notaPrimeraRevision(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesSegundaRevision())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, docente.idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
                "FROM segundarevicion AS seg\n" +
                "JOIN primerrevision AS pr ON seg.IDPRIMERAREVISION=pr.IDPRIMERREVISION\n" +
                "JOIN detallealumnosevaluados AS det ON pr.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON pr.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON  det.CARNET= est.CARNET\n" +
                "JOIN evaluacion as eva ON eva.IDEVALUACION=det.IDEVALUACION\n",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(8);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;

    }
    public String[]  listaDocentes(){
        //cantidad docentes
        long contador=0;
        Cursor datos = db.rawQuery("SELECT iddocente,idasignatura,nombredocente,apellidodocente FROM docente",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        //fin cantidad docentes

        String [] docentes= new String[(int) contador];
        Integer contador2= 0;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String idAsignatura= datos.getString(1)+" ";
                String nombreDocente= datos.getString(2)+" ";
                String apellidoDocente= datos.getString(3)+" ";
                docentes[contador2]= idAsignatura+nombreDocente+apellidoDocente;
                contador2=contador2+1;
                datos.moveToNext();
            }
        }
        return docentes;

    }
    public String[]  listaIdDocentes(){


        String [] docentes= new String[listaDocentes().length];
        Integer contador2= 0;
        Cursor datos = db.rawQuery("SELECT iddocente,idasignatura,nombredocente,apellidodocente FROM docente",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String iddocente= datos.getString(0);

                docentes[contador2]= iddocente;
                contador2=contador2+1;
                datos.moveToNext();
            }
        }
        return docentes;

    }
    public String[] docentes_segundarevision(int idSegundaRevision){
        //cantidad docentes
        long contador=0;
        Cursor datos = db.rawQuery("SELECT d.idasignatura,nombredocente,apellidodocente FROM docente AS d\n" +
                "JOIN docentessegundarev AS seg ON d.IDDOCENTE=seg.IDDOCENTE\n" +
                "JOIN segundarevicion rev ON seg.IDSEGUNDAREVICION=rev.IDSEGUNDAREVICION\n" +
                "WHERE seg.IDSEGUNDAREVICION= "+idSegundaRevision,null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        //fin cantidad docentes

        String [] docentes= new String[(int) contador];
        Integer contador2= 0;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String idAsignatura= datos.getString(0)+" ";
                String nombreDocente= datos.getString(1)+" ";
                String apellidoDocente= datos.getString(2)+" ";
                docentes[contador2]= idAsignatura+nombreDocente+apellidoDocente;
                contador2=contador2+1;
                datos.moveToNext();
            }
        }
        return docentes;
    }

    //fin datos SEGUNDA reivision

    //inicio crud segunda revision
    public String actualizar(SegundaRevision segundaRevision){
        String[] id = {segundaRevision.getIdSegundaRevision()};

        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("ESTADOSEGUNDAREVICION", segundaRevision.getEstadoSegundaRevision());
        cv.put("FECHASEGUNDAREVICION", segundaRevision.getFechaSegundaRevision());
        cv.put("HORASEGUNDAREVICION", segundaRevision.getHoraSegundaRevision());
        cv.put("IDLOCAL", segundaRevision.getLocalSegundaRevision());
        cv.put("OBSERVACIONESSEGUNDAREVICION", segundaRevision.getObservacionesSegundaRevision());
        //dar revision

        db.update("segundarevicion", cv, "IDSEGUNDAREVICION = ? ",id);
        return "Registro Actualizado Correctamente";
    }
    public String actualizar1R(SegundaRevision segundaRevision){
        String[] id = {segundaRevision.getIdSegundaRevision()};

        ContentValues cv = new ContentValues();
        //dar revision
        cv.put("NOTADESPUESSEGUNDAREVICION", segundaRevision.getNotaDespuesSegundaRevision());


        db.update("segundarevicion", cv, "IDSEGUNDAREVICION = ? ",id);
        return "Registro Actualizado Correctamente";
    }
    public String insertar(Docente docente){
        ContentValues datos = new ContentValues();
        datos.put("IDDOCENTE", docente.getIdDocente());
        datos.put("IDSEGUNDAREVICION", docente.getIdSegundaRevision());
        db.insert("docentessegundarev", null, datos);
        String resultado="Docente Agregado Para Segunda Revision";
        return  resultado;
    }
    public String eliminar(SegundaRevision segundaRevision){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("segundarevicion", "IDSEGUNDAREVICION= '"+segundaRevision.getIdSegundaRevision()+"'", null);



        regAfectados+=contador;
        return regAfectados;
    }
    //fin crud segunda revision
    public String[] obtenerLocales(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT * FROM local",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador2= contador2+1;
                datos.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                String nombreLocal=(datos.getString(1))+" ";
                String ubicacion=(datos.getString(2))+" ";
                data[contador]= nombreLocal+ubicacion;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public String[] IDLocales(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT IDLOCAL FROM local",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                contador2= contador2+1;
                datos.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                String idlocal=(datos.getString(0));

                data[contador]= idlocal;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public Locales getDataLocales(String idLocal){
        String[] id = {idLocal};
        String [] campos= {"IDLOCAL","NOMBRELOCAL","UBICACION"};
        Cursor cursor = db.query("local", campos, "idlocal = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Locales locales = new Locales();
            locales.setIdLocal(cursor.getString(0));
            locales.setNombreLocal(cursor.getString(1));
            locales.setUbicacion(cursor.getString(2));
            return locales;
        }else{
            return null;
        }

    }
    public  String actualizar(Locales locales){
        String resultado="Local Actualizado Exitosamente";
        String [] id={locales.getIdLocal()};
        ContentValues contentValues= new ContentValues();
        contentValues.put("NOMBRELOCAL",locales.getNombreLocal());
        contentValues.put("UBICACION",locales.getUbicacion());
        db.update("local",contentValues,"IDLOCAL = ?",id);
        return resultado;
    }
    public String eliminar(String idLocal){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("local", "IDLOCAL= '"+idLocal+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String insertar(Locales local){
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDLOCAL", local.getIdLocal());
        contentValues.put("NOMBRELOCAL",local.getNombreLocal());
        contentValues.put("UBICACION",local.getUbicacion());
        db.insert("local", null, contentValues);
        String resultado= "Local Creado";
        return resultado;
    }
    public boolean verificarUsuario(String usuario){

        Cursor datos= db.rawQuery("SELECT ac.usuario,us.contrasena FROM accesousuario AS ac\n" +
                "JOIN usuario as us ON ac.usuario=us.USUARIO where ac.usuario= '"+usuario+"'",null);
        boolean resultado=false;
        if(datos.moveToFirst()){
            resultado=true;
        }
        return resultado;
    }
    public boolean verificarPassword(String password){
        Cursor datos= db.rawQuery("SELECT ac.usuario,us.contrasena FROM accesousuario AS ac\n" +
                "JOIN usuario as us ON ac.usuario=us.USUARIO where us.contrasena= '"+password+"'" ,null);
        boolean resultado=false;
        if(datos.moveToFirst()){
            resultado=true;
        }
        return resultado;
    }


    //FINALIZACION!!!!      METODOS NECESARIOS MP16001------------------------------------------------

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        return false;
    }
    // METODOS DE CS17049 --------------------------------------------------------------------
    public String actualizar(RepetidoTabla repetidoTabla){
        String[] id = {repetidoTabla.getIDREPETIDO()};
        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("ESTADOREPETIDO", repetidoTabla.getESTADOREPETIDO());
        cv.put("FECHAREPETIDO", repetidoTabla.getFECHAREPETIDO());
        cv.put("HORAREPETIDO", repetidoTabla.getHORAREPETIDO());
        cv.put("LOCAL", repetidoTabla.getLOCAL());
        cv.put("OBSERVACIONES", repetidoTabla.getOBSERVACIONES());

        db.update("REPETIDO", cv, "IDREPETIDO = ? ",id);
        return "Registro Actualizado Correctamente";
    }

    public String actualizarDetalleAlumnosEvaluados2(float nota1, int idDetalle ){
        String[] id ={String.valueOf(idDetalle)};
        String nota = String.valueOf(nota1);
        ContentValues cv = new ContentValues();
        cv.put("NOTADIFERIDO", nota);
        // Actualiza Nota
        db.update("SOLICITUDDIFERIDO", cv, "IDDIFERIDO = ? ",id);
        return "Registro Actualizado Correctamente";
    }

    public String actualizarNotaRepetido(float notaDespues, float notaAntes ,int idDetalle ){
        String[] id ={String.valueOf(idDetalle)};
        String notaAntesD = String.valueOf(notaAntes);
        String notaDespuesD = String.valueOf(notaDespues);

        ContentValues cv = new ContentValues();
        cv.put("NOTADESPUESREPETIDO", notaDespuesD);
        cv.put("NOTAANTESREPETIDO", notaAntesD);
        // Actualiza Nota
        db.update("REPETIDO", cv, "IDREPETIDO = ? ",id);
        return "Registro Actualizado Correctamente";
    }

    public String actualizar(SolicitudDiferidoTabla solicitudDiferido){
        String[] id = {solicitudDiferido.getIDDIFERIDO()};

        ContentValues cv = new ContentValues();
        cv.put("ESTADODIFERIDO", solicitudDiferido.getESTADODIFERIDO());
        cv.put("FECHADIFERIDO", solicitudDiferido.getFECHADIFERIDO());
        cv.put("HORADIFERIDO", solicitudDiferido.getHORADIFERIDO());
        cv.put("IDLOCAL", solicitudDiferido.getLOCALDIFERIDO());

        cv.put("OBSERVACIONESDIFERIDO", solicitudDiferido.getOBSERVACIONESDIFERIDO());

        db.update("SOLICITUDDIFERIDO", cv, "IDDIFERIDO = ? ",id);
        return "Registro Actualizado Correctamente";
    }

    // ADM DIFERIDOS
    public String estadoSolicitudDiferido( String idDiferido ){
        String alumnos = null;

        Cursor datos = db.rawQuery("SELECT ESTADODIFERIDO FROM SOLICITUDDIFERIDO WHERE IDDIFERIDO = " +idDiferido, null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos= carnet;
                datos.moveToNext();
            }
        }
        return alumnos;
    }

    public String consultarCantidadSolicitudesDiferidos(){
        long contador=0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, eva.NOMBREEVALUACION, estu.NOMBREESTUDIANTE\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return String.valueOf(contador);
    }

    public String[]  idDiferido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, eva.NOMBREEVALUACION, estu.NOMBREESTUDIANTE\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  carnetDiferido(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, eva.NOMBREEVALUACION, estu.NOMBREESTUDIANTE\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(1);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  nombreEvaluacionDiferido(){

        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, eva.NOMBREEVALUACION, estu.NOMBREESTUDIANTE\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(2);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  NombreMateriaDiferido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, estu.NOMBREESTUDIANTE, doc.IDASIGNATURA\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET\n" +
                "JOIN docente as doc ON det.IDDOCENTE = doc.IDDOCENTE",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(3);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  NombreEstudianteDiferido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT solD.IDDIFERIDO, det.CARNET, eva.NOMBREEVALUACION, estu.NOMBREESTUDIANTE\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(3);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[] idDetalleAlumnosEvaluacionSolicitudesDiferidos(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesDiferidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "FROM SOLICITUDDIFERIDO AS solD\n" +
                "JOIN detallealumnosevaluados AS det ON solD.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "JOIN estudiante as estu ON det.CARNET = estu.CARNET",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    // FIN ADM DIFERIDOS

    // ADM REPETIDOS
    public String estadoSolicitudRepetido( String idDiferido ){
        String alumnos = null;

        Cursor datos = db.rawQuery("SELECT ESTADOREPETIDO FROM REPETIDO WHERE IDREPETIDO = " +idDiferido, null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos= carnet;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String consultarCantidadSolicitudesRepetidos(){
        long contador=0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return String.valueOf(contador);
    }
    public String[]  carnetSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  notaAntesSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.NOTAEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(0);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  nombreSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(1);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  tipoEvaluacionSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(3);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  fechaSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION, r.FECHASOLICITUDREPETIDO\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(4);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  idAsignaturaSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(2);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    public String[]  idSolicitudRepetido(){
        String [] alumnos= new String[Integer.parseInt(this.consultarCantidadSolicitudesRepetidos())];
        Integer contador= 0;
        Cursor datos = db.rawQuery("SELECT det.CARNET, est.NOMBREESTUDIANTE, docente.IDASIGNATURA, eva.NOMBREEVALUACION, r.FECHASOLICITUDREPETIDO, r.IDREPETIDO\n" +
                "FROM repetido AS r\n" +
                "JOIN detallealumnosevaluados AS det ON r.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON det.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN estudiante as est ON det.CARNET= est.CARNET\n" +
                "JOIN evaluacion AS eva ON det.IDEVALUACION = eva.IDEVALUACION",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                String carnet= datos.getString(5);
                alumnos[contador]= carnet;
                contador=contador+1;
                datos.moveToNext();
            }
        }
        return alumnos;
    }
    // FIN ADM REPETIDOS

    // TABLA ROLES INICIO
    public String[] idRoles(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT ID_ROL FROM rol",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador2= contador2+1;
                datos.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                String idlocal=(datos.getString(0));
                data[contador]= idlocal;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public String[] obtenerRoles(){

        // Obtener cantidad de roles
        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT * FROM rol",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contador2= contador2+1;
                datos.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                String nombreLocal=(datos.getString(1));
                data[contador]= nombreLocal;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }

    public String eliminarRol(String id){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("rol", "id_rol= '"+id+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String ActualizarRol(String nombreRol, String idRol){
        String resultado="Local Actualizado Exitosamente";
        String [] id={idRol};
        ContentValues contentValues= new ContentValues();
        contentValues.put("NOMBRE_ROL",nombreRol);

        db.update("rol",contentValues,"id_rol = ?",id);
        return resultado;
    }
    // TABLA ROLES FIN
    public String eliminarDiferido(String id){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("SOLICITUDDIFERIDO", "IDDIFERIDO= '"+id+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String eliminarRepetido(String id){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("REPETIDO", "IDREPETIDO= '"+id+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }




    // FIN METODOS DE CS17049 --------------------------------------------------------------------

    public List<String> llenar_lv(){
        List<String> lista = new ArrayList<String>();
        db = DBHelper.getWritableDatabase();
        String q = "SELECT * FROM SOLICITUDDIFERIDO";
        Cursor registros = db.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0));
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
        final String[] IDDIFERIDO = {"11","22","33"};
        final String[] ID_DETALLEALUMNOSEVALUADOS = {"1","2","3"};
        final String[] FECHASOLICITUDDIFERIDO = {"04/04/2020","04/04/2020","04/04/2020"};
        // 0 (falso) y 1 (verdadero)
        final String[] ESTADODIFERIDO = {"0","APROBADO","0"};
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
            solDiferido.setIDDIFERIDO(IDDIFERIDO[i]);
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
    // METODOS INUTILES --------------------------------------------------------------

    public String insertar(RolTabla rol){
        ContentValues roles = new ContentValues();
        roles.put("ID_ROL", rol.getID_ROL());
        roles.put("NOMBRE_ROL", rol.getNOMBRE_ROL());
        db.insert("ROL", null, roles);
        return "";
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

    // FIN METODOS INUTILES --------------------------------------------------------------
    /*--------------------METODOS PARA REPETIDO---------------------------*/
    public Repetido consultarEstadoSolicitudRepetido(String carnet, String nombremateria, String nombreevaluacion){
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor = db.query("REPETIDO rep INNER JOIN DETALLEALUMNOSEVALUADOS det " +
                        "ON rep.ID_DETALLEALUMNOSEVALUADOS=det.ID_DETALLEALUMNOSEVALUADOS INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",
                camposRepetido,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        if(cursor.moveToFirst()){
            Repetido repetido = new Repetido();
            repetido.setIDREPETIDO(cursor.getInt(0));
            repetido.setID_DETALLEALUMNOSEVALUADOS(cursor.getInt(1));
            repetido.setFECHASOLICITUDREPETIDO(cursor.getString(2));
            repetido.setESTADOREPETIDO(cursor.getInt(3));
            repetido.setFECHAREPETIDO(cursor.getString(4));
            repetido.setHORAREPETIDO(cursor.getString(5));
            repetido.setNOTADESPUESREPETIDO(cursor.getInt(6));
            repetido.setNOTAANTESREPETIDO(cursor.getInt(7));
            repetido.setOBSERVACIONES(cursor.getString(8));
            repetido.setMATERIA(cursor.getString(9));
            repetido.setLOCAL(cursor.getString(10));
            return repetido;
        }else{
            return null;
        }
    }
    public String enviarSolicitudRepetido(Repetido repetido,String carnet,String nombreevaluacion){
        String regInsertados=" ";
        long contador=0;
        // 1 Verificar integridad referencial
        if(verificarIntegridadRepetido(repetido,carnet,nombreevaluacion,1))
        {
            int idDetalleAlumnosEvaluados=obtenerIdDetalleAlumnosEvaluadosRepetido(carnet,repetido.getMATERIA(),nombreevaluacion);
            String[] id={String.valueOf(idDetalleAlumnosEvaluados)};

            if (asistioAlumnoaEvaluacion(carnet,repetido.getMATERIA(),nombreevaluacion)==0)
                regInsertados="Usted no asistió a la evaluación "+nombreevaluacion+ "de la materia "+repetido.getMATERIA() +
                        ". No puede realizarse la solicitud de repetido";
            else {
                Cursor cursor=db.query("REPETIDO",null,"ID_DETALLEALUMNOSEVALUADOS=?",
                        id,null, null, null);
                if (cursor.moveToFirst())
                    regInsertados = "Esta solicitud ya ha sido enviada antes. Puede realizar una consulta";
                else {
                    ContentValues soliRep = new ContentValues();
                    soliRep.put("ID_DETALLEALUMNOSEVALUADOS", idDetalleAlumnosEvaluados);
                    soliRep.put("FECHASOLICITUDREPETIDO", repetido.getFECHASOLICITUDREPETIDO());
                    soliRep.put("MATERIA", repetido.getMATERIA());
                    soliRep.put("NOTAANTESREPETIDO", obtenerNotaAntesRepetido(carnet, repetido.getMATERIA(), nombreevaluacion));
                    contador = db.insert("REPETIDO", null, soliRep);
                    regInsertados = "Solicitud enviada";
                }
            }
        }
        else
        {
            regInsertados= "Usted no ha realizado esta evaluacion. Verifique sus datos";
        }
        return regInsertados;
    }
    private boolean verificarIntegridadRepetido(Object dato,String carnet,String nombreevaluacion, int opcion) throws SQLException {

        switch (opcion) {

            case 1: {
                //verificar que el alumno haya realizado la evaluacion (DETALLEALUMNOEVALUADO)
                //antes de hacer solicitud de repetido (REPETIDO)
                Repetido rep1 = (Repetido) dato;
                String[] consulta = {carnet, rep1.getMATERIA(), nombreevaluacion};
                abrir();
                Cursor c1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                                "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                                "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                                "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                                "ON det.CARNET=est.CARNET",
                        null, "est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                        consulta, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontraron datos ||
                    return true;
                }
                return false;
                //return true;
            }

            default:
                return false;

        }
    }
    public int obtenerIdDetalleAlumnosEvaluadosRepetido(String carnet,String nombremateria, String nombreevaluacion){
        int idDetalleAlumnosEvaluados;
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",null
                ,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        cursor1.moveToFirst();
        idDetalleAlumnosEvaluados=cursor1.getInt(4);//columna que contiene el id
        return idDetalleAlumnosEvaluados;
    }
    public int obtenerNotaAntesRepetido(String carnet,String nombremateria, String nombreevaluacion){
        int nota;
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",null
                ,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        cursor1.moveToFirst();
        nota=cursor1.getInt(1);//columna que contiene la nota
        return nota;
    }
    /*------------------------FIN METODOS REPETIDO---------------------*/


    /*------------------------DIFERIDO--------------------------------*/
    public Diferido consultarEstadoSolicitudDiferido(String carnet, String nombremateria, String nombreevaluacion){
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor = db.query("SOLICITUDDIFERIDO dif INNER JOIN DETALLEALUMNOSEVALUADOS det " +
                        "ON dif.ID_DETALLEALUMNOSEVALUADOS=det.ID_DETALLEALUMNOSEVALUADOS INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",
                camposDiferido,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        if(cursor.moveToFirst()){
            Diferido diferido = new Diferido();
            diferido.setIDDIFERIDO(cursor.getInt(0));
            diferido.setID_DETALLEALUMNOSEVALUADOS(cursor.getInt(1));
            diferido.setFECHASOLICITUDDIFERIDO(cursor.getString(2));
            diferido.setESTADODIFERIDO(cursor.getString(3));
            diferido.setFECHADIFERIDO(cursor.getString(4));
            diferido.setNOTADIFERIDO(cursor.getInt(5));
            diferido.setOBSERVACIONESDIFERIDO(cursor.getString(6));
            diferido.setMATERIADIFERIDO(cursor.getString(7));
            diferido.setIDLOCAL(cursor.getString(8));
            diferido.setMOTIVODIFERIDO(cursor.getString(9));
            diferido.setHORADIFERIDO(cursor.getString(10));
            return diferido;
        }else{
            return null;
        }
    }
    public String enviarSolicitudDiferido(Diferido diferido,String carnet,String nombreevaluacion){
        String regInsertados=" ";
        long contador=0;
        // 1 Verificar integridad referencial
        if(verificarIntegridadDiferido(diferido,carnet,nombreevaluacion,1))
        {
            int idDetalleAlumnosEvaluados=obtenerIdDetalleAlumnosEvaluadosDiferido(carnet,diferido.getMATERIADIFERIDO(),nombreevaluacion);
            String[] id={String.valueOf(idDetalleAlumnosEvaluados)};

            if (asistioAlumnoaEvaluacion(carnet,diferido.getMATERIADIFERIDO(),nombreevaluacion)==1)
                regInsertados="Usted asistió a la evaluación "+nombreevaluacion+ "de la materia "+diferido.getMATERIADIFERIDO()+
                        " .No puede realizarse la solicitud";
            else {

                Cursor cursor = db.query("SOLICITUDDIFERIDO", null, "ID_DETALLEALUMNOSEVALUADOS=?",
                        id, null, null, null);
                if (cursor.moveToFirst())
                    regInsertados = "Esta solicitud ya ha sido enviada antes. Puede realizar una consulta";
                else {
                    ContentValues soliRep = new ContentValues();
                    soliRep.put("ID_DETALLEALUMNOSEVALUADOS", idDetalleAlumnosEvaluados);
                    soliRep.put("FECHASOLICITUDDIFERIDO", diferido.getFECHASOLICITUDDIFERIDO());
                    soliRep.put("MATERIADIFERIDO", diferido.getMATERIADIFERIDO());
                    soliRep.put("MOTIVODIFERIDO", diferido.getMOTIVODIFERIDO());
                    soliRep.put("ESTADODIFERIDO", "PENDIENTE DE REVISION");
                    contador = db.insert("SOLICITUDDIFERIDO", null, soliRep);
                    regInsertados = "Solicitud enviada";
                }
            }
        }
        else
        {
            regInsertados= "Esta prueba no puede diferirse. Verifique sus datos";
        }
        return regInsertados;
    }
    private boolean verificarIntegridadDiferido(Object dato,String carnet,String nombreevaluacion, int opcion) throws SQLException {

        switch (opcion) {

            case 1: {
                //verificar que el alumno haya realizado la evaluacion (DETALLEALUMNOEVALUADO)
                //antes de hacer solicitud de repetido (REPETIDO)
                Diferido dif1 = (Diferido) dato;
                String[] consulta = {carnet, dif1.getMATERIADIFERIDO(), nombreevaluacion};
                abrir();
                Cursor c1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                                "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                                "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                                "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                                "ON det.CARNET=est.CARNET",
                        null, "est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                        consulta, null, null, null);
                if (c1.moveToFirst()) {
                    //Se encontraron datos ||
                    return true;
                }
                return false;
                //return true;
            }

            default:
                return false;

        }
    }
    public int obtenerIdDetalleAlumnosEvaluadosDiferido(String carnet,String nombremateria, String nombreevaluacion){
        int idDetalleAlumnosEvaluados;
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",null
                ,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        cursor1.moveToFirst();
        idDetalleAlumnosEvaluados=cursor1.getInt(4);//columna que contiene el id
        return idDetalleAlumnosEvaluados;
    }
    public int asistioAlumnoaEvaluacion(String carnet,String nombremateria, String nombreevaluacion){
        int asistio;
        String[] consulta = {carnet, nombremateria, nombreevaluacion};
        Cursor cursor1 = db.query("DETALLEALUMNOSEVALUADOS det INNER JOIN EVALUACION ev " +
                        "ON det.IDEVALUACION=ev.IDEVALUACION INNER JOIN DOCENTE doc " +
                        "ON det.IDDOCENTE=doc.IDDOCENTE INNER JOIN MATERIA asig " +
                        "ON doc.IDASIGNATURA=asig.IDASIGNATURA INNER JOIN ESTUDIANTE est " +
                        "ON det.CARNET=est.CARNET",null
                ,"est.CARNET=? AND asig.NOMBREASIGNATURA=? AND ev.NOMBREEVALUACION=?",
                consulta,null, null, null);
        cursor1.moveToFirst();
        asistio=cursor1.getInt(0);//columna que contiene el indicador de asistencia
        return asistio;
    }

    /*---------------------FIN METODOS DIFERIDO-------------------*/
    // INICIO CRISS


    public boolean verificarIntegridad(String carnet, String nombre, String materia, String evaluacion, int relacion) throws  SQLException{
        switch (relacion) {
            case 1: {
                String[] id1 = {carnet};
                String[] id2 = {nombre};
                String[] id3 = {materia};
                String[] id4 = {evaluacion};
                //abrir();
                Cursor carnet1 = db.query("DETALLEALUMNOSEVALUADOS", null, "carnet = ?", id1, null, null, null);
                Cursor nombre1 = db.query("ESTUDIANTE", null, "nombreestudiante = ?", id2, null, null, null);
                Cursor materia1 = db.query("MATERIA", null, "idasignatura = ?", id3, null, null, null);
                Cursor evaluacion1 = db.query("EVALUACION", null, "nombreevaluacion = ?", id4, null, null, null);
                Cursor asistio1 = db.rawQuery("SELECT CARNET, ASISTIO\n" +
                        "FROM detallealumnosevaluados\n" +
                        "where  CARNET = '" +carnet+ "' and ASISTIO = 1", null);
                if(asistio1.moveToFirst() && carnet1.moveToFirst() && nombre1.moveToFirst() && materia1.moveToFirst() && evaluacion1.moveToFirst()){

                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int idPrimeraRevisionSolicitudCV(String carnet, String nombre, String materia, String evaluacion){
        int idDetalle=0;

        Cursor datos = db.rawQuery("SELECT  det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "FROM detallealumnosevaluados as det\n" +
                "JOIN estudiante as estu ON  estu.CARNET = det.CARNET\n" +
                "JOIN docente as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "where det.CARNET = "+carnet+" and det.ASISTIO = 1 and doc.IDASIGNATURA = "+materia+" and eva.NOMBREEVALUACION = "+evaluacion+"",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                idDetalle=datos.getInt(0);
                datos.moveToNext();
            }
        }
        return idDetalle;
    }
    public String actualizarSolicitudCV(PrimeraRevision primeraRevision){
        String[] id = {primeraRevision.getIdPrimeraRevision()};

        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("IDDETALLESEVALUADOS", primeraRevision.getIdDetalleAlumnosEvaluados());
        cv.put("FECHA_LIMITE", primeraRevision.getFechaSolicitudPrimeraRevision());


        db.update("primerrevision", cv, "IDPRIMERREVISION = ? ",id);
        //  db.insert("DETALLEALUMNOSEVALUADORS", cv, "ID_DETALLEALUMNOSEVALUADOS=?", id);

        return "Registro Actualizado Correctamente";
    }

    public Integer fechaLimiteDetalleEvaluacion(String materia, String evaluacion, String carnet){
        abrir();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = simpleDateFormat.format(date);

        Cursor fechaLimite = db.rawQuery("SELECT det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS as det\n" +
                "JOIN EVALUACION AS eva ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "WHERE eva.IDASIGNATURA = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "' And det.ASISTIO = 1 AND det.CARNET = '" +carnet+ "'", null);

        return 0;

    }

    public void insertPrimerRevision(String fechaSolcitud, String carnet, String materia, String evaluacion) {

        try {

            // Abriendo base de datos
            abrir();

            // Buscando materia
            Cursor cursor = db.rawQuery("SELECT IDDOCENTE FROM DOCENTE WHERE IDASIGNATURA = '" +materia+ "' " ,null);

            // Variable del ID del Docente
            String idDocente = null;

            // Asumiendo que solo se recibe un registro, se omite un loop y se extrae la primera fila
            // Siempre que no sea la última

            if(cursor.moveToFirst()){
                while (cursor.isAfterLast()==false){
                    idDocente=cursor.getString(0);
                    cursor.moveToNext();
                }
            }
            if (idDocente == null) {
                throw new NoSuchFieldException("No se encontró el docente de la materia indicada: '" + materia + "'");
            }

            int idEvaluacion = 0;

            // Buscando que exista una evaluación para la materia indicada
            Cursor evaluacionCursor = db.rawQuery("SELECT eva.IDEVALUACION FROM EVALUACION AS EVA\n" +
                    "JOIN DETALLEALUMNOSEVALUADOS AS det ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                    "WHERE IDASIGNATURA = '"+materia+"' AND NOMBREEVALUACION = '"+evaluacion+"' And ASISTIO = 1" ,null);
            // Asumiendo que solo se recibe un registro, se omite un loop y se extrae la primera fila
            // Siempre que no sea la última

            if(evaluacionCursor.moveToFirst()){
                while (evaluacionCursor.isAfterLast()==false){
                    idEvaluacion=evaluacionCursor.getInt(0);
                    evaluacionCursor.moveToNext();
                }
            }

            if (idEvaluacion == 0) {
                throw new NoSuchFieldException("Id De evaluacion No encontrado");
            }

            // Buscando al alumno en la tabla DETALLEALUMNOSEVALUADOS
            Cursor detalleAlumnoEvaluado = db.rawQuery("SELECT det.ID_DETALLEALUMNOSEVALUADOS FROM DETALLEALUMNOSEVALUADOS as det\n" +
                    "WHERE det.IDEVALUACION = '" +idEvaluacion+ "' And ASISTIO = 1 AND CARNET = '" +carnet+ "'", null);

            int idDetalleAlumnoEvaluado = 0;

            // Asumiendo que solo se recibe un registro, se omite un loop y se extrae la primera fila
            // Siempre que no sea la última

            if(detalleAlumnoEvaluado.moveToFirst()){
                while (detalleAlumnoEvaluado.isAfterLast()==false){
                    idDetalleAlumnoEvaluado=detalleAlumnoEvaluado.getInt(0);
                    detalleAlumnoEvaluado.moveToNext();
                }
            }

            if (idDetalleAlumnoEvaluado == 0) {
                throw new NoSuchFieldException("NO se encontro evaluacion relacaionada");
            }

            Cursor integridad = db.rawQuery("SELECT p.IDPRIMERREVISION FROM PRIMERREVISION AS p\n" +
                    "JOIN DETALLEALUMNOSEVALUADOS AS det on P.ID_DETALLEALUMNOSEVALUADOS = det.ID_DETALLEALUMNOSEVALUADOS\n" +
                    "WHERE det.IDEVALUACION = '"+idEvaluacion+"' AND p.ID_DETALLEALUMNOSEVALUADOS = '"+idDetalleAlumnoEvaluado+"'",null);

            int integridadSol = 0;
            if(integridad.moveToFirst()){
                while (integridad.isAfterLast()==false){
                    integridadSol=integridad.getInt(0);
                    integridad.moveToNext();
                }
            }
            if(!(integridadSol == 0)){
                throw new NoSuchFieldException("Ya se realizo una solicitud de con estos datos");

            }else{
                abrir();
                ContentValues primerRevisionParametros = new ContentValues();
                primerRevisionParametros.put("FECHASOLICITUDPRIMERAREV", fechaSolcitud);
                primerRevisionParametros.put("IDDOCENTE", idDocente);
                primerRevisionParametros.put("ID_DETALLEALUMNOSEVALUADOS", idDetalleAlumnoEvaluado);
                // Solicitando la inserción a la tabla
                db.insert("PRIMERREVISION", null, primerRevisionParametros);
                cerrar();

            }

            cerrar();
        }
        catch(Exception e) {
            rollback();
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Función para hacer un insert en cualquier tabla
     * @param tableName Nombre de la tabla a la cual ser hará la inserción
     * @param parametros Instancia de Hashmap con KEY=Nombre de la columna y OBJECT=Valor de la columna
     * @param inicializarTransaccion Si es TRUE, la transacción se inicia y finaliza internamente. Si es FALSE o NULL, se asumirá que la transacción se controlará externamente. El valor predeterminado es TRUE
     * @return TRUE, si la inserción se realizó correctamente. FALSE en cualquier otro caso
     */
    private boolean insertIntoTable(String tableName, Map<String, Object> parametros, boolean inicializarTransaccion) throws Exception {
        try {
            // Si inicializarTransaccion no es null, se inicia una transacción
            if (inicializarTransaccion ) {
                abrir();
                db.beginTransaction();
            }

            // Creando estructura de columnas
            String columnas = "";

            // Declarando valores para el insert
            String valores = "";

            // Extrayendo los keys que representan las columnas de la tabla
            Iterator<Map.Entry<String, Object>> iterator = parametros.entrySet().iterator();

            while(iterator.hasNext()) {
                // Extrayendo registro del mapa
                Map.Entry<String, Object> entry = iterator.next();

                // Guardando la columna en la lista de columnas
                columnas += entry.getKey().toUpperCase();

                // Instanciando valor
                Object valor = entry.getValue();

                // Comprobando el tipo de dato, para añadir o no comillas al guardar el valor en la
                // lista de valores
                if (valor instanceof String) {
                    valores += "'" + valor.toString() + "'";
                }
//                else if (valor instanceof Integer || valor instanceof Float) {
                else {
                    valores += valor.toString();
                }

                // Comprobando si existe otro valor en el mapa para añadir o no la coma
                if (iterator.hasNext()) {
                    columnas += ",";
                    valores += ",";
                }
            }

            // Construyendo sentencia del insert
            String sentencia = "INSERT INTO " + tableName.toUpperCase() + "(" + columnas + ") VALUES (" + valores + ")";

            // Ejecutando instrucción SQL
            db.execSQL(sentencia);

            // Si inicializarTransaccion no es null, entonces se abrió una transacción y, por lo tanto, se debe cerrar
            if (inicializarTransaccion ) {
                commit();
//                db.endTransaction();
                cerrar();
            }

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    private boolean updateTable(String tableName, Map<String, Object> valoresNuevos, String wheres, boolean inicializarTransaccion) throws Exception {
        try {
            // Si inicializarTransaccion no es null, se inicia una transacción
            if (inicializarTransaccion ) {
                abrir();
                db.beginTransaction();
            }

            // Declarando valores para el insert
            String valores = "";

            // Extrayendo los keys que representan las columnas de la tabla
            Iterator<Map.Entry<String, Object>> iterator = valoresNuevos.entrySet().iterator();

            while(iterator.hasNext()) {
                // Extrayendo registro del mapa
                Map.Entry<String, Object> entry = iterator.next();

                // Instanciando valor
                Object valor = entry.getValue();

                // Comprobando el tipo de dato, para añadir o no comillas al guardar el valor en la
                // lista de valores
                if (valor instanceof String) {
                    valores += entry.getKey().toUpperCase() + "='" + valor.toString() + "'";
                }
//                else if (valor instanceof Integer || valor instanceof Float) {
                else {
                    valores += entry.getKey().toUpperCase() + "=" + valor.toString();
                }

                // Comprobando si existe otro valor en el mapa para añadir o no la coma
                if (iterator.hasNext()) {
                    valores += ",";
                }
            }

            // Construyendo sentencia del insert
            String sentencia = "UPDATE " + tableName.toUpperCase() + " SET " + valores;

            // Si no se especifica algún WHERE, se actualizan todos los registros de la tabla
            if (wheres == null || !wheres.isEmpty()) {
                sentencia += " WHERE " + wheres;
            }

            // Ejecutando instrucción SQL
            db.execSQL(sentencia);

            // Si inicializarTransaccion no es null, entonces se abrió una transacción y, por lo tanto, se debe cerrar
            if (inicializarTransaccion ) {
                commit();
//                db.endTransaction();
                cerrar();
            }

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    private Cursor selectFromTable(String tableName, String wheres, boolean autoOpenClose) {
        if (autoOpenClose || !db.isOpen()) {
            abrir();
        }

        String sqlSentence = "SELECT * FROM " + tableName.toUpperCase();

        if (!wheres.isEmpty()) {
            sqlSentence += " WHERE " + wheres;
        }

        Cursor datos = db.rawQuery(sqlSentence, null);

        // Comprobando si debe cerrarse la conexión automáticamente
        if (autoOpenClose) {
            cerrar();
        }

        if (datos.moveToFirst()) {
            return datos;
        }
        else {
            return null;
        }
    }

    /**
     * Función para extraer el siguiente valor de la llave primaria de una tabla, útil para hacer inserts.\nNOTA: La llave no debe ser compuesta
     * @param table Nombre de la tabla a consultar
     * @param idColumnName Nombre de la columna que es la llave primaria
     * @return
     */
    private int getNextIdFromTable(String table, String idColumnName) {
        try {
            String query = "SELECT MAX(" + idColumnName + ")+1 AS next_id FROM " + table;

            Cursor cursor = db.rawQuery(query, null);

            int id = 0;
            if (cursor.moveToFirst())
            {
                do
                {
                    id = cursor.getInt(0);
                } while(cursor.moveToNext());
            }
            return id;
        }
        catch(Exception e) {
            return 0;
        }
    }


    public void commit() {
        // Construyendo sentencia del insert
        String sentencia = "commit;";

        // Ejecutando instrucción SQL
        db.execSQL(sentencia);
    }

    public void rollback() {
        // Construyendo sentencia del insert
        String sentencia = "rollback;";

        // Ejecutando instrucción SQL
        db.execSQL(sentencia);
    }
    // FIN CRISS
}
