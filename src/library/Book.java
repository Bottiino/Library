package library;

public class Book extends Item
{    
    /***************************************************************************
                                     Fields
    ***************************************************************************/
    private String author;
    private String publisher;
    private int pageCount;
       
    /***************************************************************************
                                  Constructors
    ***************************************************************************/ 
    //Constructor for new item
    public Book(String category, String title, String genre, int releaseYear, double rating, String author, String publisher, int pageCount)
    {
        super(category, title, genre, releaseYear, rating);
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }
    public Book(int id, String category, String title, String genre, int releaseYear, double rating, String author, String publisher, int pageCount)
    {
        super(id, category, title, genre, releaseYear, rating);
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }
    //Constructors copy
    public Book(Book original)
    {
        super(original);
        this.author = original.getAuthor();
        this.publisher = original.getPublisher();
        this.pageCount = original.getPageCount();
    }
    public Book(int id, Book original)
    {
        super(id, original);
        this.author = original.getAuthor();
        this.publisher = original.getPublisher();
        this.pageCount = original.getPageCount();
    }
    public Book()
    {}    
    
    /***************************************************************************
                                     Getters
    ***************************************************************************/
    public String getAuthor()
    {
        return author;
    }
    public String getPublisher()
    {
        return publisher;
    }
    public int getPageCount()
    {
        return pageCount;
    }
    
    /***************************************************************************
                                     Setters
    ***************************************************************************/
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }    
    
    /***************************************************************************
                                     Other
    ***************************************************************************/
    @Override
    public String toString()
    {
        return super.toString() + String.format("%-23s %-29s %d" , this.author , this.publisher , this.pageCount);
    }
}
