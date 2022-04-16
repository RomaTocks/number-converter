/*
 * Copyright (c) 2022.
 * Created by Roman on 16.04.2022.
 */

package converter;

import handler.DataHandler;

import java.math.BigInteger;

/**
 * Created by Roman on 16.04.2022.
 * Convert number into words.
 * @version 0.1
 * @autor Roman Sukach
 */

/*
    Перевод числа в цифровой записи в строковую. Например 134345 будет "сто
тридцать четыре тысячи триста сорок пять". * Учесть склонения - разница
в окончаниях (к примеру, две и два).
    Алгоритм должен работать для сколько угодно большого числа, соответственно,
значения степеней - миллион, тысяча, миллиад и т.д. - должны браться их
справочника, к примеру, текстового файла.
    Обязательно создать Data Driven Test (я, как пользователь, должен иметь
возможность ввести множество наборов:
    1.число
    2.правильный эталонный результат, тест самостоятельно проверяет все наборы
и говорит, что неверное), который доказывает, что Ваш алгоритм работает правильно.
    Использовать JUnit.
    По возможности, применить ООП.
 */
public class NumberConverter extends Converter implements Convertable {

    //Divides the string by 3 characters. if part less than 3, leaves as many as there are.
    private static final String REG_EXP = "\\B(?=(\\d{3})+(?!\\d))";

    public NumberConverter(DataHandler dataHandler) {
        super(dataHandler);
    }

    @Override
    public String convertNumberIntoWords(BigInteger number) {
        String inputNumber = String.valueOf(number);
        StringBuilder convertedNumber = new StringBuilder();
        String[] partsOfNumber = inputNumber.split(REG_EXP);
        int maxIndexOfNumberParts = partsOfNumber.length-1;
        for (int index = 0; index < partsOfNumber.length; index++) {
            inputNumber = partsOfNumber[index];
            transformThreeNumbers(inputNumber, convertedNumber, maxIndexOfNumberParts-index);
        }
        return convertedNumber.toString().trim();
    }

    private void transformThreeNumbers(String inputNumber, StringBuilder convertedNumber, int index)
    {
        int lastNumbers;
        int secondNumber = 0;
        int firstNumber = getNumberOnPosition(inputNumber,0);
        String currentNumberString = String.valueOf(inputNumber.charAt(0));
        DataHandler dataHandler = this.getDataHandler();
        if(firstNumber == 0 && inputNumber.length() == 1) {
            addWord(convertedNumber, dataHandler.getUnits()[firstNumber]);
        }
        else {
            if(inputNumber.length() > 1) {
                currentNumberString = inputNumber.substring(inputNumber.length()-2);
            }
            lastNumbers = Integer.parseInt(currentNumberString);
            if(inputNumber.length() == 3) {
                if(firstNumber != 0) {
                    addWord(convertedNumber, dataHandler.getHundreds()[firstNumber]);
                }
            }
            if(inputNumber.length() == 2 || lastNumbers != 0) {
                if(lastNumbers > 10 && lastNumbers < 20) {
                    secondNumber = getNumberOnPosition(currentNumberString,1);
                    String decimal = dataHandler.getDecimalBetweenTenAndTwenty()[secondNumber];
                    secondNumber = lastNumbers;
                    addWord(convertedNumber, decimal);
                }
                else {
                    firstNumber = getNumberOnPosition(currentNumberString, 0);
                    if(currentNumberString.length() == 2) {
                        secondNumber = getNumberOnPosition(currentNumberString, 1);
                        if(firstNumber != 0) {
                            addWord(convertedNumber, dataHandler.getDecimal()[firstNumber]);
                        }
                    }
                    else {
                        secondNumber = firstNumber;
                    }
                    if(secondNumber != 0)  {
                        String unit = unitDeclination(secondNumber, index);
                        addWord(convertedNumber, unit);
                    }
                }
            }
        }
        if(Integer.parseInt(inputNumber) != 0) {
            String numberDegree = getSizeWithDeclination(secondNumber,index);
            addWord(convertedNumber, numberDegree);
        }
    }

    private String getSizeWithDeclination(int number, int degree) {
        String[] NUMBER_DEGREES = getDataHandler().getNumberDegrees();
        StringBuilder word = new StringBuilder(NUMBER_DEGREES[degree]);
        if(degree > 1) {
            declinationOverMillion(number, word);
        }
        else {
            thousandDeclination(number, word);
        }
        return word.toString();
    }
    private void thousandDeclination(int number, StringBuilder word) {
        if(!word.toString().isEmpty()) {
            String[] THOUSAND_DECLINATION = this.getDataHandler().getThousandDeclination();
            if(number == 1) {
                word.append(THOUSAND_DECLINATION[0]);
            }
            if(number >= 2 && number < 5) {
                word.append(THOUSAND_DECLINATION[1]);
            }
        }
    }
    private void declinationOverMillion(int number, StringBuilder word) {
        if(number != 1) {
            String[] DEGREE_DECLINATION = this.getDataHandler().getDegreeDeclination();
            if(number >= 2 && number < 5) {
                word.append(DEGREE_DECLINATION[0]);
            }
            else {
                word.append(DEGREE_DECLINATION[1]);
            }
        }
    }
    private String unitDeclination(int number, int degree) {
        DataHandler dataHandler = this.getDataHandler();
        String[] UNITS = dataHandler.getUnits();
        String[] ONE_DECLINATION = dataHandler.getOneDeclination();
        String[] TWO_DECLINATION = dataHandler.getTwoDeclination();
        if(degree == 1)
        {
            if(number == 1) {
                return UNITS[number] + ONE_DECLINATION[1];
            }
            if(number == 2) {
                return UNITS[number] + TWO_DECLINATION[1];
            }
        }
        else {
            if(number == 1) {
                return UNITS[number] + ONE_DECLINATION[0];
            }
            if(number == 2) {
                return UNITS[number] + TWO_DECLINATION[0];
            }
        }
        return UNITS[number];
    }
    private void addWord(StringBuilder convertedNumber, String word) {
        convertedNumber.append(word).append(" ");
    }
    private int getNumberOnPosition(String number, int position) {
        return Integer.parseInt(String.valueOf(number.charAt(position)));
    }
}
