package com.example.registros;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Registros extends AppCompatActivity {
    private ListView miLista;
    Adapter mAdapter;
    String nombre;
    TextView txt_nombre;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        txt_nombre = findViewById(R.id.txt_nombre);

        nombre = getIntent().getStringExtra("nombre");

        usuario = getIntent().getParcelableExtra("usuario");

        final ArrayList<Usuario> listUsuario = getIntent().getParcelableArrayListExtra("lista");
        TextView txtOrdenarApellido = findViewById(R.id.ordenar_apellido);
        TextView txtOrdenarGrupo = findViewById(R.id.ordenar_grupo);
        TextView txtEliminarTodos = findViewById(R.id.eliminar);
        TextView txtInvertir = findViewById(R.id.invertir);


        miLista = findViewById(R.id.lista_contacto);
        usuario = new Usuario();
        usuario.setNombre(nombre);





        mAdapter = new Adapter(Registros.this,R.layout.list_registros,listUsuario);
        miLista.setAdapter(mAdapter);
        registerForContextMenu(miLista);
        txtOrdenarApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUsuario, new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario o1, Usuario o2) {
                        return o1.getNombre().compareTo(o2.getNombre());
                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });

        txtOrdenarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUsuario, new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario o1, Usuario o2) {
                        return o1.getGrupo().compareTo(o2.getGrupo());
                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });

        txtInvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(listUsuario);

                mAdapter.notifyDataSetChanged();
            }
        });

        txtEliminarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUsuario.clear();
                mAdapter.notifyDataSetChanged();

            }
        });



    }




}
