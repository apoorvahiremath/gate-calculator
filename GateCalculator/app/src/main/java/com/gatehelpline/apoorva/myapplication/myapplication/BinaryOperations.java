package com.gatehelpline.apoorva.myapplication.myapplication;

/**
 * Created by Apoorva on 8/29/2016.
 */

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by Apoorva on 7/27/2016.
 */
public class BinaryOperations {
    private double a, b, o;

    public BinaryOperations(){}

    public BinaryOperations(double a){
        this.a = a;
    }

    public BinaryOperations(double a, double b){
        this.a = a;
        this.b = b;
    }

    private double add(double x, double y){
        o = x + y;
        return o;
    }

    private double sub(double x, double y){
        o = x - y;
        return o;
    }

    private double mult(double x, double y){
        o = x * y;
        return o;
    }

    private double div(double x, double y){
        if(y != 0.0) {
            o = x / y;
        }
        return o;
    }

    private static double percent(double x, double y){
        BigDecimal a = BigDecimal.valueOf(x);
        BigDecimal b = BigDecimal.valueOf(y);
        double res = a.multiply(b).divide(BigDecimal.valueOf(100d)).doubleValue();
        return res;
    }

    private static double mod(double x, double y){
        return x % y;
    }

    private static double ythroot(double x, double y){
        double a = Math.log10(x) / y;
        double o = Math.pow(10, a);
        return o;
    }

    private static double ythpow(double x, double y){
        return Math.pow(x ,y);
    }

    private static double ythlog(double x, double y){
        return Math.log10(x) / Math.log10(y);
    }

    private static double fact(double x){
        double o = 1.0;
        if((x == 0.0) || (x == 1.0))
            o = 1.0;
        else{
            for(double i = x; i > 1; i--){
                o *= i;
            }
        }
        return o;
    }

    private static double reciprocal(double x){
        double o = 1/x;
        return o;
    }

    private static double abs(double x){
        double o;
        if (x == 0.0) {
            o = x;
        }else {
            o = Math.abs(x);
        }
        return o;
    }

    public double chooseOperation(int i){
        double result = 0;
        switch (i){
            case 1:
                result = add(a,b);
                break;
            case 2:
                result = sub(a,b);
                break;
            case 3:
                result = mult(a,b);
                break;
            case 4:
                result = div(a,b);
                break;
            case 5:
                result = percent(a,b);
                break;
            case 6:
                result = mod(a,b);
                break;
            case 7:
                result = ythlog(a,b);
                break;
            case 8:
                result = ythpow(a,b);
                break;
            case 9:
                result = ythroot(a,b);
                break;
            case 10:
                result =fact(a);
                break;
            case 11:
                //sqrt
                result = Math.sqrt(a);
                break;
            case 12:
                //cuberoot
                result = Math.cbrt(a);
                break;
            case 13:
                result = reciprocal(a);
                break;
            case 14:
                //ln function
                result = Math.log(a);
                break;
            case 15:
                //log function
                result = Math.log10(a);
                break;
            case 16:
                //logx2 fuction
                result = Math.log10(a) / Math.log10(2.0);
                break;
            case 17:
                //abs
                result = abs(a);
                break;
            case 18:
                result = Math.pow(a, 2);
                break;
            case 19:
                result = Math.pow(a, 3);
                break;
            case 20:
                result = Math.pow(10, a);;
                break;
            case 21:
                result = Math.exp(a);
                break;

        }
        return result;
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                        //    else if (func.equals("mod")) x = mod(x, parseFactor()) ;
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
//                    while ((ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z')) nextChar();
//                    String func = str.substring(startPos, this.pos);
//                    //x = parseFactor();
//                    if (func.equals("Infinity")) x = Double.POSITIVE_INFINITY;
//                    else if (func.equals("Negative Infinity")) x = Double.NEGATIVE_INFINITY;
//                    else if (func.equals("Math Error")||func.equals("NaN")) x = Double.NaN;
//                    else throw new RuntimeException("Unknown function: " + func);
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                else if (eat('%')) x = percent(x, parseFactor());
                else if (eat('m')) x = mod(x, parseFactor());
                else if (eat('y')) x = ythroot(x, parseFactor());
                else if (eat('l')) x = ythlog(x, parseFactor());
                return x;
            }
        }.parse();
    }

    public static double evalPara(String s){
        if(!s.contains("(")){
            return eval(s);
        }else{
            Stack<String> exp = new Stack<String>();
            int j = 0, sim = 0;
            String op = "+-*/^%myl";
            String tempop = null;
            double tempRes = 0, res = 0, pop = 0;
            for(int i = j; i < s.length(); ){
                if(s.charAt(i) == '('){
                    if((i == 0)||(tempRes == 0)){
                        exp.push("0");
                        exp.push("+");
                        j = i + 1;
                    }else if(i > 1) {
                        //double tempRes = eval(s.substring(j, i - 2));
                        exp.push(tempRes+"");
                        exp.push(s.charAt(i-1)+"");
                        tempRes = 0;
                        j = i + 1;
                    }
                    sim++;
                }else if(s.charAt(i) == ')'){
                    sim--;
                    if(tempop == null){
                        res = eval(tempRes + exp.pop() + exp.pop());
                    }else{
                        String sss = res+tempop+tempRes ;
                        res = eval(sss);
                    }
                    tempRes = pop;
                    if(i < s.length()-1){
                        j = i + 1;
                        tempop = s.charAt(j)+"";
                    }
                }else{
                    if(op.contains(s.charAt(i)+"")){
                        if(s.charAt(i-1) == ')'){
                            tempRes = eval(res + s.substring(j,i));
                        }
                        else{
                            tempRes = eval(s.substring(j, i));
                        }
                    }else if(((i < s.length()-1)&&(s.charAt(i+1) == ')'))) {
                        tempRes = eval(s.substring(j, i+1));
                    }else if(i == (s .length())-1){
                        if(exp.isEmpty())
                            tempRes = eval(res + s.substring(j,i+1));
                        else
                            tempRes = eval(res + exp.pop() + exp.pop());
                        res = tempRes;
                    }else{
                        i++;
                        continue;
                    }

                }
                i++;
            }
            return res;

        }
    }
}

