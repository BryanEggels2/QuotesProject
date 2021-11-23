package com.kabisa.quotes.repositories;

import com.kabisa.quotes.services.Service;
import com.kabisa.quotes.services.implementations.QuoteApiService;
import com.kabisa.quotes.services.implementations.QuoteMockService;

public class Repository {
    private static QuoteRepository quoteRepository;

    /**
     * Returns the result repo, even if you somehow forget to initialize the repository
     * @return result repository with the desired service
     */
    public static QuoteRepository getQuoteRepository() {
        if (quoteRepository == null) {
            initialize(Service.Mock);
        }
        return quoteRepository;
    }

    /**
     * This method is for initializing all the repositories of one kind.
     * @param service: define the kind of service you want to use
     */
    static public void initialize(Service service) {
        switch (service) {
            case Api:
                quoteRepository = new QuoteRepository(new QuoteApiService());
                break;
            case Mock:
                quoteRepository = new QuoteRepository(new QuoteMockService());
                break;
        }
    }
}
