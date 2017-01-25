 1.- Project created using:
     mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.0.5

 2.- create git repository
 3.- create .gitignore
 4.- add dropwizard-hibernate and dropwizard-testing dependencies
 5.- create this file (adding to git)
 6.- git commit --message "Initial create"
  fix two problems before commit:
     - maven-project-info-reports-plugin was not found, comment entry in pom
     - dropwizard version 1.0.5 did not found dropwizard-hibernate downgrade to 1.0.4 corrected the problem
 7.- On PostgreSQL using superuser

   CREATE ROLE hlgm LOGIN
     ENCRYPTED PASSWORD 'md5578f8bdbf6ba0216997c23e0a336038b'
     NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

   CREATE DATABASE hlgm
     WITH OWNER = hlgm
          ENCODING = 'UTF8'
          TABLESPACE = pg_default
          LC_COLLATE = 'C'
          LC_CTYPE = 'C'
          CONNECTION LIMIT = -1;

 8.- Create all basig Home resource infrastructure and configure.
 9.- commit.
10.- Create entry points to HomeResource and functions on HomeDAO
11.- First healthcheck run, fixed come minor bugs.
12.- commit
13.- Create REST entries in HomeResource
14.- Create some Tests for backend.



