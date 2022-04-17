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
        String exceptedAnswer = "����";
        System.out.println("Test 0");
        assertEquals("����� �� ������������: ����.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void minusTest() {
        String testNumber = "-342000000";
        String exceptedAnswer = "����� ������ ����� ��� ��������";
        System.out.println("Minus test");
        assertEquals("����� �� ������������: ����� ������ ����� ��� ��������.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void oneTest() {
        String testNumber = "1";
        String exceptedAnswer = "����";
        System.out.println("Test 1");
        assertEquals("����� �� ������������: ����.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void fiveTest() {
        System.out.println("Test 5");
        String testNumber = "5";
        String exceptedAnswer = "����";
        assertEquals("����� �� ������������: ����.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(testNumber)));
        System.out.println("Passed!");
    }
    @Test
    public void vigintillionTest() {
        System.out.println("Test of vigintillion");
        System.out.println("Data: 3490509368034875785678687567658667888286035609348609345860950345");
        String vigintillion = "3490509368034875785678687567658667888286035609348609345860950345";
        String exceptedAnswer = "��� ������������� ��������� ��������� ������������������ ������� ������ ������������������� " +
                "������ ���������� ������ ��������������� �������� ������ ������������ ��������� ��������� ���� ��������������� " +
                "������� ����������� ���� ������������������ �������� ��������� ������ �������������� �������� ����������� ���� " +
                "�������������� ������� ���������� ���� ������������� �������� ��������� ������ ����������� �������� ���������� " +
                "���� ����������� ��������� ����������� ������ ����������� ������ ����������� ����� ������������ �������� ���� " +
                "������������ �������� ������ ������������� ������ ����� ������ ������������� �������� ������ ���������� ������ ����� ���� " +
                "���������� ��������� ���������� ��������� ��������� ��������� ����� ������ ����� ����";
        assertEquals("����� �� ������������ ��������� �������������.", exceptedAnswer, converter.convertNumberIntoWords(new BigInteger(vigintillion)));
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
        assertArrayEquals("������ � ���������� 11-19.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentDecimalNumbers() {
        String[] testNumbers = {"29", "42", "10", "34", "55", "76", "87", "56"};
        String[] exceptedAnswer = {"�������� ������", "����� ���", "������", "�������� ������",
                "��������� ����", "��������� �����", "����������� ����", "��������� �����"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Test different decimal numbers.");
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("������ � ���������� ������ ���������� �����.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentHundredsNumbers() {
        System.out.println("Test different hundreds numbers.");
        String[] testNumbers = {"229", "442", "100", "934", "155", "176", "887", "756"};
        String[] exceptedAnswer = {"������ �������� ������", "��������� ����� ���", "���", "��������� �������� ������",
                "��� ��������� ����", "��� ��������� �����", "��������� ����������� ����", "������� ��������� �����"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("������ � ���������� ������ ���������� �����.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
    @Test
    public void testDifferentThousandsNumbers() {
        String[] testNumbers = {"11229", "9442", "202100"};
        String[] exceptedAnswer = {"����������� ����� ������ �������� ������", "������ ����� ��������� ����� ���", "������ ��� ������ ���"};
        List<String> convertedNumbers = new ArrayList<>();
        System.out.println("Test different thousands numbers.");
        System.out.println("Data: " + Arrays.toString(testNumbers));
        for (String testNumber : testNumbers)
        {
            String convertedNumber = converter.convertNumberIntoWords(new BigInteger(testNumber));
            convertedNumbers.add(convertedNumber);
        }
        assertArrayEquals("������ � ���������� ������ ���������� �����.", exceptedAnswer, convertedNumbers.toArray());
        System.out.println("Passed!");
    }
}
