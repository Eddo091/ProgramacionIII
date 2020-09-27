package com.example.prueba;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {
    static String nameDB = "db_tienda";
    static String tblTienda = "CREATE TABLE tienda(idTienda integer primary key autoincrement, codigo text, producto text, fecha date, precio text)";

    public BD(@Nullable Context context, @Nullable String nameBD, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super( context, nameBD, factory, version );
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
                cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM tienda ORDER BY Producto ASC", null);
                break;
            case "nuevo":
                sqLiteDatabaseWritable.execSQL("INSERT INTO tienda (codigo,producto,fecha,precio) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"')");

                break;
            case "modificar":
                sqLiteDatabaseWritable.execSQL("UPDATE amigos SET codigo='"+ data[1] +"',producto='"+data[2]+"',fecha='"+data[3]+"',precio='"+data[4]+"' WHERE idTienda='"+data[0]+"'");
                break;

            case "eliminar":
                sqLiteDatabaseWritable.execSQL("DELETE FROM amigos WHERE idTienda='"+ data[0] +"'");
                break;

            default:
                break;

        }
        return cursor;
    }

}
