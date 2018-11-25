package ca.csf.pobj.tp2.romanNumber.roman;

public class RomanConverter {

    private static final String[] ROMAN_UNITS = {"I","X","C","M"};
    private static final String[] ROMAN_HALFS = {"V","L","D",""}; //BEN_REVIEW : Typo : "HALVES".
    private static final int MAX_POWER = 3;
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 4999;

    private RomanConverter() {
        //Private, so it is a static class
    }

    public static String ConvertToRoman(int number){

        tryIfValid(number);

        int[] intByPower = intCuttingByPower(number);

        //BEN_CORRECTION : Nommage. Devrait être en "camelCase", est en "PascalCase".
        StringBuilder RomanNumberBuilder = new StringBuilder();

        //BEN_CORRECTION : Propreté. Manque un espace après le "<". Semble généralisé au sein du code.
        for(int i = 0; i <intByPower.length ; i++){
            RomanNumberBuilder.insert(0,getRomanNumber(intByPower[i],i));
        }
        return RomanNumberBuilder.toString();
    }

    //BEN_REVIEW : Nommage de la fonction n'aide pas à comprendre ce qu'elle fait.
    private static int[] intCuttingByPower(int number){
        int[] cutNumber = new int[MAX_POWER+1];
        int rest = number;

        for(int i = MAX_POWER ; i >=0 ;i--){
            cutNumber[i] = rest/(int)Math.pow(10,i);
            rest = rest%(int)Math.pow(10,i);
        }

        return cutNumber;
    }

    private static String getRomanNumber(int number,int power){
            String unit = getUnit(power);
            String half = getHalf(power);

            if(power == 3){
                return countUnit(number,unit);
            }
            else {
                if(number == 9){
                    String nextPowerUnit = getUnit(power+1);
                    return unit+nextPowerUnit;
                }
                else if(number > 4){
                    return half+countUnit(number-5,unit);
                }
                else if(number == 4){
                    return unit+half;
                }
                else{
                    return countUnit(number,unit);
                }
            }
    }

    //BEN_CORRECTION : Nommage mensonger. Ne compte rien, car ne retourne pas un entier.
    private static String countUnit(int number, String unit) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < number ; i++){
            stringBuilder.append(unit);
        }
        return stringBuilder.toString();
    }

    private static String getUnit(int power){
            return ROMAN_UNITS[power];
    }

    private static String getHalf(int power){
            return ROMAN_HALFS[power]; //BEN_REVIEW : Propreté. Ligne vide en trop ci bas.

    }

    private static void tryIfValid(int number){
        if(number < RomanConverter.MIN_INT || number > RomanConverter.MAX_INT)
            //BEN_REVIEW : Ligne un peu longue aurait pu être mise sur plusieurs lignes.
            throw new IllegalArgumentException("Number must be betwen"+RomanConverter.MIN_INT +" and "+RomanConverter.MAX_INT);
    }
}