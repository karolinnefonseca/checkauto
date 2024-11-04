package com.gilson.checkauto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Carro extends DBHelper
{
    private String marca;
    private String modelo;
    private int ano;

    public Carro(Context context)
    {
        super(context);
    }

    public Carro(Context context, String marca, String modelo, int ano) {
        super(context);
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long inserirCarro(ContentValues cv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert("carro", null, cv);
        return  id;
    }

    public boolean removerCarro(String idCarro)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("carro", "id" + "=?", new String[]{idCarro}) > 0;
    }

    public List<ContentValues> pesquisarCarro(String pMarca)
    {
        String sql = " SELECT id, marca, modelo, ano FROM carro WHERE marca LIKE ? ";
        String where[] = new String[]{"%"+pMarca+"%"};
        return pesquisarCarro(sql, where);
    }

    public List<ContentValues> pesquisarCarro(Integer pAno)
    {
        String sql = " SELECT id, marca, modelo, ano FROM carro WHERE ano = ? ";
        String where[] = new String[]{String.valueOf(pAno)};
        return pesquisarCarro(sql, where);
    }
    public List<ContentValues> pesquisarCarro()
    {
        String sql = " SELECT id, marca, modelo, ano FROM carro ";
        String where[] = null;
        return pesquisarCarro(sql, where);
    }

    private List<ContentValues> pesquisarCarro(String sql, String[] where)
    {
        List<ContentValues> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c =db.rawQuery(sql, where);

        if(c.moveToFirst())
        {
            do {
                ContentValues cv = new ContentValues();
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                cv.put("marca", c.getString(c.getColumnIndex("marca")));
                cv.put("modelo", c.getString(c.getColumnIndex("modelo")));
                cv.put("ano", c.getInt(c.getColumnIndex("ano")));
                lista.add(cv);
            } while (c.moveToNext());
        }
        return lista;
    }
}
