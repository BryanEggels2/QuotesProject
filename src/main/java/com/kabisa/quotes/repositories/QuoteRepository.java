package com.kabisa.quotes.repositories;

import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.services.interfaces.QuoteServiceInterface;
import java.util.List;

public class QuoteRepository implements QuoteServiceInterface {

    QuoteServiceInterface serviceInterface;

    /**
     * @param serviceInterface the service you wish to use for requesting quotes. This method is protected to only be able to use it
     *                         in the {@link Repository} class
     */
    protected QuoteRepository(QuoteServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    /**
     * Get popular quotes from the service.
     *
     * @return list of quotes, ordered by popularity (descending)
     */
    @Override
    public List<Quote> getPopularQuotes() {
        return serviceInterface.getPopularQuotes();
    }

    /**
     * @return All quotes from the service
     */
    @Override
    public List<Quote> getAllQuotes() {
        return serviceInterface.getAllQuotes();
    }

    /**
     * request a single quote with a specific id.
     *
     * @param id shouldn't be bigger then the size of the quotes list from the service. If it is, return null
     * @return quote or null depending if id is valid
     */
    @Override
    public Quote getSingleQuote(int id) {
        return id <= serviceInterface.getAllQuotes().size() && id > 0 ? serviceInterface.getSingleQuote(id) : null;
    }

    /**
     * @return a random quote from the service currently in use.
     */
    @Override
    public Quote getRandomQuote() {
        return serviceInterface.getRandomQuote();
    }

    /**
     * like a specific quote with the given id
     *
     * @param id of the quote you with to like
     * @return if id is bigger then the size of the source list, return 0
     */
    @Override
    public int likeQuote(int id) {
        return id <= serviceInterface.getAllQuotes().size() && id > 0 ? serviceInterface.likeQuote(id) : 0;
    }
}
