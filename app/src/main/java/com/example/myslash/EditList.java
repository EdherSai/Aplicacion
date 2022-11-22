package com.example.myslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EditList extends AppCompatActivity {

    private EditText Name, Password;
    private RadioButton Opcion1, Opcion2, Opcion3, Opcion4;
    private int []imagenUser = { R.drawable.user,R.drawable.user1,R.drawable.user2,R.drawable.user3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        Name = (EditText) findViewById(R.id.editTextELName);
        Password = (EditText) findViewById(R.id.editTextElPassword);
        Opcion1 = (RadioButton) findViewById(R.id.radioButtonEL1);
        Opcion2 = (RadioButton) findViewById(R.id.radioButtonEL2);
        Opcion3 = (RadioButton) findViewById(R.id.radioButtonEL3);
        Opcion4 = (RadioButton) findViewById(R.id.radioButtonEL4);

        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numContext = getIntent().getExtras().getInt("numContext");

        try {
            if (numContext == 2) {
                int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");
                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + "." + numArchivoCuenta + ".txt")));
                String lineaTexto = file.readLine();
                file.close();

                Des myDes = new Des();

                Json json = new Json();
                Cuenta datos = json.leerJsonCuenta(lineaTexto);
                String valorAccountName = myDes.desCifrar(datos.getNameCuenta());
                String valorAccountPassword = myDes.desCifrar(datos.getPassCuenta());
                int valorAccountImage = datos.getImage();

                Name.setText(valorAccountName);
                Password.setText(valorAccountPassword);
                if(valorAccountImage == imagenUser[0]){Opcion1.setChecked(true);}
                if(valorAccountImage == imagenUser[1]){Opcion2.setChecked(true);}
                if(valorAccountImage == imagenUser[2]){Opcion3.setChecked(true);}
                if(valorAccountImage == imagenUser[3]){Opcion4.setChecked(true);}
            }
        }catch(Exception e){}
    }

    public void Enviar (View v){
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numContext = getIntent().getExtras().getInt("numContext");
        if(false == Opcion1.isChecked() & false == Opcion2.isChecked() &
                false == Opcion3.isChecked() & false == Opcion4.isChecked()) {
            Toast.makeText(EditList.this, "Falta un parametro", Toast.LENGTH_SHORT).show();
        }else {
            if(Name.length() > 22 || Password.length() > 30){
                String mensaje = "Parametro Erroneo";
                if(Name.length() > 22){mensaje = "Nombre Muy Largo";}
                if(Password.length() > 30){mensaje = "Contraseña Muy Larga";}
                Toast.makeText(EditList.this, mensaje, Toast.LENGTH_SHORT).show();
            }else {
                Json json = new Json();
                Des myDes = new Des();
                if (numContext == 1) {
                    try{
                        String valorNombre = myDes.cifrar(Name.getText().toString());
                        String valorPassword = myDes.cifrar(Password.getText().toString());
                        int valorImage = imagenUser[0];
                        if(Opcion1.isChecked()){valorImage = imagenUser[0];}
                        if(Opcion2.isChecked()){valorImage = imagenUser[1];}
                        if(Opcion3.isChecked()){valorImage = imagenUser[2];}
                        if(Opcion4.isChecked()){valorImage = imagenUser[3];}

                        String textoJsonCuenta = json.crearJsonCuenta( valorNombre, valorPassword, valorImage);

                        boolean BucleArchivo = true;
                        int x = 1;
                        while (BucleArchivo) {
                            File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + x + ".txt");
                            if (Cfile.exists()) {x = x + 1;}
                            else {
                                BufferedWriter fileC = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + numArchivo + "." + x + ".txt", Context.MODE_PRIVATE)));
                                fileC.write(textoJsonCuenta);
                                fileC.close();

                                BucleArchivo = false;
                            }
                        }
                    }catch(Exception e){}
                }
                if (numContext == 2){
                    try{
                        int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");

                        String valorNombre = myDes.cifrar(Name.getText().toString());
                        String valorPassword = myDes.cifrar(Password.getText().toString());
                        int valorImage = imagenUser[0];
                        if(Opcion1.isChecked()){valorImage = imagenUser[0];}
                        if(Opcion2.isChecked()){valorImage = imagenUser[1];}
                        if(Opcion3.isChecked()){valorImage = imagenUser[2];}
                        if(Opcion4.isChecked()){valorImage = imagenUser[3];}

                        String textoJsonCuenta = json.crearJsonCuenta( valorNombre, valorPassword, valorImage);

                        BufferedWriter fileC = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + numArchivo + "." + numArchivoCuenta + ".txt", Context.MODE_PRIVATE)));
                        fileC.write(textoJsonCuenta);
                        fileC.close();
                    }catch(Exception e){}
                }
                Intent intent = new Intent(EditList.this, ListMain.class);
                intent.putExtra("numArchivo", numArchivo);
                startActivity(intent);
            }
        }
    }

    public void Volver (View v){
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        Intent intent = new Intent (EditList.this, ListMain.class);
        intent.putExtra("numArchivo", numArchivo);
        startActivity( intent );
    }
}