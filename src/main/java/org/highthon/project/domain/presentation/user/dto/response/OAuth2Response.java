package org.highthon.project.domain.presentation.user.dto.response;

public interface OAuth2Response {
  String getProvider();
  String getProviderId();
  String getEmail();
  String getName();
}
