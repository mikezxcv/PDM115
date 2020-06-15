package sv.edu.ues.fia.eisi.pdm115;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.docente.Locales;

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
        private static final String BASE_DATOS = "procesosGrupo12_5.s3db";
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
                        "   IDEVALUACION         CHAR(10)                        not null,\n" +
                        "   IDTIPOEVAL           CHAR(2),\n" +
                        "   NOMBREEVALUACION     VARCHAR(50)                    not null,\n" +
                        "   FECHAEVALUACION      DATE                            not null,\n" +
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
                        "   IDPRIMERREVISION     CHAR(10)                        not null,\n" +
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
                        "   ESTADOREPETIDO       SMALLINT,\n" +
                        "   FECHAREPETIDO        DATE,\n" +
                        "   HORAREPETIDO         DATE,\n" +
                        "   NOTADESPUESREPETIDO  INTEGER,\n" +
                        "   NOTAANTESREPETIDO    INTEGER,\n" +
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
                        "   NOTADIFERIDO         INTEGER,\n" +
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
                        "\t(1, 1, 8, '2020-06-07', '2020-06-07', 'MP16001', NULL, 11, '01', NULL, 1);");
                db.execSQL("  INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "    (2, 0, 8, '2020-06-07', '2020-06-07', 'MP16001', NULL, 22, '02', NULL, 2);");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`) VALUES\n" +
                        "\t('1', '1', 'PARCIAL 1', '2020-12-12');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`) VALUES\n" +
                        "\t('2', '1', 'PARCIAL 1', '2020-12-12');");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`) VALUES\n" +
                        "\t('01', '01', '01', 'DSI115', '01-20', 'DOCENTE', '1', 'JUAN', 'RAMOS');");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`) VALUES\n" +
                        "\t('02', '01', '01', 'MAT115', '01-20', 'DOCENTE', '1', 'RUDY', 'RAMOS');");


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
                        "\t('2', NULL, '02', 2, '2020-06-07', NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO `primerrevision` (`IDPRIMERREVISION`, `IDLOCAL`, `IDDOCENTE`, `ID_DETALLEALUMNOSEVALUADOS`, `FECHASOLICITUDPRIMERAREV`, `ESTADOPRIMERAREV`, `FECHAPRIMERAREV`, `HORAPRIMERAREV`, `NOTAANTESPRIMERAREV`, `NOTADESPUESPRIMERAREV`, `OBSERVACIONESPRIMERAREV`) VALUES\n" +
                        "\t('1', NULL, '01', 1, '2020-06-07', NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO `rol` (`ID_ROL`, `NOMBRE_ROL`) VALUES\n" +
                        "\t(25, 'ADMINISTRADOR');");
                db.execSQL("INSERT INTO `tipodocente` (`IDTIPODOCENTECICLO`, `NOMTIPODOCENTECICLO`) VALUES\n" +
                        "\t('01', 'NOMBRE TIPO DOCENTE');");
                db.execSQL("INSERT INTO `usuario` (`USUARIO`, `NOMBRE_USUARIO`, `CONTRASENA`) VALUES\n" +
                        "\t('DOCENTE', 'JUAN RAMOS','PASS1'),\n" +
                        "\t('ESTUDIANTE', 'MIGUEL PEREZ','PASS2');\n");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('ESTUDIANTE','1');");
                db.execSQL("  INSERT INTO `segundarevicion` (`IDSEGUNDAREVICION`, `FECHASOLICITUDSEGUNDAREVICION`, `ESTADOSEGUNDAREVICION`, `FECHASEGUNDAREVICION`, `HORASEGUNDAREVICION`, `NOTADESPUESSEGUNDAREVICION`, `OBSERVACIONESSEGUNDAREVICION`, `MATERIASEGUNDAREVICION`, `MOTIVOSSEGUNDAREVICION`, `IDPRIMERAREVISION`) VALUES\n" +
                        "\t(1, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1'),\n" +
                        "\t(2, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2');");
                // Llenados CS17049
                db.execSQL("INSERT INTO EVALUACION VALUES (3,'', 'Parcial 1', '04/04/04');");

                //db.execSQL("INSERT INTO SOLICITUDDIFERIDO VALUES(1, 1,'2000-04-04', 0, '2000-04-04', 8, 'EXCELENTE', 'DISENO DE SISTEMAS 1', 'B11','trabajo',null);");

                //db.execSQL("INSERT INTO REPETIDO VALUES(1, 1,'2000-04-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                //db.execSQL("INSERT INTO REPETIDO VALUES(2, 1,'2000-04-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                //db.execSQL("INSERT INTO REPETIDO VALUES(3, 1,'2000-04-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                //db.execSQL("INSERT INTO REPETIDO VALUES(4, 1,'2000-04-07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,fechasolicitudprimerarev,p.IDPRIMERREVISION,p.IDDOCENTE   FROM primerrevision AS p \n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
                "SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION\n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
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
        Cursor datos = db.rawQuery("SELECT det.carnet,nombreestudiante, idasignatura,eva.NOMBREEVALUACION,seg.FECHASOLICITUDSEGUNDAREVICION,fechasolicitudprimerarev,seg.IDSEGUNDAREVICION,pr.NOTAANTESPRIMERAREV,pr.NOTADESPUESPRIMERAREV\n" +
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
        Cursor datos = db.rawQuery("SELECT idasignatura,nombredocente,apellidodocente FROM docente AS d\n" +
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

    public String actualizarDetalleAlumnosEvaluados2(int nota1, int idDetalle ){
        String[] id ={String.valueOf(idDetalle)};
        String nota = String.valueOf(nota1);
        ContentValues cv = new ContentValues();
        cv.put("NOTADIFERIDO", nota);
        // Actualiza Nota
        db.update("SOLICITUDDIFERIDO", cv, "IDDIFERIDO = ? ",id);
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

    public String eliminarDiferido(String id){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("SOLICITUDDIFERIDO", "IDDIFERIDO= '"+id+"'", null);
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
    }


