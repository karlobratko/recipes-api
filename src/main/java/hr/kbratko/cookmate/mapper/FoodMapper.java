package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateFoodRequestDto;
import hr.kbratko.cookmate.model.Food;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface FoodMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Food updateFoodWithUpdateFoodRequestDto(@MappingTarget Food healthLabel, UpdateFoodRequestDto requestDto);

}
