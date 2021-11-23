package com.kabisa.quotes.services.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.services.interfaces.QuoteServiceInterface;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuoteApiService implements QuoteServiceInterface {

    List<Quote> allQuotes;

    /**
     * Gets the popular quotes based on likes. This piece of code could also be put in the repository for less redundancy.
     * It has been chosen not to do this, so you can easily switch to the external source and return the popular quotes from the API.
     *
     * @return
     */
    @Override
    public List<Quote> getPopularQuotes() {
        if (allQuotes == null) {
            getAllQuotes();
        }
        Collections.sort(allQuotes, Comparator
                .comparing(Quote::getLikes).reversed());
        // You can choose what you want here. Gather the popular quotes from the API or sort them on (local) likes.
        //return popularQuotes = getQuoteList("http://quotes.stormconsultancy.co.uk/popular.json");
        return allQuotes;

    }

    /**
     * Get all the quotes. First try to retrieve from memory, if thats not available, retrieve from external source.
     *
     * @return
     */
    @Override
    public List<Quote> getAllQuotes() {
        return allQuotes = (allQuotes == null) ? getQuoteList("http://quotes.stormconsultancy.co.uk/quotes.json") : allQuotes;
    }

    /**
     * gets a requested quote with the specified id
     *
     * @param id is the id of the quote you wish to retrieve.
     * @return requested quote
     */
    @Override
    public Quote getSingleQuote(int id) {
        //When the list of all quotes is empty, get the quote from the API. Else just get it from the internal list.
        return (allQuotes == null || allQuotes.size() == 0) ?
                getQuote("http://quotes.stormconsultancy.co.uk/quotes/" + id + ".json") :
                allQuotes.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);

    }

    /**
     * gets a random quote from the list of memory quotes. If that list is empty / null retrieve from the external source.
     *
     * @return
     */
    @Override
    public Quote getRandomQuote() {
        //When the list of all quotes is empty, get the quote from the API. Else just get a random quote from the internal list.
        //This is done by creating a new Random int based on the size of the quotes.It can never get a null reference because the quote list
        //can't be null when it reaches that statement.
        return (allQuotes == null || allQuotes.size() == 0) ?
                getQuote("http://quotes.stormconsultancy.co.uk/random.json") :
                allQuotes.stream().filter(a -> a.getId() == new Random(allQuotes.size()).nextInt()).collect(Collectors.toList()).get(0);

    }

    /**
     * @param id is the id of the quote you want to retrieve.
     * @return the amount of likes (if the quote id can be retrieved. if not, return 0)
     */
    @Override
    public int likeQuote(int id) {
        //for this method we need an internal list. later on we could create this list in a database or something.
        if (allQuotes == null) {
            getAllQuotes();
        }
        return (id <= allQuotes.size()) ? allQuotes.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0).like() : 0;

    }

    /**
     * Method created for simple re-use of getting a list of quotes an API.
     *
     * @param url is the link to the api
     * @return list of quotes or null if that's not possible.
     */
    private List<Quote> getQuoteList(String url) {
        try {
            URIBuilder builder = new URIBuilder(url);
            String json = Request.Get(builder.build())
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .execute().returnContent().asString();
            // Convert the request Json to a list using GSON. TypeToken is needed to convert it to a list.
            // Another way would be to retrieve an array and then convert it to a list. Both would work.
            return new Gson().fromJson(json, new TypeToken<List<Quote>>() {
            }.getType());
            //TODO: maybe return mock?
        } catch (IOException | URISyntaxException e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * Method created for simple re-use of getting a quote from an API. Uses Gson to 'decode' the json into an object.
     *
     * @param url of the api where a single quote can be found in json format
     * @return Quote or null of not possible
     */
    private Quote getQuote(String url) {
        try {
            URIBuilder builder = new URIBuilder(url);
            String json = Request.Get(builder.build())
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .execute().returnContent().asString();
            //return the quote object.
            return new Gson().fromJson(json, Quote.class);
            //TODO: maybe return mock?
        } catch (URISyntaxException | IOException e) {
            e.getMessage();
        }
        return null;
    }
}
