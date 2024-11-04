package com.gilson.checkauto.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.gilson.checkauto.R;

import java.util.List;


public class Adaptador extends BaseAdapter
{
    Context context;
    List<ContentValues> cvList;

    public Adaptador(Context context, List<ContentValues> cvList)
    {
        this.context = context;
        this.cvList = cvList;
    }

    @Override
    public int getCount() {
        return this.cvList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cvList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listviewcustomizada, null);

        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtMarca = convertView.findViewById(R.id.txtMarca);
        TextView txtModelo = convertView.findViewById(R.id.txtModelo);
        TextView txtAno = convertView.findViewById(R.id.txtAno);

        //pegando o content value atual para preencher os valores
        ContentValues cv =  cvList.get(position);
        txtId.setText(String.valueOf(cv.getAsInteger("id")));
        txtMarca.setText(cv.getAsString("marca"));
        txtModelo.setText(cv.getAsString("modelo"));
        txtAno.setText(String.valueOf(cv.getAsInteger("ano")));

        return convertView;
    }
}
