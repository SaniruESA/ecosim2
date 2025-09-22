package com.ecosim;

public class NDigitString {
    int length = 0;
    String str = "";
    public NDigitString(String value, int length){
        this.length = length;
        this.str = value;
    }
    /** 
     * @param length
     */
    public void setLength(int length) {
        if(length >= 0) {this.length = length; }
    }
    /** 
     * @return String
     */
    private String CropLength(){
        return (str + " ".repeat(length)).substring(0, length);
    }
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return CropLength();
    }
}
