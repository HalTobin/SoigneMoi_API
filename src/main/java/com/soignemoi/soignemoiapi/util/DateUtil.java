package com.soignemoi.soignemoiapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date formatDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(dateString); // This will print the parsed Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new ParseException(String.format("Could not parse %s", dateString), e.getErrorOffset());
        }
    }

}
