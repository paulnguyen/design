
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/05_higher_order.html

Let’s briefly go back to the final two example 
programs in the introduction. 

The first is self-contained and six lines long.

**/

var total = 0, count = 1;
while (count <= 10) {
  total += count;
  count += 1;
}
console.log(total);

/**

The second relies on two external functions and is one line long.

**/

console.log(sum(range(1, 10)));

/**

Which one is more likely to contain a bug?

**/

function range(start, end, step) {
  if (step == null) step = 1;
  var array = [];

  if (step > 0) {
    for (var i = start; i <= end; i += step)
      array.push(i);
  } else {
    for (var i = start; i >= end; i += step)
      array.push(i);
  }
  return array;
}

function sum(array) {
  var total = 0;
  for (var i = 0; i < array.length; i++)
    total += array[i];
  return total;
}



/**

Abstracting array traversal

Plain functions, as we’ve seen them so far, are a good way to 
build abstractions. But sometimes they fall short.

... it’s easy to write a function that goes over an array 
and calls console.log on every element.

**/

var array = [1, 2, 3];
for (var i = 0; i < array.length; i++) {
  var current = array[i];
  console.log(current);
}

/** 

But what if we want to do something other than logging the elements? 
Since “doing something” can be represented as a function and 
functions are just values, we can pass our action as a function value.

**/

function forEach(array, action) {
  for (var i = 0; i < array.length; i++)
    action(array[i]);
}

forEach(["Wampeter", "Foma", "Granfalloon"], console.log);
// → Wampeter
// → Foma
// → Granfalloon


/** 

Often, you don’t pass a predefined function to forEach 
but create a function value on the spot instead.

**/

var numbers = [1, 2, 3, 4, 5], sum = 0;
forEach(numbers, function(number) {
  sum += number;
});
console.log(sum);
// → 15


/** 

Higher-order functions

Functions that operate on other functions, either by taking 
them as arguments or by returning them, are called higher-order 
functions. If you have already accepted the fact that functions 
are regular values, there is nothing particularly remarkable about 
the fact that such functions exist. The term comes from mathematics, 
where the distinction between functions and other values is taken 
more seriously.

Higher-order functions allow us to abstract over actions, 
not just values. They come in several forms. For example, 
you can have functions that create new functions.

**/

function greaterThan(n) {
  return function(m) { return m > n; };
}
var greaterThan10 = greaterThan(10);
console.log(greaterThan10(11));
// → true

/**

And you can have functions that change other functions.

**/


function noisy(f) {
  return function(arg) {
    console.log("calling with", arg);
    var val = f(arg);
    console.log("called with", arg, "- got", val);
    return val;
  };
}
noisy(Boolean)(0);
// → calling with 0
// → called with 0 - got false

/**

You can even write functions that provide new types of control flow.

**/



function unless(test, then) {
  if (!test) then();
}
function repeat(times, body) {
  for (var i = 0; i < times; i++) body(i);
}

repeat(3, function(n) {
  unless(n % 2, function() {
    console.log(n, "is even");
  });
});
// → 0 is even
// → 2 is even
