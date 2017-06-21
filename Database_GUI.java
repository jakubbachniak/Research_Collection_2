import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;

/**
 * ResearchCollection_GUI is the class responsible for creating window
 * as inaterface between user and the collection database
 * 
 * @author Jakub Bachniak
 * @version v 2.0; 6-1-2016
 */
public class Database_GUI {
    
    // fields
    private static final String VERSION = "Version v2.0; 17.1.2016";
    private JFrame frame;
    private JPanel content;
    private JPanel contentPane;
    private JPanel menuPanel;
    private Database researchDatabase;
    
    /**
     * Create the window and show it on screen
     */
    public Database_GUI(){
        makeFrame();
        showInitialStatus();
        researchDatabase = new Database();
    }
    
    /**
     * Show database initial status
     */
    private void showInitialStatus(){
        JLabel currentStatus = new JLabel("Database contains no documents. Start by creating programming paradigm category.");
        contentPane.add(currentStatus, BorderLayout.CENTER);
        currentStatus.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Create the main frame and its contents
     */
    private void makeFrame(){
        // initialise new frame object - create window - the top level container
        frame = new JFrame("Database_GUI");
        frame.setMinimumSize(new Dimension(800, 500));
        // define default action when the window is closed using JFrams's constant
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create main container as JPanel
        content = new JPanel();
        // make the component to be the contentPane
        frame.setContentPane(content);
        // set the content pane layout
        content.setLayout(new BorderLayout());
        // add menu panel
        content.add(createMenuPanel(), BorderLayout.NORTH);
        // create content pane to add components
        contentPane = new JPanel();
        content.add(contentPane, BorderLayout.CENTER);       
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * create menuBar
     */
    private JPanel createMenuPanel(){
        // create menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(SwingConstants.LEADING));
        // create database functionality menu buttons
        JButton createCategoryBtn = new JButton("Add category");
        JButton addDocumentBtn = new JButton("Add document");
        JButton deleteDocumentBtn = new JButton("Delete document");
        JButton listCollectionBtn = new JButton("List collection");
        JButton numberDocumentsBtn = new JButton("Number of Documents");
        JButton aboutBtn = new JButton("About database");
        // handle menu buttons
        createCategoryBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { makeCategoryPanel(); }
        });
        addDocumentBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { makeAddDocumentPanel(); }
        });
        deleteDocumentBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { makeRemoveDocumentPanel(); }
        });
        listCollectionBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { makeListCollectionPanel(); }
        });
        numberDocumentsBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { makeNumberDocumentsPanel(); }
        });
        
        aboutBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) { showAboutWindow(); }
        });
        // add buttons to the menu panel
        menuPanel.add(createCategoryBtn);
        menuPanel.add(addDocumentBtn);
        menuPanel.add(deleteDocumentBtn);
        menuPanel.add(listCollectionBtn);
        menuPanel.add(numberDocumentsBtn);
        menuPanel.add(aboutBtn);
        
        return menuPanel;
    }
    
    /**
     * make add category collection
     */
    private JPanel makeCategoryPanel(){
        // outer container for adding document panel
        JPanel categoryCollection = new JPanel();
        // inner 'add category' panel for setting up Layout
        JPanel categoryCollectionLayout = new JPanel();
        categoryCollectionLayout.setLayout(new BoxLayout(categoryCollectionLayout, BoxLayout.Y_AXIS));     
        categoryCollection.add(categoryCollectionLayout);
        JLabel categoryLabel = new JLabel("Enter programming paradigm to create category in database.");
        JTextField categoryName = new JTextField();
        JButton addCategoryBtn = new JButton("Add category");
        addCategoryBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event) {
            String newParadigmCategory = categoryName.getText();
            researchDatabase.addCategory(newParadigmCategory);
        }
        });
        // add components to the panel
        categoryCollectionLayout.add(categoryLabel);
        categoryCollectionLayout.add(Box.createVerticalStrut(10));
        categoryCollectionLayout.add(categoryName);
        categoryCollectionLayout.add(Box.createVerticalStrut(10));
        categoryCollectionLayout.add(addCategoryBtn);
        // refresh and display panel
        contentPane.removeAll();
        contentPane.add(categoryCollection);
        frame.repaint();
        frame.pack();
        
        return categoryCollection;
    }
    
    /**
     * create the add document panel
     */
    private JPanel makeAddDocumentPanel(){
         // outer container for adding document panel
        JPanel addDocumentPanel = new JPanel();
        // inner 'add document' panel for setting up Layout
        JPanel addDocumentPanelLayout = new JPanel();
        addDocumentPanelLayout.setLayout(new BoxLayout(addDocumentPanelLayout, BoxLayout.Y_AXIS));
        addDocumentPanelLayout.setAlignmentX(Component.LEFT_ALIGNMENT);
        addDocumentPanel.add(addDocumentPanelLayout);
        JLabel addDocumentLabel = new JLabel("Choose programming paradigm to which add new document:");
        // read the array with programming pardigms categories in the collection
        String[] paradigmStrings = researchDatabase.getParadigmStrings();
        // put the programming pardigm categories into a drop dowm menu
        JComboBox<String> paradigmList = new JComboBox<String>(paradigmStrings);
        // text fields to capture users input
        JLabel textFieldsLabel = new JLabel("Enter document details:");
        JTextField titleField = new JTextField("Enter document title");
        JTextField authorField = new JTextField("Document author");
        JTextField publisherField = new JTextField("Document publisher");
        JTextField dateField = new JTextField("Date published");
        JTextField issnField = new JTextField("issn");
        JButton addDocumentBtn = new JButton("Add document");
        // handle the button
        addDocumentBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // test for existing items in the programming paradigm category combo box
                int numberItemsComboBox = paradigmList.getItemCount();
                // if no cagegories in the combo box to choose from,
                // notify user and do nothing
                if(numberItemsComboBox == 0){
                    System.out.println("No categories defined in the database.\nAdd category to database first.");
                }
                else {
                    String paradigmName = paradigmList.getSelectedItem().toString();
                    ResearchCollection collection = researchDatabase.getCollection(paradigmName);
                    collection.addToCollection(titleField.getText(), authorField.getText(), publisherField.getText(), dateField.getText(), issnField.getText());
                    System.out.println("Document has been added to collection: " + paradigmName);
                }
            }
        });
        // add components to the panel
        addDocumentPanelLayout.add(addDocumentLabel);
        addDocumentPanelLayout.add(Box.createVerticalStrut(10));
        addDocumentPanelLayout.add(paradigmList);
        addDocumentPanelLayout.add(Box.createVerticalStrut(10));
        addDocumentPanelLayout.add(textFieldsLabel);
        addDocumentPanelLayout.add(Box.createVerticalStrut(10));
        addDocumentPanelLayout.add(titleField);
        addDocumentPanelLayout.add(authorField);
        addDocumentPanelLayout.add(publisherField);
        addDocumentPanelLayout.add(dateField);
        addDocumentPanelLayout.add(issnField);
        addDocumentPanelLayout.add(Box.createVerticalStrut(10));
        addDocumentPanelLayout.add(addDocumentBtn);
        // refresh the panel
        contentPane.removeAll();
        contentPane.add(addDocumentPanel);       
        frame.repaint();
        frame.pack();        
        return addDocumentPanel;
    }
    
    /**
     * make remove document panel method
     */
    private JPanel makeRemoveDocumentPanel(){
        // outer container for adding document panel
        JPanel removeDocumentPanel = new JPanel();
        // inner 'remove document' panel for setting up Layout
        JPanel removeDocumentPanelLayout = new JPanel();
        removeDocumentPanelLayout.setLayout(new BoxLayout(removeDocumentPanelLayout, BoxLayout.Y_AXIS));     
        removeDocumentPanel.add(removeDocumentPanelLayout);
        
        JLabel containingLabel = new JLabel("String to search that document to be deleted contains in the title:");
        JTextField containingTextField = new JTextField();
        // read the array with programming pardigms categories in the collection
        String[] paradigmStrings = researchDatabase.getParadigmStrings();
        // create the combo box and populate with programming paradigm categories
        JComboBox<String> paradigmList = new JComboBox<String>(paradigmStrings);
        JLabel collectionLabel = new JLabel("Please select which collection to remove from:");
        JButton removeDocumentBtn = new JButton("Remove document");
        removeDocumentBtn.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent event)
            {
                // test for existing items in the programming paradigm category combo box
                int numberItemsComboBox = paradigmList.getItemCount();
                // if no cagegories in the combo box to choose from,
                // notify user and do nothing
                if(numberItemsComboBox == 0){
                    System.out.println("Empty database.\nNo documents to remove. Add category and documents to database");
                }
                // else remove specified document
                else{
                    String selectedParadigm = paradigmList.getSelectedItem().toString();
                    ResearchCollection collection = researchDatabase.getCollection(selectedParadigm);
                    collection.removeContainingFrom(containingTextField.getText());
                }
            }
        });
        // add components to the panel
        removeDocumentPanelLayout.add(containingLabel);
        removeDocumentPanelLayout.add(Box.createVerticalStrut(10));
        removeDocumentPanelLayout.add(containingTextField);
        removeDocumentPanelLayout.add(Box.createVerticalStrut(10));
        removeDocumentPanelLayout.add(collectionLabel);
        removeDocumentPanelLayout.add(Box.createVerticalStrut(10));
        removeDocumentPanelLayout.add(paradigmList);
        removeDocumentPanelLayout.add(Box.createVerticalStrut(10));
        removeDocumentPanelLayout.add(removeDocumentBtn);
        // refresh the panel
        contentPane.removeAll();
        contentPane.add(removeDocumentPanel);
        frame.repaint();
        frame.pack();
        return removeDocumentPanel;
    }
    
    /**
     * make list collection panel
     */
    private JPanel makeListCollectionPanel(){
        // outer container for adding document panel
        JPanel listCollection = new JPanel();
        // inner 'list collection' panel for setting up Layout
        JPanel listCollectionLayout = new JPanel();
        listCollectionLayout.setLayout(new BoxLayout(listCollectionLayout, BoxLayout.Y_AXIS));     
        listCollection.add(listCollectionLayout);
        JLabel listCollectionLabel = new JLabel("Choose programming paradigm:");
        // read the array with programming pardigms categories in the collection
        String[] paradigmStrings = researchDatabase.getParadigmStrings();
        // create the combo box
        JComboBox<String> paradigmList = new JComboBox<String>(paradigmStrings);
        JButton listCollectionBtn = new JButton("Display collection");
        listCollectionBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // test for existing items in the programming paradigm category combo box
                int numberItemsComboBox = paradigmList.getItemCount();
                // if no cagegories in the combo box to choose from,
                // notify user and do nothing
                if(numberItemsComboBox == 0){
                    System.out.println("No documents to display. Add category and documents to database");
                }
                // else list collection
                else{
                    String listedCollection = paradigmList.getSelectedItem().toString();
                    ResearchCollection collection = researchDatabase.getCollection(listedCollection);
                    collection.listCollection(listedCollection);
                }
            }
        });
        // add componenets to the panel
        listCollectionLayout.add(listCollectionLabel);
        listCollectionLayout.add(Box.createVerticalStrut(10));
        listCollectionLayout.add(paradigmList);
        listCollectionLayout.add(Box.createVerticalStrut(10));
        listCollectionLayout.add(listCollectionBtn);
        // refresh the panel
        contentPane.removeAll();
        contentPane.add(listCollection);
        frame.repaint();
        frame.pack();
        return listCollection;
    }
    
    /**
     * Display number of documents in a collection
     */
    private JPanel makeNumberDocumentsPanel(){
        // outer container for adding document panel
        JPanel numberDocuments = new JPanel();
        // inner 'number of documents' panel for setting up Layout
        JPanel numberDocumentsLayout = new JPanel();
        numberDocumentsLayout.setLayout(new BoxLayout(numberDocumentsLayout, BoxLayout.Y_AXIS));     
        numberDocuments.add(numberDocumentsLayout);
        JLabel numberDocumentsLabel = new JLabel("Choose programming paradigm:");
        // read the array with programming pardigms categories in the collection
        String[] paradigmStrings = researchDatabase.getParadigmStrings();
        // create the combo box
        JComboBox<String> paradigmList = new JComboBox<String>(paradigmStrings);
        JButton numberDocumentsBtn = new JButton("Show number of documents");
        numberDocumentsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // test for existing items in the programming paradigm category combo box
                int numberItemsComboBox = paradigmList.getItemCount();
                // if no cagegories in the combo box to choose from,
                // notify user and do nothing
                if(numberItemsComboBox == 0){
                    System.out.println("Database contains no documents. Add category and documents to database");
                }
                // else display number of documents
                else {
                    String category = paradigmList.getSelectedItem().toString();
                    ResearchCollection collection = researchDatabase.getCollection(category);
                    collection.numberDocuments(category);
                }
            }
        });
        // add components to the panel
        numberDocumentsLayout.add(numberDocumentsLabel);
        numberDocumentsLayout.add(Box.createVerticalStrut(10));
        numberDocumentsLayout.add(paradigmList);
        numberDocumentsLayout.add(Box.createVerticalStrut(10));
        numberDocumentsLayout.add(numberDocumentsBtn);
        // refresh the panel
        contentPane.removeAll();
        contentPane.add(numberDocuments);
        frame.repaint();
        frame.pack();
        return numberDocuments;
    }
    
    /**
     * Display the 'about' window box
     */
    private void showAboutWindow(){
        String aboutText =  "Assignment 2 - part 3\n" +
                            "Implementation of Graphical User Interface for\n" +
                            "the assignment1 database version 2 - the improved version";
        JOptionPane.showMessageDialog(frame,
                            "Database_GUI " + VERSION + "\n" + 
                            aboutText,
                            "About Database GUI",
                            JOptionPane.INFORMATION_MESSAGE);
    }
}
