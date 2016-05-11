
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

As we’ve seen, Math is a grab-bag of number-related utility functions, 
such as Math.max (maximum), Math.min (minimum), and Math.sqrt (square root).

The Math object is used simply as a container to group a bunch of 
related functionality. There is only one Math object, and it is almost 
never useful as a value. Rather, it provides a namespace so that all these 
functions and values do not have to be global variables.

Having too many global variables “pollutes” the namespace. The more names 
that have been taken, the more likely you are to accidentally overwrite 
the value of some variable. For example, it’s not unlikely that you’ll 
want to name something max in one of your programs. Since JavaScript’s 
built-in max function is tucked safely inside the Math object, we don’t 
have to worry about overwriting it.

**/


/*

if you need to do trigonometry, Math can help. It contains cos (cosine), 
sin (sine), and tan (tangent), as well as their inverse functions, acos, 
asin, and atan, respectively. The number π (pi)—or at least the closest 
approximation that fits in a JavaScript number—is available as Math.PI. 
(There is an old programming tradition of writing the names of constant 
values in all caps.)

*/

function randomPointOnCircle(radius) {
  var angle = Math.random() * 2 * Math.PI;
  return {x: radius * Math.cos(angle),
          y: radius * Math.sin(angle)};
}
console.log(randomPointOnCircle(2));	// → {x: 0.3667, y: 1.966}

/*

The previous example uses Math.random. This is a function that 
returns a new pseudorandom number between zero (inclusive) 
and one (exclusive) every time you call it.

*/

console.log(Math.random());		// → 0.36993729369714856
console.log(Math.random());		// → 0.727367032552138
console.log(Math.random());		// → 0.40180766698904335

/*

If we want a whole random number instead of a fractional 
one, we can use Math.floor (which rounds down to the nearest 
whole number) on the result of Math.random.

*/

console.log(Math.floor(Math.random() * 10));	// → 2
