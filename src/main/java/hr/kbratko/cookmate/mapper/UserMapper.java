package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateUserRequestDto;
import hr.kbratko.cookmate.model.Role;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.repository.RoleRepository;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring",
  uses = {
    PasswordEncoder.class,
    RoleRepository.class
  },
  injectionStrategy = InjectionStrategy.FIELD
)
public abstract class UserMapper {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepository roleRepository;

  @AfterMapping
  protected void encodePasswordIfNecessary(@MappingTarget User recipe, UpdateUserRequestDto requestDto) {
    if (Objects.isNull(requestDto.password()))
      return;

    recipe.setPassword(passwordEncoder.encode(requestDto.password()));
  }

  @Named("mapListOfIdsToSetOfRoles")
  protected Set<Role> mapListOfIdsToSetOfRoles(List<Long> value) {
    return Set.copyOf(roleRepository.findAllById(value));
  }

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(source = "roles", target = "roles", qualifiedByName = {"mapListOfIdsToSetOfRoles"})
  public abstract User updateUserWithUpdateUserRequestDto(@MappingTarget User recipe, UpdateUserRequestDto requestDto);

}
