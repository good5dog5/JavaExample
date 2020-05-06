package com.Jordan.Example.parserSentence.grammar;

import com.Jordan.Example.parserSentence.TokenEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GrammarTreeNode {
    private TokenEntity tokenEntity;
    private List<GrammarTreeNode> childList;
}
