package com.github.yaroslavdigis;

import com.github.yaroslavdigis.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByIid(String iid);
}