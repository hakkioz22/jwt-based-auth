package com.tpe.repository;

import com.tpe.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "roles")//on ManyToMany relation default fetch type is lazy, we must change to EAGER
    Optional<User> findByUserName(String userName);
}
