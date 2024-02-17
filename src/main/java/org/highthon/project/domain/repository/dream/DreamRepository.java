package org.highthon.project.domain.repository.dream;

import org.highthon.project.domain.entity.dream.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {

  @Query("SELECT d.text, d.image, d.video, d.category " +
    "FROM Dream d JOIN user u " +
    "WHERE u.userName = :userName AND d.userId.id = u.id")
  List<Dream> findDreamByUserName(@Param("userName") String userName);
}


