
/**
 * Document class to hold information about a research document
 * It will serve as a prototype for single entry to the
 * research database
 * 
 * @author Jakub Bachniak 
 * @version v 1.0 26-11-2015
 */
public class Document
{
    // create field to hold information about a seperate document
    private String title;
    private String author;
    private String publisher;
    private String date;    // document publication date
    private String issn;
    
    /**
     * Constructor for Document
     */
    public Document(String title, String author, String publisher, String date, String issn)
    {
        // assign values from parameters to fields
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.date = date;
        this.issn = issn;
    }
    
    /**
     * Return title of document
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return author of document
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Return publisher of document
     */
    public String getPublisher()
    {
        return publisher;
    }
    
    /**
     * Return date published
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     * Return issn
     */
    public String getIssn()
    {
        return issn;
    }
    
    /**
     * Print document information
     */
    public void printDocumentInfo()
    {
       System.out.println("Title: " + "\"" + title + "\"");
       System.out.println("Author(s): " + author);
       System.out.println("Publisher: " + publisher);
       System.out.println("Date published: " + date);
       System.out.println("ISSN number: " + issn);
       System.out.println("***************************************");
    }
}

