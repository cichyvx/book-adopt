# book-adopt
api to exchange or donate unused books
## user techonlogies
- spring boot
- spring security
- spring data
- mysql
## how to use?

### first step
- to start you must have account to registry use [/user](http://localhost:8080/user), method PUT
![registry](https://user-images.githubusercontent.com/58787200/140183313-5b120bd9-3712-48b3-acd2-976e75db7903.png)
- next sign in and this all

### adding book to virtual bookcase
if you wany someday bundle off any of your book first you must add it to your bookcase
- first you caa check if maybe you already have book in your bookcase, to do it use [/bookCase](http://localhost:8080/bookCase), method GET
- if you don't nothing simplest, you can find book in us database
  - use [/name/{name}](http://localhost:8080/book/name/{name}), method GET to get all of book named that way
  - aslo you can use [/book/author/{author}](http://localhost:8080/book/author/{author}), method GET to get list of all books of this author
  - if you are patient you can also see list of all books by [/book](http://localhost:8080/book), method GET
- now if you find your book just use [/bookCase/{bookId}](http://localhost:8080/bookCase/{bookId}), method PUT, where bookId is id of book you found
- if you can't find your book see [adding books]

### adding book to database
when you have not been able to find the book in our database, it is possible that it has not been added there yet, in which case follow these steps
- use [/book](http://localhost:8080/book), method PUT following the instructions below
![adding_book](https://user-images.githubusercontent.com/58787200/140186212-a6037467-1d62-4677-a61a-b032264ba452.png)
1. name is title of book
2. author who wrote the book
3. book description

### donating book
you can donate your unused books from your bookcase, here are some tips on how to do it
- first, set up a few details on how your book is to be rendered on [/bookAd](http://localhost:8080/bookAd), method PUT
![bookAdAdding](https://user-images.githubusercontent.com/58787200/140188542-03b7d666-fe37-4802-ac3d-bacb84e0870b.png)
1. id of book you want to give away
2. short description, write what you care about, who you want to give this book to, what book you want to exchange, etc.
3. true - if you want to trade, false - if you want to give it back disinterestedly
in our case we give back a book with id 24 (title is you) we want it in exchange for some fantasy book<br>
now, after settling the details, just use PUT on the endpoint given above and wait for another users offers<br>

### checking offer
you can easily check if offers have been made to you or your current ones awaiting decisions
- use [/offer/received](http://localhost:8080/offer/received) (method GET) to check the offers submitted to you
- use [/offer/send](http://localhost:8080/offer/send) (method GET) to check the offers that you have made and that are waiting for decisions

### accepting and rejecting offers
make a decision whether the offer is acceptable or not interested in you
- to accept use [/offer/accept/{offerId}](http://localhost:8080/offer/accept/{offerId}) method POST
- to reject use [/offer/reject/{offerId}](http://localhost:8080/offer/reject/{offerId}) method DELETE

### making own offer
you can also submit offers to other users yourself
- first check list of all book ad and choose that you want
- to make offer without exchange just use [/offer/{bookAdId}](http://localhost:8080/offer/{bookAdId}) method PUT, where bookAdId is id of book ad
- to make offer with exchange use [/offer/{offerId}/bookOffered/{bookId}](http://localhost:8080/offer/{offerId}/bookOffered/{bookId}) method PUT, where bookId is book you offered to exchange
