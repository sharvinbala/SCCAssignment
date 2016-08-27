# Maven

Apache Maven is a software project managemnet and comprehension tool. Maven can manage a project's build, reporting and documentation  from a central piece of information based on the concept of POM (Project Object Model).

## [Maven's primary goals](https://maven.apache.org/what-is-maven.html)
- Making the build process easy
- Providing uniform build system
- Providing quality project information
- Guidelines for best practise development
- Transparent migration to new features

Maven is available under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)

- [Maven Issue Tracker](https://issues.apache.org/jira/browse/MNG)
- [Maven Wiki](https://cwiki.apache.org/confluence/display/MAVEN/Index)
- [Building Maven](https://maven.apache.org/guides/development/guide-building-maven.html)
- [Running Core ITs](https://maven.apache.org/core-its/core-it-suite/)

## Bootstrapping Basics

If you want to bootstrap Maven, you'll need:

- Java 1.7+
- Maven 3.0.5 or later

Run Maven, specifying a location into which the completed Maven distro should be installed:

```
mvn -DdistributionTargetFolder="$HOME/app/maven/apache-maven-3.4.x-SNAPSHOT" clean package
```

Once the build completes, you should have a new Maven distro ready to roll in that directory!
