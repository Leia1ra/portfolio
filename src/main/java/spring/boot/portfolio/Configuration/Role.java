package spring.boot.portfolio.Configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum Role{
    ADMIN("ROLE_ADMIN");

    private final String Key;
}

