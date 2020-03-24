package com.kekfeed.jwt;

import com.kekfeed.jwt.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByIid(String iid);
}