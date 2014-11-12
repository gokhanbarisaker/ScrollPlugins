package com.gokhanbarisaker.scrollpluginsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gokhanbarisaker.scrollplugins.models.Parallaxable;
import com.gokhanbarisaker.scrollpluginsapp.R;

/**
 * Created by gokhanbarisaker on 11/12/14.
 */
public class AbsListViewDemoAdapter extends BaseAdapter
{
    @Override
    public int getCount() {
        return 999;
    }

    @Override
    public Object getItem(int position) {
        return generateItemText(position, -1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;

        if (rootView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rootView = inflater.inflate(R.layout.item_abslistdemo, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.textView = (TextView) rootView.findViewById(R.id.textview);

            rootView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rootView.getTag();
        holder.position = position;
        holder.textView.setText((String) getItem(position));

        return rootView;
    }

    private String generateItemText(int position, float parallaxPercentage)
    {
        return position + ", % " + parallaxPercentage;
    }

    private class ViewHolder implements Parallaxable
    {
        private int position = -1;
        private TextView textView = null;

        @Override
        public void onParallax(float verticalPercentage, float horizontalPercentage) {
            String text = generateItemText(position, verticalPercentage);
            textView.setText(text);
        }
    }
}
