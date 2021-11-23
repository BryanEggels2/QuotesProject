package com.kabisa.quotes;

import com.kabisa.quotes.models.Quote;
import com.kabisa.quotes.repositories.Repository;
import com.kabisa.quotes.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class QuotesMockTests {

	private List<Quote> mockQuotes;
	private Quote singleQuote;

	@Test
	void getQuoteListSize(){
		assertEquals(mockQuotes.size(),Repository.getQuoteRepository().getAllQuotes().size());
	}
	@Test
	void getOneQuote(){
		//the equals method in quote has been overridden.
		assertEquals(singleQuote, Repository.getQuoteRepository().getSingleQuote(1));
	}
	@Test
	void likeQuote(){
		Repository.initialize(Service.Mock);
		//4 likes for 1st quote
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);
		Repository.getQuoteRepository().likeQuote(1);

		//1 like for 2nd quote
		Repository.getQuoteRepository().likeQuote(2);

		//the equals method in quote has been overridden.
		assertEquals(4, Repository.getQuoteRepository().getSingleQuote(1).getLikes());
		assertEquals(1, Repository.getQuoteRepository().getSingleQuote(2).getLikes());
		//Also try to get quote id that is out of range. This should return 0;
		assertEquals(0, Repository.getQuoteRepository().likeQuote(3));
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
		Repository.initialize(Service.Mock);
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
		assertNotNull(Repository.getQuoteRepository().getRandomQuote());
	}


	/**
	 * Runs before each unit tests as a set up for all the in memory quotes.
	 */
	@BeforeEach
	void setUp() {
		Repository.initialize(Service.Mock);

		singleQuote = new Quote();
		singleQuote.setAuthor("Bryan");
		singleQuote.setId(1);
		singleQuote.setPermalink("Currently not available");
		singleQuote.setQuote("Currently the quotes are not available");

		Quote quote2 = new Quote();
		quote2.setAuthor("Kabisa");
		quote2.setId(2);
		quote2.setPermalink("Currently not available");
		quote2.setQuote("Quotes should always be available");
		mockQuotes = new ArrayList<>();
		mockQuotes.add(singleQuote);
		mockQuotes.add(quote2);
	}



}
