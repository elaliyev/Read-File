package com.example;

import com.example.pojo.DataTable;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Util {

    public static String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
    public static String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    public static String PATH = "example.txt";
    public static String TAB = "\t";
    public static String NEW_LINE = "\n";


    public static SimpleDateFormat getIsoFormat() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat isoFormatter = new SimpleDateFormat(ISO_FORMAT);
        isoFormatter.setTimeZone(utc);
        return isoFormatter;
    }

    public static void printAsString(DataTable modifiedDataTable) {
        modifiedDataTable.getHeader().forEach(h->System.out.print(String.format("%-"+h.length()*2 +"s",h)));
        System.out.println();
        modifiedDataTable.getData().forEach(d->System.out.println(d));
    }

    public static void printAsHtml(DataTable modifiedDataTable) {
        StringBuilder stringBuilder = new StringBuilder("<html>").append(NEW_LINE);
        stringBuilder.append("<body>").append(NEW_LINE);
        stringBuilder.append("<table cellspacing='5' cellpadding='5' border='1'>").append(NEW_LINE);
        stringBuilder.append("<thead>").append(NEW_LINE);
        stringBuilder.append("<tr>").append(NEW_LINE);
        modifiedDataTable.getHeader().forEach(h->stringBuilder.append(TAB).append("<th>").append(h).append("</th>").append(NEW_LINE));
        stringBuilder.append("</tr>").append(NEW_LINE);
        stringBuilder.append("</thead>").append(NEW_LINE);
        stringBuilder.append("<tbody>").append(NEW_LINE);

        modifiedDataTable.getData().forEach(
                d-> {
                    stringBuilder.append("<tr>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getCustomerNo()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getActivated()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getEmailOutput()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getPhoneOutput()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getFaxOutput()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getDateCreated()).append("</td>").append(NEW_LINE);
                    stringBuilder.append(TAB).append("<td>").append(d.getDateModified()).append("</td>").append(NEW_LINE);
                    stringBuilder.append("</tr>").append(NEW_LINE);
                }

        );
        stringBuilder.append("</tr>").append(NEW_LINE);
        stringBuilder.append("</tbody>").append(NEW_LINE);
        stringBuilder.append("</table>").append(NEW_LINE);
        stringBuilder.append("</html>");
        System.out.println(stringBuilder.toString());
    }
}
