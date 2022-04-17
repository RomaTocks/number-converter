import converter.Convertable;
import converter.NumberConverter;
import reader.DataReader;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class NumberConverterApplication {
    public static void main(String[] args)
    {
        DataReader reader = new DataReader();
        try
        {
            reader.read();
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("������� �����: ");
                try
                {
                    BigInteger value = scanner.nextBigInteger();
                    Convertable convertable = new NumberConverter(reader.getConfiguredData());
                    String answer = convertable.convertNumberIntoWords(value);
                    System.out.println("�����: " + answer);
                }
                catch (Exception exception)
                {
                    System.out.println("������������ �����!");
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("�� ������� ��������� ������������ ����������.");
        }
    }
}
