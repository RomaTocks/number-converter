/*
 * Copyright (c) 2022.
 * Created by Roman on 16.04.2022.
 */

package converter;

import handler.DataHandler;

public abstract class Converter
{
    private DataHandler dataHandler;

    public Converter(DataHandler dataHandler)
    {
        this.dataHandler = dataHandler;
    }

    public DataHandler getDataHandler()
    {
        return dataHandler;
    }

    public void setDataHandler(DataHandler dataHandler)
    {
        this.dataHandler = dataHandler;
    }
}
