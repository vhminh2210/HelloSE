/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prj_class;

public class utils {
    public static String dobFormat(int ngaySinh, int thangSinh, int namSinh){
        String d, m, y;
        if(ngaySinh == 0) d = "NA";
        else d = String.valueOf(ngaySinh);
        if(thangSinh == 0) m = "NA";
        else m = String.valueOf(thangSinh);
        if(namSinh == 0) y = "NA";
        else y = String.valueOf(namSinh);
        return d + "/" + m + "/" + y;
    }
    
    public static String quoteWrap(String s){
        return "\"" + s + "\"";
    }
    
    public static boolean checkDMY(String s){
        String[] words = s.split("/"); 
        if(words[0].length() != 2) return false;
        if(words[1].length() != 2) return false;
        if(words[2].length() != 4) return false;
        /*Tham khảo: https://www.baeldung.com/java-check-string-number*/
        try {
            double d = Double.parseDouble(words[0]);
            d = Double.parseDouble(words[1]);
            d = Double.parseDouble(words[2]);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static String dmy2ymd(String s){
        String[] words = s.split("/");
        String res = words[2] + "-" + words[1] + "-" + words[0];
        return res;
    }
    
    public static boolean isNumeric(String strNum) {
    /*Tham khảo: https://www.baeldung.com/java-check-string-number*/
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
}
