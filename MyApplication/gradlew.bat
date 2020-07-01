package com.example.basedatosmp16001;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class ControlBDCarnet{
    private static final String[]camposCliente = new String []
            {"id","nombre","apellido","direccion", "telefono"};
    private static final String[] camposCliente_Vehiculo = new String []
            {"idcliente","idvehiculo", "precio"};
    private static final String[] camposVehiculo = new String []
            {"idvehiculo","marca","modelo","anio"};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBDCarnet(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);}
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "prueba.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE [cliente_vehiculo] ([idcliente] VARCHAR(20) not NULL,[idvehiculo] vaRCHAR(20) not null, [precio] varchar(20)   not NULL,PRIMARY KEY ([idcliente],[idvehiculo]));");
                db.execSQL("CREATE TABLE [cliente] ([id] varchar(30)  NOT NULL PRIMARY KEY,[nombre] VARCHAR(50)  NOT NULL,[apellido] VARCHAR(30)  NOT NULL,[direccion] VARCHAR(50)  NOT NULL,[telefono] VARCHAR(50)  NOT NULL);");
                db.execSQL("CREATE TABLE [vehiculo] ([idvehiculo] vARCHAR(20)  PRIMARY KEY NOT NULL,[marca] vaRCHAR(20) NOT NULL,[modelo] vARCHAR(20) NOT NULL,[anio] vaRCHAR(20)   NOT NULL);");

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
        db = DBHelper.get