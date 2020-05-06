package com.Jordan.Example.parserSentence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.Jordan.Example.parserSentence.grammar.Grammar;
import com.Jordan.Example.parserSentence.grammar.GrammarParser;
import com.Jordan.Example.parserSentence.grammar.GrammarTreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SegmentExample {
    public static void main(String argv[]) {
        String sentence = "美國和中國和日本的收入比較";
        System.out.println("sentence: " + sentence);
        Segment segment = new Segment();
        segment.addTokenList(Arrays.asList("國家", "收入"), TokenType.COLUMN);
        segment.addTokenList(Arrays.asList("美國", "中國", "日本"), TokenType.VALUE);
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

        HashMap<String, Grammar> grammarHashMap = new HashMap<>();
        grammarHashMap.put("start", new Grammar(Arrays.asList(Arrays.asList("intent", "data"), Arrays.asList("data", "intent")), null));
        grammarHashMap.put("intent", new Grammar(null, TokenType.INTENT));
        grammarHashMap.put("conjunction", new Grammar(null, TokenType.CONJUNCTION));
        grammarHashMap.put("column", new Grammar(null, TokenType.COLUMN));
        grammarHashMap.put("value", new Grammar(null, TokenType.VALUE));
        grammarHashMap.put("values", new Grammar(Arrays.asList(Arrays.asList("value"), Arrays.asList("value", "conjunction", "values")), null ));
        grammarHashMap.put("data",
                new Grammar(Arrays.asList(Arrays.asList("values", "conjunction", "column")), null));

        Gson gson = new Gson();
        String grammarHashMapJson = gson.toJson(grammarHashMap);
        System.out.println("grammarHashMap: " + grammarHashMapJson);
        HashMap<String, Grammar> newGrammarHashMap = gson.fromJson(grammarHashMapJson, new TypeToken<HashMap<String, Grammar>>(){}.getType());

        GrammarParser grammarParser = new GrammarParser();
        GrammarTreeNode result = grammarParser.start(newGrammarHashMap, tokenEntityList);
        System.out.println("result: " + new Gson().toJson(result));


    }
}
