package com.kabisa.quotes.endpoints;

import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.repositories.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteController {
    /**
     * http://localhost:8080/quotes
     *
     * @return all the quotes available from the repository.
     */
    @GetMapping("/quotes")
    public List<Quote> quotes() {
        return Repository.getQuoteRepository().getAllQuotes();
    }

    /**
     * http://localhost:8080/quotes/popular
     *
     * @return all the popular quotes based on likes (at the moment.) This could also be changed to popularity of the api
     */
    @GetMapping("/quotes/popular")
    public List<Quote> popular() {
        return Repository.getQuoteRepository().getPopularQuotes();
    }

    /**
     * http://localhost:8080/quotes/random
     * return a random quote from the repository. This could be retrieved from the API or from the local memory at this moment.
     *
     * @return a random {@link Quote} object
     */
    @GetMapping("/quotes/random")
    public Quote random() {
        return Repository.getQuoteRepository().getRandomQuote();
    }

    /**
     * http://localhost:8080/quotes/{id}
     *
     * @param id of the quote wished to be retrieved.
     * @return The requested {@link Quote}.
     */
    @GetMapping("quotes/{id}")
    public Quote quote(@PathVariable int id) {
        return Repository.getQuoteRepository().getSingleQuote(id);
    }

    /**
     * http://localhost:8080/quotes/{id}/like
     *
     * @param id of the quote that is wished to be liked.
     * @return the amount of likes (included the one just given)
     */
    @PostMapping("quotes/{id}/like")
    public int likeQuote(@PathVariable int id) {
        return Repository.getQuoteRepository().likeQuote(id);
    }

}
