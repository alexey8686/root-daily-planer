package com.bae.spb.repository;

import com.bae.spb.model.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
