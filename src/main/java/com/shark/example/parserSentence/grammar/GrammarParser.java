package com.shark.example.parserSentence.grammar;

import com.shark.example.parserSentence.TokenEntity;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrammarParser {

    private HashMap<String, Grammar> grammarHashMap;

    public GrammarTreeNode start(HashMap<String, Grammar> grammarHashMap, List<TokenEntity> tokenEntityList) {
        this.grammarHashMap = grammarHashMap;
        Grammar startGrammar = grammarHashMap.get("start");
        return parserGrammar(startGrammar, tokenEntityList, 0);
    }


    private GrammarTreeNode parserGrammar(Grammar grammar, List<TokenEntity> tokenEntityList, int index) {
        List<List<String>> childGrammarList = grammar.getGrammarList();
        if (childGrammarList != null && childGrammarList.size() > 0) {
            List<GrammarTreeNode> treeNodeList = null;
            for (List<String> subGrammarKeyList : childGrammarList) {
                //多個文法只會有一個符合情境
                List<GrammarTreeNode> subGrammarChildTreeNodeList = new ArrayList<>();
                int subGrammarIndex = index;
                for (String grammarKey: subGrammarKeyList) {
                    GrammarTreeNode grammarTreeNode = parserGrammar(grammarHashMap.get(grammarKey), tokenEntityList, subGrammarIndex);
                    if(grammarTreeNode == null) {
                        break;
                    }
                    subGrammarChildTreeNodeList.add(grammarTreeNode);
                    subGrammarIndex = subGrammarIndex + calculateGrammarTreeNodeTokenCount(grammarTreeNode);
                }
                if(subGrammarChildTreeNodeList.size() == subGrammarKeyList.size()) {
                    treeNodeList = subGrammarChildTreeNodeList;
                }
            }
            if (treeNodeList != null) {
                return GrammarTreeNode.builder().childList(treeNodeList).build();
            }
        } else {
            TokenEntity tokenEntity = tokenEntityList.get(index);
            if (tokenEntityList.get(index).getType() == grammar.getTokenType()) {
                return GrammarTreeNode.builder().tokenEntity(tokenEntity).build();
            }
        }
        return null;
    }

    private int calculateGrammarTreeNodeTokenCount(GrammarTreeNode grammarTreeNode) {
        if(grammarTreeNode.getChildList() != null) {
            int count = 0;
            for(GrammarTreeNode child: grammarTreeNode.getChildList()) {
                count = count + calculateGrammarTreeNodeTokenCount(child);
            }
            return count;
        } else {
            return 1;
        }
    }

    private List<Grammar> generateGrammarList(List<String> grammarKeyList) {
        List<Grammar> grammarList = new ArrayList<>();
        for (String key : grammarKeyList) {
            grammarList.add(this.grammarHashMap.get(key));
        }
        return grammarList;
    }
}
