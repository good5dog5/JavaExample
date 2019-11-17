package com.shark.example.parser;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class SegmentExample {
    public static void main(String argv[]) {
        String sentence = "~!@#美國和中國家庭的總收入比較#@!~";
        System.out.println("sentence: " + sentence);
        Segment segment = new Segment();
        segment.addTokenList(Arrays.asList("國家", "收入"), TokenType.COLUMN);
        segment.addTokenList(Arrays.asList("美國", "中國"), TokenType.VALUE);
        segment.addTokenList(Arrays.asList("總"), TokenType.OPERATION);
        segment.addTokenList(Arrays.asList("比較"), TokenType.INTENT);
        segment.addTokenList(Arrays.asList("的", "和"), TokenType.CONJUNCTION);
        List<TokenEntity> tokenEntityList = segment.segment(sentence);
        for(TokenEntity tokenEntity: tokenEntityList) {
            System.out.println(new Gson().toJson(tokenEntity));
            if(tokenEntity.getType() == TokenType.UNKNOWN) {
                System.out.println("handle unknown token: " + tokenEntity.getToken());
            }
        }
    }
}
