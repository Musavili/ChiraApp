package com.musavili.application.chiraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView checkText;
    TextView radioText;
    TextView toggleText;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkText = findViewById(R.id.checkBoxText);
        radioText =  findViewById(R.id.radioButtonText);
        toggleText =  findViewById(R.id.toggleButtonText);
        date = findViewById(R.id.date);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                openWeb();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openWeb() {
        Intent webIntent = new Intent(this, WebActivity.class);
        startActivity(webIntent);
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            checkText.setText("checked!");
        }else{
            checkText.setText("unchecked!");
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    radioText.setText("selected yes");
                    break;
            case R.id.radioButton2:
                if (checked)
                    radioText.setText("selected no");
                    break;
        }
    }
    public void onToggleButtonClicked(View view) {
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isToggled) {
                if (isToggled) {
                    toggleText.setText("ToggleOn");
                } else {
                    toggleText.setText("ToggleOff");
                }
            }
        });
    }

    public void openMap(View view) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);

        date.setText(dateMessage);

        Toast.makeText(this, "Scheduled for: " + dateMessage,
                Toast.LENGTH_LONG).show();
    }

    public void showDatePicker(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        //getSupportFragmentManager creates an instance of fragment manager to manage the frag
        dateFragment.show(getSupportFragmentManager(),"datePicker");
    }
}