package libraryv2;

import Utility.ScannerUtility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main 
{   
    private ItemStore itemStore;
    Scanner keyboard = new Scanner(System.in);
    
    // menu titles
    private final String[] mainMenuStringArray = 
    {"Load Data", "Show Item Menu", "Store Data", "Exit"};
    private final String[] ItemMenuStringArray = 
    {"Print All Items", "Search Menu", "Sort Menu", "Add Item", "Edit Item", "Delete Item", "Extra Menu", "Exit To Main Menu"};
    private final String[] searchMenuStringArray = 
    {"Search By Id", "Search By Title", "Search All By Category", "Exit To Item Menu"};
    private final String[] sortMenuStringArray = 
    {"Sort By Id", "Sort By Category", "Sort By Title", "Sort By Rating", "Exit To Item Menu"};
    private final String[] extraMenuStringArray = 
    {"Avg Rating", "Avg Rating(Category)",  "Avg PageCount", "Longest Movie", "Earliest Movie", "Exit To Item Menu"};
    
    public static void main(String[] args) throws IOException
    {
        Main theApp = new Main();
        theApp.start();
    }
     
    private void start() throws IOException
    {
        showMainMenu();
    }
    
    /***************************************************************************
                                Menu Methods
    ***************************************************************************/
    private void showMainMenu() throws IOException
    {
                
        int choice;
        String file;
        do {
            System.out.println("\n\n************************** My Library **************************");
            choice = showMenuGetChoice(mainMenuStringArray);
            switch (choice) 
            {
                case 1:
                    System.out.println("\n\n================================================================");
                    System.out.println("Loading...");
                    
                    int pick = ScannerUtility.getInt("Press 1 for Items\nPress 2 for Items2: ", 1, 2);
                    if(pick == 1)
                    {
                        file = "Items.csv";
                    }
                    else
                    {
                        file = "Items2.csv";
                    }
                    initialiseStores(file);
                    System.out.println("Load Complete");
                    System.out.println("================================================================");
                    break;
                case 2:
                    if(this.itemStore != null)
                    {
                        showItemMenu("Item Menu", ItemMenuStringArray);
                    }
                    else
                    {
                        printErrorMessage("Item Store is empty, please load some data!");
                    }                       
                    break;
                case 3:
                    if(this.itemStore != null)
                    {
                        System.out.println("\n\n================================================================");
                        System.out.println("Storing...");
                        itemStore.storeData("Items.csv");
                        System.out.println("Store Complete");
                        System.out.println("================================================================");
                    }
                    else
                    {
                        printErrorMessage("Item Store is empty, please load some data!");
                    }   
                    break;
                case 4:
                    System.out.println("\nGoodbye...");
                    break;
            }
        } while (choice != mainMenuStringArray.length);
    }   
    
    private int showMenuGetChoice(String[] menuOptions)
    {
        int index = 1;
        for (String menuOption : menuOptions) 
        {
            System.out.println("[" + index + "]" + menuOption);
            index++;
        }
        int choice = ScannerUtility.getInt("Enter choice: ", 0,menuOptions.length);

        return choice;
    }   
    
    private void showItemMenu(String menuHeader, String[] menuStringArray)
    {
        int choice;
        do {
            System.out.println("\n\n************************** " + menuHeader + "  **************************");
            choice = showMenuGetChoice(menuStringArray);
            switch (choice) 
            {
                case 1:
                    System.out.println("================================================================");
                    System.out.println("\t\t\tAll Items");
                    System.out.println("================================================================");
                    this.itemStore.printItemTitle();
                    this.itemStore.print();
                    break;
                case 2:
                    searchMenu("Search By", searchMenuStringArray);
                    break;
                case 3:
                    sortMenu("Sort By", sortMenuStringArray);
                    break;
                case 4:
                    addItem();
                    break;
                case 5:        
                    this.itemStore.edit();
                    break;
                case 6:
                    deleteItem();
                    break;
                case 7:
                    extraMenu("Extra Menu", extraMenuStringArray);
                    break;  
            }
        } while (choice != menuStringArray.length);
    }
    
    private void searchMenu(String menuHeader, String[] searchMenuStringArray)
    {
        int choice;
        do {
            System.out.println("\n\n*************************** " + menuHeader + " **************************");
            choice = showMenuGetChoice(searchMenuStringArray);

            switch (choice) 
            {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    int choice2 = ScannerUtility.getInt("Press 1 for Book, Press 2 for Movie: ", 1, 2);
                    searchAllByCategory(choice2);
                    break;
                default:
                    showItemMenu("Item Menu", ItemMenuStringArray);
                    break;
            }
        } while (choice != searchMenuStringArray.length);	
    }
    
    private void sortMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do {
            System.out.println("\n\n*************************** " + menuHeader + " **************************");        
            choice = showMenuGetChoice(sortMenuStringArray);
            
            List temp = null;
            switch (choice) 
            {
                case 1:        
                    temp = itemStore.sortById();
                    break;
                case 2:
                    temp = itemStore.sortByCategory();
                    break;
                case 3:
                    temp = itemStore.sortByTitle();
                    break;
                case 4:
                    temp = itemStore.sortByRating();
                    break;
            }
            
            if (temp != null)
            {
                System.out.println("\n\n");
                this.itemStore.printItemTitle();
                this.itemStore.printArrayList(temp);
            }
        } while (choice != sortMenuStringArray.length);	
    }
    
    private void extraMenu(String menuHeader, String[] extraMenuStringArray)
    {
        int choice;
        do {
            System.out.println("\n\n*************************** " + menuHeader + " **************************");
            choice = showMenuGetChoice(extraMenuStringArray);
            
            String[] arr = null;
            
            switch (choice) 
            {
                case 1:
                    printExtraTitle("Rating Average");
                    double rating = itemStore.getRatingAvg();
                    System.out.printf("%.2f \n", rating);
                    break;
                case 2:
                    int pick = ScannerUtility.getInt("Pick Category\nPress 1 for Book, Press 2 for movie:", 1, 2);
                    String word;
                    if(pick == 1)
                    {word = "Book";}
                    else{word = "Movie";}
                    double average = this.itemStore.averageRatingByType(pick);
                    printExtraTitle("Average Rating of " + word);
                    System.out.printf("%.2f \n", average);
                    break;
                case 3:
                    printExtraTitle("Page Count Average");
                    double pages = itemStore.getPageAvg();
                    System.out.printf("%.2f \n", pages);
                    break;                    
                case 4:
                    printExtraTitle("Longest Movie");
                    arr = this.itemStore.getLongestMovie();                    
                    break;
                case 5:
                    printExtraTitle("Earliest Movie");
                    arr = this.itemStore.getEarliestMovie();
                    break;
            }
            if(arr != null)
            {
                System.out.println(arr[0] + "  " + arr[1]);
            }            
        } while (choice != extraMenuStringArray.length);	
    }
    
    /***************************************************************************
                                Item Methods
    ***************************************************************************/
    private void addItem()
    {
        System.out.println();
        boolean bAdded;
        do {
            System.out.println("Please enter item Details");                  
            String category;
            int choice = ScannerUtility.getInt("Pick Category\nPress 1 for Book, Press 2 for movie:", 1, 2);
            if(choice == 1)
            {
                category = "Book";
            }
            else
            {
                category = "Movie";
            }
            String title = ScannerUtility.getString("Enter Title: ");
            String genre = ScannerUtility.getString("Enter Genre: ");
            int releaseYear = ScannerUtility.getInt("Enter Release Year (1900-2020): ", 1900, 2020);
            double rating = ScannerUtility.getDouble("Enter Rating (0-10): ", 0, 10);
            
            
            if(category.equalsIgnoreCase("Book"))
            {
                String author = ScannerUtility.getString("Enter Author: ");
                String publisher = ScannerUtility.getString("Enter Publisher: ");
                int pageCount = ScannerUtility.getInt("Enter Page Count (10-4000): ", 10, 4000);
                
                bAdded = itemStore.add(new Book(category,title,genre,releaseYear,rating,author,publisher,pageCount));
            }
            else if(category.equalsIgnoreCase("Movie"))
            {
                String director = ScannerUtility.getString("Enter Director: ");
                int budget = ScannerUtility.getInt("Enter Budget: ");
                double length = ScannerUtility.getInt("Enter Length (0-4): ", 0, 4);
                
                bAdded = itemStore.add(new Movie(category,title,genre,releaseYear,rating,director,budget,length));
            }
            else
            {
                bAdded = false;
            }                       

            if (bAdded) 
            {
                System.out.println("Add completed...");
            }
            else
            {
                System.out.println("Person alrady exists in the list. Try again.\n");
            }

        }while(!bAdded);
    }
    
    private void deleteItem()
    {
        System.out.println();
        this.itemStore.printItemTitle();
        this.itemStore.print();
        int id = ScannerUtility.getInt("Enter ID of item to delete(0 to skip):");

        if (id != 0) {
            boolean bSuccess = this.itemStore.removeById(id);

            if (bSuccess) {
                System.out.println("Delete completed...");
            } else {
                System.out.println("Delete failed...\n");
                printErrorMessage("Id does't exist");
            }
        }
    }
    
    /***************************************************************************
                                Search Methods
    ***************************************************************************/
    private void searchById()
    {   
        int id = ScannerUtility.getInt("Please Enter a Id: ");
        System.out.println();
        List<Item> list = this.itemStore.searchById(id);
        if(list != null)
        {
            this.itemStore.printItemTitle();
            this.itemStore.printArrayList(list);
        }
        else
        {
            printErrorMessage("Id doesnt exist");
        }
    }
    
    private void searchByTitle()
    {   
        String title = ScannerUtility.getString("Please Enter a title: ");
        System.out.println();
        List<Item> list = this.itemStore.searchByTitle(title);
        if(list != null)
        {
            this.itemStore.printItemTitle();
            this.itemStore.printArrayList(list);
        }
        else
        {
            printErrorMessage("Title doesnt exist");
        }
    }
    
    private void searchAllByCategory(int choice)
    {              
        String category;
        if(choice == 1)
        {
            category = "Book";
        }
        else
        {
            category = "Movie";
        }
        
        System.out.println();
        List<Item> list = this.itemStore.searchAllItemsByCategory(category);
        
        this.itemStore.printItemTitle();
        this.itemStore.printArrayList(list);                
    }   

    /***************************************************************************
                                Other Methods
    ***************************************************************************/
    private void initialiseStores(String file)
    {      
        this.itemStore = new ItemStore("list");
        
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {                  
            String line;
        
            while ((line = br.readLine()) != null) 
            { 
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");               
                int id = (Integer.parseInt(stringTokenizer.nextElement().toString()));
                String category = (stringTokenizer.nextElement().toString());
                String title = (stringTokenizer.nextElement().toString());
                String genre = (stringTokenizer.nextElement().toString());
                int releaseYear = (Integer.parseInt(stringTokenizer.nextElement().toString()));
                double rating = (Double.parseDouble(stringTokenizer.nextElement().toString()));
                                
                if(category.equalsIgnoreCase("book"))
                {
                    String author = (stringTokenizer.nextElement().toString());                
                    String publisher = (stringTokenizer.nextElement().toString());    
                    int pageCount = (Integer.parseInt(stringTokenizer.nextElement().toString())); 
                    
                    itemStore.add(new Book(id,category,title,genre,releaseYear,rating,author,publisher,pageCount));
                }
                else if(category.equalsIgnoreCase("movie"))
                {
                    String director = (stringTokenizer.nextElement().toString());
                    int budget = (Integer.parseInt(stringTokenizer.nextElement().toString()));
                    double length = (Double.parseDouble(stringTokenizer.nextElement().toString()));
                    
                    itemStore.add(new Movie(id,category,title,genre,releaseYear,rating,director,budget,length));
                }                   
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }          
    }   

    public void printExtraTitle(String title)
    {
        System.out.println("\n");
        System.out.println(title);
        String i = String.format("%0" + title.length() + "d" , 0).replace("0" , "-");
        System.out.println(i);
    }

    public void printErrorMessage(String error)
    {
        System.out.println("\n================================================================");
        System.out.println("");
        System.out.println("   " + error);
        System.out.println("");
        System.out.println("================================================================");
    }
}