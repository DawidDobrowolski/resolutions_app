package pl.resolutions.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;

public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return Date.valueOf(s);
    }
}
