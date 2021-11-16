package com.bae.spb.util;

import com.bae.spb.model.Authority;
import com.bae.spb.model.Role;
import com.bae.spb.model.User;
import com.bae.spb.repository.AuthorityRepository;
import com.bae.spb.repository.UserRepository;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private PasswordEncoder encoder;
  @Override

  public void onApplicationEvent(ContextRefreshedEvent event) {
    authorityRepository.saveAll(
        Arrays.asList(new Authority(Role.USER), new Authority(Role.ADMIN),
            new Authority(Role.MODERATOR)));
    User admin = new User();
    admin.setId("617b661e4955bf4b194943a4");
    admin.setUserName("admin");
    admin.setEmail("admin@emai.ru");
    admin.setPassword(encoder.encode("admin"));
    admin.setAuthorities(Collections.singleton(
        authorityRepository.findOne(Example.of(new Authority(Role.ADMIN))).get()));
    userRepository.save(admin);
  }
}
