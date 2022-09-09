package com.example.vabatahtlikud.user;

import com.example.vabatahtlikud.domain.user.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1 and u.password = ?2")
    User findUserBy(String username, String password);

    @Query("select (count(u) > 0) from User u where u.username = ?1 and u.password = ?2")
    boolean existsBy(String username, String password);


}