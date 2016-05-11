/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

So who is the prototype of that empty object? 
It is the great ancestral prototype, the entity 
behind almost all objects, Object.prototype.

**/

console.log(Object.getPrototypeOf({}) ==
            Object.prototype);
// → true

console.log(Object.getPrototypeOf(Object.prototype));
// → null


/**

As you might expect, the Object.getPrototypeOf function 
returns the prototype of an object.

The prototype relations of JavaScript objects form a 
tree-shaped structure, and at the root of this structure 
sits Object.prototype. It provides a few methods that 
show up in all objects, such as toString, which converts 
an object to a string representation.

**/