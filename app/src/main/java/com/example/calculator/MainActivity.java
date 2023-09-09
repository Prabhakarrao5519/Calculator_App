package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv,solutionTv;
    MaterialButton button_c,button_close,button_open;
    MaterialButton button_divide,button_multiply,button_add,button_sub,button_equal;
    MaterialButton button_9,button_8,button_7,button_6,button_5,button_4,button_3,button_2,button_1,button_0;
    MaterialButton button_ac,button_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(button_c,R.id.btn_c);
        assignId(button_close,R.id.btn_close);
        assignId(button_open,R.id.btn_open);
        assignId(button_9,R.id.btn_9);
        assignId(button_8,R.id.btn_8);
        assignId(button_7,R.id.btn_7);
        assignId(button_6,R.id.btn_6);
        assignId(button_5,R.id.btn_5);
        assignId(button_4,R.id.btn_4);
        assignId(button_3,R.id.btn_3);
        assignId(button_2,R.id.btn_2);
        assignId(button_1,R.id.btn_1);
        assignId(button_0,R.id.btn_0);
        assignId(button_ac,R.id.btn_Ac);
        assignId(button_add,R.id.btn_add);
        assignId(button_multiply,R.id.btn_multiply);
        assignId(button_sub,R.id.btn_sub);
        assignId(button_divide,R.id.btn_divide);
        assignId(button_equal,R.id.btn_equal);
        assignId(button_dot,R.id.btn_dot);
    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String calculate = solutionTv.getText().toString();

        if (buttonText.equals("Ac")) {
            solutionTv.setText(" ");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            calculate = calculate.substring(0, calculate.length() - 1);
        } else {
            calculate = calculate + buttonText;
        }

        solutionTv.setText(calculate);


        String finalResult=getResult(calculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            if (finalResult.endsWith(".0")){
               finalResult= finalResult.replace(".0"," ");

            }
            return finalResult;  

        }catch (Exception e){
            return "Err";
        }

    }
}