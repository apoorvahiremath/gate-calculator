package com.gatehelpline.apoorva.myapplication.myapplication;

/**
 * Created by Apoorva on 8/29/2016.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Apoorva on 7/27/2016.
 */
public class TextEditListener implements TextWatcher {
    private double input1, input2, output;
    private EditText et1, et2;
    private int operation;
    private BinaryOperations bo;
    private Calc c;
    public TextEditListener(Calc c, EditText e1, EditText e2, double a, int op){
        et1 = e1;
        et2 = e2;
        input1 = a;
        operation = op;
        this.c = c;
        input2 = 0;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        String prevText = et2.getText().toString();
        //if(prevText.length() < 0)
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String nums = "0123456789";
        String str = s.toString();
        String prevText = et2.getText().toString();
        if(nums.contains(str)){
            if(prevText.length() > 0) {
                input2 = (Double.parseDouble(prevText) * 10) + Double.parseDouble(str);
            }
            else {
                input2 = Double.parseDouble(str);
            }
            et2.setText(" "+input2);
        }else{
            input2 = Double.parseDouble(et2.getText().toString());
            bo = new BinaryOperations(input1, input2);
            output = bo.chooseOperation(operation);
            String ss = ""+input2;
            et1.append(ss);
            c.setValSize(ss.length());
            et2.removeTextChangedListener(this);
            et2.setText(" "+output);
            c.setResVal(output);
        }

        //output = Math.pow(input1, input2);

        Toast.makeText(c.getApplicationContext(),s.toString(),Toast.LENGTH_SHORT).show();
        //et2.setText(" "+output);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public double getInput2(){
        return input2;
    }
}
