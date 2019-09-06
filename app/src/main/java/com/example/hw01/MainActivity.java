package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {


    private Double bmiCount = 0.0;

    private TextView txt_bmiResult;
    private TextView txt_bmiStatus;

    private EditText et_Weight;
    private EditText et_Feet;
    private EditText et_Inches;

    private Button btn_calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String RESULT_PREFIX = "Your BMI is:";
        final String ERROR_MESSAGE = "Please input a valid number";
        final String ERROR_TOAST = "Invalid Input!";

        txt_bmiResult = findViewById(R.id.bmiResult_txt);
        txt_bmiStatus = findViewById(R.id.resultMessage_txt);

        txt_bmiStatus.setText(RESULT_PREFIX);

        et_Weight = findViewById(R.id.weight_Input);
        et_Feet = findViewById(R.id.feet_input);
        et_Inches = findViewById(R.id.inches_input);

        btn_calculate = findViewById(R.id.calculate_Btn);




        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String weightValue = et_Weight.getText().toString();
                    String feetValue = et_Feet.getText().toString();
                    String inchesValue = et_Inches.getText().toString();

                    Double weight = 0.0;
                    Double feet = 0.0;
                    Double inches = 0.0;

                    boolean isErrorThrown = false;

                    if (!weightValue.equals("")  && Double.parseDouble(weightValue ) > 0 ){

                         weight = Double.parseDouble(weightValue);

                    }else{
                        et_Weight.setError(ERROR_MESSAGE);

                        Toast.makeText(MainActivity.this, ERROR_TOAST,
                                Toast.LENGTH_SHORT).show();
                        isErrorThrown = true;
                    }

                    if(!feetValue.equals("") && Double.parseDouble(feetValue) > 0 ){

                        feet = Double.parseDouble(feetValue);

                    }else {
                        et_Feet.setError(ERROR_MESSAGE);

                        Toast.makeText(MainActivity.this, ERROR_TOAST,
                                Toast.LENGTH_SHORT).show();
                        isErrorThrown = true;
                    }


                    if(!inchesValue.equals("") && Double.parseDouble(inchesValue) > 0  && Double.parseDouble(inchesValue) <= 12){

                        inches = Double.parseDouble(inchesValue);
                    }else{
                            et_Inches.setError(ERROR_MESSAGE);

                            Toast.makeText(MainActivity.this, ERROR_TOAST,
                                    Toast.LENGTH_SHORT).show();
                            isErrorThrown = true;
                        }

                if (!isErrorThrown){
                    //convert the feet to inches
                    Double totalInches = (feet * 12) + inches;

                    //calculate the BMI.
                    Double calculatedResult = weight / (totalInches * totalInches) * 703;
                    bmiCount = calculatedResult;

                    DecimalFormat df = new DecimalFormat("##.##");
                    txt_bmiResult.setText(RESULT_PREFIX+" " + df.format(bmiCount));

                    if (calculatedResult >= 30){
                        txt_bmiStatus.setText("You are obese!");
                    }else if(calculatedResult>= 25 && calculatedResult <= 29.9 ){
                        txt_bmiStatus.setText("You are overweight!");
                    }else if(calculatedResult >= 18.5 && calculatedResult <= 24.9){
                        txt_bmiStatus.setText("Your weight is normal!");
                    }else if(calculatedResult <= 18.5){
                        txt_bmiStatus.setText("You are underweight!");
                    }

                    Toast.makeText(MainActivity.this, "BMI Calculated!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
