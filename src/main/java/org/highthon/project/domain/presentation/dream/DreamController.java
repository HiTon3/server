package org.highthon.project.domain.presentation.dream;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.presentation.dream.dto.request.DreamSaveRequest;
import org.highthon.project.domain.presentation.dream.dto.response.DreamResponse;
import org.highthon.project.domain.service.dream.DreamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
  private final DreamService dreamService;

  @PostMapping("/save")
  public void save(@RequestBody DreamSaveRequest request) {
    dreamService.save(request);
  }

  @GetMapping("/read")
  public List<DreamResponse> read(@RequestParam("userId") Long userId) {
    List<DreamResponse> dreamList = dreamService.getDreams(userId);
    System.out.println("dreamList = " + dreamList);
    return dreamList;
  }

  @GetMapping("/all")
  public List<DreamResponse> read() {
    List<DreamResponse> dreamResponseList = dreamService.getList();
    return dreamResponseList;
  }
}
