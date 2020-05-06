package com.Jordan.Example.algorithm;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FindCircleExample {

    public static void main(String argv[]) {
        List<LinkItemEntity> linkItemEntityList = new ArrayList<>();
        LinkItemEntity link1 = new LinkItemEntity("a", "d");
        linkItemEntityList.add(link1);
        LinkItemEntity link2 = new LinkItemEntity("b", "a");
        linkItemEntityList.add(link2);
        LinkItemEntity link3 = new LinkItemEntity("b", "f");
        linkItemEntityList.add(link3);
        LinkItemEntity link4 = new LinkItemEntity("c", "a");
        linkItemEntityList.add(link4);
        LinkItemEntity link5 = new LinkItemEntity("c", "f");
        linkItemEntityList.add(link5);
        LinkItemEntity link6 = new LinkItemEntity("e", "b");
        linkItemEntityList.add(link6);
        checkCircle(linkItemEntityList);
        System.out.println(new Gson().toJson(linkItemEntityList));
    }

    private static void checkCircle(List<LinkItemEntity> linkItemEntityList) {
        for (int i = 0; i < linkItemEntityList.size(); i++) {
            LinkItemEntity linkItemEntity = linkItemEntityList.get(i);
            if(linkItemEntity.isCircle()) {
                continue;
            }
            List<String> nodeList = new ArrayList<>();
            List<LinkItemEntity> circleLinkList = new ArrayList<>();
            nodeList.add(linkItemEntity.getSource());
            circleLinkList.add(linkItemEntity);
            findCircle(nodeList, circleLinkList, linkItemEntityList, 0);
        }
    }

    private static void findCircle(List<String> nodeList, List<LinkItemEntity> circleLinkList, List<LinkItemEntity> linkItemEntityList, int depth) {
        if(depth > 5) {
            return;
        }
        for(int i = 0; i < linkItemEntityList.size(); i ++) {
            System.out.println("====================================================================");
            System.out.println("findCircle() circle list: " + new Gson().toJson(circleLinkList));
            System.out.println("findCircle() node list: " + new Gson().toJson(nodeList));
            LinkItemEntity linkItemEntity = linkItemEntityList.get(i);
            System.out.println("findCircle() item: " + new Gson().toJson(linkItemEntity));
            String start = nodeList.get(0);
            String end = nodeList.get(nodeList.size() -1);
            if (circleLinkList.contains(linkItemEntity)) {
                System.out.println("contains");
                continue;
            }
            //find
            System.out.println("firstCondition: " + (end.equalsIgnoreCase(linkItemEntity.getSource()) && start.equalsIgnoreCase(linkItemEntity.getDestination())));
            System.out.println("secondCondition: " + (end.equalsIgnoreCase(linkItemEntity.getDestination()) && start.equalsIgnoreCase(linkItemEntity.getSource())));
            if((end.equalsIgnoreCase(linkItemEntity.getSource()) && start.equalsIgnoreCase(linkItemEntity.getDestination())) ||
                    (end.equalsIgnoreCase(linkItemEntity.getDestination()) && start.equalsIgnoreCase(linkItemEntity.getSource()))) {
                for(LinkItemEntity circleItem: circleLinkList) {
                    if(nodeList.contains(circleItem.getSource()) &&
                            nodeList.contains(circleItem.getDestination())) {
                        circleItem.setCircle(true);
                    }
                }
                linkItemEntity.setCircle(true);
            } else if (end.equalsIgnoreCase(linkItemEntity.getSource()) && !nodeList.contains(linkItemEntity.getDestination())) {
                System.out.println("findCircle() add destination node");
                List<String> newNodeList = copyNodeList(nodeList);
                List<LinkItemEntity> newCircleLinkList = copyLinkList(circleLinkList);
                newNodeList.add(linkItemEntity.getDestination());
                newCircleLinkList.add(linkItemEntity);
                findCircle(newNodeList, newCircleLinkList, linkItemEntityList, (depth + 1));
            } else if(end.equalsIgnoreCase(linkItemEntity.getDestination()) && !nodeList.contains(linkItemEntity.getSource())) {
                System.out.println("findCircle() add source node");
                List<String> newNodeList = copyNodeList(nodeList);
                List<LinkItemEntity> newCircleLinkList = copyLinkList(circleLinkList);
                newNodeList.add(linkItemEntity.getSource());
                newCircleLinkList.add(linkItemEntity);
                findCircle(newNodeList, newCircleLinkList, linkItemEntityList, (depth + 1));
            } else {
                System.out.println("findCircle() not find");
            }
        }
    }

    private static List<String> copyNodeList(List<String> nodeList) {
        List<String> newNodeList = new ArrayList<>();
        for (String word : nodeList) {
            newNodeList.add(word);
        }
        return newNodeList;
    }

    private static List<LinkItemEntity> copyLinkList(List<LinkItemEntity> linkItemEntityList) {
        List<LinkItemEntity> newLinkList = new ArrayList<>();
        for (LinkItemEntity linkItemEntity : linkItemEntityList) {
            newLinkList.add(linkItemEntity);
        }
        return newLinkList;
    }
}
