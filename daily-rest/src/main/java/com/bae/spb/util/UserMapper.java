package com.bae.spb.util;

import com.bae.spb.daily.planner.dto.UserDto;
import com.bae.spb.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto mapDto (User user);

  User mapUser (UserDto dto);


}
