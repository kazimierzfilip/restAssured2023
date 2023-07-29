# Test cases

- [TYPI-1](#typi-1)
- [TYPI-2](#typi-2)
- [TYPI-3](#typi-3)
- [TYPI-4](#typi-4)

## TYPI-1

### Title:

Create new post

### Preconditions:

Some user exists.

### Steps:

Send request:

- userId - id of user
- title - some string
- body - some string

### Assertions:

- Assert response http status code is 201
- Assert location header contains url of created resource
- Assert that response body contains same values as request body

## TYPI-2

### Title:

New post id is generated

### Preconditions:

Some user exists.

### Steps:

Send request:

- id - some numeric value
- userId - id of user
- title - some string
- body - some string

### Assertions:

- Assert response http status code is 201
- Assert location header contains same id as in response
- Assert that id in **response** body is different from id in **request** body

## TYPI-3

### Title:

User id type validation

### Preconditions:

### Steps:

Send request:

- userId - some **string**
- title - some string
- body - some string

### Assertions:

- Assert response http status code is 400
- Assert location header is empty

## TYPI-4

### Title:

Invalid content type

### Preconditions:

Some user exists.

### Steps:

- Set header content-type to application/xml
- Send request:
	- userId - user id
	- title - some string
	- body - some string

### Assertions:

- Assert response http status code is 415
- Assert location header is empty
