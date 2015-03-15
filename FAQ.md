# Frequently Asked Questions #

This section is frequently updated based on findings in the community and the most common questions received.

If your question is not answered by the list bellow, please feel free to post an issue under [Issues](Issues.md) section.


## Setup ##
  1. How do I get started?
    * See [Tutorial](http://code.google.com/p/openpojo/wiki/Tutorial)
  1. How do I configure my eclipse project against the source code of OpenPojo?
    * Checkout the code:
      * svn checkout http://openpojo.googlecode.com/svn/trunk/ openpojo-read-only
    * Download and Install maven (http://maven.apache.org/download.html)
    * Download and install eclipse (http://www.eclipse.org/downloads/)
    * Open a command prompt and go to the folder where you check'ed out the code.
      * run mvn eclipse:eclipse
    * Start eclipse and import the project.

## Usage ##
  1. [How do I generate a random instance of my class?](GenerateRandomInstance.md)
  1. [How do I get a list of all classes in a given package OR How to I filter the list of classes I am getting?](PojoFiltering.md)
  1. [Field Prefixes are causing OpenPojo to faill getters / setters lookup, Help!!](PojoFieldPrefixes.md)

**Have a question that was not answered?**
  * Post a comment on this page and I'll answer you.