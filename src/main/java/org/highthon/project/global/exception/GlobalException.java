package org.highthon.project.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.highthon.project.global.exception.ErrorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
  private final ErrorCode errorCode;
}
