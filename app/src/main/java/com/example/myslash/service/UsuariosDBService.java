package com.example.myslash.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.myslash.contract.UsuariosContract;
import com.example.myslash.dao.Usuarios;

public class UsuariosDBService extends SQLiteOpenHelper
{
    public static final String TAG = "UsuariosDBService";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuarios.db";

    public UsuariosDBService(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        if( sqLiteDatabase == null )
        {
            return;
        }
        sqLiteDatabase.execSQL(UsuariosContract.UsuarioEntry.getCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase , int oldVersion , int newVersion )
    {
    }

    public boolean saveUsuario(Usuarios usuario)
    {
        long resultado = 0;
        SQLiteDatabase sqLiteDatabase = null;
        if( usuario == null )
        {
            return false;
        }
        sqLiteDatabase = getWritableDatabase();
        resultado = sqLiteDatabase.insert(UsuariosContract.UsuarioEntry.TABLE_NAME,null,UsuariosContract.UsuarioEntry.toContentValues(usuario));
        return resultado > 0;
    }


    //Método para desplegar usuarios (?)

    public List<Usuarios> getUsuarios( )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<Usuarios>usuarios = null;
        Usuarios usuario = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(UsuariosContract.UsuarioEntry.TABLE_NAME,null,null, null, null, null, UsuariosContract.UsuarioEntry.USERNAME );
        if( cursor == null )
        {
            return null;
        }
        if( cursor.getCount() < 1)
        {
            return null;
        }
        if( !cursor.moveToFirst() )
        {
            return null;
        }
        Log.d(TAG, "" + cursor.getCount());
        usuarios = new ArrayList<Usuarios>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            usuario = new Usuarios( );
            usuario.setID( cursor.getInt( 0 ) );
            usuario.setName( cursor.getString( 1 ) );
            usuario.setFirstName( cursor.getString( 2 ) );
            usuario.setLastName( cursor.getString( 3 ) );
            usuario.setUserName( cursor.getString( 4 ) );
            usuario.setMail( cursor.getString( 5 )  );
            usuario.setAge( cursor.getString( 6 ) );
            usuario.setNumber( cursor.getString( 7 ) );
            usuario.setGender( cursor.getString( 8 ) );
            usuario.setType( cursor.getString( 9 ) );
            usuario.setPassword( cursor.getString( 10 ) );
            usuarios.add( usuario );
            cursor.moveToNext( );
        }
        return usuarios;
    }
}
