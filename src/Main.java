import contactBook.Contact;
import contactBook.ContactBook;

import java.util.Scanner;


public class Main {
    //Constants defining the commands
    public static final String ADD_CONTACT    = "AC";
    public static final String REMOVE_CONTACT = "RC";
    public static final String GET_PHONE      = "GP";
    public static final String GET_EMAIL      = "GE";
    public static final String SET_PHONE      = "SP";
    public static final String SET_EMAIL      = "SE";
    public static final String LIST_CONTACTS  = "LC";
    public static final String SEARCH_CONTACT_BY_NUMBER  = "GN";
    public static final String SAME_PHONE_NUMBER = "EP";
    public static final String QUIT           = "Q";

    //Constants defining the messages shown to the user
    public static final String CONTACT_EXISTS = "contactBook.Contact already exists.";
    public static final String NAME_NOT_EXIST = "contactBook.Contact does not exist.";
    public static final String CONTACT_ADDED = "contactBook.Contact added.";
    public static final String CONTACT_REMOVED = "contactBook.Contact removed.";
    public static final String CONTACT_UPDATED = "contactBook.Contact updated.";
    public static final String BOOK_EMPTY = "contactBook.Contact book empty.";
    public static final String CONTACTS_WITH_SAME_PHONE_NUMBER = "There are contacts that share phone numbers.";
    public static final String ALL_DIFFERENT_NUMBERS = "All contacts have different phone numbers.";
    public static final String QUIT_MSG = "Goodbye!";
    public static final String COMMAND_ERROR = "Unknown command.";

    /**
     * Entry point of the application. Reads commands from standard input
     * in a loop, dispatching each one to its handler, until the QUIT
     * command is received.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ContactBook cBook = new ContactBook();
        String comm = getCommand(in);

        while (!comm.equals(QUIT)){
            switch (comm) {
                case ADD_CONTACT:
                    addContact(in,cBook);
                    break;
                case REMOVE_CONTACT:
                    deleteContact(in,cBook);
                    break;
                case GET_PHONE:
                    getPhone(in,cBook);
                    break;
                case GET_EMAIL:
                    getEmail(in,cBook);
                    break;
                case SET_PHONE:
                    setPhone(in,cBook);
                    break;
                case SET_EMAIL:
                    setEmail(in,cBook);
                    break;
                case LIST_CONTACTS:
                    listAllContacts(cBook);
                    break;
                case SEARCH_CONTACT_BY_NUMBER:
                    searchContactByNumber(in, cBook);
                    break;
                case SAME_PHONE_NUMBER:
                    samePhoneNumber(cBook);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
            }
            System.out.println();
            comm = getCommand(in);
        }
        System.out.println(QUIT_MSG);
        System.out.println();
        in.close();
    }

    /**
     * Reads a command from input and normalizes it to upper case.
     * @param in the Scanner used to read input
     * @return the command entered by the user, in upper case
     */
    private static String getCommand(Scanner in) {
        String input;

        input = in.nextLine().toUpperCase();
        return input;
    }

    /**
     * Handles the AC command: reads a name, phone number and email from
     * input and adds a new contact to the book, unless a contact with
     * that name already exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to add the contact to
     */
    private static void addContact(Scanner in, ContactBook cBook) {
        String name, email;
        int phone;

        name = in.nextLine();
        phone = in.nextInt(); in.nextLine();
        email = in.nextLine();
        if (!cBook.hasContact(name)) {
            cBook.addContact(name, phone, email);
            System.out.println(CONTACT_ADDED);
        }
        else System.out.println(CONTACT_EXISTS);
    }

    /**
     * Handles the RC command: reads a name from input and removes the
     * matching contact from the book, if it exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to remove the contact from
     */
    private static void deleteContact(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.deleteContact(name);
            System.out.println(CONTACT_REMOVED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Handles the GP command: reads a name from input and prints the
     * phone number of the matching contact, if it exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to search in
     */
    private static void getPhone(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getPhone(name));
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Handles the GE command: reads a name from input and prints the
     * email of the matching contact, if it exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to search in
     */
    private static void getEmail(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getEmail(name));
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Handles the SP command: reads a name and a new phone number from
     * input and updates the matching contact, if it exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to update
     */
    private static void setPhone(Scanner in, ContactBook cBook) {
        String name;
        int phone;
        name = in.nextLine();
        phone = in.nextInt(); in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setPhone(name,phone);
            System.out.println(CONTACT_UPDATED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Handles the SE command: reads a name and a new email from input
     * and updates the matching contact, if it exists.
     * @param in the Scanner used to read input
     * @param cBook the contact book to update
     */
    private static void setEmail(Scanner in, ContactBook cBook) {
        String name;
        String email;
        name = in.nextLine();
        email = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setEmail(name,email);
            System.out.println(CONTACT_UPDATED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    /**
     * Handles the LC command: prints every contact in the book, in
     * insertion order, or a message if the book is empty.
     * @param cBook the contact book to list
     */
    private static void listAllContacts(ContactBook cBook) {
        if (cBook.getNumberOfContacts() != 0) {
            cBook.initializeIterator();
            while( cBook.hasNext() ) {
                Contact c = cBook.next();
                System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
            }
        }
        else System.out.println(BOOK_EMPTY);
    }

    /**
     * Handles the GN command: reads a phone number from input and
     * prints the name of the oldest contact with that number, or an
     * error message if no contact has it.
     * @param in the Scanner used to read input
     * @param cBook the contact book to search in
     */
    private static void searchContactByNumber(Scanner in, ContactBook cBook) {
        int phone = in.nextInt(); in.nextLine();
        String name = cBook.getOlderContactNameByPhone(phone);
        if (name != null)
            System.out.println(name);
        else System.out.println("Phone number does not exist.");
    }

    /**
     * Handles the EP command: checks whether any two contacts in the
     * book share the same phone number and prints the corresponding
     * message.
     * @param cBook the contact book to check
     */
    private static void samePhoneNumber(ContactBook cBook) {
        if (cBook.samePhoneNumber())
            System.out.println(CONTACTS_WITH_SAME_PHONE_NUMBER);
        else
            System.out.println(ALL_DIFFERENT_NUMBERS);
    }
}