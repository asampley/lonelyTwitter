package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;

/**
 * Created by esports on 2/17/16.
 */
public class ElasticsearchTweetController {
    private static JestDroidClient client;

    //TODO: A function that gets tweets
    public static ArrayList<Tweet> getTweets() {
        verifyConfig();

        return null;
    }

    //TODO: A function that adds a tweet
    public static void addTweet(Tweet tweet) {
        verifyConfig();

        Index index = new Index.Builder(tweet).index("testing").type("tweet").build();

        try {
            DocumentResult execute = client.execute(index);
            if (execute.isSucceeded()) {
                tweet.setId(execute.getId());
            } else {
                Log.e("TODO", "Insert Tweet failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<NormalTweet>> {

        @Override
        protected ArrayList<NormalTweet> doInBackground(String... strings) {
            verifyConfig();

            ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();

            // A HUGE ASSUMPTION IS ABOUT TO BE MADE
            // ONLY ONE STRING
            String searchString = strings[0];

            Search search = new Search.Builder(searchString).addIndex("testing").addType("tweet").build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    tweets.addAll(result.getSourceAsObjectList(NormalTweet.class));
                } else {
                    Log.i("TODO", "Search was unsuccesful");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tweets;
        }
    }

    public static class AddTweetTask extends AsyncTask<NormalTweet, Void, Void> {
        @Override
        protected Void doInBackground(NormalTweet... tweets) {
            verifyConfig();

            for (NormalTweet tweet : tweets) {

                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();

                try {
                    DocumentResult execute = client.execute(index);
                    if (execute.isSucceeded()) {
                        tweet.setId(execute.getId());
                    } else {
                        Log.e("TODO", "Insert Tweet failed");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }
    }

    /**
     * Create a client, if one does not already exist
     */
    public static void verifyConfig() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
