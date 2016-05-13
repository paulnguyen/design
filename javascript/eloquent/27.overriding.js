/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

Overriding derived properties

When you add a property to an object, whether it 
is present in the prototype or not, the property 
is added to the object itself, which will 
henceforth have it as its own property. 

If there is a property by the same name 
in the prototype, this property will no 
longer affect the object. The prototype 
itself is not changed.

**/

function Rabbit(type) {
  this.type = type;
}

var killerRabbit = new Rabbit("killer");
var blackRabbit = new Rabbit("black");


Rabbit.prototype.teeth = "small";
console.log(killerRabbit.teeth);
// → small

killerRabbit.teeth = "long, sharp, and bloody";
console.log(killerRabbit.teeth);
// → long, sharp, and bloody

console.log(blackRabbit.teeth);
// → small

console.log(Rabbit.prototype.teeth);
// → small


/** 

The following diagram sketches the situation after this 
code has run. The Rabbit and Object prototypes lie behind 
killerRabbit as a kind of backdrop, where properties that 
are not found in the object itself can be looked up.

See: 27.overriding.png

**/


/**

Overriding properties that exist in a prototype is often 
a useful thing to do. As the rabbit teeth example shows, 
it can be used to express exceptional properties in 
instances of a more generic class of objects, while 
letting the nonexceptional objects simply take a 
standard value from their prototype.

It is also used to give the standard function and array 
prototypes a different toString method than the basic 
object prototype.

**/

console.log(Array.prototype.toString ==
            Object.prototype.toString);
// → false

console.log([1, 2].toString());
// → 1,2


/**

Calling toString on an array gives a result similar 
to calling .join(",") on it—it puts commas between 
the values in the array. Directly calling 
Object.prototype.toString with an array produces 
a different string. That function doesn’t know about 
arrays, so it simply puts the word “object” and 
the name of the type between square brackets

**/

console.log(Object.prototype.toString.call([1, 2]));
// → [object Array]

console.log([1, 2].toString());
// → 1,2

console.log(Array.prototype.toString.call([1, 2]));
// → 1,2






