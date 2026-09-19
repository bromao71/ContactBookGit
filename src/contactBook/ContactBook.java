package contactBook;

public class ContactBook {
    static final int DEFAULT_SIZE = 100;

    private int counter;
    private Contact[] contacts;
    private int currentContact;

    /**
     * Creates an empty contact book with the default capacity.
     */
    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
        currentContact = -1;
    }

    /**
     * Checks whether a contact with the given name exists in the book.
     * Pre: name != null
     * @param name the name to search for
     * @return true if a contact with that name exists, false otherwise
     */
    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    /**
     * Returns how many contacts are currently stored in the book.
     * @return the number of contacts
     */
    public int getNumberOfContacts() {
        return counter;
    }

    /**
     * Adds a new contact to the book, resizing the internal array if needed.
     * Pre: name != null && !hasContact(name)
     * @param name the contact's name
     * @param phone the contact's phone number
     * @param email the contact's email address
     */
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    /**
     * Removes the contact with the given name from the book, shifting the
     * remaining contacts to fill the gap.
     * Pre: name != null && hasContact(name)
     * @param name the name of the contact to remove
     */
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for(int i=index; i<counter; i++)
            contacts[i] = contacts[i+1];
        counter--;
    }

    /**
     * Returns the phone number of the contact with the given name.
     * Pre: name != null && hasContact(name)
     * @param name the contact's name
     * @return the contact's phone number
     */
    public int getPhone(String name) {
        return contacts[searchIndex(name)].getPhone();
    }

    /**
     * Returns the email address of the contact with the given name.
     * Pre: name != null && hasContact(name)
     * @param name the contact's name
     * @return the contact's email address
     */
    public String getEmail(String name) {
        return contacts[searchIndex(name)].getEmail();
    }

    /**
     * Updates the phone number of the contact with the given name.
     * Pre: name != null && hasContact(name)
     * @param name the contact's name
     * @param phone the new phone number
     */
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name)].setPhone(phone);
    }

    /**
     * Updates the email address of the contact with the given name.
     * Pre: name != null && hasContact(name)
     * @param name the contact's name
     * @param email the new email address
     */
    public void setEmail(String name, String email) {
        contacts[searchIndex(name)].setEmail(email);
    }

    /**
     * Finds the index of the contact with the given name using a linear
     * search over the contacts currently stored.
     * @param name the name to search for
     * @return the index of the contact, or -1 if not found
     */
    private int searchIndex(String name) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i<counter && !found)
            if (contacts[i].getName().equals(name))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    /**
     * Finds the index of the first contact with the given phone number
     * using a linear search over the contacts currently stored.
     * @param phone the phone number to search for
     * @return the index of the contact, or -1 if not found
     */
    private int searchIndex(int phone) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i<counter && !found)
            if (contacts[i].getPhone() == phone)
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    /**
     * Doubles the capacity of the internal contacts array, copying over
     * the contacts that already exist.
     */
    private void resize() {
        Contact tmp[] = new Contact[2*contacts.length];
        for (int i=0;i<counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }

    /**
     * Resets the internal iterator to point at the first contact.
     */
    public void initializeIterator() {
        currentContact = 0;
    }

    /**
     * Checks whether the iterator has a next contact to return.
     * @return true if there is a next contact, false otherwise
     */
    public boolean hasNext() {
        return (currentContact >= 0 ) && (currentContact < counter);
    }

    /**
     * Returns the current contact and advances the iterator.
     * Pre: hasNext()
     * @return the next contact
     */
    public Contact next() {
        return contacts[currentContact++];
    }

    /**
     * Finds the name of the oldest (first inserted) contact with the
     * given phone number.
     * @param phone the phone number to search for
     * @return the contact's name, or null if no contact has that number
     */
    public String getOlderContactNameByPhone(int phone){
        int index = searchIndex(phone);
        if (index == -1)
            return null;
        else return contacts[index].getName();
    }

    /**
     * Checks whether any two contacts in the book share the same phone
     * number, by comparing every pair of contacts.
     * @return true if at least two contacts share a phone number,
     *         false otherwise (including when there are 0 or 1 contacts)
     */
    public boolean samePhoneNumber(){
        if (counter == 1 || counter == 0)
            return false;

        for (int i = 0; i < counter; i++)
            for (int j = i + 1; j < counter; j++)
                if (contacts[i].getPhone() == contacts[j].getPhone())
                    return true;
            
        return false;
    }
}