# Making Testing Easy #

# Introduction #
So you've written say a few POJOs to map out certain domain model entities (i.e. Person, Expense, ...etc).
Each one of those entities has a set of private fields that make up the entity (i.e. Person.firstname, Person.lastname, etc...).

You've used your IDE to generate setters and getters on those, so you're 100% sure that things are perfect.

Now you start saving Person to the database, so typically you just create a table with those fields mapped to columns.

And then it hits you keep getting the following for lastname
  * Person.lastname = "twain"
  * Person.lastname = "Twain"
  * Person.lastname = "TWAIN"
  * ...etc.

So you decide the lastname needs to be normalized...

So now you have the ability to put this logic in the service layer where comparisons take place (best option), but you don't, because the service logic would take too much time.

So due to various external forces you (well not really you) decide to just modify Person.setLastname to have it lower the string before holding on to it, problem solved (or is it?!).

Now if you had a way to test EVERY POJO you have for how they should behave, you'd increase your coverage dramatically, as well as ensure that this kind of code plaque never creeps into your code base.

# So how is testing easier? #

Suppose you want to enforce the following rule
  * I want all my POJOs to never have public fields and enforce this through testing. 

All you need to do is create such a rule which would look like this:

```
public final class NoPublicFieldsRule implements Rule {

    @Override
    public void evaluate(PojoClass pojoClass) {
        for (PojoField fieldEntry : pojoClass.getPojoFields()) {
            if (fieldEntry.isPublic()) {
                Assert.fail("Public fields not allowed " + fieldEntry);
            }
        }
    }
}

```

Now all that is left is that in your test, get a list of your POJO classes and run this rule on them, something like that:

```
    @Test
    public void myTest() {
        pojoValidator = new PojoValidator();
        pojoValidator.addRule(new NoPublicFieldsRule());

        pojoClasses = PojoClassFactory.getPojoClasses("com.mypackage");
        for (PojoClass entry : pojoClasses) {
            pojoValidator.runValidation(entry);
        }
    }
```

And that is all there is to it...