package com.kabisa.quotes;

import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.repositories.Repository;
import com.kabisa.quotes.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuotesApiTests {

	@Test
	void getQuoteListSize(){
		assertNotNull(Repository.getQuoteRepository().getAllQuotes());
		assertNotNull(Repository.getQuoteRepository().getAllQuotes().get(0));
		assertNotNull(Repository.getQuoteRepository().getAllQuotes().get(1));
		assertNotNull(Repository.getQuoteRepository().getAllQuotes().get(0).getAuthor());
	}
	@Test
	void getOneQuote(){
		Quote singleQuote = new Quote();
		singleQuote.setAuthor("C. A. R. Hoare");
		singleQuote.setId(1);
		singleQuote.setQuote("We should forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil.");
		//the equals method in quote has been overridden.
		assertEquals(singleQuote,Repository.getQuoteRepository().getSingleQuote(1));
	}
	@Test
	void likeQuote(){
		//4 likes for 1st quote
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);

		//1 like for 2nd quote
		Repository.getQuoteRepository().likeQuote(2);

		//Also try to get quote id that is out of range. This should return 0;
		assertEquals(Repository.getQuoteRepository().likeQuote(Repository.getQuoteRepository().likeQuote(9999)), 0);

		//It should also be possible to insert negative numbers and not let the application crash.
		//This should return 0.
		assertEquals(Repository.getQuoteRepository().likeQuote(-2), 0);

		//the equals method in quote has been overridden.
		assertEquals(4, Repository.getQuoteRepository().getSingleQuote(1).getLikes());
		assertEquals(1, Repository.getQuoteRepository().getSingleQuote(2).getLikes());
		assertEquals(0, Repository.getQuoteRepository().getSingleQuote(3).getLikes());
	}

	@Test
	public void sortPopular() {
		//4 likes for 2nd quote
		Repository.getQuoteRepository().likeQuote(2);
		Repository.getQuoteRepository().likeQuote(2);
		Repository.getQuoteRepository().likeQuote(2);
		Repository.getQuoteRepository().likeQuote(2);

		//1 like for 1st quote
		Repository.getQuoteRepository().likeQuote(1);

		assertEquals(Repository.getQuoteRepository().getSingleQuote(2), Repository.getQuoteRepository().getPopularQuotes().get(0) );
		assertEquals(Repository.getQuoteRepository().getSingleQuote(1), Repository.getQuoteRepository().getPopularQuotes().get(1) );
	}
	@Test
	//check if this method doesn't crash
	public void randomQuote(){
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
		Repository.getQuoteRepository().getRandomQuote();
	}

	@BeforeEach
	void setUp() {
		Repository.initialize(Service.Api);
	}



}
