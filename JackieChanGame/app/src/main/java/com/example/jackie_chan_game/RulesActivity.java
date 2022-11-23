package com.example.jackie_chan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class RulesActivity extends AppCompatActivity {

    ArrayList<String> TalismansNames = new ArrayList<>(Arrays.asList("Rooster Talisman: дарует левитацию и теликинез.", "Ox Talisman: дарует необычайную мощь.", "Snake Talisman: дарует способность быть невидимым.",
            "Sheep Talisman: дарует способность находиться в астральной проекции, которая способна проникать в сон других людей.", "Dragon Talisman: дарует способность пускать файрболы из ладони.", "Rabbit Talisman: дарует способность неимоверно быстро бегать",
            "Rat Talisman: дарует жизнь неодушевленному предмету", "Horse Talisman: дарует способность лечения любых ран и болезней", "Monkey Talisman: дарует способность превращать что угодно в любое животное",
            "Dog Talisman: дарует вечную молодрсть и бессмертие", "Pig Talisman: дарует воспломеняющий взгяд", "Tiger Talisman: дарует возможность разделиться на инь и ян"));
    ArrayList<Integer> TalismansImages = new ArrayList<>(Arrays.asList(R.drawable.rooster_talisman, R.drawable.ox_talisman, R.drawable.snake_talisman,
            R.drawable.sheep_talisman, R.drawable.dragon_talisman, R.drawable.rabbit_talisman,
            R.drawable.rat_talisman, R.drawable.horse_talisman, R.drawable.monkey_talisman,
            R.drawable.dog_talisman, R.drawable.pig_talisman, R.drawable.tiger_talisman));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        ListView listView = findViewById(R.id.ListTalismans);
        ListTalismanAdapter adapter = new ListTalismanAdapter(RulesActivity.this, R.layout.list_talisman_rules, TalismansNames, TalismansImages);
        listView.setAdapter(adapter);


        Button backward_btn = findViewById(R.id.backward_btn);
        backward_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_next = new Intent(RulesActivity.this, LoadGameActivity.class);
                startActivity(intent_next);
            }
        });
    }

    public class ListTalismanAdapter extends ArrayAdapter {

        private final int resourceLayout;
        private Context context;
        private ArrayList<String> TalismansNames;
        private ArrayList<Integer> TalismansImages;

        public ListTalismanAdapter(Context context, int resource, ArrayList<String> _Names, ArrayList<Integer> _TalismansImages) {
            super(context, resource, _Names);
            this.resourceLayout = resource;
            this.context = context;
            this.TalismansNames = _Names;
            TalismansImages = _TalismansImages;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resourceLayout, parent, false);
            TextView TalismanName = rowView.findViewById(R.id.TalismanName);
            ImageView TalismanImage = rowView.findViewById(R.id.TalismanImage);
            TalismanName.setText(String.valueOf(TalismansNames.get(position)));
            TalismanImage.setImageResource(TalismansImages.get(position));

            return rowView;
        }
    } // ListTalismanAdapter
}