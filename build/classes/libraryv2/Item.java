package libraryv2;

public class Item implements Comparable<Item>
{
    //Class variable
    private static int idCount = 0;
    
    /***************************************************************************
                                    Fields
    ***************************************************************************/
    private int id;
    private String category;
    private String title, genre;
    private int releaseYear;
    private double rating;   
 
    /***************************************************************************
                                Constructors
    ***************************************************************************/
    //constructor for new Item
    public Item(String category, String title, String genre, int releaseYear, double rating)
    {
        this.id = -1;
        this.category = category;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
    public Item(int id, String category, String title, String genre, int releaseYear, double rating)
    {        
        this.id = id;
        this.category = category;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
    // Copy Constructor - used to clone an object
    public Item(Item original)
    {
        this.id = original.id;
        this.category = original.category;
        this.title = original.title;
        this.genre = original.genre;
        this.releaseYear = original.releaseYear;
        this.rating = original.rating;
    }
    public Item(int id, Item original)
    {
        this.id = id;
        this.category = original.category;
        this.title = original.title;
        this.genre = original.genre;
        this.releaseYear = original.releaseYear;
        this.rating = original.rating;
    }
    public Item()
    {}
             
    /***************************************************************************
                                    Getters
    ***************************************************************************/
    public static int getIdCount()
    {
        return idCount;
    }
    public String getCategory()
    {
        return category;
    }
    public String getTitle()
    {
        return title;
    }
    public String getGenre()
    {
        return genre;
    }
    public int getReleaseYear()
    {
        return releaseYear;
    }
    public double getRating()
    {
        return rating;
    }
    public int getId()
    {
        return id;
    }
    
    /***************************************************************************
                                    Setters
    ***************************************************************************/
    public static void setIdCount(int idCount)
    {
        Item.idCount = idCount;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public void setReleaseYear(int release)
    {
        this.releaseYear = release;
    }
    public void setRating(double rating)
    {
        this.rating = rating;
    } 

    /***************************************************************************
                                     Other
    ***************************************************************************/
    @Override
    public String toString() 
    {
        return String.format("%-7d %-15s %-23s %-26s %-13d %-15.1f" , this.id , this.category , this.title , this.genre , this.releaseYear , this.rating);   
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Item other = (Item) obj;
        if (this.id != other.id) 
        {
            return false;
        }
        return true;
    }       

    @Override
    public int compareTo(Item that)
    {
        if (this.getId() < that.getId())
        {
            return -1;
        }
        else if (this.getId() > that.getId())
        {
            return 1;
        }
        else
        {
            if (this.getCategory().compareTo(that.getCategory()) < 0)  
            {   
                return -1;
            } 
            else if (this.getCategory().compareTo(that.getCategory()) > 0)  
            {   
                return 1;
            }
            else 
            {
                if (this.getTitle().compareTo(that.getTitle()) < 0)
                {
                    return -1;
                }
                else if (this.getTitle().compareTo(that.getTitle()) > 0)
                {
                    return 1;
                }
            }
        }        
        return 0;
    }
}
