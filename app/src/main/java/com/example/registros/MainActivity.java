package com.example.registros;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText edt_nombre, edt_telefono;
    Spinner spinner_grupo;
    Button btn_guardar;
    String nombre,telefono, grupo;
    Usuario mi_usuario;
    String doc;
    ArrayList<Usuario> listaUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nombre = (EditText)findViewById(R.id.edt_nombre);
        edt_telefono = (EditText)findViewById(R.id.edt_telefono);
        spinner_grupo =(Spinner)findViewById(R.id.spinner_seleccion);
        btn_guardar = (Button)findViewById(R.id.btn_guardar);

        listaUsuario = new ArrayList<>();

        registerForContextMenu(edt_telefono);
        registerForContextMenu(edt_nombre);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edt_nombre.getText().toString();
                telefono = edt_telefono.getText().toString();
                grupo = spinner_grupo.getSelectedItem().toString();

                if (nombre.isEmpty() || telefono.isEmpty() ){
                    Toast.makeText(MainActivity.this, "Te falto llenar un campo", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
                    mi_usuario = new Usuario();
                    mi_usuario.setNombre(nombre);
                    mi_usuario.setGrupo(grupo);
                    mi_usuario.setTelefono(telefono);
                    listaUsuario.add(mi_usuario);

                }
            }
        });

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Escoge una opcion");

        getMenuInflater().inflate(R.menu.telefonos,menu);
        getMenuInflater().inflate(R.menu.contextual_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
        if (id== R.id.opcion){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
                edt_telefono.setText("300"+rnd.nextInt(9000000-(-30)+5));

       }
        else if (id==R.id.opcion2){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
                edt_telefono.setText("310"+rnd.nextInt(9000000-(-30)+5));
        }
        else if (id==R.id.opcion3){
            Random rnd = new Random();
            int array[] = new int[30];
            for(int i = 0; i < array.length; i++)
                edt_telefono.setText("320"+rnd.nextInt(9000000-(-30)+5));

        }
        else if(id==R.id.convertir){
            String edt = edt_nombre.getText().toString();
            if (edt.equals(edt.toLowerCase()))
                edt_nombre.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        }
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.limpiar_campos){
            edt_nombre.setText("");
            edt_telefono.setText("");
        }
        else if(id == R.id.ver_contactos){
            Intent intent = new Intent(MainActivity.this, Registros.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("usuario",mi_usuario);
            intent.putExtra("lista",listaUsuario);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        doc =(String)spinner_grupo.toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}