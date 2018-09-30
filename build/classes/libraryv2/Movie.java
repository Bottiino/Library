package libraryv2;

public class Movie extends Item
{    
    /***************************************************************************
                                     Fields
    ***************************************************************************/
    private String director;
    private int budget;
    private double length; 
    
    /***************************************************************************
                                    Constructors
    ***************************************************************************/
    //Constructor for new item
    public Movie(String category, String title, String genre, int releaseYear, double rating, String director, int budget, double length)
    {
        super(category, title, genre, releaseYear, rating);
        this.director = director;
        this.budget = budget;
        this.length = length;
    }
    public Movie(int id, String category, String title, String genre, int releaseYear, double rating, String director, int budget, double length)
    {
        super(id, category, title, genre, releaseYear, rating);
        this.director = director;
        this.budget = budget;
        this.length = length;
    }
    //Constructors copy
    public Movie(Movie original)
    {
        super(original);
        this.director = original.getDirector();
        this.budget = original.getBudget();
        this.length = original.getLength();
    }  
    public Movie(int id, Movie original)
    {
        super(id, original);
        this.director = original.getDirector();
        this.budget = original.getBudget();
        this.length = original.getLength();
    }  
    public Movie()
    {}
    
    /***************************************************************************
                                    Getters
    ***************************************************************************/
    public String getDirector()
    {
        return director;
    }
    public int getBudget()
    {
        return budget;
    }
    public double getLength()
    {
        return length;
    }
    
    /***************************************************************************
                                     Setters
    ***************************************************************************/
    public void setDirector(String director)
    {
        this.director = director;
    }
    public void setBudget(int budget)
    {
        this.budget = budget;
    }
    public void setLength(double length)
    {
        this.length = length;
    }
    
    /***************************************************************************
                                     Other
    ***************************************************************************/
    @Override
    public String toString()
    {
        return super.toString() + String.format("%-23s %-29d %.2f" , this.director , this.budget , this.length);
    }
    
}
