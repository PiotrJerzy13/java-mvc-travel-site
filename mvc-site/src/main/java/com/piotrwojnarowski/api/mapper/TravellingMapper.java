package com.piotrwojnarowski.api.mapper;

import com.piotrwojnarowski.api.dto.*;
import com.piotrwojnarowski.domain.Travelling;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TravellingMapper {

    // Entity -> DTOs
    @Mapping(target = "destination",  source = "name")
    @Mapping(target = "date",         source = "visitedAt")
    @Mapping(target = "description",  source = "review")
    @Mapping(target = "ratingSummary", source = "rating")
    TravellingDetailDTO toDetailDto(Travelling entity);

    @Mapping(target = "destination",  source = "name")
    @Mapping(target = "date",         source = "visitedAt")
    @Mapping(target = "ratingSummary", source = "rating")
    TravellingListDTO toListDto(Travelling entity);

    // Create DTO -> Entity
    @Mapping(target = "name",      source = "destination")
    @Mapping(target = "visitedAt", source = "date")
    @Mapping(target = "review",    source = "description")
    @Mapping(target = "rating",    source = "rating")
    Travelling toEntity(CreateTravellingRequest request);

    // Full update (PUT): overwrite fields, including nulls if they are null in request
    @Mapping(target = "name",      source = "destination")
    @Mapping(target = "visitedAt", source = "date")
    @Mapping(target = "review",    source = "description")
    @Mapping(target = "rating",    source = "rating")
    void updateEntity(@MappingTarget Travelling entity, UpdateTravellingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name",      source = "destination")
    @Mapping(target = "visitedAt", source = "date")
    @Mapping(target = "review",    source = "description")
    @Mapping(target = "rating",    source = "rating")
    void mergeEntity(@MappingTarget Travelling entity, UpdateTravellingRequest request);
}
