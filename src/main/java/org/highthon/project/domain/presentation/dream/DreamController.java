package org.highthon.project.domain.presentation.dream;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.presentation.dream.dto.request.DreamSaveRequest;
import org.highthon.project.domain.service.dream.DreamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
  private final DreamService dreamService;
  @PostMapping("/save")
  public void save(@RequestBody DreamSaveRequest request) {
    dreamService.save(request);
  }
}
