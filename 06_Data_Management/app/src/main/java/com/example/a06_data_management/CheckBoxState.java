package com.example.a06_data_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class CheckBoxState extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_state);
        CheckBox checkBox = findViewById(R.id.checkBox);
        final boolean[] checked = {PreferenceManager.getDefaultSharedPreferences(this).getBoolean("checkBox", false)};
        checkBox.setChecked(checked[0]);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked[0] = checkBox.isChecked();
                PreferenceManager.getDefaultSharedPreferences(CheckBoxState.this).edit().putBoolean("checkBox", checked[0]).commit();
            }
        });
        View saveLayout = findViewById(R.id.SaveLayout);
        EditText editText = findViewById(R.id.SaveNote);
        final String[] text = {PreferenceManager.getDefaultSharedPreferences(this).getString("editText", "")};
        editText.setText(text[0]);
        saveLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                text[0] = String.valueOf(editText.getText());
                PreferenceManager.getDefaultSharedPreferences(CheckBoxState.this).edit().putString("editText", text[0]).commit();
            }
        });

        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckBoxState.this, Login.class);
                startActivity(intent);
            }
        });
        Button bPrevious = findViewById(R.id.PreviousPage);
        bPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckBoxState.this, NoteList.class);
                startActivity(intent);
            }
        });
    }
}
