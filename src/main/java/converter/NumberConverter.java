package converter;

import java.math.BigInteger;

public class NumberConverter {
    private static final String[] ONE_DECLINATION = {"ин", "на"};
    private static final String[] TWO_DECLINATION = {"а", "e"};
    private static final String[] THOUSAND_DECLINATION = {"а", "и"};
    private static final String[] DEGREE_DECLINATION = {"а","ов"};
    private static final String[] NUMBER_DEGREES = {"", "тысяч", "миллион", "миллиард","триллион","квадриллион","квинтиллион", "секстилион", "септиллион", "октиллион", "ноналлион", "декаллион", "эндекаллион", "додекаллион"};
    private static final String[] UNITS = {"ноль", "од", "дв", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    private static final String[] DECIMAL_BETWEEN_TEN_AND_TWENTY = {"одиннадцать", "двенадцать", "тринадцать","четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", " восемнадцать", "девятнадцать"};
    private static final String[] DECIMAL = {"","десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String[] HUNDREDS = {"","сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    public static String convertNumberIntoWords(BigInteger number) {
        String inputNumber = String.valueOf(number);
        StringBuilder convertedNumber = new StringBuilder();
        String[] partsOfNumber = inputNumber.split("\\B(?=(\\d{3})+(?!\\d))");
        int maxIndexOfNumberParts = partsOfNumber.length-1;
        for (int index = 0; index < partsOfNumber.length; index++) {
            inputNumber = partsOfNumber[index];
            transformThreeNumbers(inputNumber, convertedNumber, maxIndexOfNumberParts-index);
        }
        return convertedNumber.toString();
    }

    private static void transformThreeNumbers(String inputNumber, StringBuilder convertedNumber, int index)
    {
        int secondNumber = 0;
        String currentNumberString = String.valueOf(inputNumber.charAt(0));
        int firstNumber = getNumberOnPosition(inputNumber,0);
        if(firstNumber == 0 && inputNumber.length() == 1) {
            addWord(convertedNumber, UNITS[firstNumber]);
        }
        else {
            if(inputNumber.length() > 1) {
                currentNumberString = inputNumber.substring(inputNumber.length()-2);
            }
            int lastNumbers = Integer.parseInt(currentNumberString);
            if(inputNumber.length() == 3) {
                if(firstNumber != 0) {
                    addWord(convertedNumber, HUNDREDS[firstNumber]);
                }
            }
            if(inputNumber.length() == 2 || lastNumbers != 0) {
                if(lastNumbers > 10 && lastNumbers < 20) {
                    secondNumber = getNumberOnPosition(currentNumberString,1);
                    String decimal = DECIMAL_BETWEEN_TEN_AND_TWENTY[secondNumber];
                    addWord(convertedNumber, decimal);
                }
                else {
                    firstNumber = getNumberOnPosition(currentNumberString, 0);
                    if(currentNumberString.length() == 2) {
                        secondNumber = getNumberOnPosition(currentNumberString, 1);
                        if(firstNumber != 0) {
                            addWord(convertedNumber, DECIMAL[firstNumber]);
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

    private static String getSizeWithDeclination(int number, int degree) {
        StringBuilder word = new StringBuilder(NUMBER_DEGREES[degree]);
        if(degree > 1) {
            declinationOverMillion(number, word);
        }
        else {
            thousandDeclination(number, word);
        }
        return word.toString();
    }
    private static void thousandDeclination(int number, StringBuilder word) {
        if(!word.toString().isEmpty()) {
            if(number == 1) {
                word.append(THOUSAND_DECLINATION[0]);
            }
            if(number >= 2 && number < 5) {
                word.append(THOUSAND_DECLINATION[1]);
            }
        }
    }
    private static void declinationOverMillion(int number, StringBuilder word) {
        if(number != 1) {
            if(number >= 2 && number < 5) {
                word.append(DEGREE_DECLINATION[0]);
            }
            else {
                word.append(DEGREE_DECLINATION[1]);
            }
        }
    }
    private static String unitDeclination(int number, int degree) {
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
    private static void addWord(StringBuilder convertedNumber, String word) {
        convertedNumber.append(word).append(" ");
    }
    private static int getNumberOnPosition(String number, int position) {
        return Integer.parseInt(String.valueOf(number.charAt(position)));
    }
}
