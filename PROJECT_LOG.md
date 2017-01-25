

1 Project created using:

  mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.0.5

2 create git repository

3 create .gitignore

4 git add .

5 add dropwizard-hibernate and dropwizard-testing dependencies

6 create this file (adding to git)

6 git commit --message "Initial create"
  fix two problems before commit:
     - maven-project-info-reports-plugin was not found, comment entry
     - dropwizard version 1.0.5 did not found dropwizard-hibernate downgrade to 1.0.4 corrected the problem


