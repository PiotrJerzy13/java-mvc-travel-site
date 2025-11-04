package com.piotrwojnarowski.converter;

import com.piotrwojnarowski.domain.Category;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CategoryToStringConverter implements Converter<Category, String> {
    @Override
    public String convert(MappingContext<Category, String> context) {
        return context.getSource().getName();
    }
}
