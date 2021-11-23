package com.kabisa.quotes.services.interfaces;

import com.kabisa.quotes.models.Quote;
import java.util.List;

public interface QuoteServiceInterface {

    /**
     * Retrieve all the popular quotes from the service. This can be based on amount of likes or an external source.
     *
     * @return
     */
    List<Quote> getPopularQuotes();

    /**
     * Gets all available quotes from the service.
     *
     * @return
     */
    List<Quote> getAllQuotes();

    /**
     * Gets a single quote requested by ID
     *
     * @param id
     * @return quote
     */
    Quote getSingleQuote(int id);

    /**
     * Gets a random quote from the service.
     *
     * @return Quote object
     */
    Quote getRandomQuote();

    /**
     * Like a quote. This should return the amount of likes the quote currently has.
     *
     * @param id of the quote you with to like
     * @return amount of likes given to this specific quote.
     */
    int likeQuote(int id);

}
