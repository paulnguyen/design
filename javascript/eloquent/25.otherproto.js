/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

Many objects don’t directly have Object.prototype 
as their prototype, but instead have another object, 
which provides its own default properties. 
Functions derive from Function.prototype, 
and arrays derive from Array.prototype.

**/

console.log(Object.getPrototypeOf(isNaN) ==
            Function.prototype);
// → true
console.log(Object.getPrototypeOf([]) ==
            Array.prototype);
// → true

/**

Such a prototype object will itself have a prototype, 
often Object.prototype, so that it still indirectly 
provides methods like toString.

The Object.getPrototypeOf function obviously returns 
the prototype of an object. You can use Object.create 
to create an object with a specific prototype.

**/

var protoRabbit = {
  speak: function(line) {
    console.log("The " + this.type + " rabbit says '" +
                line + "'");
  }
};
var killerRabbit = Object.create(protoRabbit);
killerRabbit.type = "killer";
killerRabbit.speak("SKREEEE!");
// → The killer rabbit says 'SKREEEE!'

/** 

The “proto” rabbit acts as a container for the properties 
that are shared by all rabbits. An individual rabbit object, 
like the killer rabbit, contains properties that apply only 
to itself—in this case its type—and derives shared properties 
from its prototype.

**/