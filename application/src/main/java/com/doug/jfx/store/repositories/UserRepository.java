package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.isActive = TRUE")
    @NotNull
    List<User> findAll();

    @Query("SELECT user FROM User user WHERE user.email = :username AND user.password = :password")
    UserDTO login(String username, String password);
}
