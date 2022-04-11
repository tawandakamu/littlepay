#LittlePay Coding Test - Tawanda Kamuzonde

## Java Coding Exercise

### Assumptions to problem statement
- All times for tap ons or off are always in the correct order for processing
- Setting up Maven not required to showcase Business logic
- Setting Git repo & committing workflow out of scope
- Setting up docker for ease of development, running solution & portability to other machines out of scope.
- Linting code out of scope
- Unit tests sacrificed due to being time pressed but coming soon
- Times are in LocalDateTime & do not carry UTC outscope as timezone conversions considered out of scope.

### How to run the application

###IntelliJ IDEA
1. Open your IntelliJ IDEA (CE) IDE
2. Hit the "Run Main" button in the top right of the IDE with the Play icon
3. Go to the `data` folder in the root of the application to find the generated trips.csv files

### Command Line
In your terminal window, type in the following Java Command

~~~bash
/Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=60941:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/tawanda/IdeaProjects/LittlePay/out/production/LittlePay com.littlepay.Main
~~~

Example values in the above to be adjusted with your machine paths:

JAVA_HOME = /Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home/bin/java
OUTPUT_DIR = /Users/tawanda/IdeaProjects/LittlePay/out/production/LittlePay

Openjdk 17.0.2 2022-01-18
Java 8+

### Coming soon

- Finishing off the rest of the coding challenge as time permits.