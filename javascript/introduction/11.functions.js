/*
	REF:  https://developer.mozilla.org/en-US/docs/Web/JavaScript/A_re-introduction_to_JavaScript

    Along with objects, functions are the core component in understanding
	JavaScript. The most basic function couldn't be much simpler:

*/


function add(x, y) {
  var total = x + y;
  return total;
}

// This demonstrates a basic function. A JavaScript function can take 0 or
// more named parameters. The function body can contain as many statements as
// you like, and can declare its own variables which are local to that
// function. The return statement can be used to return a value at any time,
// terminating the function. If no return statement is used (or an empty
// return with no value), JavaScript returns undefined.

// The named parameters turn out to be more like guidelines than anything
// else. You can call a function without passing the parameters it expects, in
// which case they will be set to undefined.


