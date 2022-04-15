import converter.NumberConverter;

import java.math.BigInteger;

public class NumberConverterApplication {
    public static void main(String[] args) {
        String input = "134345";
        BigInteger bigInteger = new BigInteger(input);
        System.out.println(NumberConverter.convertNumberIntoWords(bigInteger));
    }
}
