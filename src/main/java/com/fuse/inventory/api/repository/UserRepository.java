package com.fuse.inventory.api.repository;

import com.fuse.inventory.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
