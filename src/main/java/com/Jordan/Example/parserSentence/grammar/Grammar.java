package com.Jordan.Example.parserSentence.grammar;

import com.Jordan.Example.parserSentence.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Grammar {
    private List<List<String>> grammarList;
    private TokenType tokenType;
}
