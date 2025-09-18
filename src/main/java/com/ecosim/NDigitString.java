package com.ecosim;

public class NDigitString {
    int length = 0;
    String str = "";
    public NDigitString(String value, int length){
        this.length = length;
        this.str = value;
    }
    public void setLength(int length) {
        if(length >= 0) {this.length = length; }
    }
    private String CropLength(){
        return (str + " ".repeat(length)).substring(0, length);
    }
    @Override
    public String toString(){
        return CropLength();
    }
}
