=begin 


The Right Tool for the Job

Sooner or later, you're going to need to perform a repetitive task in your programs. Many programming languages allow you to do this with a for loop, and while Ruby does include for loops, there are better tools available to us.

If we want to do something a specific number of times, we can use the .times method, like so:

5.times { puts "Odelay!" }
# Prints 5 "Odelay!"s on separate lines

If we want to repeat an action for every element in a collection, we can use .each:

[1, 2, 3].each { |x| puts x * 10 }
# Prints 10, 20, 30 on separate lines

Let's get a little inventive. Write a loop that only puts the even values of my_array. (Bonus points if you use a one-line if!)

=end


my_array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# Using Modulus Operator
my_array.each { |x| puts x if x % 2 == 0 }

# Using Evens Method from Integer Class
# http://ruby-doc.org/core-1.9.3/Integer.html#method-i-even-3F
my_array.each { |x| puts x if x.even? }