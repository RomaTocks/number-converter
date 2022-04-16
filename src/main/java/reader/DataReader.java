/*
 * Copyright (c) 2022.
 * Created by Roman on 16.04.2022.
 */

package reader;

import handler.DataHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataReader implements Readable
{
    private static final String PATH = "src/main/resources/data.txt";
    private static final Map<String, String[]> data = new HashMap<>();

    @Override
    public void read() throws IOException
    {
        File file = new File(PATH);
        if (file.exists()){
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.lines().forEach(line -> {
                String[] splittedLine = line.split(":");
                String[] values = splittedLine[1].split(",");
                data.put(splittedLine[0], values);
            });
            reader.close();
        }
    }
    public DataHandler getConfiguredData() {
        DataHandler handler = new DataHandler();
        for (String key : data.keySet())
        {
            String[] currentData = data.get(key);
            switch (key) {
                case "one_declination": {
                    handler.setOneDeclination(currentData);
                    break;
                }
                case "two_declination": {
                    handler.setTwoDeclination(currentData);
                    break;
                }
                case "thousand_declination": {
                    handler.setThousandDeclination(currentData);
                    break;
                }
                case "degree_declination": {
                    handler.setDegreeDeclination(currentData);
                    break;
                }
                case "number_degrees": {
                    handler.setNumberDegrees(currentData);
                    break;
                }
                case "units": {
                    handler.setUnits(currentData);
                    break;
                }
                case "decimal_between_ten_and_twenty": {
                    handler.setDecimalBetweenTenAndTwenty(currentData);
                    break;
                }
                case "decimal": {
                    handler.setDecimal(currentData);
                    break;
                }
                case "hundreds": {
                    handler.setHundreds(currentData);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return handler;
    }
}
