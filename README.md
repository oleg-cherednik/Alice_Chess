Chess Challenge

We would like for you to build the game of Chess using the command line in Java. The game has some interesting rules and is a great parallel for various business rules we encounter in real projects. Our objective is to see how you organize your thoughts and implement major OO principles into your work. We will also assess your overall coding skills in the Java language. The end product must be a PLAYABLE GAME. This does not mean all the rules need to be perfect, but the general game play should be implemented.

This should be a command line program, you will be playing against another human user. If you want to make it fun, and are daring, you can make a chess bot to play against.

The part of this game we care most about is the infrastructure of the game, not how the visuals look. Please make your code easy to read, well commented, and all zombie code cleaned up.

Once completed please create an account on BitBucket, upload this private repo, and send us a link. The link should be sent to: fernando.silva@alice-app.com. This assignment needs to be submitted by Thursday evening. 

Note: We have seen many of the solutions out on the web. If we see a striking similarity to what is out there, we will not be able to proceed with the interview. Please use this to express your personal coding style


-----

# Alice Chess

Java code challenge for **Alice**

## Problem description

We would like for you to build the game of Chess using the command line in Java. The game has some interesting rules and is a great parallel for
various business rules we encounter in real projects. Our objective is to see how you organize your thoughts and implement major OO principles into
your work. We will also assess your overall coding skills in the Java language. The end product must be a PLAYABLE GAME. This does not mean all the
rules need to be perfect, but the general game play should be implemented.

This should be a command line program, you will be playing against another human user. If you want to make it fun, and are daring, you can make a
chess bot to play against.

The part of this game we care most about is the infrastructure of the game, not how the visuals look. Please make your code easy to read, well
commented, and all zombie code cleaned up.

Note: We have seen many of the solutions out on the web. If we see a striking similarity to what is out there, we will not be able to proceed with the interview. Please use this to express your personal coding style

## How to build and run

### Prerequisite

- Java 11+
- Maven 3.x

#### Build

The project uses Maven. To build this application run following command:

```
> mvn clean install
```

It will build an executable jar file in `/target/alice-chess-all.jar`. 

#### Run

To run the program, move to the `/target` folder and run following command:

```
> java -jar alice-chess.jar
```

### Output

#### Help or no argument

To get help for available command run application with `-h`:

```
> usage: alice-chess.jar
 -h,--help         Print this help
 -utf8,--unicode   Use 'utf8' symbols
```
