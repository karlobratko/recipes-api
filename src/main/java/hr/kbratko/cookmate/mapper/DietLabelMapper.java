package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateDietLabelRequestDto;
import hr.kbratko.cookmate.model.DietLabel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface DietLabelMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  DietLabel updateDietLabelWithUpdateDietLabelRequestDto(@MappingTarget DietLabel healthLabel, UpdateDietLabelRequestDto requestDto);

}
