/*
 * Copyright (c) 2022.
 * Created by Roman on 16.04.2022.
 */

package reader;

import handler.DataHandler;

import java.io.IOException;

public interface Readable
{
    void read() throws IOException;
    DataHandler getConfiguredData();
}
