package com.project.water;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
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
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        preferences = getSharedPreferences("waterdb", Context.MODE_PRIVATE);
        active = preferences.getBoolean("active", false);

        if(active){
            button.setText(R.string.pause);

            int interval = preferences.getInt("interval",0);
            int hour = preferences.getInt("hour",timePicker.getCurrentHour());
            int minute = preferences.getInt("minute",timePicker.getCurrentMinute());

            editText.setText(String.valueOf(interval));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
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

        SharedPreferences.Editor editor = preferences.edit();

        if(!active){
            button.setText(R.string.pause);
            Object color = ContextCompat.getColor(this, R.color.pink);
            //button.setBackground(color);
            active = true;

            editor.putBoolean("active", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();
        } else {
            button.setText(R.string.notify);
            int color = ContextCompat.getColor(this, R.color.colorAccent);
            //button.setBackground(color);
            active = false;

            editor.putBoolean("active", false);
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minute");
            editor.apply();
        }

        Log.d("test", "Hour"+hour);
        Log.d("test", "Minute"+minute);
        Log.d("test", "Interval"+interval);
    }
}
