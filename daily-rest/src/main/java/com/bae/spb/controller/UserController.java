package com.bae.spb.controller;

import com.bae.spb.daily.planner.dto.UserDto;
import com.bae.spb.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest-client", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/user/save", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> saveOrUpdateUser(@RequestBody @Valid UserDto userDto,
      Errors errors) {
    LOG.info("Save user with name - {}", userDto.getUserName());
    if (errors.hasErrors()) {
      List<ObjectError> allErrors = errors.getAllErrors();
      String validationConstraints = allErrors.stream().map(ObjectError::getDefaultMessage)
          .collect(Collectors.joining(", "));
      return ResponseEntity.badRequest().body(validationConstraints);
    }
    try {
      userService.saveOrUpdate(userDto);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      ResponseEntity.internalServerError().body("Error was happened while saving user");
    }
    return ResponseEntity.ok().body("User was saved");
  }
}
