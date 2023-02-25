package com.example.myslash.contract;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.Serializable;
import com.example.myslash.dao.Usuarios;

public class UsuariosContract implements Serializable {

    public static final String TAG = "UsuariosContract";
    public static abstract class UsuarioEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "USUARIOS";
        public static final String ID = "ID";
        public static final String NAME = "NOMBRE";
        public static final String FIRST_NAME = "APELLIDO1";
        public static final String LAST_NAME = "APELLIDO2";
        public static final String USERNAME = "NOM_USR";
        public static final String MAIL = "CORREO";
        public static final String AGE = "EDAD";
        public static final String NUMBER = "TELEFONO";
        public static final String GENDER = "GENERO";
        public static final String TYPE = "TIPO";
        public static final String PASSWORD = "CONTRASENA";

        public static final String getCreateTable()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append( "CREATE TABLE" );
            stringBuilder.append( TABLE_NAME );
            stringBuilder.append( "( " );
            stringBuilder.append( ID );
            stringBuilder.append( "INT NOT NULL PRIMARY KEY AUTOINCREMENT," );
            stringBuilder.append( NAME );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( FIRST_NAME );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( LAST_NAME );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( USERNAME );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( MAIL );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( AGE );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( NUMBER );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( GENDER );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( TYPE );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( PASSWORD );
            stringBuilder.append( " TEXT NOT NULL," );
            stringBuilder.append( " UNIQUE (" );
            stringBuilder.append( USERNAME );
            stringBuilder.append( " ) )" );
            Log.d(TAG, stringBuilder.toString());
            return stringBuilder.toString();
        }

        public static ContentValues toContentValues(Usuarios usuario)
        {
            ContentValues values = new ContentValues();
            values.put(ID, usuario.getID());
            values.put(NAME, usuario.getName());
            values.put(FIRST_NAME, usuario.getFirstName());
            values.put(LAST_NAME, usuario.getLastName());
            values.put(USERNAME, usuario.getUserName());
            values.put(MAIL, usuario.getMail());
            values.put(AGE, usuario.getAge());
            values.put(NUMBER, usuario.getNumber());
            values.put(GENDER, usuario.getGender());
            values.put(TYPE, usuario.getType());
            values.put(PASSWORD, usuario.getPassword());
            values.put(USERNAME, usuario.getUserName());
            return values;
        }
    }
}
