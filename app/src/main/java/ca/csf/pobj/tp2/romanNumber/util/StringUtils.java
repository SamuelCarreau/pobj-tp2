package ca.csf.pobj.tp2.romanNumber.util;

public class StringUtils {

    private  StringUtils(){
        //Private, so it is a static class
    }

    public static boolean isBlank(String string){
        for(int i = 0 ; i < string.length() ; i++){
            if(!Character.isWhitespace(string.charAt(i))){
                return false;
            }
        }
        return true;
    }


}
