package org.highthon.project.domain.service.dream;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.entity.dream.Dream;
import org.highthon.project.domain.entity.user.User;
import org.highthon.project.domain.presentation.dream.dto.request.DreamSaveRequest;
import org.highthon.project.domain.repository.dream.DreamRepository;
import org.highthon.project.domain.repository.user.UserRepository;
import org.highthon.project.global.exception.ErrorCode.ErrorCode;
import org.highthon.project.global.exception.GlobalException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DreamService {
  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;

  @Transactional
  public void save(DreamSaveRequest request) {
    String userName = request.getUserName();

    User user = userRepository.findByUserName(userName);

    if (user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }

    Dream dream = request.toEntity();
    user.addDream(dream);
  }

  @Transactional(readOnly = true)
  public List<Dream> getDreams(Long userId) {
    List<Dream> dreamList = dreamRepository.findDreamsByUserId(userId);
    System.out.println("dreamList = " + dreamList);
    return dreamList;
  }
}
