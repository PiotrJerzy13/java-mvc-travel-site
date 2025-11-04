package com.piotrwojnarowski.converter;

import com.piotrwojnarowski.domain.Category;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(MappingContext<String, Category> context) {

        for (Category category : Category.values()) {
            if (category.getName().equals(context.getSource())) {
                return category;
            }
        }
        throw new IllegalArgumentException("Can not convert " + context.getSource() + " to Category");
    }
}
