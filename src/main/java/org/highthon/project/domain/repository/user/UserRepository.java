package org.highthon.project.domain.repository.user;

import org.highthon.project.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

  User findByEmail(String email);
}
