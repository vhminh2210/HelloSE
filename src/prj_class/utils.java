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
}
