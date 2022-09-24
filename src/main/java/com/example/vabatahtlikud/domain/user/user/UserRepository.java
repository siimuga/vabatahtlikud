package com.example.vabatahtlikud.domain.user.user;

import com.example.vabatahtlikud.domain.user.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1 and u.password = ?2")
    User findUserBy(String username, String password);

    @Query("select (count(u) > 0) from User u where u.username = ?1 and u.password = ?2")
    boolean existsBy(String username, String password);

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);

    @Query("select count(u) from User u where u.username = ?1")
    long countByUsername(String username);

    @Query("select u from User u where u.id > ?1 order by u.username")
    List<User> findAllUsers(Integer id);




}