package com.kabisa.quotes.services.implementations;

import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.services.interfaces.QuoteServiceInterface;

import java.util.*;
import java.util.stream.Collectors;

public class QuoteMockService implements QuoteServiceInterface {

    private ArrayList<Quote> quotes;
    private Quote singleQuote;

    public QuoteMockService() {
        quotes = new ArrayList<>();

        Quote quote = new Quote();
        quote.setAuthor("Bryan");
        quote.setId(1);
        quote.setPermalink("Currently not available");
        quote.setQuote("Currently the quotes are not available");

        Quote quote2 = new Quote();
        quote2.setAuthor("Kabisa");
        quote2.setId(2);
        quote2.setPermalink("Currently not available");
        quote2.setQuote("Quotes should always be available");

        quotes.add(quote);
        quotes.add(quote2);

        singleQuote = quote;
    }

    @Override
    public List<Quote> getPopularQuotes() {
        // Sort the quotes list based on likes. The quotes with the most likes are in the top of the list.
        // to sort on ascending order, remove '.reversed()'
        Collections.sort(quotes, Comparator
                .comparing(Quote::getLikes).reversed());
        return quotes;

    }

    @Override
    public List<Quote> getAllQuotes() {
        return quotes;
    }

    @Override
    public Quote getSingleQuote(int id) {
        return quotes.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Quote getRandomQuote() {
        Random r = new Random();
        //The minimum random should be 1. So first we substract 1 from the max size and later we add it back.
        int random = r.nextInt(quotes.size() - 1) + 1;
        return quotes.stream().filter(a -> a.getId() == random).collect(Collectors.toList()).get(0);
    }

    @Override
    public int likeQuote(int id) {
        return quotes.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0).like();
    }
}
