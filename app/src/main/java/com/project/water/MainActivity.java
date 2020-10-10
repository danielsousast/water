package com.project.water;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TimePicker timePicker;
    private Integer hour;
    private Integer minute;
    private Integer interval;
    private boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);
    }

    public void notifyClick(View view) {
        String intervalString = editText.getText().toString();

        if(intervalString.isEmpty()) {
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(intervalString);

        if(!active){
            button.setText(R.string.pause);
            Object color = ContextCompat.getColor(this, R.color.pink);
            //button.setBackground(color);
            active = true;
        } else {
            button.setText(R.string.notify);
            int color = ContextCompat.getColor(this, R.color.colorAccent);
            //button.setBackground(color);
            active = false;
        }

        Log.d("test", "Hour"+hour);
        Log.d("test", "Minute"+minute);
        Log.d("test", "Interval"+interval);
    }
}
