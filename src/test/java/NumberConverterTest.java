/*
 * Copyright (c) 2022.
 * Created by Roman on 16.04.2022.
 */

import converter.Convertable;
import converter.NumberConverter;
import handler.DataHandler;
import org.junit.Before;
import org.junit.Test;
import reader.DataReader;
import reader.Readable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NumberConverterTest
{
    private Convertable converter;
    private DataHandler dataHandler;

    @Before
    public void setUp() throws Exception
    {
        Readable reader = new DataReader();
        reader.read();
        dataHandler = reader.getConfiguredData();
        converter = new NumberConverter(dataHandler);
    }
    @Test
    public void zeroTest() {
        String testNumber = "0";
        String exceptedAnswer = "ноль";
        System.out.println("Test 0");
        assertEquals("Ответ не соответсвует: ноль.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void minusTest() {
        String testNumber = "-342000000";
        String exceptedAnswer = "минус триста сорок два миллиона";
        System.out.println("Minus test");
        assertEquals("Ответ не соответсвует: минус триста сорок два миллиона.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void oneTest() {
        String testNumber = "1";
        String exceptedAnswer = "один";
        System.out.println("Test 1");
        assertEquals("Ответ не соответсвует: один.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void fiveTest() {
        System.out.println("Test 5");
        String testNumber = "5";
        String exceptedAnswer = "пять";
        assertEquals("Ответ не соответсвует: пять.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void vigintillionTest() {
        System.out.println("Test of vigintillion");
        System.out.println("Data: 3490509368034875785678687567658667888286035609348609345860950345");
        String vigintillion = "3490509368034875785678687567658667888286035609348609345860950345";
        String exceptedAnswer = "три вигинтиллиона четыреста девяносто ундевигинтиллионов пятьсот девять дуодевигинтиллионов " +
                "триста шестьдесят восемь септдециллионов тридцать четыре седециллиона восемьсот семьдесят пять квиндециллионов " +
                "семьсот восемьдесят пять кваттордециллионов шестьсот семьдесят восемь тредециллионов шестьсот восемьдесят семь " +
                "дуодециллионов пятьсот шестьдесят семь ундециллионов шестьсот пятьдесят восемь дециллионов шестьсот шестьдесят " +
                "семь ноналлионов восемьсот восемьдесят восемь октиллионов двести восемьдесят шесть септиллионов тридцать пять " +
                "секстилионов шестьсот девять квинтиллионов триста сорок восемь квадриллионов шестьсот девять триллионов триста сорок пять " +
                "миллиардов восемьсот шестьдесят миллионов девятьсот пятьдесят тысяч триста сорок пять";
        assertEquals("Ответ не соответсвует заданному вигинтиллиону.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(vigintillion)));
        System.out.println("Passed!");
    }
    @Test
    public void testNumbersBetweenTenAndTwenty() {
        String[] testNumbers = {"11", "12", "13", "14", "15", "16", "17", "18", "19"};
        String[] exceptedAnswer = dataHandler.getDecimalBetweenTenAndTwenty();
        List<String> convertedNumbers = new ArrayList<>(Collections.singletonList(""));
        System.out.println("Test 11-19");
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("Ошибка в промежутке 11-19.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentDecimalNumbers() {
        String[] testNumbers = {"29", "42", "10", "34", "55", "76", "87", "56"};
        String[] exceptedAnswer = {"двадцать девять", "сорок два", "десять", "тридцать четыре",
                "пятьдесят пять", "семьдесят шесть", "восемьдесят семь", "пятьдесят шесть"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Test different decimal numbers.");
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("Ошибка в промежутке разных десятичных чисел.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentHundredsNumbers() {
        System.out.println("Test different hundreds numbers.");
        String[] testNumbers = {"229", "442", "100", "934", "155", "176", "887", "756"};
        String[] exceptedAnswer = {"двести двадцать девять", "четыреста сорок два", "сто", "девятьсот тридцать четыре",
                "сто пятьдесят пять", "сто семьдесят шесть", "восемьсот восемьдесят семь", "семьсот пятьдесят шесть"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("Ошибка в промежутке разных десятичных чисел.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentThousandsNumbers() {
        String[] testNumbers = {"11229", "9442", "202100"};
        String[] exceptedAnswer = {"одиннадцать тысяч двести двадцать девять", "девять тысяч четыреста сорок два", "двести две тысячи сто"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Test different thousands numbers.");
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("Ошибка в промежутке разных десятичных чисел.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
}
