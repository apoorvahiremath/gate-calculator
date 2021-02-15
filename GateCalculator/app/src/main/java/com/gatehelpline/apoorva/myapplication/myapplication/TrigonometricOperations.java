package com.gatehelpline.apoorva.myapplication.myapplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Apoorva on 8/30/2016.
 */
public class TrigonometricOperations {

    private static double getAngle(double a, boolean d){
        if(d == true) {
            return toRad(a);
        }else{
            return a;
        }
    }
    private static double getInverseAngle(double a, boolean d){
        if(d == true) {
            return toDeg(a);
        }else{
            return a;
        }
    }
    private static double toDeg(double x){
        BigDecimal a = BigDecimal.valueOf(x);
        BigDecimal b = BigDecimal.valueOf(180);
        BigDecimal c = a.multiply(b);
        BigDecimal d = c.divide(BigDecimal.valueOf(Math.PI), RoundingMode.CEILING);
        return d.doubleValue();
    }
    private static double toRad(double x){
        BigDecimal a = BigDecimal.valueOf(x);
        BigDecimal b = BigDecimal.valueOf(Math.PI);
        BigDecimal c = a.multiply(b);
        BigDecimal d = c.divide(BigDecimal.valueOf(180), RoundingMode.CEILING);
        return d.doubleValue();
    }
    static double getSin(double a, boolean d){
        double angle = getAngle(a, d);
        return Math.sin(angle);
    }
    static double getCos(double a, boolean d){
        double angle = getAngle(a, d);
        return Math.cos(angle);
    }
    static double getTan(double a, boolean d){
        double angle = getAngle(a, d);
        double res;
        if(angle == 45 || angle==Math.toRadians(45) || angle== 225 || angle == Math.toRadians(225))
            res = 1;
        else if(angle == 90 || angle==Math.toRadians(90) || angle== 270 || angle == Math.toRadians(270))
            res = Double.POSITIVE_INFINITY;
        else
            res = Math.tan(angle);
        return res;

    }
    static double getSinh(double a, boolean d){
        double angle = getAngle(a, d);
        return Math.sinh(angle);
    }
    static double getCosh(double a, boolean d){
        double angle = getAngle(a, d);
        return Math.cosh(angle);
    }
    static double getTanh(double a, boolean d){
        double angle = getAngle(a, d);
        double res;
        if(angle == 45 || angle==Math.toRadians(45) || angle== 225 || angle == Math.toRadians(225))
            res = 1;
        else if(angle == 90 || angle==Math.toRadians(90) || angle== 270 || angle == Math.toRadians(270))
            res = 1;
        else
            res = Math.tanh(angle);
        return res;
    }
    static double getSinI(double a, boolean d){
        if((a <= 1)&&(a >= -1)) {
            double res = Math.asin(a);
            return getInverseAngle(res, d);
        }else
            return Double.NaN;
    }
    static double getCosI(double a, boolean d){
        if((a <= 1)&&(a >= -1)) {
            double res = Math.acos(a);
            return getInverseAngle(res, d);
        }else
            return Double.NaN;
    }
    static double getTanI(double a, boolean d){
        double res = Math.atan(a);
        return getInverseAngle(res, d);
    }
    static double getSinhI(double a, boolean d){
        if((a <= 1)&&(a >= -1)) {
            double res = Math.log(a + Math.sqrt(a*a + 1.0));
            return getInverseAngle(res, d);
        }else
            return Double.NaN;
    }
    static double getCoshI(double a, boolean d){
        if((a <= 1)&&(a >= -1)) {
            double res = Math.log(a + Math.sqrt(a * a - 1.0));
            return getInverseAngle(res, d);
        }else
            return Double.NaN;
    }
    static double getTanhI(double a, boolean d){
        double res = 0.5*Math.log( (a + 1.0) / (a - 1.0) );
        return getInverseAngle(res, d);
    }

    public static double chooseOperation(double a, boolean b, int i){
        double result = 0;
        switch (i) {
            case 1:
                result = getSin(a, b);
                break;
            case 2:
                result = getCos(a, b);
                break;
            case 3:
                result = getTan(a, b);
                break;
            case 4:
                result = getSinh(a, b);
                break;
            case 5:
                result = getCosh(a, b);
                break;
            case 6:
                result = getTanh(a, b);
                break;
            case 7:
                result = getSinI(a,b);
                break;
            case 8:
                result = getCosI(a, b);
                break;
            case 9:
                result = getTanI(a, b);
                break;
            case 10:
                result = getSinhI(a, b);
                break;
            case 11:
                result = getCoshI(a, b);
                break;
            case 12:
                result = getTanhI(a, b);
                break;
        }
            return result;
    }
}
