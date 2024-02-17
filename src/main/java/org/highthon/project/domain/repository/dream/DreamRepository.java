package org.highthon.project.domain.repository.dream;

import org.highthon.project.domain.entity.user.User;
import org.highthon.project.domain.presentation.dream.dto.response.DreamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.highthon.project.domain.entity.dream.Dream;

import java.util.List;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {
//  @Query("select d " +
//    "from Dream d " +
//    "where d.userId = :userId")
  List<DreamResponse> findAllByUserId(User userId);
}
