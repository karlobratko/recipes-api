package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateCautionRequestDto;
import hr.kbratko.cookmate.model.Caution;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface CautionMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Caution updateCautionWithUpdateCautionRequestDto(@MappingTarget Caution healthLabel, UpdateCautionRequestDto requestDto);

}
