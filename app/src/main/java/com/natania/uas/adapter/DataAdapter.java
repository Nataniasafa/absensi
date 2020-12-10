package com.natania.uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.natania.uas.R;
import com.natania.uas.activity.DataActivity;
import com.natania.uas.model.PersonItem;

import java.util.List;

public class DataAdapter extends ArrayAdapter<PersonItem> {
    private Context context;
    private List<PersonItem> personItem;

    public DataAdapter(@NonNull Context context,
                          int resource,
                          @NonNull List<PersonItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.personItem = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, parent, false);

        TextView txtIdData = v.findViewById(R.id.txt_data_id);
        TextView txtNameData = v.findViewById(R.id.txt_data_name);
        TextView txtDateData = v.findViewById(R.id.txt_data_date);
        TextView txtClassData = v.findViewById(R.id.txt_data_class);
        TextView txtLessonData = v.findViewById(R.id.txt_data_lesson);
        TextView txtDescData = v.findViewById(R.id.txt_data_desc);

        txtIdData.setText(String.valueOf( personItem.get(position).getId()));
        txtNameData.setText(String.valueOf(personItem.get(position).getName()));
        txtDateData.setText(String.valueOf(personItem.get(position).getDate()));
        txtClassData.setText(String.valueOf(personItem.get(position).getClass()));
        txtLessonData.setText(String.valueOf(personItem.get(position).getLesson()));
        txtDescData.setText(String.valueOf(personItem.get(position).getDescription()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DataActivity.class);
                intent.putExtra("id", String.valueOf(personItem.get(position).getId()));
                intent.putExtra("name", personItem.get(position).getName());
                intent.putExtra("date", personItem.get(position).getDate());
                intent.putExtra("class", personItem.get(position).getClass());
                intent.putExtra("lesson", personItem.get(position).getLesson());
                intent.putExtra("desc", personItem.get(position).getDescription());
                context.startActivity(intent);
            }
        });

        return v;
    }

}
