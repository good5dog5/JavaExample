package com.Jordan.Example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Map;

public class SearchExample {

    public static void main(String[] argv) {
        long start = System.currentTimeMillis();
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("es.qa.sis.ai", 9200, "http")));
        long end = System.currentTimeMillis();
        System.out.println("connection time: " + (end - start));
        try {
            start = System.currentTimeMillis();
            getData(client);
            client.close();
            end = System.currentTimeMillis();
            System.out.println("find time: " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(RestHighLevelClient client) {
        try {
            SearchResponse response = client.search(getRequest("583", "TCustNo00000001"), RequestOptions.DEFAULT);

            SearchHits hits = response.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                String id = hit.getId();
                System.out.println("hit id: " + id);
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Integer dataFrameId = (Integer) sourceAsMap.get("data_frame_id");
                System.out.println("dataFrameId: " + dataFrameId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SearchRequest getRequest(String dataSourceId, String value) {
        SearchRequest request = new SearchRequest(dataSourceId);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("value", value);
        searchSourceBuilder.query(matchQueryBuilder);
        String[] includeFields = new String[] {"data_frame_id"};
        String[] excludeFields = new String[] {"value"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        request.source(searchSourceBuilder);

        return request;
    }

}
