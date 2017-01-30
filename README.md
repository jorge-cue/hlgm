curl# HomeLocationsOnGoogleMaps

Application preparation.

How to start the HomeLocationsOnGoogleMaps application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/hlgm-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

URLs to operate on homes
---

To query a list of all homes in database `curl -H "Accept: application/json" http://localhost:8080/api/home`
To query a home `curl -H "Accept: application/json"  http://localhost:8080/api/:id`
For example for a home with id 5 use `curl -H "Accept: application/json"  http://localhost:8080/api/5`

To create a home

`curl -d '{"streetAddressLine1": "Address Line 1","streetAddressLine2": "Address Line 2","city": "city","state": "STATE","zipCode": "12345","country": "Mexico"}' -H "Content-Type: application/json" -H "Accept: application/json" -i -v http://localhost:8080/api/home`

Location header in the response shall content the URL to access the new home

To modify an existing home, assuming it has id 1

`curl -X PUT -d '{"id":1,"streetAddressLine1": "Modified Address Line 1","streetAddressLine2": "Modified Address Line 2","city": "New city","state": "ST","zipCode": "67890","country": "USA"}' -H "Content-Type: application/json" -H "Accept: application/json" -i -v http://localhost:8080/api/home/1`

Note: the field id in d paramater and the number at the end of the URL must match.
