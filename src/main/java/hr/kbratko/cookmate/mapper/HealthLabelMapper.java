package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateHealthLabelRequestDto;
import hr.kbratko.cookmate.model.HealthLabel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface HealthLabelMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  HealthLabel updateHealthLabelWithUpdateHealthLabelRequestDto(@MappingTarget HealthLabel healthLabel, UpdateHealthLabelRequestDto requestDto);

}
