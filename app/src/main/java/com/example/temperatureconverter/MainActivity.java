package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView history;
    private TextView textView2,textView3;
    private EditText numText;
    private RadioButton radioButton,radioButton2;
    private SharedPreferences myPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        history = findViewById(R.id.resultText);
        numText = findViewById(R.id.fahrenheitText);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);



        myPrefs = getSharedPreferences("my pref", Context.MODE_PRIVATE);

        String myData = myPrefs.getString("my pref", "first");

        if (myData.equals("first")){
            radioButton.setChecked(true);

            textView2.setText("Fahrenheit Degrees:");
            textView3.setText("Celsius Degrees:");
        }

        else if(myData.equals("second")) {

            radioButton2.setChecked(true);

            textView2.setText("Celsius Degrees:");

            textView3.setText("Fahrenheit Degrees:");
        }



    }

        @Override
        protected void onSaveInstanceState(Bundle outState) {

        outState.putString("HISTORY", history.getText().toString());
        outState.putString("HistoryButton1", textView2.getText().toString());
        outState.putString("HistoryButton2", textView3.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);


    }

     @Override
     protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);


        history.setText(savedInstanceState.getString("HISTORY"));
        textView2.setText(savedInstanceState.getString("HistoryButton1"));
        textView3.setText(savedInstanceState.getString("HistoryButton2"));
    }


    public void groupClick(View v){


        numText.setText("");

        SharedPreferences.Editor editor = myPrefs.edit();
        switch (v.getId()){

            case R.id.radioButton:

                String selectionText = ((RadioButton) v).getText().toString();

                TextView fahrenheit1 = findViewById(R.id.textView2);
                TextView celsius1 = findViewById(R.id.textView3);
                fahrenheit1.setText("Fahrenheit Degrees:");
                celsius1.setText("Celsius Degrees:");

                Toast.makeText(this, "You selected" + selectionText, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "group1Click: Fahrenheit to Celsius " + R.id.radioButton);

                editor.putString("my pref", "first");
                editor.apply();
                break;

            case R.id.radioButton2:


                String selectionText2 = ((RadioButton) v).getText().toString();
                TextView fahrenheit2 = findViewById(R.id.textView3);
                TextView celsius2 = findViewById(R.id.textView2);
                fahrenheit2.setText("Fahrenheit Degrees:");
                celsius2.setText("Celsius Degrees:");

                Toast.makeText(this, "You selected" + selectionText2, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "group1Click: Celsius to Fahrenheit  " + R.id.radioButton2);


                editor.putString("my pref", "second");
                editor.apply();

                break;
            default:
                Log.d(TAG, "GroupClick: UNKNOWN VIEW SELECTED");

                break;
        }



    }

    public void convert(View v) {


        RadioButton fahrenheitBtn = findViewById(R.id.radioButton);
        RadioButton celsiusBtn = findViewById(R.id.radioButton2);

        EditText fahrenheit = findViewById(R.id.fahrenheitText);
        TextView celsius = findViewById(R.id.textcelsius);
        TextView output = findViewById(R.id.resultText);


        TextView choice = findViewById(R.id.textView2);
        TextView choice2 = findViewById(R.id.textView3);


        String c1 = choice.getText().toString();
  //      String c2 = choice2.getText().toString();
        String F = "Fahrenheit Degrees:";
        String C = "Celsius Degrees:";

        output.setMovementMethod(new ScrollingMovementMethod());


        String inputstring;
        inputstring = fahrenheit.getText().toString();
        double intputVal = Double.parseDouble(inputstring);


        if(c1.equals(F)){


            double FToC = ((intputVal - 32.0) / 1.8);


            String original = output.getText().toString();
            String res = String.format("F to C: %.1f => %.1f %n", intputVal, FToC);

            String showNum = String.format("%.1f", FToC);
            celsius.setText(showNum);

            Log.d(TAG, "covert F to C: " + FToC);
            output.setText(res + original);

        }
        else if (c1.equals(C)){

            double CToF =  ((intputVal * 1.8) + 32);

            String original = output.getText().toString();
            String res2 = String.format("C to F: %.1f => %.1f %n", intputVal, CToF);

            String showNum = String.format("%.1f", CToF);

            celsius.setText(showNum);

            Log.d(TAG, "covert C to F: " + CToF);
            output.setText(res2 + original);

        }

    }

    public void clear(View v){

     TextView resultText = findViewById(R.id.resultText);

     if(resultText != null){
         resultText.setText("");
     }

    }



}

















