package sv.edu.ues.fia.eisi.pdm115;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.CacheRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.docente.Evaluacion;
import sv.edu.ues.fia.eisi.pdm115.docente.Locales;


// Import Cris
import android.widget.Toast;

import java.util.Iterator;
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
        private static final String BASE_DATOS = "procesosGrupo12_42.s3db";
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
                        "   ASISTIO              SMALLINT                        ,\n" +
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
                        "   NOMBRELOCAL         CHAR(30),\n" +
                        "   primary key (ID_DETALLEALUMNOSEVALUADOS)\n" +
                        ");");
                db.execSQL("CREATE TABLE DOCENTE  (\n" +
                        "   IDDOCENTE            CHAR(10)                        not null,\n" +
                        "   IDTIPODOCENTECICLO   CHAR(2),\n" +
                        "   IDESCUELA            CHAR(10),\n" +
                        "   IDASIGNATURA         CHAR(10),\n" +
                        "   IDCICLO              CHAR(6),\n" +
                        "   USUARIO              VARCHAR(15),\n" +
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
                        "   IDENCARGADO          INTEGER                        not null,\n" +
                        "   IDESCUELA            CHAR(10)                        not null,\n" +
                        "   USUARIO              VARCHAR(15),\n" +
                        "   ID_OPCION            CHAR(3),\n" +
                        "   NOMBREENCARGADO      VARCHAR(50)                    not null,\n" +
                        "   APELLIDOENCARGADO    VARCHAR(50)                    not null,\n" +
                        "    primary key (IDENCARGADO)\n" +
                        ");");
                db.execSQL("CREATE TABLE ESCUELA  (\n" +
                        "   IDESCUELA            INTEGER                       not null,\n" +
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
                        "   NOMBREEVALUACION     VARCHAR(50)                    /*not null*/,\n" +
                        "   FECHAEVALUACION      DATE                            /*not null*/,\n" +
                        "   IDASIGNATURA      CHAR(20)                            /*not null*/,\n" +
                        "   primary key (IDEVALUACION)\n" +
                        ");");
                db.execSQL("CREATE TABLE LOCAL  (\n" +
                        "   IDLOCAL              INTEGER                        not null,\n" +
                        "   NOMBRELOCAL          CHAR(50)                    not null,\n" +
                        "   UBICACION            CHAR(50),\n" +
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
                        "   FECHALIMITESEGUNDAREVISION DATE,\n" +
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
                        "   HORAREPETIDO         CHAR(50),\n" +
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
                       "[ID_DETALLEALUMNOSEVALUADOS] INTEGER  default null,\n" +
                       "[IDDOCENTE] VARCHAR(12)  default null,\n" +
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
                        "   HORADIFERIDO         CHAR(100),\n" +
                        "   primary key (IDDIFERIDO)\n" +
                        ");");
                db.execSQL("CREATE TABLE SOLICITUDIMPRESION  (\n" +
                        "   IDSOLICITUDIMPRESION INTEGER                        not null,\n" +
                        "   IDDOCENTE            CHAR(10),\n" +
                        "   IDENCARGADO           INTEGER,\n" +
                        "   MOTIVONOIMP           INTEGER,\n" +
                        "   DESCRIPCION_NO_IMP    CHAR(150),\n" +
                        "   DESCRIPCION_SOLICITUD CHAR(150)                      not null,\n" +
                        "   CANTIDADEXAMENES     INTEGER                         not null,\n" +
                        "   ESTADOAPROBACION     SMALLINT,\n" +
                        "   ESTADOIMPRESION      SMALLINT,\n" +
                        "   HOJASEMPAQUE         SMALLINT                        not null,\n" +
                        "  primary key (IDSOLICITUDIMPRESION)\n" +
                        ");");

                db.execSQL("CREATE TABLE MOTIVONOIMPRESION  (\n" +
                        "   IDMOTIVONOIMP INTEGER                        not null,\n" +
                        "   MOTIVONOIMP CHAR(150)                        not null,\n" +
                        "  primary key (IDMOTIVONOIMP)\n" +
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
                        "\t(1, 1, 8, '2020-06-07', '2020-06-25', 'MP16001', NULL, 11, '1', NULL, 1);");
                db.execSQL("  INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`) VALUES\n" +
                        "    (2, 1, 8, '2020-06-08', '2020-06-26', 'MP16001', NULL, 22, '2', NULL, 2);");


              /*   db.execSQL("INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`,`NOMBRELOCAL`) VALUES\n" +
                        "\t(3, 1, 8, '2020-06-09', '2020-06-27', 'MP16001', NULL, 11, '1', NULL, 3, NULL);");
                db.execSQL("  INSERT INTO `detallealumnosevaluados` (`ID_DETALLEALUMNOSEVALUADOS`, `ASISTIO`, `NOTAEVALUACION`, `FECHA_PUBLICACION`, `FECHA_LIMITE`, `CARNET`, `IDREPETIDO`, `IDDIFERIDO`, `IDDOCENTE`, `IDPRIMERREVISION`, `IDEVALUACION`,`NOMBRELOCAL`) VALUES\n" +
                        "    (4, 1, 8, '2020-06-10', '2020-06-28', 'MP16001', NULL, 22, '2', NULL, 4, NULL);");*/

                db.execSQL("INSERT INTO `usuario` (`USUARIO`, `NOMBRE_USUARIO`, `CONTRASENA`) VALUES\n" +
                        "\t('DOCENTE', 'DOCENTE 1','PASS1'),\n" +
                        "\t('ESTUDIANTE', 'ESTUDIANTE 1','PASS2');\n");
                db.execSQL("INSERT INTO `usuario` (`USUARIO`, `NOMBRE_USUARIO`, `CONTRASENA`) VALUES\n" +
                        "\t('IMPRESIONADMIN', 'ADMIN IMPRESION 1','PASS3'),\n" +
                        "\t('ADMIN', 'ADMIN GENERAL','PASS4'),\n" +
                        "\t('DOCENTE2', 'DOCENTE 2','PASS5'),\n" +
                        "\t('DOCENTE3', 'DOCENTE 3','PASS6'),\n" +
                        "\t('IMPRESIONADMIN2', 'ADMIN IMPRESION 2','PASS7');\n");

                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('DOCENTE','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('ESTUDIANTE','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('IMPRESIONADMIN','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('ADMIN','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('DOCENTE2','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('DOCENTE3','1');");
                db.execSQL("INSERT INTO ACCESOUSUARIO VALUES ('IMPRESIONADMIN2','1');");


                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(1, '1', 'PARCIAL 1', '2020-12-12','DSI115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(2, '1', 'PARCIAL 2', '2020-12-12','DSI115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(3, '1', 'PARCIAL 1', '2020-12-12','MAT115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(4, '1', 'PARCIAL 2', '2020-12-12','MAT115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(5, '1', 'PARCIAL 1', '2020-12-12','SIC115');");
                db.execSQL("INSERT INTO `evaluacion` (`IDEVALUACION`, `IDTIPOEVAL`, `NOMBREEVALUACION`, `FECHAEVALUACION`,`IDASIGNATURA`) VALUES\n" +
                        "\t(6, '1', 'PARCIAL 1', '2020-12-12','BAD115');");


                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(1, '01', 1, 'DSI115', '01-20', 'DOCENTE', '1', 'JUAN', 'RAMOS',1);");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(2, '01', 1, 'MAT115', '01-20', 'DOCENTE', '1', 'RUDY', 'RAMOS',1);");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(3, '01', 2, 'SIC115', '01-20', 'DOCENTE2', '1', 'RODRIGO', 'BREMER',1);");
                db.execSQL("INSERT INTO `docente` (`IDDOCENTE`, `IDTIPODOCENTECICLO`, `IDESCUELA`, `IDASIGNATURA`, `IDCICLO`, `USUARIO`, `ID_OPCION`, `NOMBREDOCENTE`, `APELLIDODOCENTE`,`ID_ROL`) VALUES\n" +
                        "\t(4, '01', 1, 'BAD115', '01-20', 'DOCENTE3', '1', 'ELMER', 'CARBALLO',1);");

                db.execSQL("INSERT INTO `encargadodeimpresiones` (`IDENCARGADO`, `IDESCUELA`, `USUARIO`, `ID_OPCION`, `NOMBREENCARGADO`, `APELLIDOENCARGADO`) VALUES\n" +
                        "\t(1, 1, 'IMPRESIONADMIN', '1', 'ELIAS', 'AYUB');");
                db.execSQL("INSERT INTO `encargadodeimpresiones` (`IDENCARGADO`, `IDESCUELA`, `USUARIO`, `ID_OPCION`, `NOMBREENCARGADO`, `APELLIDOENCARGADO`) VALUES\n" +
                        "\t(2, 2, 'IMPRESIONADMIN2', '1', 'PATRICIA', 'SANCHEZ');");

                db.execSQL("INSERT INTO `escuela` (`IDESCUELA`, `ID_AREA`, `NOMBREESCUELA`, `FACULTAD`) VALUES\n" +
                        "\t(1, 1, 'ESCUELA DE SISTEMAS INFORMATICOS', 'INGENIERIA Y ARQUITECTURA');");
                db.execSQL("INSERT INTO `escuela` (`IDESCUELA`, `ID_AREA`, `NOMBREESCUELA`, `FACULTAD`) VALUES\n" +
                        "\t(2, 3, 'ESCUELA DE INGENIERIA INDUSTRIAL', 'INGENIERIA Y ARQUITECTURA');");

                db.execSQL("INSERT INTO `estudiante` (`CARNET`, `USUARIO`, `ID_OPCION`, `NOMBREESTUDIANTE`, `APELLIDOESTUDIANTE`, `CARRERA`, `CONTRA`) VALUES\n" +
                        "\t('MP16001', 'DOCENTE', '1    ', 'MIGUEL', 'PEREZ', 'SISTEMAS', 'SISTEMAS');");
                db.execSQL("INSERT INTO `estudiante` (`CARNET`, `USUARIO`, `ID_OPCION`, `NOMBREESTUDIANTE`, `APELLIDOESTUDIANTE`, `CARRERA`, `CONTRA`) VALUES\n" +
                        "\t('CS17049', 'DOCENTE', '1', 'JOSE', 'AMILCAR', 'SISTEMAS', 'SISTEMAS');");

                db.execSQL("INSERT INTO `local` (`IDLOCAL`, `NOMBRELOCAL`, `UBICACION`) VALUES\n" +
                        "\t(1, 'B-21', 'PLANTA MEDIA');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('DSI115', 'DISENO DE SISTEMAS 1');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('MAT115', 'MATEMATICAS 1');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('SIC115', 'SISTEMAS CONTABLES');");
                db.execSQL("INSERT INTO `materia` (`IDASIGNATURA`, `NOMBREASIGNATURA`) VALUES\n" +
                        "\t('BAD115', 'BASES DE DATOS');");

                db.execSQL("INSERT INTO `materiaciclo` (`IDASIGNATURA`, `IDCICLO`) VALUES\n" +
                        "\t('DSI115', '01-20');");
                db.execSQL("INSERT INTO `opcioncrud` (`ID_OPCION`, `DESOPCION`, `NUMCRUD`) VALUES\n" +
                        "\t('1', 'VER SOLICITUD', 1);");


                db.execSQL("INSERT INTO `primerrevision` (`IDPRIMERREVISION`, `IDLOCAL`, `IDDOCENTE`, `ID_DETALLEALUMNOSEVALUADOS`, `FECHASOLICITUDPRIMERAREV`, `ESTADOPRIMERAREV`, `FECHAPRIMERAREV`, `HORAPRIMERAREV`, `NOTAANTESPRIMERAREV`, `NOTADESPUESPRIMERAREV`, `OBSERVACIONESPRIMERAREV`,`FECHALIMITESEGUNDAREVISION`) VALUES\n" +
                        "\t(1, NULL, '1', 1, '2020-06-07', 'APROBADO', NULL, NULL, NULL, NULL, NULL, '2020-06-28');");
                db.execSQL("INSERT INTO `primerrevision` (`IDPRIMERREVISION`, `IDLOCAL`, `IDDOCENTE`, `ID_DETALLEALUMNOSEVALUADOS`, `FECHASOLICITUDPRIMERAREV`, `ESTADOPRIMERAREV`, `FECHAPRIMERAREV`, `HORAPRIMERAREV`, `NOTAANTESPRIMERAREV`, `NOTADESPUESPRIMERAREV`, `OBSERVACIONESPRIMERAREV`,`FECHALIMITESEGUNDAREVISION`) VALUES\n" +
                        "\t(2, NULL, '2', 2, '2020-06-07', NULL, NULL, NULL, NULL, NULL, NULL, '2020-06-28');");

                db.execSQL("INSERT INTO `rol` (`ID_ROL`, `NOMBRE_ROL`) VALUES\n" +
                        "\t(25, 'ADMINISTRADOR');");
                db.execSQL("INSERT INTO `tipodocente` (`IDTIPODOCENTECICLO`, `NOMTIPODOCENTECICLO`) VALUES\n" +
                        "\t('01', 'NOMBRE TIPO DOCENTE');");
                db.execSQL("  INSERT INTO `segundarevicion` (`IDSEGUNDAREVICION`, `FECHASOLICITUDSEGUNDAREVICION`, `ESTADOSEGUNDAREVICION`, `FECHASEGUNDAREVICION`, `HORASEGUNDAREVICION`, `NOTADESPUESSEGUNDAREVICION`, `OBSERVACIONESSEGUNDAREVICION`, `MATERIASEGUNDAREVICION`, `MOTIVOSSEGUNDAREVICION`, `IDPRIMERAREVISION`,`ID_DETALLEALUMNOSEVALUADOS`) VALUES\n" +
                        "\t(1, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1',1),\n" +
                        "\t(2, '2020-06-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2',2);");
                // Llenados CS17049

                db.execSQL("INSERT INTO REPETIDO VALUES(1, 1,'2000-04-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(2, 1,'2000-04-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(3, 1,'2000-04-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO REPETIDO VALUES(4, 1,'2000-04-07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);");
                db.execSQL("INSERT INTO SOLICITUDDIFERIDO VALUES (1,1, '2020-04-01','APROBADO','2020-04-02','5','OBSERVACION 1','MAT115','1', 'MOTIVO 1', '00:05:00');");
                db.execSQL("INSERT INTO SOLICITUDDIFERIDO VALUES (2,1, '2020-04-01','APROBADO','2020-04-02','5','OBSERVACION 1','DSI115','1', 'MOTIVO 1', '00:05:00');");
                // Fin Llenados CS17049

                db.execSQL("INSERT INTO SOLICITUDIMPRESION VALUES(1,3,2,null,null,'Desc. generica', 25, null, null,0);");
                db.execSQL("INSERT INTO SOLICITUDIMPRESION VALUES(2,4,1,null,null,'Desc. generica', 40, null, null,1);");
                db.execSQL("INSERT INTO SOLICITUDIMPRESION VALUES(3,3,2,null,null,'Desc. generica', 80, null, null,2);");

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
    public String actualizarNegado(PrimeraRevision primeraRevision){
        String[] id = {primeraRevision.getIdPrimeraRevision()};

        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("ESTADOPRIMERAREV", primeraRevision.getEstadoPrimeraRevision());
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
    public String actualizar1NotaLimSegundaRevision(String idEntrante, String fechaLim){
        String[] id = {idEntrante};
        ContentValues cv = new ContentValues();
        //dar revision
        cv.put("FECHALIMITESEGUNDAREVISION", fechaLim);

        db.update("primerrevision", cv, "IDPRIMERREVISION = ? ", id);
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
    public String actualizarNegado(SegundaRevision segundaRevision){
        String[] id = {segundaRevision.getIdSegundaRevision()};
        ContentValues cv = new ContentValues();
        //aprobar revision
        cv.put("ESTADOSEGUNDAREVICION", segundaRevision.getEstadoSegundaRevision());
        cv.put("OBSERVACIONESSEGUNDAREVICION", segundaRevision.getObservacionesSegundaRevision());
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
    public String insertar(DocenteSegundaRevision docenteSegundaRevision){
        ContentValues datos = new ContentValues();
        datos.put("IDDOCENTE", docenteSegundaRevision.getIdDocente());
        datos.put("IDSEGUNDAREVICION", docenteSegundaRevision.getIdSegundaRevision());
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
    public String eliminarLocal(String idLocal){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("local", "IDLOCAL= '"+idLocal+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String insertar(Locales local){
        ContentValues contentValues = new ContentValues();
       // contentValues.put("IDLOCAL", local.getIdLocal());
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
    public boolean verificarPassword(String user,String password){
        Cursor datos= db.rawQuery("SELECT ac.usuario,us.contrasena FROM accesousuario AS ac\n" +
                "JOIN usuario as us ON ac.usuario=us.USUARIO where us.contrasena= '"+password+"'"+" and us.usuario= '"+user+"'",null);
        boolean resultado=false;
        if(datos.moveToFirst()){
            resultado=true;
        }
        return resultado;
    }
    public String[] obtenerEscuelas(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT * FROM ESCUELA",null);
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

                String nombre=(datos.getString(2))+"-- ";
                String facultad=(datos.getString(3))+" ";
                data[contador]= nombre+facultad;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public String[] IDescuelas(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT IDESCUELA FROM ESCUELA",null);
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

                String ID=(datos.getString(0));

                data[contador]= ID;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public String insertar(Escuela escuela){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_AREA", escuela.getIdArea());
        contentValues.put("NOMBREESCUELA",escuela.getNombreEscuela());
        contentValues.put("FACULTAD",escuela.getFacultad());
        db.insert("ESCUELA", null, contentValues);
        String resultado= "Escuela Creada";
        return resultado;
    }
    public Escuela getDataEscuela(String idEscuela){
        String[] id = {idEscuela};
        String [] campos= {"ID_AREA","NOMBREESCUELA","FACULTAD"};
        Cursor cursor = db.query("ESCUELA", campos, "IDESCUELA = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Escuela escuela = new Escuela();
            escuela.setIdArea(cursor.getInt(0));
            escuela.setNombreEscuela(cursor.getString(1));
            escuela.setFacultad(cursor.getString(2));
            return escuela;
        }else{
            return null;
        }

    }
    public  String actualizar(Escuela escuela){
        String resultado="Local Actualizado Exitosamente";
        String [] id={escuela.getIdEscuela()};
        ContentValues contentValues= new ContentValues();
        contentValues.put("ID_AREA",escuela.getIdArea());
        contentValues.put("NOMBREESCUELA",escuela.getNombreEscuela());
        contentValues.put("FACULTAD",escuela.getFacultad());
        db.update("ESCUELA",contentValues,"IDESCUELA = ?",id);
        return resultado;
    }
    public String eliminarEscuela(String idEscuela){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("ESCUELA", "IDESCUELA= '"+idEscuela+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public List<String> verificarSolicitudPrimeraRevision(String id){
        List<String> resultado= new ArrayList<String>();
        Cursor datos= db.rawQuery("SELECT  * FROM PRIMERREVISION WHERE IDPRIMERREVISION  = "+id,null);
        if(datos.moveToFirst()){
            resultado.add(datos.getString(7));
        }
        return resultado;
    }
    public List<String> verificarSolicitudSegundaRevision(String id){
        List<String> resultado= new ArrayList<String>();
        Cursor datos= db.rawQuery("select * from segundarevicion where idsegundarevicion = "+id,null);
        if(datos.moveToFirst()){
            resultado.add(datos.getString(4));
        }
        return resultado;
    }

    //metodos necesarios para WEBSERVICES------------INICIO
    public void guardarLocalesExterno(String nombre,String ubicacion){
        //ejecutar aqui vaciar tabla db.execSQL();
        ContentValues contentValues= new ContentValues();
        contentValues.put("NOMBRELOCAL",nombre);
        contentValues.put("UBICACION",ubicacion);
       long result= db.insert("local", null, contentValues);
    }
    public void guardarEstudianteExterno(String carnet,String nombre,String apellido,String carrera){
        //ejecutar aqui vaciar tabla db.execSQL();
        ContentValues contentValues= new ContentValues();
        contentValues.put("CARNET",carnet);
        contentValues.put("NOMBREESTUDIANTE",nombre);
        contentValues.put("APELLIDOESTUDIANTE",apellido);
        contentValues.put("CARRERA",carrera);
        long result= db.insert("ESTUDIANTE", null, contentValues);
    }
    //metodos necesarios para WEBSERVICES------------FIN

    //FINALIZACION!!!!      METODOS NECESARIOS MP16001------------------------------------------------

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        return false;
    }
    // INICIO METODOS DE CS17049 --------------------------------------------------------------------
    // INICIO METODOS DE EVALUACION
    public String[] obtenerDocentesParaDetalle(String materia){

        Integer contador2=0;
        Cursor datos1= db.rawQuery("SELECT NOMBREDOCENTE FROM DOCENTE where IDASIGNATURA = '"+materia+"'",null);
        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                contador2= contador2+1;
                datos1.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                String nombreEva= datos1.getString(0);
                data[contador]= nombreEva;
                contador= contador+1;
                datos1.moveToNext();
            }
        }
        return data;
    }

    public String obtenerIDDocentesParaDetalle(String materia, String nombre){

        Integer contador2=0;
        Cursor datos1= db.rawQuery("SELECT * FROM DOCENTE where IDASIGNATURA = '"+materia+"' AND NOMBREDOCENTE = '"+nombre+"' ",null);
        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                contador2= contador2+1;
                datos1.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;
        String nombreEva = null;
        if(datos1.moveToFirst()){
            nombreEva= datos1.getString(0);
        }
        return nombreEva;
    }

    public String[] obtenerEvaluacioneParaDetalle(String materia){

        Integer contador2=0;
        Cursor datos1= db.rawQuery("SELECT NOMBREEVALUACION\n" +
                "FROM EVALUACION AS eva\n" +
                "WHERE  IDASIGNATURA = '"+materia+"'",null);
        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                contador2= contador2+1;
                datos1.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                String nombreEva= datos1.getString(0);
                data[contador]= nombreEva;
                contador= contador+1;
                datos1.moveToNext();
            }
        }
        return data;
    }
    public String insertarDetalleEvaluacion(String carnetEva,String materiaEva,String evaluacionEva, String notaEva,String asistenciaEva,String fechaRealizacionEva,String fechaLimEva,String docEncargadoEva, String local){
        String resultado = null;
        Cursor idEvaluacion = db.rawQuery("SELECT eva.IDEVALUACION\n" +
                "FROM evaluacion as eva\n" +
                "WHERE eva.IDASIGNATURA = '" +materiaEva+ "' AND eva.NOMBREEVALUACION = '" +evaluacionEva+ "'",null);

         Cursor integridadEvaluacion = db.rawQuery("SELECT eva.IDEVALUACION FROM evaluacion as eva\n" +
                "JOIN DETALLEALUMNOSEVALUADOS as det ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                "WHERE eva.IDASIGNATURA = '" +materiaEva+ "' AND eva.NOMBREEVALUACION =  '" +evaluacionEva+ "'  and det.CARNET = '" +carnetEva+ "'",null);
         if(integridadEvaluacion.moveToFirst()){
             return "Ya esxiste un registro con CARNET MATERIA y EVALUACION similares";
         }else{
             int idEva;
             if(idEvaluacion.moveToFirst()){
                 idEva = idEvaluacion.getInt(0);

                 ContentValues datos = new ContentValues();
                 datos.put("CARNET", carnetEva);
                 datos.put("NOTAEVALUACION", notaEva);
                 datos.put("ASISTIO", asistenciaEva);
                 datos.put("FECHA_PUBLICACION", fechaRealizacionEva);
                 datos.put("FECHA_LIMITE", fechaLimEva);
                 datos.put("IDDOCENTE", docEncargadoEva);
                 datos.put("IDEVALUACION", idEva);
                 datos.put("NOMBRELOCAL", local);


                 db.insert("DETALLEALUMNOSEVALUADOS", null, datos);
                 resultado="Detalle de evaluacion Agregado";
                 return resultado;
             }else{
                 return "NO EXISTE EVALUACION CON ESTA MATERIA Y EVALUACION";
             }
         }
    }

    public String actualizar(String id, String carnet, String materia, String evaluacion, String nota, String asistencia, String fechaRealizacion, String fechaLim, String docEncargado ){
       /* String[] id = {primeraRevision.getIdPrimeraRevision()};

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

        db.update("primerrevision", cv, "IDPRIMERREVISION = ? ",id);*/
        return "Registro Actualizado Correctamente";
    }
    public String carnetDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(0);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String materiaDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(1);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }
    public String evaluacionDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(2);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }
    public String notaDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(3);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String asistenciaDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(4);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String fechaPublicacionDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(5);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String fechaLimiteDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(6);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String docenteDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT det.IDDOCENTE, estu.CARNET, eva.IDASIGNATURA, eva.NOMBREEVALUACION, det.NOTAEVALUACION, det.ASISTIO, det.FECHA_PUBLICACION, det.FECHA_LIMITE FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET\n" +
                "JOIN DOCENTE as doc ON doc.IDDOCENTE = det.IDDOCENTE\n" +
                "WHERE det.ID_DETALLEALUMNOSEVALUADOS = '"+id+"' ",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(0);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }

    public String nombredocenteDetalleEvaluacionesTODO(String id){
        Cursor datos= db.rawQuery("SELECT NOMBREDOCENTE FROM docente where IDDOCENTE = '"+id+"'",null);

        Integer contador=0;
        String Carnet = null;
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                Carnet= datos.getString(0);
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return Carnet;
    }






    public String[] obtenerDetalleEvaluaciones(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT det.CARNET, eva.NOMBREEVALUACION, eva.IDASIGNATURA, det.ID_DETALLEALUMNOSEVALUADOS FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET",null);
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
                String idDetalle=(datos.getString(3))+" ";
                String Carnet=(datos.getString(0))+" ";
                String Evaluacion=(datos.getString(1))+" ";
                String Materia=(datos.getString(2));

                data[contador]= idDetalle + Carnet + Evaluacion + Materia;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }

    public String[] IDDetalleEvaluaciones(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT det.ID_DETALLEALUMNOSEVALUADOS, det.CARNET, eva.NOMBREEVALUACION, eva.IDASIGNATURA FROM DETALLEALUMNOSEVALUADOS AS det\n" +
                "JOIN EVALUACION AS eva ON eva.IDEVALUACION = det.IDEVALUACION\n" +
                "JOIN ESTUDIANTE AS ESTU on ESTU.CARNET = DET.CARNET",null);
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

                String Carnet=(datos.getString(0));
                data[contador]= Carnet;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    // FIN METODOS DE EVALUACION

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
                return String.valueOf(contador);
            }else{
                return null;
            }


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
        final String[] ID_AREA = {"1", "2", "3"};
        final String[] ID_ROL = {"1", "1", "1"};
        final String[] NOMBRE_AREA = {"Area 1", "BASE DE DATOS", "ADMINISTRACION"};

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
        for (int i = 0; i < 3; i++) {
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
            repetido.setESTADOREPETIDO(cursor.getString(3));
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
                    /*soliRep.put("ESTADOREPETIDO","PENDIENTE DE REVISION");*/
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
    /*-------------------METODOS CRUD EVALUACION------------------*/
    public String[] obtenerEvaluacionesCRUD(){

        Integer contadorEv=0;
        Cursor datos= db.rawQuery("SELECT * FROM evaluacion",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){
                contadorEv= contadorEv+1;
                datos.moveToNext();
            }
        }
        String [] data=new String[contadorEv];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                String nombre=(datos.getString(2))+" ---->  ";
                String fecha=(datos.getString(3))+" ";
                data[contador]= nombre+fecha;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public int[] IDEvaluacionesCRUD(){

        Integer contadorIDev=0;
        Cursor datos= db.rawQuery("SELECT IDEVALUACION FROM evaluacion",null);
        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                contadorIDev= contadorIDev+1;
                datos.moveToNext();
            }
        }
        int [] data=new int[contadorIDev];

        Integer contador=0;

        if(datos.moveToFirst()){
            while (datos.isAfterLast()== false){

                int idEvaluacion=(datos.getInt(0));

                data[contador]= idEvaluacion;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public Evaluacion getDataEvaluacionCRUD(int idEvaluacion){
        String[] id = {String.valueOf(idEvaluacion)};
        String [] campos= {"IDEVALUACION","IDTIPOEVAL","NOMBREEVALUACION","FECHAEVALUACION"};
        Cursor cursor = db.query("evaluacion", campos, "idEvaluacion = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setIdEvaluacion(cursor.getInt(0));
            evaluacion.setTipoEvaluacion(cursor.getString(1));
            evaluacion.setNombreEvaluacion(cursor.getString(2));
            evaluacion.setFechaEvaluacion(cursor.getString(3));
            return evaluacion;
        }else{
            return null;
        }

    }
    public  String actualizar(Evaluacion evaluacion){
        String resultado=" ";
        String [] id={String.valueOf(evaluacion.getIdEvaluacion())};
        ContentValues contentValues= new ContentValues();
        contentValues.put("IDTIPOEVAL",evaluacion.getTipoEvaluacion());
        contentValues.put("NOMBREEVALUACION",evaluacion.getNombreEvaluacion());
        contentValues.put("FECHAEVALUACION",evaluacion.getFechaEvaluacion());
        db.update("evaluacion",contentValues,"IDEVALUACION = ?",id);
        return resultado;
    }
    public String eliminarEvaluacion(int idEvaluacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("evaluacion", "IDEVALUACION= '"+idEvaluacion+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String insertar(Evaluacion evaluacion) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put("IDEVALUACION", evaluacion.getIdEvaluacion());
        contentValues.put("IDTIPOEVAL", evaluacion.getTipoEvaluacion());
        contentValues.put("NOMBREEVALUACION", evaluacion.getNombreEvaluacion());
        contentValues.put("FECHAEVALUACION",evaluacion.getFechaEvaluacion());
        db.insert("evaluacion", null, contentValues);
        String resultado = " ";
        return resultado;
    }
    /*----------------------------------FIN METODOS CRUD EVALUACION---------------------------*/
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

    public String fechaLimiteDetalleEvaluacionPRIMERREVICION(String materia, String evaluacion, String carnet){
        String fechaLim = null;
        try{
            Cursor fechaLimCursor = db.rawQuery("SELECT det.FECHA_LIMITE FROM detallealumnosevaluados AS det\n" +
                    "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                    "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'" ,null);

            if(fechaLimCursor.moveToFirst()){
                while (fechaLimCursor.isAfterLast()==false){
                    fechaLim=fechaLimCursor.getString(0);
                    fechaLimCursor.moveToNext();
                }
            }

            Toast.makeText(this.context, fechaLim, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return fechaLim;
    }

    public String fechaLimiteParaSegundaRevicion(String materia, String evaluacion, String carnet){
        String fechaLim = null;
        try{
            Cursor fechaLimCursor = db.rawQuery("SELECT p.FECHALIMITESEGUNDAREVISION FROM detallealumnosevaluados AS det\n" +
                    "JOIN PRIMERREVISION as p ON p.ID_DETALLEALUMNOSEVALUADOS = det.ID_DETALLEALUMNOSEVALUADOS\n" +
                    "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                    "WHERE det.CARNET = '"+carnet+"' AND eva.idasignatura = '"+materia+"' AND eva.NOMBREEVALUACION = '"+evaluacion+"'" ,null);
            if(fechaLimCursor.moveToFirst()){
                while (fechaLimCursor.isAfterLast()==false){
                    fechaLim=fechaLimCursor.getString(0);
                    fechaLimCursor.moveToNext();
                }
            }

            Toast.makeText(this.context, "La fecha limite es: "+ fechaLim, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return fechaLim;
    }

    public String fechaLimiteDetalleEvaluacionSEGUNDAREVISION(String materia, String evaluacion, String carnet){
        String fechaLim = null;
        try{
            abrir();
            Cursor fechaLimCursor = db.rawQuery("SELECT det.FECHA_LIMITE FROM detallealumnosevaluados AS det\n" +
                    "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                    "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'" ,null);

            if(fechaLimCursor.moveToFirst()){
                while (fechaLimCursor.isAfterLast()==false){
                    fechaLim=fechaLimCursor.getString(0);
                    fechaLimCursor.moveToNext();
                }
            }
            cerrar();

            Toast.makeText(this.context, fechaLim, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return fechaLim;
    }

    public String insertPrimerRevision(String fechaSolcitud, String carnet, String materia, String evaluacion) {
        try {
            abrir();
            // Buscando materia
            Cursor cursor = db.rawQuery("SELECT IDDOCENTE FROM DOCENTE WHERE IDASIGNATURA = '" +materia+ "' " ,null);

            // Variable del ID del DocenteSegundaRevision
            String idDocente = null;

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
                //throw new NoSuchFieldException("Ya se realizo una solicitud de con estos datos");
                return null;
            }else{
                abrir();
                ContentValues primerRevisionParametros = new ContentValues();
                primerRevisionParametros.put("FECHASOLICITUDPRIMERAREV", fechaSolcitud);
                primerRevisionParametros.put("IDDOCENTE", idDocente);
                primerRevisionParametros.put("ID_DETALLEALUMNOSEVALUADOS", idDetalleAlumnoEvaluado);
                // Solicitando la inserción a la tabla
                db.insert("PRIMERREVISION", null, primerRevisionParametros);
                cerrar();
                return "1";
            }

        }
        catch(Exception e) {
            rollback();
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
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

    public String[] obtenerMateriasCR(){

        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT * FROM materia",null);
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
                String idMateria=(datos.getString(0));
                data[contador]= idMateria;
                contador= contador+1;
                datos.moveToNext();
            }
        }
        return data;
    }
    public String[] idMateriasCR(){
        Integer contador2=0;
        Cursor datos= db.rawQuery("SELECT IDASIGNATURA FROM materia",null);
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

    public String[] obtenerEvaluacionesCR(String carnet, String materia){

        Integer contador2=0;
        Cursor datos1= db.rawQuery("SELECT NOMBREEVALUACION\n" +
                " FROM EVALUACION AS eva\n" +
                " JOIN DETALLEALUMNOSEVALUADOS AS det ON det.IDEVALUACION = eva.idEVALUACION\n" +
                " WHERE det.CARNET = '" +carnet+ "' AND IDASIGNATURA = '" +materia+ "' AND ASISTIO = 1",null);
        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                contador2= contador2+1;
                datos1.moveToNext();
            }
        }
        String [] data=new String[contador2];

        Integer contador=0;

        if(datos1.moveToFirst()){
            while (datos1.isAfterLast()== false){
                String nombreEva= datos1.getString(0);
                data[contador]= nombreEva;
                contador= contador+1;
                datos1.moveToNext();
            }
        }
        return data;
    }



















    public String estadoSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

               Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                        "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                        "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                        "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                        "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

         if(datos == null){
             return estadoSalida;
         }else{
             if(datos.moveToFirst()){
                 while (datos.isAfterLast()==false){
                     estadoSalida = datos.getString(0);
                     datos.moveToNext();
                 }
             }
         }

        return estadoSalida;
    }


    public String fechaSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHAPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(1);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }

    public String horaSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(2);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }

    public String localSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, local.NOMBRELOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "JOIN local ON local.IDLOCAL = p.IDLOCAL\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(3);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }


    public String notaAntesSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(4);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }

    public String notaDespuesSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(5);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }

    public String observacionesSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOPRIMERAREV, p.FECHASOLICITUDPRIMERAREV, p.HORAPRIMERAREV, p.IDLOCAL, p.NOTAANTESPRIMERAREV, p.NOTADESPUESPRIMERAREV, p.OBSERVACIONESPRIMERAREV FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(6);
                    datos.moveToNext();
                }
            }
        }

        return estadoSalida;
    }


    //METODO PARA INSERTAR SEGUNDA REVISION
    public void insertSegundaRevision(String fechaSolicitud, String carnet, String materia, String evaluacion)
    {

        try {

            // Abriendo base de datos
            abrir();

            Cursor cursor = db.rawQuery("SELECT IDDOCENTE FROM DOCENTE WHERE IDASIGNATURA = '" +materia+ "' " ,null);

            // Variable del ID del Docente
            String idDocente = null;

            if(cursor.moveToFirst()){
                while (cursor.isAfterLast()==false){
                    idDocente=cursor.getString(0);
                    cursor.moveToNext();
                }
            }
            if (idDocente == null) {
                throw new NoSuchFieldException("No se encontró el docente de la materia indicada: '" + materia + "'");
            }

            // Buscando que exista una evaluación para la materia indicada
            Cursor evaluacionCursor = db.rawQuery("SELECT eva.IDEVALUACION FROM EVALUACION AS EVA\n" +
                    "JOIN DETALLEALUMNOSEVALUADOS AS det ON det.IDEVALUACION = eva.IDEVALUACION\n" +
                    "WHERE IDASIGNATURA = '"+materia+"' AND NOMBREEVALUACION = '"+evaluacion+"' And det.ASISTIO = 1" ,null);
            // Asumiendo que solo se recibe un registro, se omite un loop y se extrae la primera fila
            // Siempre que no sea la última

            int idEvaluacion = 0;
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
            if(detalleAlumnoEvaluado.moveToFirst()){
                while (detalleAlumnoEvaluado.isAfterLast()==false){
                    idDetalleAlumnoEvaluado=detalleAlumnoEvaluado.getInt(0);
                    detalleAlumnoEvaluado.moveToNext();
                }
            }
            if (idDetalleAlumnoEvaluado == 0) {
                throw new NoSuchFieldException("NO se encontro evaluacion relacaionada");
            }

            Cursor integridad = db.rawQuery("\n" +
                    "SELECT p.IDSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                    "JOIN DETALLEALUMNOSEVALUADOS AS det on P.ID_DETALLEALUMNOSEVALUADOS = det.ID_DETALLEALUMNOSEVALUADOS\n" +
                    "WHERE det.IDEVALUACION = '"+idEvaluacion+"' AND p.ID_DETALLEALUMNOSEVALUADOS = '"+idDetalleAlumnoEvaluado+"'",null);

            int integridadSol = 0;
            if(integridad.moveToFirst()){
                while (integridad.isAfterLast()==false){
                    integridadSol=integridad.getInt(0);
                    integridad.moveToNext();
                }
            }
            if (integridad == null) {
                throw new NoSuchFieldException("Integridad Nula");
            }
            if(!(integridadSol == 0)){
                throw new NoSuchFieldException("Ya se realizo una solicitud de con estos datos");
            }else{
                abrir();
                ContentValues segundaRevisionParametros = new ContentValues();
                segundaRevisionParametros.put("FECHASOLICITUDSEGUNDAREVICION", fechaSolicitud);
                segundaRevisionParametros.put("ID_DETALLEALUMNOSEVALUADOS", idDetalleAlumnoEvaluado);
                segundaRevisionParametros.put("MATERIASEGUNDAREVICION", materia);
                // Solicitando la inserción a la tabla
                db.insert("SEGUNDAREVICION", null, segundaRevisionParametros);
                cerrar();
            }

            cerrar();
        }
        catch(Exception e) {
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    // SOLICITUDES SEGUNDA REVISION
    public int idSolicitudesPrimeraRevisionCR(String carnet, String materia, String evaluacion){
        int estadoSalida = 0;

        Cursor datos = db.rawQuery("SELECT p.IDPRIMERREVISION FROM primerrevision AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN DOCENTE ON p.IDDOCENTE= docente.IDDOCENTE\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "'  AND eva.NOMBREEVALUACION = '" +evaluacion+ "' AND P.ESTADOPRIMERAREV = 'APROBADO' ",null);

        if(datos.moveToFirst()){
            while (datos.isAfterLast()==false){
                estadoSalida = datos.getInt(0);
                datos.moveToNext();
            }
        }

        return estadoSalida;
    }

    public String estadoSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, p.IDLOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(0);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }

    public String fechaSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, p.IDLOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(1);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }

    public String horaSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, p.IDLOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(2);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }
    public String localSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, LOCAL.NOMBRELOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "JOIN local ON local.IDLOCAL = p.IDLOCAL\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(3);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }

    public String notaDespuesSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, p.IDLOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(4);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }
    public String observacionSolicitudesSegundaRevisionCR(String carnet, String materia, String evaluacion){
        String estadoSalida= null;

        Cursor datos = db.rawQuery("SELECT P.ESTADOSEGUNDAREVICION, p.FECHASEGUNDAREVICION, p.HORASEGUNDAREVICION, p.IDLOCAL, p.NOTADESPUESSEGUNDAREVICION, p.OBSERVACIONESSEGUNDAREVICION FROM SEGUNDAREVICION AS p\n" +
                "JOIN detallealumnosevaluados AS det ON p.ID_DETALLEALUMNOSEVALUADOS= det.ID_DETALLEALUMNOSEVALUADOS\n" +
                "JOIN evaluacion as eva ON det.IDEVALUACION= eva.IDEVALUACION\n" +
                "WHERE det.CARNET = '" +carnet+ "' AND eva.idasignatura = '" +materia+ "' AND eva.NOMBREEVALUACION = '" +evaluacion+ "'",null);

        if(datos == null){
            return estadoSalida;
        }else{
            if(datos.moveToFirst()){
                while (datos.isAfterLast()==false){
                    estadoSalida = datos.getString(5);
                    datos.moveToNext();
                }
            }
        }
        return estadoSalida;
    }
    // FIN CRISS

    //Encargado Impresion

    public ArrayList<Escuela> consultarEscuelas(){
        ArrayList<Escuela> escuelas = new ArrayList<Escuela>();

        Cursor cursor = db.rawQuery("SELECT * FROM ESCUELA",null);

        while (cursor.moveToNext()){
            Escuela escuela = new Escuela(
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getInt(cursor.getColumnIndex("ID_AREA")),
                    cursor.getString(cursor.getColumnIndex("NOMBREESCUELA")),
                    cursor.getString(cursor.getColumnIndex("FACULTAD"))
            );
            escuelas.add(escuela);
        }
        return escuelas;
    }

    public Escuela consultarEscuela(String id){
        Escuela escuela = new Escuela();
        Cursor cursor = db.rawQuery("SELECT * FROM ESCUELA WHERE IDESCUELA='"+id+"'",null);
        if(cursor.moveToFirst()){
            return new Escuela(
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getInt(cursor.getColumnIndex("ID_AREA")),
                    cursor.getString(cursor.getColumnIndex("NOMBREESCUELA")),
                    cursor.getString(cursor.getColumnIndex("FACULTAD"))
            );
        }else {
            return null;
        }
    }

    public String[] obtenerUsuariosEncargados(){
        Cursor cursor = db.rawQuery("SELECT USUARIO FROM USUARIO WHERE USUARIO LIKE '%IMPRESION%'",null);
        if (cursor==null) return null;

        String[] usuarios;
        int i=0;
        usuarios = new String[cursor.getCount()];
        while (cursor.moveToNext()) {
            usuarios[i] = cursor.getString(cursor.getColumnIndex("USUARIO"));
            i++;
        }
        return usuarios;
    }

    public String obtenerPassEncargado(String idUsuario){
        Cursor cursor = db.rawQuery("SELECT CONTRASENA FROM USUARIO WHERE USUARIO='"+idUsuario+"'",null);
        if(cursor != null && cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex("CONTRASENA"));
        }
        return null;
    }


    public String crearUsuarioEncargado(String usuario, String nombreUsuario, String pass){
        ContentValues regUsuario = new ContentValues();
        regUsuario.put("USUARIO", usuario);
        regUsuario.put("NOMBRE_USUARIO", nombreUsuario);
        regUsuario.put("CONTRASENA", pass);

        ContentValues regAccceso = new ContentValues();
        regAccceso.put("USUARIO", usuario);
        regAccceso.put("ID_OPCION", "1");

        long res1 = db.insert("USUARIO",null,regUsuario);
        long res2 = res1!=-1 && res1!=0 ?
                db.insert("ACCESOUSUARIO",null,regAccceso) : -1;

        return  res2>0 ? "Usuario creado: "+usuario :"Error";

    }

    public String insertarEncargado(EncargadoDeImpresiones encargado){
        ContentValues regEncargado = new ContentValues();
        regEncargado.put("IDESCUELA", encargado.getIdEscuela());
        regEncargado.put("USUARIO", encargado.getUsuario());
        regEncargado.put("ID_OPCION", encargado.getIdOpcion());
        regEncargado.put("NOMBREENCARGADO", encargado.getNombreEncargado());
        regEncargado.put("APELLIDOENCARGADO", encargado.getApellidoEncargado());
       return  (db.insert("ENCARGADODEIMPRESIONES",null,regEncargado)>0)
               ? "Registro exitoso: "+encargado.getNombreEncargado()+""+encargado.getApellidoEncargado() : "Error";

    }

    public ArrayList<EncargadoDeImpresiones> obtenerEncargados(){
        ArrayList<EncargadoDeImpresiones> encargados = new ArrayList<EncargadoDeImpresiones>();
        Cursor cursor = db.rawQuery("SELECT * FROM ENCARGADODEIMPRESIONES",null);

        while (cursor.moveToNext()){
            EncargadoDeImpresiones encargado = new EncargadoDeImpresiones(
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("NOMBREENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("APELLIDOENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")));
            encargados.add(encargado);
        }
        return encargados;
    }

    public EncargadoDeImpresiones consultarEncargado(String id){
        EncargadoDeImpresiones encargados = new EncargadoDeImpresiones();
        Cursor cursor = db.rawQuery("SELECT * FROM ENCARGADODEIMPRESIONES WHERE IDENCARGADO='"+id+"'",null);
        if(cursor.moveToFirst()){
            return new EncargadoDeImpresiones(
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("NOMBREENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("APELLIDOENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")));
        }else {
            return null;
        }
    }

    public String actualizarEncargado(EncargadoDeImpresiones encargado){
        ContentValues regEncargado = new ContentValues();

        regEncargado.put("IDESCUELA", encargado.getIdEscuela());
        regEncargado.put("USUARIO", encargado.getUsuario());
        regEncargado.put("ID_OPCION", encargado.getIdOpcion());
        regEncargado.put("NOMBREENCARGADO", encargado.getNombreEncargado());
        regEncargado.put("APELLIDOENCARGADO", encargado.getApellidoEncargado());
        return  (db.update("ENCARGADODEIMPRESIONES",regEncargado,"IDENCARGADO='"+encargado.getIdEncargado()+"'",null)>0)
                ? "Registro actualizado exitosamente" : "Error";

    }

    public String eliminarEncargado(String idEncargado, String usuario){

        if((db.delete("ENCARGADODEIMPRESIONES","IDENCARGADO='"+idEncargado+"'",null)>0)){
            if ( db.delete("ACCESOUSUARIO","USUARIO='"+usuario+"'",null)>0){
                return (db.delete("USUARIO","USUARIO='"+usuario+"'",null)>0) ? "Registro eliminado":"Error";
            }
        }
        return "Error";
    }

  //Solicitud impresiones DOCENTE

    public Docente getDocenteActual(String usuario){
        String query = "SELECT * FROM DOCENTE d INNER JOIN USUARIO usr ON d.USUARIO = usr.USUARIO WHERE usr.USUARIO=? LIMIT 1;";
        Cursor cursor = db.rawQuery(query,new String[]{usuario});
        if(cursor.moveToFirst()){

            return new Docente(
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("IDTIPODOCENTECICLO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("IDASIGNATURA")),
                    cursor.getString(cursor.getColumnIndex("IDCICLO")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")),
                    cursor.getString(cursor.getColumnIndex("NOMBREDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("APELLIDODOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("ID_ROL"))
            );
        }else {
            return null;
        }
    }

    public ArrayList<Docente> getDocentes(String idEscuela){
        ArrayList<Docente> docentes = new ArrayList<>();
        String query = "SELECT * FROM DOCENTE WHERE IDESCUELA=? ";
        Cursor cursor = db.rawQuery(query,new String[]{idEscuela});
       while (cursor.moveToNext()){

            Docente docente = new Docente(
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("IDTIPODOCENTECICLO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("IDASIGNATURA")),
                    cursor.getString(cursor.getColumnIndex("IDCICLO")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")),
                    cursor.getString(cursor.getColumnIndex("NOMBREDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("APELLIDODOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("ID_ROL"))
            );
            docentes.add(docente);
        }
       return docentes;
    }

    public Docente getDocenteAdmin(String idDocente){
        String query = "SELECT * FROM DOCENTE WHERE IDDOCENTE=? LIMIT 1";
        Cursor cursor = db.rawQuery(query,new String[]{idDocente});
        if (cursor.moveToFirst()){

            return new Docente(
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("IDTIPODOCENTECICLO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("IDASIGNATURA")),
                    cursor.getString(cursor.getColumnIndex("IDCICLO")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")),
                    cursor.getString(cursor.getColumnIndex("NOMBREDOCENTE")),
                    cursor.getString(cursor.getColumnIndex("APELLIDODOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("ID_ROL"))
            );

        }
        return null;
    }

    public int getIdEncargadoImpresionesEscuela(String idEscuela){
        final String query = "SELECT en.IDENCARGADO FROM ENCARGADODEIMPRESIONES en INNER JOIN ESCUELA esc ON esc.IDESCUELA = en.IDESCUELA WHERE esc.IDESCUELA=? LIMIT 1;";
        Cursor c = db.rawQuery(query, new String[]{idEscuela});
        if (c.moveToFirst()){
            return c.getInt(c.getColumnIndex("IDENCARGADO"));
        }
        return 0;
    }

    public String insertarSolicitudImpresion(Impresion impresion){
        String resultado = "Solicitud enviada con exito";

        ContentValues solicitud = new ContentValues();
        solicitud.put("IDDOCENTE", impresion.getIdDocente());
        solicitud.put("IDENCARGADO", impresion.getIdEncargado());
        solicitud.put("DESCRIPCION_SOLICITUD", impresion.getDescripcionSolicitud());
        solicitud.put("CANTIDADEXAMENES", impresion.getCantidadExamenes());
        solicitud.put("HOJASEMPAQUE", impresion.getHojasEmpaque());
        return (db.insert("SOLICITUDIMPRESION",null,solicitud)>0)
                ? resultado : "Error";
    }

    public String actualizarSolicitudImpresion(Impresion impresion){
        String resultado = "Solicitud actualizada exitosamente";
        ContentValues solicitud = new ContentValues();
        solicitud.put("IDDOCENTE", impresion.getIdDocente());
        solicitud.put("IDENCARGADO", impresion.getIdEncargado());
        solicitud.put("DESCRIPCION_SOLICITUD", impresion.getDescripcionSolicitud());
        solicitud.put("CANTIDADEXAMENES", impresion.getCantidadExamenes());
        solicitud.put("HOJASEMPAQUE", impresion.getHojasEmpaque());

        return  (db.update("SOLICITUDIMPRESION",solicitud,"IDSOLICITUDIMPRESION='"+impresion.getIdSolicitudImpresion()+"'",null)>0)
                ? resultado : "Error";
    }

    public ArrayList<Impresion> consultarImpresionesDocente(String idDocente){
        ArrayList<Impresion> solicitudes = new ArrayList<Impresion>();
        final String query = "SELECT * FROM SOLICITUDIMPRESION WHERE IDDOCENTE=?";
        Cursor cursor = db.rawQuery(query,new String[]{idDocente});

        while (cursor.moveToNext()){
            Impresion solicitud = new Impresion(
                    cursor.getInt(cursor.getColumnIndex("IDSOLICITUDIMPRESION")),
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getInt(cursor.getColumnIndex("MOTIVONOIMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_NO_IMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_SOLICITUD")),
                    cursor.getInt(cursor.getColumnIndex("CANTIDADEXAMENES")),
                    cursor.getInt(cursor.getColumnIndex("HOJASEMPAQUE")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOAPROBACION")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOIMPRESION"))
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    public String eliminarSolicitudImpresion(String idSolicitud){
        return (db.delete("SOLICITUDIMPRESION","IDSOLICITUDIMPRESION='"+idSolicitud+"'",null)>0)
                ?"Registro eliminado":"Error";
    }

    public Impresion getSolicitudImpresion(String idSolicitud) {
        String query = "SELECT * FROM SOLICITUDIMPRESION WHERE IDSOLICITUDIMPRESION=? LIMIT 1;";
        Cursor cursor = db.rawQuery(query,new String[]{idSolicitud});
        if(cursor.moveToFirst()){

            return new Impresion(
                    cursor.getInt(cursor.getColumnIndex("IDSOLICITUDIMPRESION")),
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getInt(cursor.getColumnIndex("MOTIVONOIMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_NO_IMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_SOLICITUD")),
                    cursor.getInt(cursor.getColumnIndex("CANTIDADEXAMENES")),
                    cursor.getInt(cursor.getColumnIndex("HOJASEMPAQUE")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOAPROBACION")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOIMPRESION"))
            );
        }else {
            return null;
        }
    }

    //Solicitud impresiones ENCARGADO

    public EncargadoDeImpresiones getEncargadoActual(String usuario){
        final String query = "SELECT * FROM ENCARGADODEIMPRESIONES en INNER JOIN USUARIO usr ON en.USUARIO = usr.USUARIO WHERE usr.USUARIO=? LIMIT 1;";
        Cursor cursor = db.rawQuery(query,new String[]{usuario});
        if(cursor.moveToFirst()){
            return new EncargadoDeImpresiones(
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("IDESCUELA")),
                    cursor.getString(cursor.getColumnIndex("USUARIO")),
                    cursor.getString(cursor.getColumnIndex("ID_OPCION")),
                    cursor.getString(cursor.getColumnIndex("NOMBREENCARGADO")),
                    cursor.getString(cursor.getColumnIndex("APELLIDOENCARGADO"))
            );
        }else {
            return null;
        }
    }

    public ArrayList<Impresion> consultarImpresionesEncargado(int idEncargado){
        ArrayList<Impresion> solicitudes = new ArrayList<Impresion>();
        final String query = "SELECT * FROM SOLICITUDIMPRESION WHERE IDENCARGADO=?";
                Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(idEncargado)});

        while (cursor.moveToNext()){
            Impresion solicitud = new Impresion(
                    cursor.getInt(cursor.getColumnIndex("IDSOLICITUDIMPRESION")),
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getInt(cursor.getColumnIndex("MOTIVONOIMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_NO_IMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_SOLICITUD")),
                    cursor.getInt(cursor.getColumnIndex("CANTIDADEXAMENES")),
                    cursor.getInt(cursor.getColumnIndex("HOJASEMPAQUE")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOAPROBACION")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOIMPRESION"))
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    public String actualizarAprobacionSolicitudImpresion(Impresion impresion){
        String resultado = "Solicitud actualizada exitosamente";
        ContentValues solicitud = new ContentValues();
        solicitud.put("MOTIVONOIMP", impresion.getIdMotivoNoImp());
        solicitud.put("DESCRIPCION_NO_IMP", impresion.getDescripcionNoImp());
        solicitud.put("ESTADOAPROBACION", impresion.getEstadoAprobacion());
        solicitud.put("ESTADOIMPRESION", impresion.getEstadoImpresion());

        return  (db.update("SOLICITUDIMPRESION",solicitud,"IDSOLICITUDIMPRESION='"+impresion.getIdSolicitudImpresion()+"'",null)>0)
                ? resultado : "Error";
    }

    public String actualizarEstadoDeImpresion(Impresion impresion){
        String resultado = "Solicitud actualizada exitosamente";
        ContentValues solicitud = new ContentValues();
        solicitud.put("MOTIVONOIMP", impresion.getIdMotivoNoImp());
        solicitud.put("DESCRIPCION_NO_IMP", impresion.getDescripcionNoImp());
        solicitud.put("ESTADOIMPRESION", impresion.getEstadoImpresion());

        return  (db.update("SOLICITUDIMPRESION",solicitud,"IDSOLICITUDIMPRESION='"+impresion.getIdSolicitudImpresion()+"'",null)>0)
                ? resultado : "Error";
    }

    public ArrayList<Impresion> obtenerImpresiones(){
        ArrayList<Impresion> solicitudes = new ArrayList<Impresion>();
        final String query = "SELECT * FROM SOLICITUDIMPRESION";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            Impresion solicitud = new Impresion(
                    cursor.getInt(cursor.getColumnIndex("IDSOLICITUDIMPRESION")),
                    cursor.getString(cursor.getColumnIndex("IDDOCENTE")),
                    cursor.getInt(cursor.getColumnIndex("IDENCARGADO")),
                    cursor.getInt(cursor.getColumnIndex("MOTIVONOIMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_NO_IMP")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPCION_SOLICITUD")),
                    cursor.getInt(cursor.getColumnIndex("CANTIDADEXAMENES")),
                    cursor.getInt(cursor.getColumnIndex("HOJASEMPAQUE")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOAPROBACION")),
                    cursor.getInt(cursor.getColumnIndex("ESTADOIMPRESION"))
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }


    //Motivos de impresion no realizada

    public String insertarMotivo(MotivoNoImpresion motivoNoImpresion){
        ContentValues mot = new ContentValues();
        mot.put("MOTIVONOIMP", motivoNoImpresion.getMotivoNoImpresion());
        return (db.insert("MOTIVONOIMPRESION", null, mot)>0)
                ? "Registro exitoso" : "Error";
    }

    public ArrayList<MotivoNoImpresion> obtenerMotivos(){
        ArrayList<MotivoNoImpresion> motivos = new ArrayList<MotivoNoImpresion>();
        Cursor c = db.rawQuery("SELECT * FROM MOTIVONOIMPRESION", null);
        while (c.moveToNext()){
            MotivoNoImpresion motivo = new MotivoNoImpresion(
                    c.getInt(c.getColumnIndex("IDMOTIVONOIMP")),
                    c.getString(c.getColumnIndex("MOTIVONOIMP"))
            );
            motivos.add(motivo);
        }
        return motivos;
    }

    public String getMotivo(int idMotivo){
        final String query = "SELECT MOTIVONOIMP FROM MOTIVONOIMPRESION  WHERE IDMOTIVONOIMP=? LIMIT 1;";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(idMotivo)});
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex("MOTIVONOIMP"));
        }else {
            return "Causa desconocida"; //Se agregará un default
        }
    }

    public String actualizarMotivo(MotivoNoImpresion motivoNoImpresion){
        ContentValues mot = new ContentValues();
        mot.put("MOTIVONOIMP", motivoNoImpresion.getMotivoNoImpresion());
        return (db.update("MOTIVONOIMPRESION", mot,"IDMOTIVONOIMP='"+motivoNoImpresion.getIdMotivo()+"'",null)>0)
                ? "Registro actualizado exitosamente" : "Error";
    }
    public String eliminarMotivo(int idMotivo){
        return (db.delete("MOTIVONOIMPRESION","IDMOTIVONOIMP='"+idMotivo+"'",null)>0)?"Registro eliminado":"Error";
    }


}
