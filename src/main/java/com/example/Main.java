package com.example;

import com.example.pojo.Data;
import com.example.pojo.DataTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.Util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean isOutputAsHtml = false;

        if (args.length == 0 ){
            System.out.println("Please specify input text file path");
            System.exit(1);
        }

        String path = args[0];

        if(!Files.exists(Path.of(path))) {
            System.out.println(String.format("Could not find file in the %s path", path));
            System.exit(1);
        }
        if (args.length > 1 && args[1].equalsIgnoreCase("HTML")) {
            isOutputAsHtml = true;
        }

        DataTable dataTable = readFile(path);
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        List<Data> result = dataTable.getData().stream()
                .sorted(Comparator.comparing(Data::getCustomerNoWithoutPrefix))
                .map(data -> data.changeModifiedDate(formatter.format(new Date())))
                .map(s -> {
                    Data data = null;
                    try {
                        data = s.changeCreatedDateToUTC(getIsoFormat().format(formatter.parse(s.getDateCreated())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return data;
                })
                .collect(Collectors.toList());

        DataTable modifiedDataTable = new DataTable(dataTable.getHeader(), result);

        if(isOutputAsHtml)
            printAsHtml(modifiedDataTable);
        else
            printAsString(modifiedDataTable);

    }

    private static DataTable readFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        if (lines.size() == 0) {
            return new DataTable(Collections.emptyList(), Collections.emptyList());
        }

       List<String> columnNames =
               Arrays.asList("cust_no", "activated" ,"email_optout" ,"phone_optout" ,"fax_optout" ,"date_created", "date_modified");


        List<Data> dataList = lines.stream().skip(1)
                .map(line -> line.split(TAB))
                .filter(line-> lines.size()>=columnNames.size()) //if the file contains header and body with spaces
                .map(line -> {
                    Data data = null;
                    try {
                        data = DataMapper.mapData(line);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        return null;
                    }
                    return data;

                }).collect(Collectors.toList());

        DataTable dataTable = new DataTable(columnNames, dataList);
        return dataTable;
    }

}
