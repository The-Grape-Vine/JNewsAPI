package xyz.thegrapevine.jnewsapi.responses;

import xyz.thegrapevine.jnewsapi.objects.NewsSource;

import java.util.List;

public class SourcesResponse implements NewsResponse {

    private String status;
    private List<NewsSource> sources;

    public SourcesResponse() {

    }

    public String getStatus() {
        return status;
    }

    public List<NewsSource> getSources() {
        return sources;
    }
}
