
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

**/


/**

Whenever a function is called, a special variable named 
arguments is added to the environment in which the function 
body runs. This variable refers to an object that holds 
all of the arguments passed to the function. Remember 
that in JavaScript you are allowed to pass more 
(or fewer) arguments to a function than the number 
of parameters the function itself declares.

**/

function noArguments() {}
noArguments(1, 2, 3); // This is okay
function threeArguments(a, b, c) {}
threeArguments(); // And so is this


/**

The arguments object has a length property that tells us 
the number of arguments that were really passed to the function. 
It also has a property for each argument, named 0, 1, 2, and so on.

If that sounds a lot like an array to you, you’re right, it is a 
lot like an array. But this object, unfortunately, does not have 
any array methods (like slice or indexOf), so it is a little 
harder to use than a real array.

**/


function argumentCounter() {
  console.log("You gave me", arguments.length, "arguments.");
}
argumentCounter("Straw man", "Tautology", "Ad hominem");	// → You gave me 3 arguments.


/**

Some functions can take any number of arguments, like console.log. 
These typically loop over the values in their arguments object. 
They can be used to create very pleasant interfaces. For example, 
remember how we created the entries to Jacques’ journal.

**/

var journal = [];

function addEntry(squirrel) {
  var entry = {events: [], squirrel: squirrel};
  for (var i = 1; i < arguments.length; i++)
    entry.events.push(arguments[i]);
  journal.push(entry);
}
addEntry(true, "work", "touched tree", "pizza",
         "running", "television");
console.log( journal ) ;



         

