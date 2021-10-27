package com.allam.ets.contact;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.allam.ets.R;
import com.allam.ets.contact.data.ContactModel;

import java.util.List;

public class Adapter extends ArrayAdapter<ContactModel> {
    private static class ViewHolder {
        TextView tvNama;
        TextView tvNoPhone;
    }

    public Adapter(Context context, int resource, List<ContactModel> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        ContactModel kontak = getItem(position);
        ViewHolder viewKontak;
        if (ConvertView == null) {
            viewKontak = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
            viewKontak.tvNama = (TextView) ConvertView.findViewById(R.id.tvNama);
            viewKontak.tvNoPhone = (TextView) ConvertView.findViewById(R.id.tvNoPhone);
            ConvertView.setTag(viewKontak);
        }
        else {
            viewKontak = (ViewHolder) ConvertView.getTag();
        }

        viewKontak.tvNama.setText(kontak.getNama());
        viewKontak.tvNoPhone.setText(kontak.getNoHp());

        return ConvertView;
    }
}