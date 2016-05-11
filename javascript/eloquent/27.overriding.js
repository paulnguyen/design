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

**/