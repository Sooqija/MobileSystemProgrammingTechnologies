package com.a07_user_interface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ButtonsBegin extends AppCompatActivity implements ColorPickerDialogListener {
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_begin);

        ListView listView = findViewById(R.id.listView);
        LinearLayout ll = findViewById(R.id.ButtonsStack);
        ArrayList Actions = new ArrayList<String>();
        Actions.add("Attack!");
        Actions.add("Sleep");
        Actions.add("Get");
        ListAdapter adapter = new ListAdapter(this, R.layout.list_items, Actions);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myButton = new Button(ButtonsBegin.this);
                myButton.setText((CharSequence) Actions.get(position));
                myButton.setBackgroundResource(R.drawable.selector);
                createColorPickerDialog(view.getId());
                ll.addView(myButton);
            }
        });
        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsBegin.this, TextMotion.class);
                startActivity(intent);
            }
        });
        Button bNextPage = findViewById(R.id.NextPage);
        bNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsBegin.this, Stopwatch.class);
                startActivity(intent);
            }
        });


    }

    private void createColorPickerDialog(int id) {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.CIRCLE)
                .setDialogId(id)
                .show(this);
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        myButton.setBackgroundColor(color);
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show();
    }

    public class ListAdapter extends ArrayAdapter {

        private int resourceLayout;
        private Context mContext;
        private ArrayList<String> source;

        public ListAdapter(Context context, int resource, ArrayList<String> Notes) {
            super(context, resource, Notes);
            this.resourceLayout = resource;
            this.mContext = context;
            this.source = Notes;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(resourceLayout, null);
            }
            TextView item = v.findViewById(R.id.Item);
            item.setText(source.get(position));

            return v;
        }
    }
}
