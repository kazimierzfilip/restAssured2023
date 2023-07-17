# Issues

## TYPICODE-1

### [POST /posts] http instead of https in Location header

POST /posts return http:// instead of https:// in Location header.

Request:

```
POST https://jsonplaceholder.typicode.com/posts
{
    "id": null,
    "userId": 1,
    "title": "First post",
    "body": "Lorem ipsum dolor sit amet"
}
```

```
curl --location --request POST 'https://jsonplaceholder.typicode.com/posts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "userId": 1,
    "title": "First post",
    "body": "Lorem ipsum dolor sit amet"
}'
```

Response:

```
HTTP/1.1 201 Created
Date: Mon, 17 Jul 2023 19:17:24 GMT
Content-Type: application/json; charset=utf-8
Content-Length: 95
Connection: keep-alive
X-Powered-By: Express
X-Ratelimit-Limit: 1000
X-Ratelimit-Remaining: 996
X-Ratelimit-Reset: 1689621449
Vary: Origin, X-HTTP-Method-Override, Accept-Encoding
Access-Control-Allow-Credentials: true
Cache-Control: no-cache
Pragma: no-cache
Expires: -1
Access-Control-Expose-Headers: Location
Location: http://jsonplaceholder.typicode.com/posts/101

{
    "id": 101,
    "userId": 1,
    "title": "First post",
    "body": "Lorem ipsum dolor sit amet"
}
```

Location header has `http://`

```
Location: http://jsonplaceholder.typicode.com/posts/101
```

Please change to `https://`