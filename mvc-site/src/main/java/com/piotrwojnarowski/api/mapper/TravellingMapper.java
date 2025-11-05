package com.piotrwojnarowski.api.mapper;

import com.piotrwojnarowski.api.dto.*;
import com.piotrwojnarowski.domain.Travelling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TravellingMapper {

    TravellingListDTO toListDto(Travelling entity);

    TravellingDetailDTO toDetailDto(Travelling entity);

    Travelling toEntity(CreateTravellingRequest request);

    Travelling toEntity(UpdateTravellingRequest request);
}
