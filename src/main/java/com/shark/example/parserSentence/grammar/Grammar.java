package com.shark.example.parserSentence.grammar;

import com.shark.example.parserSentence.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Grammar {
    private List<List<String>> grammarList;
    private TokenType tokenType;
}
