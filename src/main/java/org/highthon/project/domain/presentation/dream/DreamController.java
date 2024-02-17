package org.highthon.project.domain.presentation.dream;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.entity.dream.Dream;
import org.highthon.project.domain.presentation.dream.dto.request.DreamSaveRequest;
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
  public List<Dream> read(@RequestParam("name") String userName) {
    List<Dream> dreamList = dreamService.getDreams(userName);
    return dreamList;
  }
}
