package com.bae.spb.service.impl;

import com.bae.spb.daily.planner.dto.UserDto;
import com.bae.spb.model.Authority;
import com.bae.spb.model.Role;
import com.bae.spb.model.User;
import com.bae.spb.repository.AuthorityRepository;
import com.bae.spb.repository.UserRepository;
import com.bae.spb.service.UserService;
import com.bae.spb.util.UserMapper;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Primary
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

  private final UserRepository userRepository;

  private final AuthorityRepository authorityRepository;

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      AuthorityRepository authorityRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.getUserByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }


  @Override
  public UserDto saveOrUpdate(UserDto userDto) {
    Assert.notNull(userDto, () -> "User have to be not null");
    return userMapper.mapDto(userRepository.save(fillUserAndGet(userDto)));
  }

  private User fillUserAndGet(UserDto userDto) {
    User user = userMapper.mapUser(userDto);
    user.setEnable(true);
    List<Authority> authorityList = authorityRepository.findAll();
    if (!authorityList.isEmpty()) {
      user.setAuthorities(Collections.singleton(
          authorityList.stream().filter(a -> Role.USER.equals(a.getRole())).findFirst()
              .orElseThrow(() -> new IllegalArgumentException("There is no role - USER in db"))));
    }

    return user;
  }


}
