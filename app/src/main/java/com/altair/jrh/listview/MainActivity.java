package com.altair.jrh.listview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txt_nombre;
    private EditText txt_edad;
    private Spinner sp_nacionalidad;
    ArrayList<Nacionalidad> paises = new ArrayList<Nacionalidad>();

    private Button btn_guardar;
    private Button btn_editar;
    private Button btn_borrar;

    private ListView extranjeros;
    ArrayList<Inmigrantes> lista_inmigrantes = new ArrayList<Inmigrantes>();


    ArrayAdapter<Inmigrantes> adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_edad = (EditText) findViewById(R.id.txt_edad);
        txt_nombre = (EditText) findViewById(R.id.txt_name);

        sp_nacionalidad = (Spinner) findViewById(R.id.sp_nacionalidad);
        extranjeros = (ListView) findViewById(R.id.lista);


        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_editar = (Button) findViewById(R.id.btn_editar);
        btn_borrar = (Button) findViewById(R.id.btn_borrar);

        btn_guardar.setOnClickListener(this);
        btn_editar.setOnClickListener(this);
        btn_borrar.setOnClickListener(this);


        paises.add(new Nacionalidad(0,"España"));
        paises.add(new Nacionalidad(1,"Alemania"));
        paises.add(new Nacionalidad(2,"Francia"));
        paises.add(new Nacionalidad(3,"Inglaterra"));
        paises.add(new Nacionalidad(4,"Portugal"));
        paises.add(new Nacionalidad(5,"Irlanda"));
        paises.add(new Nacionalidad(6,"Grecia"));
        paises.add(new Nacionalidad(7,"Suiza"));
        paises.add(new Nacionalidad(8,"Albania"));
        paises.add(new Nacionalidad(9,"Rusia"));
        paises.add(new Nacionalidad(10,"China"));
        paises.add(new Nacionalidad(11,"Japon"));
        paises.add(new Nacionalidad(12,"Tailandia"));
        paises.add(new Nacionalidad(13,"USA"));
        paises.add(new Nacionalidad(14,"Mexico"));
        paises.add(new Nacionalidad(15,"Venezuela"));

        ArrayAdapter<Nacionalidad> adapter =  new ArrayAdapter<Nacionalidad>(this,R.layout.support_simple_spinner_dropdown_item,paises);
        sp_nacionalidad.setAdapter(adapter);

        adapter2 =  new ArrayAdapter<Inmigrantes>(this,R.layout.support_simple_spinner_dropdown_item,lista_inmigrantes);
        extranjeros.setAdapter(adapter2);

        registerForContextMenu(extranjeros);
        // Este le asigna el menu creado en res
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Le decimos que tiene un menu contextual. Esto hace que lo muestra
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();



        switch (item.getItemId()){

            case R.id.opceditar:

                Inmigrantes i = lista_inmigrantes.get(info.position);



                txt_nombre.setText(i.getNombre());
                txt_edad.setText(String.valueOf(i.getEdad()));
           //     sp_nacionalidad.setSelection(i.getNacionalidad().);
            //    sp_nacionalidad.setSelection(paises.indexOf(i.getNacionalidad()));







                sp_nacionalidad.setSelection(0);


                btn_guardar.setText(getResources().getString(R.string.editar));

                break;
            case R.id.opcborrar:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setMessage(getResources().getString(R.string.mgsborrar));
                // Alt + Enter => Para los errores | Control + Espacio => Ayuda
                alerta.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       lista_inmigrantes.remove(info.position);
                        adapter2.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),R.string.borrar,Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialogo = alerta.create();
                dialogo.show();
                break;

        }
    return true;
    }

    @Override
    public void onClick(View v) {

        int pos = extranjeros.getCheckedItemPosition();

        switch (v.getId()) {

            case R.id.btn_guardar:

                if (!TextUtils.isEmpty(txt_nombre.getText().toString()) && !TextUtils.isEmpty(txt_edad.getText().toString())) {
                    String nombre = txt_nombre.getText().toString();
                    int edad = Integer.parseInt(txt_edad.getText().toString());
                   // String nacionalidad = String.valueOf(sp_nacionalidad.getSelectedItemPosition());
                   String nacionalidad = String.valueOf(paises.get(sp_nacionalidad.getSelectedItemPosition()).getNacion());
                   //ESTO HAY QUE CAMBIARLO SI O SI

                    Inmigrantes in = new Inmigrantes(nombre, edad, nacionalidad);
                    lista_inmigrantes.add(in);
                    adapter2.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), R.string.guardar, Toast.LENGTH_LONG).show();

                    //Limpiar Campos
                    txt_nombre.setText("");
                    txt_edad.setText("");
                    sp_nacionalidad.setSelection(0);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_editar:


                    String nombre = txt_nombre.getText().toString();
                    int edad = Integer.parseInt(txt_edad.getText().toString());
                    String nacionalidad = String.valueOf(sp_nacionalidad.getSelectedItem());
                    Inmigrantes in = new Inmigrantes(nombre, edad, nacionalidad);
                    lista_inmigrantes.set(pos, in);
                    adapter2.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), R.string.editar, Toast.LENGTH_LONG).show();
                    break;
            case R.id.btn_borrar:
                pos = extranjeros.getCheckedItemPosition();
                lista_inmigrantes.remove(pos);
                adapter2.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),R.string.borrar,Toast.LENGTH_LONG).show();

                break;


        }

        class AdaptadorNacionalidad extends ArrayAdapter {
            public AdaptadorNacionalidad(Context context, int resource) {
                super(context, resource);

            }
        }


    }
}
