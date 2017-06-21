import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * A class to hold a collection of programming research papers
 * in given - user defined programming paradigm category.
 * 
 * @author Jakub Bachniak 
 * @version v 2.0; 26-11-2015
 */
public class ResearchCollection
{
    // ArrayList container for documents in one category
    private ArrayList<Document> researchCollection;

    /**
     * Constructor method for creating an instance of ResearchDatabase
     */
    public ResearchCollection()
    {
        // create container for documents in given category
        researchCollection = new ArrayList<Document>();
    }
   
    /**
     * Add new document category collection
     * @param document title
     * @param document author(s)
     * @param document publisher
     * @param document date published
     * @param document issn; if no issn provide empty string ""
     */
    public void addToCollection(String title, String author, String publisher, String date, String issn)
    {
        Document document = new Document(title, author, publisher, date, issn);
        researchCollection.add(document);
    }
    
    /**
     * Remove document, from specified collection, whose
     * title contains specified string
     * @param String phrase to search in the title
     * of a document to be removed 
     */
    public void removeContainingFrom(String containing)
    {
        // boolean set to true if matching string has been found
        boolean matchFound = false;
        if(containing.isEmpty()){
            // if empty string provided notify user and do nothing
            System.out.println("Empty string provided. No document removed.");
        }
        else{
            // if string, iterate over collection and remove document
            Iterator<Document> it = researchCollection.iterator();
                while(it.hasNext()){
                    Document document = it.next();
                    String documentTitle = document.getTitle();
                    if(documentTitle.contains(containing)){
                        it.remove();
                        System.out.println("Document: " + documentTitle + " has been removed from database.");
                        matchFound = true;
                    }
                }
                // if no matching string found notify user and do nothing
                if(!matchFound){
                    System.out.println("No document exists in the collection that has the \"" + containing + "\" in the title.");
                    System.out.println("No document has been removed from the database.");
                }
        }
    }
    
    /**
     * List all document in specified collection
     * @param String collection
     */
    public void listCollection(String collection)
    {   
        System.out.println("***************************************");
        System.out.println("* Collection: " + collection + " contains:");
        System.out.println("***************************************");
        for(Document document : researchCollection){
            document.printDocumentInfo();
        }
    }
    
    /**
     * Print number of documents in  the specified collection
     * @param String programming pardigm category in the database
     */
    
    public void numberDocuments(String collection)
    {
        System.out.println("Number of documents in the " + collection + " collection: " + researchCollection.size());
    }
}
