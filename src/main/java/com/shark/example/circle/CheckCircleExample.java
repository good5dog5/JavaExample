package com.shark.example.circle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CheckCircleExample {
    public static void main(String argv[]) {
        List<LinkItemEntity> linkItemEntityList = new ArrayList<>();
        LinkItemEntity link1 = new LinkItemEntity("b", "a");
        linkItemEntityList.add(link1);
        LinkItemEntity link2 = new LinkItemEntity("c", "a");
        linkItemEntityList.add(link2);
        LinkItemEntity link3 = new LinkItemEntity("b", "c");
        linkItemEntityList.add(link3);
        LinkItemEntity link4 = new LinkItemEntity("d", "a");
        linkItemEntityList.add(link4);
        LinkItemEntity link5 = new LinkItemEntity("e", "d");
        linkItemEntityList.add(link5);
        LinkItemEntity link6 = new LinkItemEntity("e", "b");
        linkItemEntityList.add(link6);
        LinkItemEntity link7 = new LinkItemEntity("a", "d");
        linkItemEntityList.add(link7);
//        LinkItemEntity link5 = new LinkItemEntity("a", "d");
//        linkItemEntityList.add(link5);
        checkCircle(linkItemEntityList);

        System.out.println(new Gson().toJson(linkItemEntityList));
    }

    private static void checkCircle(List<LinkItemEntity> linkItemEntityList) {
        for(int i = 0; i < linkItemEntityList.size(); i ++) {
            LinkItemEntity linkItemEntity = linkItemEntityList.get(i);
            if(linkItemEntity.isCircle()) {
                continue;
            }
            List<LinkItemEntity> circleLinkList = new ArrayList<>();
            circleLinkList.add(linkItemEntity);
            findCircle(circleLinkList, linkItemEntityList);
        }
    }

    private static void findCircle(List<LinkItemEntity> circleLinkList, List<LinkItemEntity> linkItemEntityList) {
        System.out.println("findCircle() circleLinkList: " + new Gson().toJson(circleLinkList));
        for(int i = 0; i < linkItemEntityList.size(); i ++) {
            for(int j = 0; j < linkItemEntityList.size(); j ++) {
                System.out.println("findCircle() circle list: " + new Gson().toJson(circleLinkList));
                LinkItemEntity linkItemEntity = linkItemEntityList.get(j);
                System.out.println("findCircle() item: " + new Gson().toJson(linkItemEntity));
                LinkItemEntity startLink = circleLinkList.get(0);
                String start = startLink.getSource();
                LinkItemEntity lastLink = circleLinkList.get(circleLinkList.size() - 1);
                String end = lastLink.getDestination();
                System.out.println("start: " + start + ", end: " + end);
                if(circleLinkList.contains(linkItemEntity)) {
                    System.out.println("contains");
                    continue;
                }
                //find
                System.out.println("firstCondition: " + (end.equalsIgnoreCase(linkItemEntity.getSource()) && start.equalsIgnoreCase(linkItemEntity.getDestination())));
                System.out.println("secondCondition: " + (end.equalsIgnoreCase(linkItemEntity.getDestination()) && start.equalsIgnoreCase(linkItemEntity.getSource())));
                if((end.equalsIgnoreCase(linkItemEntity.getSource()) && start.equalsIgnoreCase(linkItemEntity.getDestination())) ||
                        (end.equalsIgnoreCase(linkItemEntity.getDestination()) && start.equalsIgnoreCase(linkItemEntity.getSource()))) {
                    for(LinkItemEntity circleItem: circleLinkList) {
                        circleItem.setCircle(true);
                    }
                    linkItemEntity.setCircle(true);
                    System.out.println("findCircle() find circle");
                    System.out.println("findCircle() find circle");
                    if (lastLink.getDestination().equalsIgnoreCase(linkItemEntity.getSource())) {
                        System.out.println("findCircle() add item");
                        circleLinkList.add(linkItemEntity);
                    }
                } else if (lastLink.getDestination().equalsIgnoreCase(linkItemEntity.getSource())) {
                    //copy circle link
                    System.out.println("findCircle() add item");
                    circleLinkList.add(linkItemEntity);
                } else {
                    System.out.println("findCircle() not find");

                }
            }

        }
    }

    private static List<LinkItemEntity> copyList(List<LinkItemEntity> linkItemEntityList) {
        List<LinkItemEntity> newList = new ArrayList<>();
        for(LinkItemEntity linkItemEntity: linkItemEntityList) {
            newList.add(linkItemEntity);
        }
        return newList;
    }
}
