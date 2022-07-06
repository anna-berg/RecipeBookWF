package com.berg.dto.user;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Value
public class UserPrincipleDto extends User {

     Long id;

     public UserPrincipleDto(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
          super(username, password, authorities);
          this.id = id;
     }
}
