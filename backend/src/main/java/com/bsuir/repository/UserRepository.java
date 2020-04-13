package com.bsuir.repository;

import com.bsuir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    List<User> findByIduserIn(Collection<Long> idusers);

    List<User> findByAssignProject(Long assignProject);

    User findByLoginAndPassword(String login, String password);
}
