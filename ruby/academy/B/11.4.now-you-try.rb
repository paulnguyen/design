
=begin 


Now You Try!

Great work! You've written your own lambda and seen 
how to pass it to a method. Now it's time for you 
to write a lambda and pass it to a method!

If you think this will be a lot like what you've 
already done with procs, you're exactly right. 
Just like with procs, we'll need to put & at 
the beginning of our lambda name when we pass 
it to the method, since this will convert the 
lambda into the block the method expects.

That symbolize lambda was pretty cool. Let's 
riff on it with a lambda that checks to see 
if each element in an array is a symbol. 
We can do this checking with the .is_a? 
method, which returns true if an object 
is the type of object named and false otherwise:

:hello.is_a? Symbol
# ==> true

The word Symbol has to be capitalized when you're doing an .is_a? check!



1. 	Create a lambda, symbol_filter, that takes one parameter and checks 
	to see if that parameter .is_a? Symbol.

2.	Create a new variable called symbols, and store the result of calling
	my_array.select and passing it your lambda.


my_array = ["raindrops", :kettles, "whiskers", :mittens, :packages]

# Add your code below!

=end

my_array = ["raindrops", :kettles, "whiskers", :mittens, :packages]

symbol_filter = lambda { |p| p.is_a? Symbol }
symbols = my_array.select( &symbol_filter )
