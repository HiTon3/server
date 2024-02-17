package org.highthon.project.domain.presentation.dto.response;

public interface OAuth2Response {
  String getProvider();
  String getProviderId();
  String getEmail();
  String getName();
}
