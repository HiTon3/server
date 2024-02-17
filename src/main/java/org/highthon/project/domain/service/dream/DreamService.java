package org.highthon.project.domain.service.dream;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.entity.dream.Dream;
import org.highthon.project.domain.entity.user.User;
import org.highthon.project.domain.presentation.dream.dto.request.DreamSaveRequest;
import org.highthon.project.domain.presentation.dream.dto.response.DreamResponse;
import org.highthon.project.domain.repository.dream.DreamRepository;
import org.highthon.project.domain.repository.user.UserRepository;
import org.highthon.project.global.exception.ErrorCode.ErrorCode;
import org.highthon.project.global.exception.GlobalException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
  public List<DreamResponse> getDreams(Long userId) { // Entity 타입을 리턴하는 것은 위험하기 때문에 Dto를 만든다
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));
    List<DreamResponse> dreamList = dreamRepository.findAllByUserId(user);
    System.out.println("dreamList = " + dreamList);
    List<DreamResponse> dreamResponseList = new ArrayList<>();

    for(DreamResponse dream : dreamList) {
      DreamResponse dto = DreamResponse.builder()
        .image(dream.getImage())
        .video(dream.getVideo())
        .text(dream.getText())
        .category(dream.getCategory())
        .build();

      dreamResponseList.add(dto);
    }
    return dreamResponseList;
  }
}
