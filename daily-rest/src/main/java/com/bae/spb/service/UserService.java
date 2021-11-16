package com.bae.spb.service;

import com.bae.spb.daily.planner.dto.UserDto;

public interface UserService {

  UserDto saveOrUpdate(UserDto userDto);

}
