package ca.csf.pobj.tp2.romanNumber.roman;

public class RomanConverter {

    private static final String[] ROMAN_UNITS = {"I","X","C","M"};
    private static final String[] ROMAN_HALFS = {"V","L","D",""};
    private static final int MAX_POWER = 3;
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 4999;

    private RomanConverter() {
        //Private, so it is a static class.
    }

    public static String ConvertToRoman(int integer){

        tryIfValid(integer);

        int[] intByPower = intCuttingByPower(integer);

        StringBuilder RomanNumberBuilder = new StringBuilder();

        for(int i = 0; i <intByPower.length ; i++){
            RomanNumberBuilder.insert(0,getRomanNumber(intByPower[i],i));
        }
        return RomanNumberBuilder.toString();
    }

    private static int[] intCuttingByPower(int integer){
        int[] cutInteger = new int[MAX_POWER+1];
        int rest = integer;

        for(int i = MAX_POWER ; i >=0 ;i--){
            cutInteger[i] = rest/(int)Math.pow(10,i);
            rest = rest%(int)Math.pow(10,i);
        }

        return cutInteger;
    }

    private static String getRomanNumber(int integer,int power){
            String unit = getUnit(power);
            String half = getHalf(power);

            if(power == 3){
                return countUnit(integer,unit);
            }
            else {
                if(integer == 9){
                    String nextPowerUnit = getUnit(power+1);
                    return unit+nextPowerUnit;
                }
                else if(integer > 4){
                    return half+countUnit(integer-5,unit);
                }
                else if(integer == 4){
                    return unit+half;
                }
                else{
                    return countUnit(integer,unit);
                }
            }
    }

    private static String countUnit(int integer, String unit) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < integer ; i++){
            stringBuilder.append(unit);
        }
        return stringBuilder.toString();
    }

    private static String getUnit(int power){
            return ROMAN_UNITS[power];
    }

    private static String getHalf(int power){
            return ROMAN_HALFS[power];

    }

    private static void tryIfValid(int integer){
        if(integer < RomanConverter.MIN_INT || integer > RomanConverter.MAX_INT)
            throw new IllegalArgumentException("number must be betwen"+RomanConverter.MIN_INT +" and "+RomanConverter.MAX_INT);
    }
}
