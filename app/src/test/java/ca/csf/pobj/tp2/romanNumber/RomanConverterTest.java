package ca.csf.pobj.tp2.romanNumber;

import org.junit.Test;

import java.util.TreeSet;

import ca.csf.pobj.tp2.romanNumber.roman.RomanConverter;

import static org.junit.Assert.*;

public class RomanConverterTest {

    @Test
    public void convertToRomanGiveExpectedRomanNumber(){

        //for 4876 expect MMMMDCCCLXXVI
        assertEquals("MMMMDCCCLXXVI",RomanConverter.ConvertToRoman(4876));

        //for 4999 expect MMMMCMXCIX
        assertEquals("MMMMCMXCIX",RomanConverter.ConvertToRoman(4999));

        //for 180 expect CLXXX
        assertEquals("CLXXX",RomanConverter.ConvertToRoman(180));

        //for 49 expect XLIX
        assertEquals("XLIX",RomanConverter.ConvertToRoman(49));

        //for 7 expect VII
        assertEquals("VII",RomanConverter.ConvertToRoman(7));

        //for 1 expect I
        assertEquals("I",RomanConverter.ConvertToRoman(1));

        //for 2001 expect MMI
        assertEquals("MMI",RomanConverter.ConvertToRoman(2001));
    }

    @Test
    public void convertOfNumberFrom1to4999GiveUniqueAnswer(){
        TreeSet<String> romanNumbers = new TreeSet<>();
        for(int i = 1 ; i <= 4999 ; i++){
            romanNumbers.add(RomanConverter.ConvertToRoman(i));
        }

        assertEquals(4999,romanNumbers.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void WillTrowExceptionForNumberBelowOne(){
        RomanConverter.ConvertToRoman(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WillTrowExceptionForNumberAbove4999(){
        RomanConverter.ConvertToRoman(5000);
    }
}