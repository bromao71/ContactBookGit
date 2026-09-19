# ContactBookGit

**Software Engineering class of 2026/27 - Homework Lab. 1**

## Group Members

| Nome | Número de aluno |
|------|------------------|
| Bernardo Romão | 67709 |
| Elsa Coimbra | 67915 |
| Inês Isabel Silva | 70929 |


## Application Information

**ContactBook** is a contact book application. It stores contact information such as name, email and phone number, and lets the user perform actions like creating, deleting and comparing contacts.


## Task Summary

The base implementation was provided by the teaching team, and our group completed the two missing commands:

- **GN** - search for a contact given its phone number
- **EP** - check whether there are contacts sharing the same phone number

In addition, we added Javadoc comments to every method in the provided classes (`Contact`, `ContactBook` and `Main`), documenting each method's purpose, parameters, return values and preconditions.


## Command Details

### GN - Search Contact by Phone Number
...

### EP - Check for Duplicate Phone Numbers

The command is handled by `samePhoneNumber()` in `Main.java`, which calls `ContactBook.samePhoneNumber()` to check for duplicates and prints the corresponding message.

`ContactBook.samePhoneNumber()` works as follows:
1. If the book has 0 or 1 contacts, it immediately returns `false` - no duplicates are possible with fewer than two contacts.
2. Otherwise, it compares every pair of contacts using two nested loops: the outer loop picks a contact `i`, and the inner loop compares it against every contact `j` that comes after it (`j = i + 1`). This ensures each pair is checked exactly once and no contact is ever compared against itself.
3. As soon as two contacts are found with the same phone number, the method returns `true`.
4. If no matching pair is found after checking all contacts, it returns `false`.

Depending on the result, `Main.java` prints `"There are contacts that share phone numbers."` or `"All contacts have different phone numbers."`

## Examples

```
GN
123456789
Phone number does not exist.

EP
All contacts have different phone numbers.
```
