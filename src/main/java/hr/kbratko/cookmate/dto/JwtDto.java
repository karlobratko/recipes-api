package hr.kbratko.cookmate.dto;

import java.util.List;

public record JwtDto(String username, List<String> authorities) {
}
