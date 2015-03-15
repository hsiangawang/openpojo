## Up Coming features in no particular order ##
  * Enhancements to testing aspect.
    * Add the ability to test / enforce POJO's to be Immutable.
    * Add the ability to filter PojoClass based on source path.
    * Add the ability to read / write values for PojoClass easily for testing purpose.
    * Add the ability to invoke methods on PojoClass easily for testing.
    * Extend the framework to return all boundary conditions for a given type, not just a random value.
      * For example, if testing int, then test min, zero, max, ...etc.
  * Code cleanup
    * Better handling for JDK 1.6 collection's generation vs. 1.5.
    * Rewire static references for ServiceRegistrar, allowing greater configurability and better decoupling.

**Note:** If there is something you'd like to see that isn't listed please drop me a line or open a ticket in the [Issues](http://code.google.com/p/openpojo/issues) section.