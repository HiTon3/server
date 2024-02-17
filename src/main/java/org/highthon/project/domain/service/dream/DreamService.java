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

@Service
@RequiredArgsConstructor
public class DreamService {
  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;

  @Transactional
  public void save(DreamSaveRequest request) {
    String userId = String.valueOf(request.getUserName());

    User user = userRepository.findByUserName(userId);

    if (user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }

    Dream dream = request.toEntity();
    user.addDream(dream);
  }

  @Transactional(readOnly = true)
  public List<Dream> getDreams(String userName) {
    System.out.println("check1");
    User user = userRepository.findByUserName(userName);
    System.out.println("user = " + user);
    System.out.println("check2");

    if(user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }
    System.out.println("check3");

    List<Dream> dreamList = dreamRepository.findDreamByUserName(userName);

    System.out.println("check4");
    System.out.println("dreamList = " + dreamList);
    return dreamList;
  }
}
