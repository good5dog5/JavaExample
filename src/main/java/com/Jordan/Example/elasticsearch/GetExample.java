package com.Jordan.Example.elasticsearch;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

public class GetExample {

    public static void main(String[] argv) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        try {
            getData(client);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(RestHighLevelClient client) {
        try {
            GetResponse getResponse = client.get(getRequest("1", "1", "Proxy"), RequestOptions.DEFAULT);
            System.out.println(new Gson().toJson(getResponse.getSource()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GetRequest getRequest(String dataSourceId, String dataColumnId,  String value) {
        GetRequest request = new GetRequest(dataSourceId, dataColumnId);
        String[] includes = new String[]{"data_frame_id"};
        String[] excludes = new String[]{"values"};
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
//        request.routing(value);
        return request;
    }

}
