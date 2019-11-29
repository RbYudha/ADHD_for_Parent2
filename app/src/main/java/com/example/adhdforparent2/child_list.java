package com.example.adhdforparent2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class child_list extends ArrayAdapter<child_data> {

    private Activity context ;
    private List<child_data> childList;


    public child_list(Activity context, List<child_data> childList){
        super(context,R.layout.child_list_layout, childList);
        this.context = context;
        this.childList = childList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.child_list_layout, null, true);

        TextView textViewChildName = listViewItem.findViewById(R.id.textView_childName);
        TextView textViewChildGender = listViewItem.findViewById(R.id.textView_childGender);
        TextView textViewChildAge = listViewItem.findViewById(R.id.textView_childAge);

        TextView textViewName = listViewItem.findViewById(R.id.textView_lookName);
        TextView textViewGender = listViewItem.findViewById(R.id.textView_lookGender);
        TextView textViewAge = listViewItem.findViewById(R.id.textView_lookAge);

        textViewChildName.findViewById(R.id.textView_childName);
        textViewChildGender.findViewById(R.id.textView_childGender);
        textViewChildAge.findViewById(R.id.textView_childAge);

        child_data childData = childList.get(position);

        textViewName.setText(childData.getChildName());
        textViewGender.setText(childData.getChildGender());
        textViewAge.setText(childData.getChildAge());

        return listViewItem;
    }

}
