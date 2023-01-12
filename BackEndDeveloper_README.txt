BackEndDeveloper README for Project Two (CS400 @ UW Madison)
========================================================

Name of BackEndDeveloper: Xiaohan Zhu
@wisc.edu Email of BackEndDeveloper: xzhu274@wisc.edu
Group: KB
Team: blue

Files Written by Me:
--------------------
BackendInterface.java --- the interface that Backend would implement, including
all the methods Backend.java overrides

Backend.java --- The class reads in the data file by making use of Data Wrangler's
CharacterDataReader. The class stores lists of Character objects in a red black
tree, sorted by the total power level of each Character object. The class implements
BackendInterface and searches for Characters by name, filters Characters by power
level, gets three Characters in the filtered list, etc.

BackEndDeveloperTests.java --- test the functionality of Backend.java to make
sure all constructors and methods return correct outputs.

RedBlackTree.java --- the implementation of a red black tree with a Node inner
class for representing the nodes of the tree. The class helps store all Character
objects in a red black tree. 

Additional Contributions:
-------------------------
I resolved the problem of duplicate objects in the csv data file for the Data Wrangler.
I made changes to CharacterDataReader.java and Character.java so that characters in the
file can all be read in and inserted to the red black tree.

Signature:
----------
Xiaohan Zhu


