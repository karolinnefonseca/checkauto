package com.gilson.checkauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Cadastrar extends AppCompatActivity
{
    private Button btnCadastro, btnVoltar;
    private EditText txtMarca, txtModelo, txtAno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtAno = findViewById(R.id.txtAno);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ContentValues cv = new ContentValues();
                cv.put("marca", txtMarca.getText().toString().trim());
                cv.put("modelo", txtModelo.getText().toString().trim());
                cv.put("ano", txtAno.getText().toString().trim());

                Carro car =  new Carro(Cadastrar.this);
                String msg = "";

                if(car.inserirCarro(cv) > 0)
                {
                    msg = "Carro inserido com sucesso!";
                    limparCampos();

                    //Temporariamente mostrar o carro inserido no "log"
                    List<ContentValues> cvList = car.pesquisarCarro();

                    for(int i = 0; i < cvList.size(); i ++)
                    {
                        ContentValues cvAux = cvList.get(i);
                        Log.i("ID Carro", cvAux.getAsString("id"));
                        Log.i("Marca do Carro", cvAux.getAsString("marca"));
                        Log.i("Modelo do Carro", cvAux.getAsString("modelo"));
                        Log.i("Ano do Carro", cvAux.getAsString("ano"));

                    }
                }
                else
                {
                    msg = "Ocorreu erro ao inserir o carro!";
                }

                Toast.makeText(Cadastrar.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
     });
    }
    private void limparCampos()
    {
        txtAno.setText("");
        txtModelo.setText("");
        txtMarca.setText("");
        txtMarca.requestFocus();
    }
}