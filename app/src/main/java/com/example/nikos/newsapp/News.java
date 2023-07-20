package com.example.nikos.newsapp;


/**
 * {@link News} represents a news article which will be shown in the news_list layout.
 * It contains a title, the news section, the web URL as well as the date and time it was
 * published.
 * */

public class News {

    /** The article's title */
    private final String mTitle;

    /**The article's Author*/
    private final String mAuthors;

    /** The section the article belongs to */
    private final String mSectionName;

    /** Url for the article's webpage */
    private final String mUrl;

    /** Publication date and time of the article */
    private final String mPublicationDate;

    /**
     * Create a new NewsItem object
     * @param title is the article's title
     * @param authors is the article's title
     * @param sectionName is the section the article belongs to
     * @param url is the article's webpage Url
     * @param publicationDate is the article's publication date
     */
    public News(String title,String authors, String sectionName, String url, String publicationDate) {
        mTitle = title;
        mAuthors = authors;
        mSectionName = sectionName;
        mUrl = url;
        mPublicationDate = publicationDate;
    }
//Getters
    public String getTitle() {
        return mTitle;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }
}
