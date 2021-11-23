package com.kabisa.quotes.models;

/**
 * Thin model of a Quote object. This object just consists of getters and setters withour any real logic.
 * Also contains an Equals method to compare Quote objects..
 */
public class Quote {
    private int id;
    private String author;
    private String quote;
    private String permalink;
    private int likes = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public int like() {
        likes++;
        return likes;
    }

    public int getLikes() {
        return likes;
    }

    /**
     * Thinking has been put into the order of the comparison. The chances of being equal get higher if the id matches.
     * If that's the case, also compare the rest of the quote object.
     * @param obj is compared to the current quote, based on id, quote, author and amount of likes.
     * @return true or false, based on outcome
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Quote other = (Quote) obj;
        //optimized the order of verifying this object.
        return other.getId() == this.getId() && this.getQuote().equals(other.getQuote()) && other.getAuthor().equals(this.author) && other.getLikes() == this.getLikes();
    }

    @Override
    public String toString() {
        return this.getId() + "\n" +
                this.getQuote() + "\n" +
                this.getAuthor();
    }
}
