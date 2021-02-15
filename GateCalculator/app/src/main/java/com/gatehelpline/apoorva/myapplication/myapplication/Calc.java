package com.gatehelpline.apoorva.myapplication.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Calc extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private RadioButton degButton;
    private boolean equalsto = false;
    private final String clearScreenZero = " 0";
    private final String clearScreenSpace = " ";
    private boolean otherOpCheck = false;
    private int valSize = 0;
    private int valSize1 = 0;
    private double resVal = 0.0;
    private TextEditListener tel = null;
    private String operators = "+-*/%dy^t(";
    private int perCode = 5;
    private int modCode = 6;
    private int ylogCode = 7;
    private int ypowCode = 8;
    private int yrootCode = 9;
    private BinaryOperations binaryOperations;
    private double memoryVal = 0.0;
    private boolean memSet = false;
    private boolean dualOp = false;
    private TextView expression;
    private final String TAG = "Calc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calc);

        editText1 = (EditText) findViewById(R.id.editText_1);
        editText2 = (EditText) findViewById(R.id.editText_2);
        expression = (TextView) findViewById(R.id.hiddenText);
        degButton = (RadioButton)findViewById(R.id.degButton);

        String sqrtSymbol = "&#8730;";
        String cubertSymbol = "&#179&#8730;";
        String ythrtSymbol = "<sup>y</sup>&#8730;x";
        String pieSymbol = "&#8719;";
        String sqrSymbol = "x&sup2;";
        String cubeSymbol = "x&#179;";
        String ythpowSymbol = "x<sup>y</sup>";
        String sinhinverseSymbol = "sinh<sup>-1</sup>";
        String coshinverseSymbol = "cosh<sup>-1</sup>";
        String tanhinverseSymbol = "tanh<sup>-1</sup>";
        String sininverseSymbol = "sin<sup>-1</sup>";
        String cosinverseSymbol = "cos<sup>-1</sup>";
        String taninverseSymbol = "tan<sup>-1</sup>";
        String logx2Symbol = "log<sub>2</sub>x";
        String logxySymbol = "log<sub>y</sub>x";
        String epowxSymbol = "e<sup>x</sup>";
        String tenpowxSymbol = "10<sup>x</sup>";
        String backspcSymbol = "&larr;";

        Button button = (Button)findViewById(R.id.sqrt);
        button.setText(Html.fromHtml(sqrtSymbol));
        button = (Button)findViewById(R.id.pie);
        button.setText(Html.fromHtml(pieSymbol));
        button = (Button)findViewById(R.id.ythrt);
        button.setText(Html.fromHtml(ythrtSymbol));
        button = (Button)findViewById(R.id.cubert);
        button.setText(Html.fromHtml(cubertSymbol));
        button = (Button)findViewById(R.id.sqr);
        button.setText(Html.fromHtml(sqrSymbol));
        button = (Button)findViewById(R.id.cube);
        button.setText(Html.fromHtml(cubeSymbol));
        button = (Button)findViewById(R.id.ythpow);
        button.setText(Html.fromHtml(ythpowSymbol));
        button = (Button)findViewById(R.id.sinhi);
        button.setText(Html.fromHtml(sinhinverseSymbol));
        button = (Button)findViewById(R.id.coshi);
        button.setText(Html.fromHtml(coshinverseSymbol));
        button = (Button)findViewById(R.id.tanhi);
        button.setText(Html.fromHtml(tanhinverseSymbol));
        button = (Button)findViewById(R.id.sini);
        button.setText(Html.fromHtml(sininverseSymbol));
        button = (Button)findViewById(R.id.cosi);
        button.setText(Html.fromHtml(cosinverseSymbol));
        button = (Button)findViewById(R.id.tani);
        button.setText(Html.fromHtml(taninverseSymbol));
        button = (Button)findViewById(R.id.logx2);
        button.setText(Html.fromHtml(logx2Symbol));
        button = (Button)findViewById(R.id.logxy);
        button.setText(Html.fromHtml(logxySymbol));
        button = (Button)findViewById(R.id.epowx);
        button.setText(Html.fromHtml(epowxSymbol));
        button = (Button)findViewById(R.id.tenpowx);
        button.setText(Html.fromHtml(tenpowxSymbol));
        button = (Button)findViewById(R.id.backspace);
        button.setText(Html.fromHtml(backspcSymbol));
    }

    //**************************Clear EditText view methods start here******************************

    public void clearScreen(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        dualOp = false;
        otherOpCheck = false;
        equalsto = false;
        valSize = 0;
        resVal = 0.0;
        expression.setText(clearScreenSpace);
        editText1.setText(clearScreenSpace);
        editText2.setText(clearScreenZero);
    }

    private void clearEditText1(){
        editText1.setText("");
    }

    private void clearEditText2(){
        editText2.setText("");
    }
    //**********************************************************************************************

    //*******************Input number and other charactors methods start here***********************

    public void putNumber(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        if(dualOp == true) {
            clearEditText2();
            //dualOp = false;
        }
        Button numberButton = (Button)v;
        String valueToSet = numberButton.getText().toString();
        String currentText = editText2.getText().toString();
        String editText1val = editText1.getText().toString();
        String newText = null;
        equalsto = false;
        otherOpCheck = false;
        //this checks if the any binary operator is present at end editText1
        int size = editText1val.length();
        if(size > 0){
            String end = editText1val.substring(size - 1, size);
            if(!operators.contains(end)) {
                //Toast.makeText(this,end,Toast.LENGTH_LONG).show();
                clearEditText1();
                clearEditText2();
            }
        }

        //the number should  be at max 9 digit long
        // if(currentText.length() < 9) {
        if (currentText.equals(clearScreenZero)||currentText.contains(clearScreenSpace) || ((currentText.length() > 9)&& (currentText.contains(clearScreenSpace)))) {
            clearEditText2();

            newText = valueToSet;
        } else if ((currentText.length() >= 9) && !(currentText.contains(clearScreenSpace))) {
            newText = currentText;
        } else {
            newText = currentText + valueToSet;
        }
        editText2.setText(newText);
        if(dualOp == true){
           // expression.append(newText);
            dualOp = false;
        }
        // }
    }

    public void putDecPoint(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        String decimalPoint = ".";
        if(currentText.equals(clearScreenZero))
            editText2.setText("0"+decimalPoint);
        else if (currentText.contains(clearScreenSpace))
            editText2.setText("0"+decimalPoint);
        else {
            if (currentText.length() < 9 && !currentText.contains(decimalPoint)) {
                editText2.setText(currentText + decimalPoint);
            }
        }
    }

    public void putOpenBracket(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText1.getText().toString();
        String newText = null;
        if(currentText.length()>1)
            newText = currentText + "(";
        else
            newText = "(";
        expression.append("(");
        editText1.setText(newText);
        editText2.setText(clearScreenZero);
    }

    public void putCloseBracket(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText1.getText().toString();
        String newText = null;
        String num = editText2.getText().toString().trim();
        if(shouldPutCloseBracket(currentText)) {
            newText = currentText + num + ")";
            expression.append(num+")");
            editText1.setText(newText);
            editText2.setText(clearScreenSpace+editText2.getText());
        }
    }

    private boolean shouldPutCloseBracket(String s){
        int cCount = 0, oCount = 0;
        char [] a = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            if(a[i] == '(')
                oCount++;
            else if(a[i] == ')')
                cCount++;

        }
        if(oCount > cCount)
            return true;
        else
            return false;
    }

    public void putBackspace(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        String newText = null;
        int size = currentText.length();
        if(size > 1){
            newText = currentText.substring(0, size-1);
        }
        else{
            newText = clearScreenZero;
        }
        editText2.setText(newText);
    }

    //**********************************************************************************************

    //******************************Setting constants start here************************************

    public void putPie(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        clearEditText2();
        String pie = Math.PI + "";
        editText2.setText(pie);
    }

    public void putE(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        clearEditText2();
        String e = Math.E + "";
        editText2.setText(e);
    }
    //**********************************************************************************************

    //*********************************Unary operations start here**********************************

    private boolean endChar(String s){
        int size = s.length();

        if(size > 0) {
            char end = s.charAt(size - 1);
            if (operators.contains(end + ""))
                return true;
            else
                return false;
        }
        return false;
    }
    private boolean endBracket(String s){
        int size = s.length();

        if(size > 0) {
            String end = s.charAt(size - 1) + "";
            if (end.equals(")"))
                return true;
            else
                return false;
        }
        return false;
    }
    private String fmt(double d) {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    private String checkOutput(String o){
        if(o.equals("NaN"))
            return "Math Error";
        else
            return o;
    }
    private void trigOps(View v, String op, int opcode, boolean deg){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        double input = Double.parseDouble(currentText);
        if(input != Double.POSITIVE_INFINITY) {
            double output = TrigonometricOperations.chooseOperation(input, deg, opcode);
            String out = checkOutput(fmt(output));
            String et1 = editText1.getText().toString();
            String a = null;
            if(otherOpCheck){
                String prevVal = et1.substring(et1.length() - valSize, et1.length());
                editText1.setText(et1.substring(0, et1.length() - valSize ));
                a = op + "(" + prevVal + ")";
            }
            else{
                a = op + "(" + currentText.trim() + ")";
            }
            valSize = a.length();
            resVal = output;
            if (endChar(et1)||otherOpCheck)
                editText1.append(a);
            else
                editText1.setText(a);
            editText2.setText(clearScreenSpace + out);
            otherOpCheck = true;
        }
    }

    private void uniOps(View v, String op, int opCode){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        double input = Double.parseDouble(currentText);
        if(input != Double.POSITIVE_INFINITY) {
            binaryOperations = new BinaryOperations(input);
            double output = binaryOperations.chooseOperation(opCode);
            String out = checkOutput(fmt(output));
            String et1 = editText1.getText().toString();
            String a = null;
            if(otherOpCheck){
                String prevVal = et1.substring(et1.length() - valSize, et1.length());
                editText1.setText(et1.substring(0, et1.length() - valSize ));
                a = op + "(" + prevVal + ")";
            }
            else{
                a = op + "(" + currentText.trim() + ")";
            }
            valSize = a.length();
            resVal = output;
            if (endChar(et1)||otherOpCheck)
                editText1.append(a);
            else
                editText1.setText(a);
            editText2.setText(clearScreenSpace + out);
            otherOpCheck = true;
        }
    }

    public void putSin(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "sind";
        }else{
            toSet = "sinr";
        }
        trigOps(v, toSet, 1, deg);
    }
    public void putCos(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "cosd";
        }else{
            toSet = "cosr";
        }
        trigOps(v, toSet, 2, deg);
    }
    public void putTan(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "tand";
        }else{
            toSet = "tanr";
        }
        trigOps(v, toSet, 3, deg);
    }

    public void putSinh(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "sinhd";
        }else{
            toSet = "sinhr";
        }
        trigOps(v, toSet, 4, deg);
    }
    public void putCosh(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "coshd";
        }else{
            toSet = "coshr";
        }
        trigOps(v, toSet, 5, deg);
    }
    public void putTanh(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "tanhd";
        }else{
            toSet = "tanhr";
        }
        trigOps(v, toSet, 6, deg);
    }

    public void putSinI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "asind";
        }else{
            toSet = "asinr";
        }
        trigOps(v, toSet, 7, deg);
    }
    public void putCosI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "acosd";
        }else{
            toSet = "acosr";
        }
        trigOps(v, toSet, 8, deg);
    }
    public void putTanI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "atand";
        }else{
            toSet = "atanr";
        }
        trigOps(v, toSet, 9, deg);
    }

    public void putSinhI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "sinh-1d";
        }else{
            toSet = "sinh-1r";
        }
        trigOps(v, toSet, 10, deg);
    }
    public void putCoshI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "acosh-1d";
        }else{
            toSet = "acosh-1r";
        }
        trigOps(v, toSet, 11, deg);
    }
    public void putTanhI(View v){
        boolean deg = degButton.isChecked();
        String toSet = null;
        if(deg == true) {
            toSet = "atanh-1d";
        }else{
            toSet = "atanh-1r";
        }
        trigOps(v, toSet, 12, deg);

    }

    public void putFact(View v){
        uniOps(v, "fact", 10);
      }

    public void putSQRT(View v){
        uniOps(v, "sqrt", 11);
    }
    public void putCubeRT(View v){
        uniOps(v, "cuberoot", 12);
    }

    public void putReciprocal(View v){
        uniOps(v, "recipro", 13);
    }

    public void putLn(View v){
        uniOps(v, "ln", 14);
    }
    public void putLog(View v){
        uniOps(v, "log", 15);
    }
    public void putLogX2(View v){
        uniOps(v, "logXbase2", 16);
    }

    public void putAbs(View v){
        uniOps(v, "abs", 17);
    }

    public void putSqr(View v){
        uniOps(v, "sqr", 18);
    }
    public void putCube(View v){
        uniOps(v, "cube", 19);
    }
    public void putTenPowX(View v){
        uniOps(v, "powten", 20);
    }
    public void putEPowX(View v){
        uniOps(v, "powe", 21);
    }

    public void putExp(View v){
        //Yet to set validations
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        double input = Double.parseDouble(currentText);
        double output;
        output = Math.getExponent(input);

        if(endChar(editText1.getText().toString()))
            editText1.append(input + "E+0");
        else
            editText1.setText(input + "E+0");

        editText2.setText(clearScreenSpace + input);
    }//yet to implement correct method

    public void putSign(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String currentText = editText2.getText().toString();
        double input = Double.parseDouble(currentText);
        String newText = null;
        if(currentText.equals("0.0")||currentText.equals("0")){
            newText = "0";
        }else {
            input = (-1.0) * input;
            newText = "" + input;
        }
        editText2.setText(newText);
    }

    //**********************************************************************************************

    //**************************** Binary oprations start from here*********************************

    public void putBinaryOperator(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        Button operatorButton = (Button)v;
        String operation = operatorButton.getText().toString();
        String currentText = editText1.getText().toString();
        String inputText = editText2.getText().toString();
        String expressionText = expression.getText().toString();
        String newExpression = null;
        String newText = null;
        equalsto = false;
        if(currentText.contains(clearScreenSpace)){
            //Toast.makeText(this,currentText.indexOf(clearScreenSpace)+"",Toast.LENGTH_LONG).show();
            if(!inputText.contains("Infinity")&&!inputText.contains("Math Error")&&!inputText.contains("NaN")) {
                newText = inputText + operation;
                newExpression = newText;
            }
        }else if(!inputText.contains("Infinity")&&!inputText.contains("Math Error")&&!inputText.contains("NaN")){
            Log.i("Inputtext", inputText);
            int size = currentText.length();
            char endChar;
            if(size > 0 ) {
                endChar = currentText.charAt(size - 1);
            }else{
                endChar = 'x';
            }
            if(dualOp == true) {
               // Toast.makeText(this,1+"",Toast.LENGTH_LONG).show();
//                valSize += valSize1;
//                newExpression = expressionText + resVal + operation;
//                newText = currentText + operation;
                newText = currentText.substring(0, currentText.length() - valSize) + inputText + operation;
                newExpression = expressionText + inputText + operation;
                dualOp = false;
                valSize = 0;
                valSize1 = 0;
                resVal = 0.0;
            }else if(otherOpCheck == true){
               // Toast.makeText(this,2+"",Toast.LENGTH_LONG).show();
                newExpression = expressionText + resVal + operation;
                newText = currentText + operation;
                otherOpCheck = false;
                valSize = 0;
                resVal = 0.0;
            }else if((inputText.contains(clearScreenZero)) && (inputText.length() <= 2)){
               // Toast.makeText(this,3+"",Toast.LENGTH_LONG).show();
                newText = "0" + operation;
                newExpression = newText;
            }else if(inputText.contains("E")){
                // Toast.makeText(this,3+"",Toast.LENGTH_LONG).show();
                newText = currentText + inputText + operation;
                newExpression = expressionText + Double.parseDouble(inputText)+ operation;
            }
//            else if(inputText.equals("Infinity")){
//                newText = currentText + inputText + operation;
//                newExpression = expressionText + Double.POSITIVE_INFINITY+ operation;
//            }
//            else if(inputText.equals("-Infinity")){
//                newText = currentText + inputText + operation;
//                newExpression = expressionText + "Negative Infinity" + operation;
//            }
            else if(inputText.equals("Math Error")||inputText.equals("NaN")){
                newText = currentText + inputText + operation;
                newExpression = expressionText + Double.NaN+ operation;
            }
            else if(!inputText.contains(clearScreenSpace) && !(endChar == ')')) {
               // Toast.makeText(this,4+"",Toast.LENGTH_LONG).show();
                newText = currentText + inputText + operation;
                newExpression = expressionText + inputText + operation;
            }else if(endChar == ')'){
              //  Toast.makeText(this,5+"",Toast.LENGTH_LONG).show();
                newText = currentText + operation;
                newExpression = expressionText +operation;
            }else if("+-*/".contains(endChar+"")){
                if(!operation.equals(endChar+"") && inputText.contains(clearScreenSpace)){
                    newText = currentText.substring(0, size-1) + operation;
                    newExpression = expressionText.substring(0, expressionText.length() - 1) + operation;
               //     Toast.makeText(this,6+"",Toast.LENGTH_LONG).show();
                }else {
                //    Toast.makeText(this,7+"",Toast.LENGTH_LONG).show();
                    newText = currentText;
                    newExpression = expressionText;
                }
            }else{
               // Toast.makeText(this,8+"",Toast.LENGTH_LONG).show();
                newText = currentText;
                newExpression = expressionText;
            }

        }
        expression.setText(newExpression);
        //expression.setText(evalString());
       // Toast.makeText(this,newExpression,Toast.LENGTH_LONG).show();
        editText1.setText(newText);
        editText2.setText(clearScreenSpace+inputText);

    }

    public void setValSize(int size){
        valSize1 = size;
    }
    public void setResVal(double result){
        resVal = result;
    }

    private void dualOps(View v, String op, int opcode){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        dualOp = true;
        String ch = editText1.getText().toString();
        if(endBracket(ch)){
            editText1.append(op);
        }else {
            String currentText = editText2.getText().toString();
            double input1 = Double.parseDouble(currentText);
            String a = currentText + op;
            valSize = a.length();
            expression.append(a);
            if (endChar(ch))
                editText1.append(a);
            else
                editText1.setText(a);
        }
    }

    public void putLogXY(View v){
        dualOps(v, "logxBasey", ylogCode);
    }

    public void putXPowY(View v){
        dualOps(v, "^", ypowCode);
    }

    public void putYthRT(View v){
        dualOps(v, "yroot", yrootCode);
    }

    public void putPercent(View v){
        dualOps(v, "%", perCode);
    }

    public void putMod(View v){
        dualOps(v, "mod", modCode);
    }

    public void equalTo(View v) {
        //  Toast.makeText(this,equalsto+"",Toast.LENGTH_LONG).show();
        v.playSoundEffect(SoundEffectConstants.CLICK);
        String exp = expression.getText().toString().trim();
        String et1 = editText1.getText().toString().trim();
        String et2 = editText2.getText().toString();
        String replaced = null, replaced1 = null, replaced2 = null;
        Log.i("Expression", exp);

        if (et1.contains("mod")) {
            replaced = exp.replace("mod", "m");
            //  Toast.makeText(this,replaced+"",Toast.LENGTH_LONG).show();
        } else {
            replaced = exp;
        }
        if (replaced.contains("yroot")) {
            replaced1 = replaced.replace("yroot", "y");
        } else {
            replaced1 = replaced;
        }
        if (et1.contains("logxBasey")) {
            replaced2 = replaced1.replace("logxBasey", "l");
        } else {
            replaced2 = replaced1;
        }
        if (replaced2.length() > 1) {
            if ((!et2.contains(clearScreenSpace) || (otherOpCheck == true)) && (equalsto == false) && (dualOp == false)) {
                String currentText = null;
                if (otherOpCheck) {
                    currentText = et1;
                } else {
                    currentText = et1 + et2;
                }
                String input = replaced2 + et2;
                Log.i(TAG, input);
                //           Toast.makeText(this,"input"+input,Toast.LENGTH_LONG).show();
                double output = BinaryOperations.eval(input);

                editText1.setText(clearScreenSpace + currentText);
                editText2.setText(checkOutput(fmt(output)) + "");
                otherOpCheck = false;
                expression.setText("");
            } else if ((et1.charAt(et1.length() - 1) == ')') && (equalsto == false) && (dualOp == false)) {
                String input = replaced2;
                Log.i("input", input);
                double output = BinaryOperations.eval(input);
                //     Toast.makeText(this,"input"+input,Toast.LENGTH_LONG).show();
                editText1.setText(clearScreenSpace + et1);
                editText2.setText(checkOutput(fmt(output)) + "");
                otherOpCheck = false;
                expression.setText("");
            } else {
            }
            equalsto = true;
        }
    }
    //**********************************************************************************************

    //**************************** Memory oprations start from here*********************************
    private void setMem(double m){
        memSet = true;
        memoryVal = m;
    }

    private double getMem(){
        return  memoryVal;
    }

    public void putMC(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        memSet = false;
        setMem(0.0);
        //remove "M" from EditText2
        String mtext = "";
        editText2.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(mtext), null, null, null);
        editText2.setCompoundDrawablePadding(mtext.length()*10);
    }

    public void putMR(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        editText2.setText(" "+getMem());
    }

    public void putMS(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        double m = Double.parseDouble(editText2.getText().toString());
        setMem(m);
        //set "M" to EditText2
        String mtext = "M";
        editText2.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(mtext), null, null, null);
        editText2.setCompoundDrawablePadding(mtext.length()*10);
    }

    public void putMp(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        double i = Double.parseDouble(editText2.getText().toString());
        setMem(getMem()+i);
    }

    public void putMm(View v){
        v.playSoundEffect(SoundEffectConstants.CLICK);
        double i = Double.parseDouble(editText2.getText().toString());
        setMem(getMem()-i);
    }
    //**********************************************************************************************
}