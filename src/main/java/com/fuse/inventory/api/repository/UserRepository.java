package com.fuse.inventory.api.repository;

import com.fuse.inventory.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where id=:id", nativeQuery = true)
    User findUserById(@Param("id") int id);

}

