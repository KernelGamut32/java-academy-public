package gts.spring.registration.dto;

import gts.spring.registration.entity.Role;

import java.util.Set;

public record RegisterRequestDTO(String username, String password, Set<Role> roles) {
}
