package xyz.thegrapevine.jnewsapi.responses;

import xyz.thegrapevine.jnewsapi.objects.NewsArticle;

import java.util.List;

/**
 * Response of the /everything endpoint
 * @author rainestormee
 */
public class EverythingResponse implements NewsResponse {

    private String status;
    private int totalResults;
    private List<NewsArticle> articles;

    public EverythingResponse() {
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }
}
