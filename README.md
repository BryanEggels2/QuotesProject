# QuotesProject
API for retrieving quotes.


## Usage:

If you want to use the local mocks you can change the code in the QuotesApplication class.

```java
Repository.initialize(Service.Api);
```
to:
```java
Repository.initialize(Service.Mock);
```

### Endpoints:
`GET /quotes`
return all the quotes available from the repository in json format


`GET /quotes/popular`
return all the popular quotes based on likes (at the moment.) This could also be changed to popularity of the api

`GET /quotes/random`
return a random quote from the repository. This could be retrieved from the API or from the local memory at this moment.

`GET quotes/{id}`
returns the quote with the requested id

`POST quotes/{id}/like`
likes the post with the given id (only local)
