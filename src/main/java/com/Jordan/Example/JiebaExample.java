package com.Jordan.Example;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;

public class JiebaExample {
    public static void main(String[] argv) {
        JiebaSegmenter segmenter = new JiebaSegmenter();

//        String[] sentences = new String[]{"星際大戰很好看"};
        String[] sentences = new String[]{"鄉民老二有三十公分"};

        for (String sentence : sentences) {
            //斷詞
            System.out.println("===SegMode.INDEX===");
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }

        for (String sentence : sentences) {
            //斷詞
            System.out.println("===SegMode.SEARCH===");
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH).toString());
        }

        System.out.println("===沒有index的斷詞結果===");
        //使用INDEX模式斷詞，並且列出沒有index的斷詞結果
        List<SegToken> segmentedList = segmenter.process(sentences[0], JiebaSegmenter.SegMode.INDEX);
        for (int i = 0; i < segmentedList.size(); i++) {
            System.out.println(segmentedList.get(i).word);
        }
    }
}
