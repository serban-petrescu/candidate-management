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
