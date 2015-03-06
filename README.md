Java-Rummy
==========

A java implementation of the card game Gin-Rummy as part of an optional, ungraded project for COMP 303.
The rules that will be used for this implementation of the game can be found [here](http://www.pagat.com/rummy/ginrummy.html).

The project compiles but suffers from runtime exception errors. Since the project was not graded it served as an experiment in program structure and with the swing UI. You can open any of the diagrams in the diagrams folder using violet UML, which can be found in the tools folder. 

##General Setup (for working on the project)

1. Install git and clone the repository. [Github for Windows](https://windows.github.com/) is a nice tool to use.
2. Clone the repository either through command line or clicking "Clone to Desktop" on the right if you have github for Windows
3. Now you'll have a Java-Rummy folder. Create your eclipse workspace one level above (myworkspace\Java-Rummy) and import the project.


##Milestone 1 Setup

1. Install [the Eclipse Checkstyle plugin](http://eclipse-cs.sourceforge.net/downloads.html).
2. Install [EclEmma plugin](http://eclemma.com/).
3. Make sure Checkstyle is using the /style/Style.xml file from the imported project.
4. To make your life easier, you can also import the code formatter found in /style

##TO DO:


###MILESTONE 1:
1. Complete the implementation of the autoMatch method. This method must always complete in less than 0.1 second... say it has to be O(2^n) where n is the number of cards in the hand.
2. Complete all the test classes and use EclEmma to ensure you achieve 100% statement coverage. You may want to consider implementing a small code generator to generate tests for automatch.
3. Make sure all your code respects the style guidelines

###MILESTONE 2:
1. ~~Define the RummyBot interface~~
2. ~~Design the Driver (will likely go hand in hand with the above)~~
3. ~~Decide on Exceptions vs return types for performing actions that shouldn't be possible to do (ie drawing from an empty discard pile).~~
4. ~~Implement the loggers.~~
   4a. Refactor loggers and replace log statements with bundle.
5. Implement and test functionality
6. Take care of any tasks labelled TODO in eclipse (to see them, look at the menu bar in eclipse. Click Window -> show view -> tasks).

###MILESTONE 3:
* Create all of the user interface elements required for multiple games
* Create the user interface elements required to save and load the game
* Log game events and errors to a log file
* Implement the GUI panels as observers of the game Engine
* Implement the application within a single JFrame: the only additional windows should be modal dialog boxies
  * In other words, the only other windows are:
   1. The startup window (load game or new game)
   2. The window asking the user how many games they want to play (new game)
   3. When the user has to specify where to save/load the game
* Externalize the strings (internationalization)
  * Possibly ask for languages on startup
* When the application works, package it as a self executing .jar file

  #### Notes on milestone 3:
  * Try to avoid implementing your own concurrent code
  * Make the whole UI functional before making it nice
  * Prof clearly wants us to use TaskNav to look things up for GUI


### (Optional) Post final goals
* Complete unimplemented methods
* Finish debugging
* Replace outdated Swing UI with Java FX
* Clean and refactor this mess.
