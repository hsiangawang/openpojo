# Testing HowTo #

## Requirements ##
  1. Java 5 or above.
  1. Optional
    1. JUnit 4.11+, Or TestNG 6.8.8+, if neither is present, an AssertionError will be thrown when a test fails.
    1. SLF4J (1.7.7+), or Log4j (1.2.17+), if neither is present, Java's Logging will be utilized instead.

## Setup ##
### Get Jar ###
  * If you use Maven use the following
```
  <dependency>
    <groupId>com.googlecode.openpojo</groupId>
    <artifactId>openpojo</artifactId>
    <version>0.6.5</version>
  </dependency>
```
Or
  * Download the latest OpenPojo ([Zip](http://bit.ly/1oiDOun))

### Next ###
  1. Identify the list of packages that have the POJOs you want to test.
  1. Define classes to implement rules you want evaluate against see built in set in "com.openpojo.validation.rule" package under src folder.
  1. Define Testers to implement the behaviors you want to run your POJOs through see the built in set in "com.openpojo.validation.test" package under src folder.
  1. In your "setup" method in your JUnit class, create and instance of PojoValidator class and add all the rules and testers to it.
  1. For each PojoClass run your PojoValidator against it.

## Example ##
See com.openpojo.samplepojo.Person for a sample POJO and com.openpojo.samplepojo.PojoTest under example folder for a sample implementation.  Here is what the code looks like:

```
public class PojoTest {
    // Configured for expectation, so we know when a class gets added or removed.
    private static final int EXPECTED_CLASS_COUNT = 1;

    // The package to test
    private static final String POJO_PACKAGE = "com.openpojo.samplepojo";

    private List<PojoClass> pojoClasses;
    private PojoValidator pojoValidator;

    @Before
    public void setup() {
        pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE, new FilterPackageInfo());

        pojoValidator = new PojoValidator();

        // Create Rules to validate structure for POJO_PACKAGE
        pojoValidator.addRule(new NoPublicFieldsRule());
        pojoValidator.addRule(new NoPrimitivesRule());
        pojoValidator.addRule(new NoStaticExceptFinalRule());
        pojoValidator.addRule(new GetterMustExistRule());
        pojoValidator.addRule(new SetterMustExistRule());
        pojoValidator.addRule(new NoNestedClassRule());
        pojoValidator.addRule(new BusinessKeyMustExistRule());

        // Create Testers to validate behaviour for POJO_PACKAGE
        pojoValidator.addTester(new DefaultValuesNullTester());
        pojoValidator.addTester(new SetterTester());
        pojoValidator.addTester(new GetterTester());
        pojoValidator.addTester(new BusinessIdentityTester());
    }

    @Test
    public void ensureExpectedPojoCount() {
        Affirm.affirmEquals("Classes added / removed?", EXPECTED_CLASS_COUNT, pojoClasses.size());
    }

    @Test
    public void testPojoStructureAndBehavior() {
        for (PojoClass pojoClass : pojoClasses) {
            pojoValidator.runValidation(pojoClass);
        }
    }
}
```