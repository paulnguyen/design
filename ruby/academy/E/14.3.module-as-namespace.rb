
=begin 


Resolve to Keep Learning

One of the main purposes of modules is to separate methods and constants into named spaces. This is called (conveniently enough) namespacing, and it's how Ruby doesn't confuse Math::PI and Circle::PI.

See that double colon we just used? That's called the scope resolution operator, which is a fancy way of saying it tells Ruby where you're looking for a specific bit of code. If we say Math::PI, Ruby knows to look inside the Math module to get that PI, not any other PI (such as the one we created in Circle).

Let's get some practice in with an existing Ruby module: Math.
Instructions

Use the scope resolution operator to puts the value of PI from the Math module to the console.

=end


# Write your code below!

puts Math::PI