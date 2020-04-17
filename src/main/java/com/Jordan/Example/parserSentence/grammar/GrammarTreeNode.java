package com.shark.example.parserSentence.grammar;

import com.shark.example.parserSentence.TokenEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GrammarTreeNode {
    private TokenEntity tokenEntity;
    private List<GrammarTreeNode> childList;
}
