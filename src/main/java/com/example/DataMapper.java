package com.example;

import com.example.pojo.Data;
import java.text.ParseException;

public class DataMapper {

    public static Data mapHeader(String[] line) {


        Data data = new Data.DataBuilder(line[0])
                .activated(Integer.parseInt(line[1]))
                .emailOutput(Integer.parseInt(line[2]))
                .phoneOutput(Integer.parseInt(line[3]))
                .faxOutput(Integer.parseInt(line[4]))
                .dateCreated(line[5])
                .dateModified(line[6]).build();
        return data;
    }

    public static Data mapData(String[] line) throws ParseException {

        Data data = new Data.DataBuilder(line[0])
                .activated(Integer.parseInt(line[1]))
                .emailOutput(Integer.parseInt(line[2]))
                .phoneOutput(Integer.parseInt(line[3]))
                .faxOutput(Integer.parseInt(line[4]))
                .dateCreated(line[5])
                .dateModified(line[6]).build();
        return data;
    }

}
