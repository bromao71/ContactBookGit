package contactBook;

public class Contact {
    private String name;
    private int phone;
    private String email;

    /**
     * Creates a new contact with the given name, phone number and email.
     * @param name the contact's name
     * @param phone the contact's phone number
     * @param email the contact's email address
     */
    public Contact(String name, int phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Returns the contact's name.
     * @return the contact's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the contact's phone number.
     * @return the contact's phone number
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Returns the contact's email address.
     * @return the contact's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the contact's phone number.
     * @param phone the new phone number
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Updates the contact's email address.
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks whether this contact is the same as another contact, based
     * on their names.
     * Pre: otherContact != null
     * @param otherContact the contact to compare against
     * @return true if both contacts have the same name, false otherwise
     */
    public boolean equals(Contact otherContact) {
        return name.equals(otherContact.getName());
    }
}