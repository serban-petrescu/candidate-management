## Running the server
Run the maven command
```sh
mvn spring-boot:run
```
The server will run on `localhost:8080` unless configured otherwise in `application.properties`.
## Test the api

Run the command
```sh
curl localhost:8080/api
```
This route will give infos about the api. The response may look like
```sh
{
  "_links" : {
    "candidates" : {
      "href" : "http://localhost:8080/api/candidates"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile"
    }
  }
}
```
To see the existent candidates follow the `candidates` route in the `_links` node, specifically `http://localhost:8080/api/candidates`.

## How to use Postman

You import the files or the folder src/test/resources/postman
```
  Candidate Management.postman_collection.json - for the rest points collection
  Candidate Management Tests.postman_collection.json - for the tests
```
Candidate Management Tests contains all the tests. The tests can be run:
- individually - you simply choose the test and click Send
- as a whole - you use the collection runner where you choose the Candidate Management Tests and click Start Run