# Duke's Java Programming and Software Engineering Fundamentals Specialization
## About
This repository is a place to store my work for the [Java Programming and Software Engineering Fundamentals 
Specialization](https://www.coursera.org/specializations/java-programming) offered on Coursera. I have taken the 
liberty of building JUnit tests and implementing Maven.
## Using this repository
Frankly, I don't know why you would want to clone this repository, but if you do, you'll have to manually add
Duke's `courserajava.jar` dependency to your local Maven repository. This is because this Jar is not available 
in any public repositories. In order to do this, you'll have to download the jar by clicking `edu.duke` under the 
"Course Code Packages" section of [this page.](https://www.dukelearntoprogram.com/downloads/bluej.php)  
  
Next, you'll navigate to wherever you downloaded the Jar file to and run the following command from your command 
line:
```shell script
mvn install:install-file -Dfile=courserajava.jar -DgroupId=edu.duke -DartifactId=courserajava -Dversion=1.0 -Dpackaging=jar
```
If you run into any issues, please consult [this page.](http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)