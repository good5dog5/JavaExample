package com.Jordan.Example.parserSentence;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenEntity {
    private String token;
    private TokenType type;
}
