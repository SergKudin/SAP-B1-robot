This project was created to automatically enter data into the database through a web interface.
The data is taken from an Excel file.
The result of the execution is written to the source file.
To use the program, you need a JVM, Java v.16.
It is possible to work both with testNG and through Main class.
When run through testNG:
The browser is set via vm options.
The address of the start page and the Path to the data file are set in the class BaseTest
When launched through the Main class, browser type, the path to the data file and the address of the start page are set in the Main class.