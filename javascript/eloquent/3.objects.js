
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

Values of the type object are arbitrary collections of properties, 
and we can add or remove these properties as we please. One way 
to create an object is by using a curly brace notation.

Inside the curly braces, we can give a list of properties 
separated by commas. Each property is written as a name, 
followed by a colon, followed by an expression that provides 
a value for the property. Spaces and line breaks are not significant. 

When an object spans multiple lines, indenting it like in the previous 
example improves readability. Properties whose names are not valid variable
names or valid numbers have to be quoted.

**/


var day1 = {
  squirrel: false,
  events: ["work", "touched tree", "pizza", "running",
           "television"]
};
console.log(day1.squirrel);		// → false
console.log(day1.wolf);			// → undefined
day1.wolf = false;
console.log(day1.wolf);			// → false


/**

Properties whose names are not valid variable names or valid numbers have to be quoted.

**/

var descriptions = {
  work: "Went to work",
  "touched tree": "Touched a tree"
};

console.log( descriptions["touched tree"] ) ;


/**

Reading a property that doesn’t exist will produce the value undefined, 
which happens the first time we try to read the wolf property in the previous example.

It is possible to assign a value to a property expression with the = operator. 
This will replace the property’s value if it already existed or create a new 
property on the object if it didn’t.

To briefly return to our tentacle model of variable bindings—property bindings 
are similar. They grasp values, but other variables and properties might be 
holding onto those same values. You may think of objects as octopuses with 
any number of tentacles, each of which has a name inscribed on it.

The delete operator cuts off a tentacle from such an octopus. It is a unary operator 
that, when applied to a property access expression, will remove the named property 
from the object. This is not a common thing to do, but it is possible.


**/


var anObject = {left: 1, right: 2};
console.log(anObject.left);			// → 1
delete anObject.left;
console.log(anObject.left);			// → undefined
console.log("left" in anObject);	// → false
console.log("right" in anObject);	// → true


/*

The binary in operator, when applied to a string and an object, returns a 
Boolean value that indicates whether that object has that property. The 
difference between setting a property to undefined and actually deleting 
it is that, in the first case, the object still has the property 
(it just doesn’t have a very interesting value), whereas in the second 
case the property is no longer present and in will return false.

Arrays, then, are just a kind of object specialized for storing sequences 
of things. If you evaluate typeof [1, 2], this produces "object". 
You can see them as long, flat octopuses with all their arms in a 
neat row, labeled with numbers.

*/


var anObject = {left: 1, right: 2};
console.log(anObject.left);			// → 1
delete anObject.left;
console.log(anObject.left);			// → undefined
console.log("left" in anObject);	// → false
console.log("right" in anObject);	// → true


/*

So we can represent Jacques’ journal as an array of objects.

*/


var journal = [
  {events: ["work", "touched tree", "pizza",
            "running", "television"],
   squirrel: false},
  {events: ["work", "ice cream", "cauliflower",
            "lasagna", "touched tree", "brushed teeth"],
   squirrel: false},
  {events: ["weekend", "cycling", "break",
            "peanuts", "beer"],
   squirrel: true},
  /* and so on... */
];

console.log (journal) ;
