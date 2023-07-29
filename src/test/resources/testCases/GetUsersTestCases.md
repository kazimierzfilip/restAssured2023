# Test cases

- [TYPI-5](#typi-5)
- [TYPI-6](#typi-6)
- [TYPI-7](#typi-7)

## TYPI-5

### Title:

GET users success HTTP status code

### Preconditions:

Some user exists.

### Steps:

Send GET request

### Assertions:

- Assert response http status code is 200

## TYPI-6

### Title:

All users are returned

### Preconditions:

10 user exists.

### Steps:

Send GET request

### Assertions:

- Assert response http status code is 200
- Assert all 10 users are returned

## TYPI-7

### Title:

All users properties are returned.

### Preconditions:

Some user with all data exists.

### Steps:

Send GET request

### Assertions:

- Assert response http status code is 200
- Assert all user properties are returned:
    - id
    - name
    - username
    - email
    - address
        - street
        - suite
        - city
        - zipcode
        - geo
            - lat
            - lng
    - phone
    - website
    - company
        - name
        - catchPhrase
        - bs