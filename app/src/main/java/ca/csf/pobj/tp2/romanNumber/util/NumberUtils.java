package ca.csf.pobj.tp2.romanNumber.util;

public final class NumberUtils {

    private  NumberUtils(){
        //Private, so it is a static class
    }

    public static  int toInt(String string){
        return Integer.parseInt(string);
    }

    public static boolean isNumber(String suspectedNumber){
        try{
            Integer.parseInt(suspectedNumber);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }


}
