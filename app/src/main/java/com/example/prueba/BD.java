package com.example.prueba;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {
    static String nameDB = "db_tienda";
    static String tblTienda = "CREATE TABLE tienda(idTienda integer primary key autoincrement, nombre text, apellido text, direccion text, producto_id integrer, cuenta_id integrer)";

    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( tblTienda );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor mantenimientoTienda(String accion, String[] data) {
        SQLiteDatabase sqLiteDatabaseReadable = getReadableDatabase();
        SQLiteDatabase sqLiteDatabaseWritable = getWritableDatabase();
        Cursor cursor = null;
        switch (accion) {
            case "consultar":
                cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM tienda ORDER BY nombre ASC", null);
                break;
            case "nuevo":
                sqLiteDatabaseWritable.execSQL("INSERT INTO tienda (nombre,apellido,direccion,producto_id) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')");
                break;
            case "modificar":
                break;

            case "eliminar":
                break;

            default:
                break;

        }
        return cursor;
    }

}
