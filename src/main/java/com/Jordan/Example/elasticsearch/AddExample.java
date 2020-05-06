package com.Jordan.Example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AddExample {

    public static void main(String[] argv) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        try {
            putData(client);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putData(RestHighLevelClient client) {
        try {
            client.index(insertValue("1",  "1","1", Arrays.asList("Shark", "Howard", "George", "Proxy")), RequestOptions.DEFAULT);
            client.index(insertValue("1", "2", "2", Arrays.asList("Christan", "Proxy")), RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexRequest insertValue(String dataSourceId, String dataFrameId, String dataColumnId,  List<String> valueList) {
        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("data_frame_id", dataFrameId);
        jsonMap.put("values", valueList);
        IndexRequest indexRequest = new IndexRequest(dataSourceId)
                .id(dataColumnId).source(jsonMap);
        return indexRequest;
    }

}
