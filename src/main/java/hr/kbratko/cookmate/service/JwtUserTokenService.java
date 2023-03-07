package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.model.User;

public interface JwtUserTokenService extends JwtTokenService<User> {

  String getUsername(String token);

}
