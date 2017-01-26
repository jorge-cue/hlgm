curl# HomeLocationsOnGoogleMaps

Application preparation.

Create homes table on database using the next CREATE TABLE SQL statement:

CREATE TABLE homes
(
  id serial NOT NULL,
  streetaddressline1 character varying(60) NOT NULL,
  streetaddressline2 character varying(60) NOT NULL,
  city character varying(60) NOT NULL,
  state character varying(5) NOT NULL,
  zipcode character varying(5) NOT NULL,
  country character varying(30) NOT NULL,
  CONSTRAINT homes_pkey PRIMARY KEY (id)
)


How to start the HomeLocationsOnGoogleMaps application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/hlgm-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
