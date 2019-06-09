package xyz.thegrapevine.jnewsapi;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import xyz.thegrapevine.jnewsapi.exceptions.NewsAPIException;
import xyz.thegrapevine.jnewsapi.objects.HTTPParameter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xyz.thegrapevine.jnewsapi.responses.*;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NewsClient {

    private OkHttpClient client;
    private String key;

    /**
     * Instantiates the news client.
     *
     * @param key Token to connect to the API with.
     * @author @rainestormee
     */
    public NewsClient(String key) {
        client = new OkHttpClient();
        this.key = key;
    }

    /**
     * Gets the top headlines of the day.
     * @param args Array of parameters to pass to the client.
     * @return A {@link TopHeadlinesResponse} object.
     * @throws NewsAPIException A custom exception for the client.
     * @author @rainestormee
     */
    public TopHeadlinesResponse topHeadlines(HTTPParameter... args) throws NewsAPIException {
        return get(TopHeadlinesResponse.class, "top-headlines", args);
    }

    /**
     * Returns a detailed list of sources.
     * @param args Array of parameters to pass to the client.
     * @return A {@link SourcesResponse} object.
     * @throws NewsAPIException A custom exception for the client.
     * @author @rainestormee
     */
    public SourcesResponse sources(HTTPParameter... args) throws NewsAPIException {
        return get(SourcesResponse.class, "sources", args);
    }

    /**
     * Returns everything related to a query.
     * @param args Array of parameters to pass to the client.
     * @return A {@link EverythingResponse} object.
     * @throws NewsAPIException A custom exception for the client.
     * @author @rainestormee
     */
    public EverythingResponse everything(HTTPParameter... args) throws NewsAPIException {
       return get(EverythingResponse.class, "everything", args);
    }

    private <T extends NewsResponse> T get(Class<T> type, String endpoint, HTTPParameter... args) throws NewsAPIException {
        Request request = new Request.Builder()
                .url(getRequestUrl(endpoint, args))
                .addHeader("X-Api-Key", key)
                .addHeader("Content-Type", "application/json; charset=utf8;")
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String raw = response.body().string();
            try {
                T parsedResponse = new Gson().fromJson(raw, type);
                if (parsedResponse.getStatus().equalsIgnoreCase("error")) throw new NewsAPIException("Error Message Returned");
                return parsedResponse;
            } catch (NewsAPIException e1) {
                ErrorResponse er = new Gson().fromJson(raw, ErrorResponse.class);
                throw new NewsAPIException(er.getCode() + ": " + er.getMessage());
            }
        } catch (NullPointerException | JsonParseException e1) {
            throw new NewsAPIException("Malformed response received from server.");
        } catch (Exception e2) {
            throw new NewsAPIException(e2.getMessage());
        }
    }

    private String parseArgs(HTTPParameter... args) {
        return Arrays.stream(args).map(arg -> (URLEncoder.encode(arg.getKey(), UTF_8) + "=" + URLEncoder.encode(arg.getValue(), UTF_8))).collect(Collectors.joining("&", "?", ""));
    }

    private String getRequestUrl(String endpoint, HTTPParameter... args) throws NewsAPIException {
        try {
            return "https://newsapi.org/v2/" + endpoint + (args == null ? "" : parseArgs(args));
        } catch (Exception e) {
            throw new NewsAPIException("Error formatting the request URL.");
        }
    }
}