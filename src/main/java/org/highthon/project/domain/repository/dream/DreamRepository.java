package org.highthon.project.domain.repository.dream;

import org.highthon.project.domain.entity.dream.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {

}
