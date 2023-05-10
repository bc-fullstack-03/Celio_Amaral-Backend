package com.sysmap.restApi.data;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sysmap.restApi.entities.User;

public interface UserRepository extends MongoRepository<User, UUID> {
    User findUserByEmail(String email);

    User findUserByName(String name);

    User deleteById(String id);

    boolean existsUserByEmail(String email);

    User findUserById(UUID id);
}
