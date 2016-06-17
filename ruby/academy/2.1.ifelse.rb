
=begin

Ruby's if statement takes an expression, which is just a fancy word 
for something that has a value (like 4, true, or pants). 
If that expression is true, Ruby executes the block of code 
that follows the if. If it's not true (that is, false), 
Ruby doesn't execute that block of code: it skips it and goes on to the next thing.

	if ( 10 > 1 )
	    print "TRUE"
	else
	    print "FALSE"
	end

The partner to the if statement is the else statement. 
An if/else statement says to Ruby: "If this expression is true, 
run this code block; otherwise, run the code after the else statement." 
Here's an example:

	if ( 10 > 1 )
	    print "TRUE"
	else
	    print "FALSE"
	end

What if you want more than two options, though? It's elsif to the rescue! The
elsif statement can add any number of alternatives to an if/else statement,
like so:

	if ( 10 > 1 )
	    print "TRUE"
	elsif ( 10 < 1 )
	    print "NO!"
	else
	    print "FALSE"
	end

=end

print "Integer please: "
user_num = Integer(gets.chomp)

if user_num < 0
  puts "You picked a negative integer!"
elsif user_num > 0
  puts "You picked a positive integer!"
else
  puts "You picked zero!"
end





