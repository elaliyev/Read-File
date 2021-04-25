package com.example.pojo;

import java.util.Collections;
import java.util.List;

public class DataTable {

    private final List<String> header;
    private  final List<Data> data;

    public DataTable(List<String> header, List<Data> data) {
        this.header = header;
        this.data = data;
    }

    public List<String> getHeader() {
        return Collections.unmodifiableList(header);
    }

    public List<Data> getData() {
        return data;
    }

}
