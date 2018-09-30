package libraryv2;

import Utility.ScannerUtility;
import libraryv2.Sorts.SortByCategoryAsc;
import libraryv2.Sorts.SortByIdAsc;
import libraryv2.Sorts.SortByRatingDesc;
import libraryv2.Sorts.SortByTitleAsc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemStore 
{
    //Declaring variables
    private String storeName;
    private List<Item> itemList;
    
    //Constructor
    public ItemStore(String name) 
    {
        this.storeName = name;
        this.itemList = new ArrayList();
    }

    /***************************************************************************
                                Add Methods
    ***************************************************************************/
    public boolean add(Item i)
    {
        if (i != null) 
        {
            if(!this.itemList.contains(i)) 
            {
                if(i.getId() > 0) {
                    if (i instanceof Book) 
                    {
                        this.itemList.add(new Book((Book)i));
                    } 
                    else if (i instanceof Movie) 
                    {
                        this.itemList.add(new Movie((Movie)i));
                    } 
                    else 
                    {
                        return false;
                    }
                }
                else 
                {
                    int nextId = this.getNextId();
                    if (i instanceof Book) 
                    {
                        this.itemList.add(new Book(nextId, (Book)i));
                    } 
                    else if (i instanceof Movie)
                    {
                        this.itemList.add(new Movie(nextId, (Movie)i));
                    } else {
                        return false;
                    }
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public int getNextId()
    {
        int id = -1;
        
        for (Item i : this.itemList) 
        {
            if (i.getId() > id) 
            {
                id = i.getId();
            }
        }
        
        return id + 1;
    }   
    
    /***************************************************************************
                                Edit Methods
    ***************************************************************************/
    public void edit()
    {
        System.out.println();
        System.out.println("What category do you want to edit?");
        int choice = ScannerUtility.getInt("Press 1 for Book, Press 2 for Movie: ", 1, 2);
        String type;
        if(choice == 1)
            type = "book";
        else
            type = "movie";
        
        List o = searchAllItemsByCategory(type);
        System.out.println("\n");
        printItemTitle();
        printArrayList(o);

        System.out.println("\n");
        int id = ScannerUtility.getInt("Enter Id of person to edit:", 1, this.itemList.size());
        String newTitle = ScannerUtility.getString("Enter new title (or leave blank to skip): ");
        String newGenre = ScannerUtility.getString("Enter new genre (or leave blank to skip): ");
        int newReleaseYear = ScannerUtility.getInt("Enter new release year, 1900-2020 (-1 to skip): ", 1900, 2020);
        double newRating = ScannerUtility.getDouble("Enter new rating, 0-10 (-1 to skip): ", -1, 10);

        if(choice == 1)
        {          
            Book b = (Book)itemList.get(id-1);

            String newAuthor = ScannerUtility.getString("Enter new author (or leave blank to skip): ");
            String newPublisher = ScannerUtility.getString("Enter new publisher (or leave blank to skip): ");
            int newPageCount = ScannerUtility.getInt("Enter new page count, 10-4000 (-1 to skip): ", 10, 4000);  

            newTitle = (newTitle.length() == 0) ? b.getTitle() : newTitle;
            newGenre = (newGenre.length() == 0) ? b.getGenre() : newGenre;
            newReleaseYear = (newReleaseYear == -1) ? b.getReleaseYear() : newReleaseYear;
            newRating = (newRating == -1) ? b.getRating() : newRating;
            newAuthor = (newAuthor.length() == 0) ? b.getAuthor() : newAuthor;
            newPublisher = (newPublisher.length() == 0) ? b.getPublisher() : newPublisher;
            newPageCount = (newPageCount == -1) ? b.getPageCount() : newPageCount;

            b.setTitle(newTitle);
            b.setGenre(newGenre);
            b.setReleaseYear(newReleaseYear);
            b.setRating(newRating);            
            b.setAuthor(newAuthor);
            b.setPublisher(newPublisher);
            b.setPageCount(newPageCount);
        }
        else if(choice == 2)
        {
            Movie m = (Movie)itemList.get(id-1);

            String newDirector = ScannerUtility.getString("Enter new director (or leave blank to skip): ");
            int newBudget = ScannerUtility.getInt("Enter new budget (-1 to skip): ");
            double newLength = ScannerUtility.getDouble("Enter new length, 0-4 (-1 to skip): ");  

            newTitle = (newTitle.length() == 0) ? m.getTitle() : newTitle;
            newGenre = (newGenre.length() == 0) ? m.getGenre() : newGenre;
            newReleaseYear = (newReleaseYear == -1) ? m.getReleaseYear() : newReleaseYear;
            newRating = (newRating == -1) ? m.getRating() : newRating;
            newDirector = (newDirector.length() == 0) ? m.getDirector() : newDirector;
            newBudget = (newBudget == -1) ? m.getBudget() : newBudget;
            newLength = (newLength == -1) ? m.getLength() : newLength;

            m.setTitle(newTitle);
            m.setGenre(newGenre);
            m.setReleaseYear(newReleaseYear);
            m.setRating(newRating);            
            m.setDirector(newDirector);
            m.setBudget(newBudget);
            m.setLength(newLength);
        }
        System.out.println("Edit completed...");
    }
    
    /***************************************************************************
                                Search Methods
    ***************************************************************************/
    public List<Item> searchById(int id)
    {
        List<Item> list = new ArrayList<>();
        
        if (this.itemList.isEmpty()) 
        {
            return null;
        }

        for(Item i : itemList) 
        {
            if(i.getId() == id)
            list.add(i); 
        }
        
        if(!list.isEmpty())
        {
            return list;
        }
               
        return null;  
    }
    
    public List<Item> searchByTitle(String title)
    {
        List<Item> titleList = new ArrayList<>();
        
        if (this.itemList.isEmpty()) 
        {
            return null;
        }

        if ((title == null) || (title.length() == 0))
        {
            return null;
        }

        for( Item i : itemList )
        {
            if(i.getTitle().equalsIgnoreCase(title) )
            titleList.add(i); 
        }
        
        if(!titleList.isEmpty())
        {
            return titleList;
        }
               
        return null;  
    }
    
    public List<Item> searchAllItemsByCategory(String category)
    {
        List<Item> categoryList = new ArrayList<>();
        
        if (this.itemList.isEmpty()) 
        {
            return null;
        }

        if ((category == null) || (category.length() == 0)) {
            return null;
        }

        for(Item i : this.itemList)
        {
            if(i.getCategory().equalsIgnoreCase(category) )
            categoryList.add(i);
        }
        
        if(!categoryList.isEmpty())
        {
            return categoryList;
        }
               
        return null; 
    }   
    
    /***************************************************************************
                                Delete Method
    ***************************************************************************/
    public boolean removeById(int id)
    {
        if (this.itemList.isEmpty()) 
        {
            return false;
        }
        for(Item i : itemList)
        {
            if(i.getId() == id)
            {
                this.itemList.remove(i);
                return true;
            }            
        }          
        return false;
    }
    
    /***************************************************************************
                                Sort Methods
    ***************************************************************************/
    public List<Item> sortByCategory()
    {
        List<Item> temp = new ArrayList<>();        
        for(Item i : this.itemList)
        {
            temp.add(i);
        }        
        Collections.sort(temp, new SortByCategoryAsc());        
        return temp;
    }
    
    public List<Item> sortById()
    {
        List<Item> temp = new ArrayList<>();        
        for(Item i : this.itemList)
        {
            temp.add(i);
        }        
        Collections.sort(temp, new SortByIdAsc());
        return temp;
    }
    
    public List<Item> sortByTitle()
    {
        List<Item> temp = new ArrayList<>();        
        for(Item i : this.itemList)
        {
            temp.add(i);
        }        
        Collections.sort(temp, new SortByTitleAsc());        
        return temp;
    }
    
    public List<Item> sortByRating()
    {
        List<Item> temp = new ArrayList<>();        
        for(Item i : this.itemList)
        {
            temp.add(i);
        }        
        Collections.sort(temp, new SortByRatingDesc());        
        return temp;
    }
    
    /***************************************************************************
                                Extra Methods
    ***************************************************************************/
    public double getRatingAvg()
    {
        double rating = 0;
        for(Item i : this.itemList)
        {
            rating += i.getRating();
        }
        return rating/this.itemList.size();
    }
    
    public double averageRatingByType(int choice)
    {        
        double total = 0;
        int count = 0;
        double average;

        for (Item i : itemList)
        {
            if(choice == 1)
            {
                if(i.getCategory().equalsIgnoreCase("Book"))
                {
                    total += i.getRating();
                    count++;
                }
            }
            else
            {
                if(i.getCategory().equalsIgnoreCase("Movie"))
                {
                    total += i.getRating();
                    count++;
                }
            }
        }
        average = total / count;
        
        return average;
    }
    
    public double getPageAvg()
    {
        double pages = 0;
        for(Item i : this.itemList)
        {
            if(i.getCategory().equalsIgnoreCase("Book"))
            {
                Book b = (Book)i;
                pages += b.getPageCount();
            }
        }
        return pages/this.itemList.size();
    }
    
    public String[] getLongestMovie()
    {
        String[] arr = new String[2];
        double length = 0;
        String title = null;
        for(Item i : this.itemList)
        {
            if(i.getCategory().equalsIgnoreCase("Movie"))
            {
                Movie m = (Movie)i;
                if(m.getLength() > length)
                {
                    length = m.getLength();
                    title = m.getTitle();
                }
            }           
        }
        
        String length2 = "" + length;
        
        arr[0] = title;
        arr[1] = length2;
        
        return arr;
    }
    
    public String[] getEarliestMovie()
    {
        String[] arr = new String[2];
        int year = 4000;
        String title = null;
        for(Item i : this.itemList)
        {
            if(i.getCategory().equalsIgnoreCase("Movie"))
            {
                Movie m = (Movie)i;
                if(m.getReleaseYear() < year)
                {
                    year = m.getReleaseYear();
                    title = m.getTitle();
                }
            }           
        }
        
        String release = "" + year;
        
        arr[0] = title;
        arr[1] = release;
        
        return arr;
    }
    
    /***************************************************************************
                                Store Data
    ***************************************************************************/
    public void storeData(String filePath) throws IOException
    {
        File file = new File(filePath);
        try 
        {
            PrintWriter print = new PrintWriter(new FileOutputStream(file));
            FileOutputStream fo = new FileOutputStream(filePath);
            
            for (Item i: this.itemList)
            {
               
                if(i.getCategory().equalsIgnoreCase("Book"))
                {      
                    Book b = (Book)i;
                    
                    int id = b.getId();
                    String category = b.getCategory();
                    String title = b.getTitle();
                    String genre = b.getGenre();
                    int releaseYear = b.getReleaseYear();
                    double rating = b.getRating();
                    String author = b.getAuthor();
                    String publisher = b.getPublisher();
                    int pageCount = b.getPageCount();
                    
                    print.write(id + "," + category + "," + title + "," + genre + "," + releaseYear + "," + rating + "," + author + "," + publisher + "," + pageCount + "\r\n");
                }
                else
                {    
                    
                    Movie m = (Movie)i;
                    
                    int id = m.getId();
                    String category = m.getCategory();
                    String title = m.getTitle();
                    String genre = m.getGenre();
                    int releaseYear = m.getReleaseYear();
                    double rating = m.getRating();
                    String director = m.getDirector();
                    int budget = m.getBudget();
                    double length = m.getLength();
                    
                    print.write(id + "," + category + "," + title + "," + genre + "," + releaseYear + "," + rating + "," + director + "," + budget + "," + length + "\r\n");
                }                  
            } 
            print.close();
            fo.close();
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ItemStore.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    /***************************************************************************
                                Other Methods
    ***************************************************************************/  
    public int size()
    {
        if (this.itemList != null) 
        {
            return this.itemList.size();
        } 
        else 
        {
            return 0;
        }
    }

    public void print()
    {
        for(Item i : this.itemList)
        {
            System.out.println(i.toString());
        }
    }

    public List<Item> getAllPersons()
    {
        return Collections.unmodifiableList(itemList);
    }  
    
    public void printArrayList(List<Item> items)
    {
        for (Item i: items)
        {
            System.out.println(i);
        }
    }  
    
    public void printItemTitle()
    {
        System.out.println("ID \tCategory \tTitle \t\t\tGenre \t\t\tReleaseYear \tRating \t\tAuthor/Director \tPublisher/Budget \tPageCount/Length");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}