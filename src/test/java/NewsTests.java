import xyz.thegrapevine.jnewsapi.NewsClient;
import xyz.thegrapevine.jnewsapi.exceptions.NewsAPIException;
import xyz.thegrapevine.jnewsapi.objects.HTTPParameter;
import xyz.thegrapevine.jnewsapi.objects.NewsArticle;
import org.junit.jupiter.api.Test;

public class NewsTests {

    private final NewsClient client = new NewsClient("bb554838724b452fb14adf8661be7646");

    @Test
    public void topHeadlinesTest() throws NewsAPIException {
        NewsArticle article = client.topHeadlines(new HTTPParameter("q", "bitcoin")).getArticles().get(0);
        System.out.println(article.getSource() + " " + article.getAuthor() + " " + article.getTitle());
        assert (article != null);
    }

    @Test
    public void everythingTest() throws NewsAPIException {
        assert (client.everything(new HTTPParameter("q", "android")).getArticles().get(0) != null);
    }

    @Test
    public void sourcesTest() throws NewsAPIException {
        assert (client.sources(new HTTPParameter("q", "cnn")).getSources().get(0) != null);
    }
}