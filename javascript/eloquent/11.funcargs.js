
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/05_higher_order.html


Passing along arguments

The noisy function defined earlier, which wraps its 
argument in another function, has a rather serious deficit.


**/


function noisy(f) {
  return function(arg) {
    var val = f(arg);
    return val;
  };
}

function test(arg1, arg2, arg3) {
	console.log ( arg1, arg2, arg3 ) ;
}
noisy(test)(0,1,2);
// → 0 undefined undefined

/**

If f takes more than one parameter, it gets only the first one. 
We could add a bunch of arguments to the inner function (arg1, 
arg2, and so on) and pass them all to f, but it is not clear 
how many would be enough. This solution would also deprive 
f of the information in arguments.length. Since we’d always 
pass the same number of arguments, it wouldn’t know how many 
arguments were originally given.

For these kinds of situations, JavaScript functions have an 
apply method. You pass it an array (or array-like object) 
of arguments, and it will call the function with those arguments.

**/

function transparentWrapping(f) {
  return function() {
    return f.apply(null, arguments);
  };
}


transparentWrapping(test)(0,1,2) ;
// → 0 1 2

/**

That’s a useless function, but it shows the pattern we are 
interested in—the function it returns passes all of the given 
arguments, and only those arguments, to f. It does this by 
passing its own arguments object to apply. The first argument 
to apply, for which we are passing null here, can be used 
to simulate a method call. 

**/