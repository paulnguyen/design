/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

A prototype can be used at any time to add new 
properties and methods to all objects based on it. 
For example, it might become necessary for our 
rabbits to dance.

That’s convenient. But there are situations where 
it causes problems. In previous chapters, we used 
an object as a way to associate values with names 
by creating properties for the names and giving 
them the corresponding value as their value. 

Here’s an example:

**/


var map = {};
function storePhi(event, phi) {
  map[event] = phi;
}

storePhi("pizza", 0.069);
storePhi("touched tree", -0.081);

console.log( map ) ;


/** 

We can iterate over all phi values in the object 
using a for/in loop and test whether a name is 
in there using the regular in operator. 
But unfortunately, the object’s prototype gets in the way.

**/

Object.prototype.nonsense = "hi";
for (var name in map)
  console.log(name);
// → pizza
// → touched tree
// → nonsense
console.log("nonsense" in map);
// → true
console.log("toString" in map);
// → true

// Delete the problematic property again
delete Object.prototype.nonsense;

/** 

That’s all wrong. There is no event called “nonsense” 
in our data set. And there definitely is no event called “toString”.

Oddly, toString did not show up in the for/in loop, but the in operator 
did return true for it. This is because JavaScript distinguishes 
between enumerable and nonenumerable properties.

All properties that we create by simply assigning to them are enumerable. 
The standard properties in Object.prototype are all nonenumerable, which is
why they do not show up in such a for/in loop.

It is possible to define our own nonenumerable properties by using the 
Object.defineProperty function, which allows us to control the type 
of property we are creating.

**/

Object.defineProperty(Object.prototype, "hiddenNonsense",
                      {enumerable: false, value: "hi"});
for (var name in map)
  console.log(name);
// → pizza
// → touched tree
console.log(map.hiddenNonsense);
// → hi


/** 

So now the property is there, but it won’t show up in a loop. 
That’s good. But we still have the problem with the regular 
in operator claiming that the Object.prototype properties 
exist in our object. For that, we can use the object’s 
hasOwnProperty method.

**/


console.log(map.hasOwnProperty("toString"));
// → false


/** 

This method tells us whether the object itself has the property, 
without looking at its prototypes. This is often a more useful 
piece of information than what the in operator gives us.

When you are worried that someone (some other code you loaded 
into your program) might have messed with the base object 
prototype, I recommend you write your for/in loops like this:

**/

for (var name in map) {
  if (map.hasOwnProperty(name)) {
    // ... this is an own property
  }
}
