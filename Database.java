import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Database class. Creates main container for research documents.
 * Documents will be grouped into categories - programming pardigms
 * and each category stored in an ArrayList object collection.
 * HashMap is responsible for mapping given ArrayList collection to
 * its category label String
 * 
 * @author Jakub Bachniak 
 * @version v2.0; 17.1.2016
 */
public class Database
{
    // create main container for documents and all categories
    // map defined category to the programming paradigm category collection
    private HashMap<String, ResearchCollection> database;
    
    
    /**
     * Create new database
     */
    public Database(){
        // create main container for the database
        database = new HashMap<String, ResearchCollection>();
    }

    /**
     * create new programming paradigm category and the collection in the database
     */
    public void addCategory(String category){
        // get the set of HashMap keys
        Set<String> keysDatabaseCategories = database.keySet();
        // test if specified category already exists
        boolean categoryExists = false;
        for(String existingCategory : keysDatabaseCategories){
            if(existingCategory.equals(category)){
                categoryExists = true;
            }
        }
        if(category.isEmpty()){
            System.out.println("Empty string. No category added");
        }
        else if(categoryExists){
            System.out.println("Category " + category + " alredy exists in the database.");
            System.out.println("No category added to database.");
        }
        else{
            database.put(category, new ResearchCollection());
            System.out.println("Category " + category + " added to database.");
        }
    }
    
    /**
     * get Array of String names for programming pardigms collections
     */
    public String[] getParadigmStrings(){
        Set<String> keys = database.keySet();
        ArrayList<String> paradigms = new ArrayList<String>();
        for(String paradigm : keys){
            paradigms.add(paradigm);
        }
        // put the ArrayList elements into an array
        String[]paradigmsArray = new String[paradigms.size()];
        paradigms.toArray(paradigmsArray);
        
        return paradigmsArray;
    }
    
    /**
     * get researchCollection for specified String programming
     * paradigm category
     * @param String programming paradigm category in database
     * @return ResearchCollection collection of documents
     * mapped for specified key
     */
    public ResearchCollection getCollection(String paradigmCategory){
        ResearchCollection collection = database.get(paradigmCategory);
        return collection;
    }  
}