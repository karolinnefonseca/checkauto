package com.gilson.checkauto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gilson.checkauto.Adapters.Adaptador;

import java.util.ArrayList;
import java.util.List;

public class Pesquisar extends AppCompatActivity
{
    private ListView lstvPesquisa;
    private Adaptador meuAdp;
    private Button btnVoltar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        btnVoltar2 = findViewById(R.id.btnVoltar2);
        lstvPesquisa = findViewById(R.id.lstvPesquisa);

        Intent it = getIntent();

        if (it != null) {
            int tipoPesquisa = it.getIntExtra("tipo", 0);
            String textoPesquisa = it.getStringExtra("pesquisa");

            List<ContentValues> listaParamentros = new ArrayList<>();

            //Verificando o tipo de pesquisa que o usuário quer fazer
            if (tipoPesquisa == R.id.rbtMarca) {
                listaParamentros = new Carro(this).pesquisarCarro(textoPesquisa);
            }
            else if (tipoPesquisa == R.id.rbtAno)
            {
                try
                {
                    int ano = Integer.parseInt(textoPesquisa);
                    listaParamentros = new Carro(this).pesquisarCarro(ano);
                }
                catch (Exception e) {
                    listaParamentros = new Carro(this).pesquisarCarro();
                }
            }
            else if (tipoPesquisa == R.id.rbtTodos) {
                listaParamentros = new Carro(this).pesquisarCarro();
            }

            if (listaParamentros.size() > 0)
            {
                meuAdp = new Adaptador(this, listaParamentros);
                lstvPesquisa.setAdapter(meuAdp);
                meuAdp.notifyDataSetChanged();
            }
            else
            {
                Toast.makeText(Pesquisar.this, "Não foi possível mostrar o resultado!", Toast.LENGTH_LONG).show();
            }
        }
        lstvPesquisa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                final int positionRemove = i;
                ContentValues c = (ContentValues) lstvPesquisa.getItemAtPosition(positionRemove);
                String id = String.valueOf(c.getAsString("id"));
                String marca = String.valueOf(c.getAsString("marca"));

                AlertDialog.Builder adb = new AlertDialog.Builder(Pesquisar.this);
                adb.setTitle("REMOVER?");
                adb.setMessage("Tem certeza que deseja remover o carro " + marca + "?");

                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            new Carro(Pesquisar.this).removerCarro(id);
                            Toast.makeText(Pesquisar.this, "Carro removido com sucesso!", Toast.LENGTH_LONG);
                            meuAdp.notifyDataSetChanged();
                        }
                        catch (Exception e) {
                            Toast.makeText(Pesquisar.this, "Não foi possível remover o carro", Toast.LENGTH_LONG);
                        }
                    }});
                adb.show();

                return false;
            }
        });

        btnVoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}