package com.Jordan.Example.parserSentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Segment {
    private HashMap<String, List<TokenType>> tokenHashMap = new HashMap();
    private int maxTokenLength = 0;

    public void addTokenList(List<String> tokenList, TokenType tokenType) {
        for(String token: tokenList) {
            List<TokenType> tokenTypeList = tokenHashMap.get(token);
            if(tokenTypeList != null) {
                if(!tokenTypeList.contains(tokenType)) {
                    tokenTypeList.add(tokenType);
                }
            } else {
                tokenTypeList = new ArrayList<TokenType>();
                tokenTypeList.add(tokenType);
                this.maxTokenLength = Math.max(this.maxTokenLength, token.length());
                tokenHashMap.put(token, tokenTypeList);
            }
        }
    }

    public List<TokenEntity> segment(String sentence) {
        List<TokenEntity> tokenEntityList = new ArrayList<TokenEntity>();
        int unknownIndex = 0;
        int startIndex = 0;
        int endIndexOffset = 1;
        while (startIndex != sentence.length()) {
            while (endIndexOffset <= this.maxTokenLength &&
                    (startIndex + endIndexOffset) <= sentence.length() ) {
                String token = sentence.substring(startIndex, startIndex + endIndexOffset);
                if(tokenHashMap.containsKey(token)) {
                    if(unknownIndex < startIndex) {
                        String unknown = sentence.substring(unknownIndex, startIndex);
                        TokenEntity tokenEntity =
                                TokenEntity.builder().token(unknown).type(TokenType.UNKNOWN).build();
                        tokenEntityList.add(tokenEntity);
                    }
                    for(TokenType tokenType: tokenHashMap.get(token)) {
                        TokenEntity tokenEntity = TokenEntity.builder().token(token).type(tokenType).build();
                        tokenEntityList.add(tokenEntity);
                    }
                    unknownIndex = startIndex + endIndexOffset;
                }
                endIndexOffset = endIndexOffset + 1;
            }
            startIndex = startIndex + 1;
            endIndexOffset = 1;
        }
        if(unknownIndex + endIndexOffset < sentence.length()) {
            String unknown = sentence.substring(unknownIndex);
            TokenEntity tokenEntity =
                    TokenEntity.builder().token(unknown).type(TokenType.UNKNOWN).build();
            tokenEntityList.add(tokenEntity);
        }
        return tokenEntityList;
    }
}
