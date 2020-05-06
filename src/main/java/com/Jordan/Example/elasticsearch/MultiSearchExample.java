package com.Jordan.Example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MultiSearchExample {

    public static void main(String[] argv) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("es.dev.sis.ai", 9200, "http")));
        try {
            getData(client, Arrays.asList("TCustNo00000001", "TCustNo00000002"));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(RestHighLevelClient client, List<String> valueList) {
        MultiSearchRequest request = new MultiSearchRequest();
        String[] includeFields = new String[] {"data_frame_id", "data_column_id"};
        String[] excludeFields = new String[] {"values"};
        for(String value: valueList) {
            SearchRequest searchRequest = new SearchRequest("43");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("value", value));
            searchSourceBuilder.fetchSource(includeFields, excludeFields);
            searchRequest.source(searchSourceBuilder);
            request.add(searchRequest);
        }
        try {
            MultiSearchResponse response = client.msearch(request, RequestOptions.DEFAULT);
            for(int i = 0; i < valueList.size(); i ++) {
                System.out.println("value: " + valueList.get(i));
                MultiSearchResponse.Item item = response.getResponses()[i];
                SearchResponse searchResponse = item.getResponse();
                long totalHits = searchResponse.getHits().getTotalHits().value;
                for(long j = 0; j < totalHits; j ++) {
                    Map<String, Object> map = searchResponse.getHits().getHits()[(int) j].getSourceAsMap();
                    String dataFrameId = String.valueOf(map.get("data_frame_id"));
                    String dataColumnId = String.valueOf(map.get("data_column_id"));
                    System.out.println("dataFrameId: " + dataFrameId);
                    System.out.println("dataColumnId: " + dataColumnId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
