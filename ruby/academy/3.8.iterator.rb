
=begin 

The .each Iterator

Great work! You're really getting the hang of this.

The loop iterator is the simplest, but also one of the least powerful. 
A more useful iterator is the .each method, which can apply an expression 
to each element of an object, one at a time. The syntax looks like this:

	object.each { |item| # Do something }

You can also use the do keyword instead of {}:

	object.each do |item| # Do something end

The variable name between | | can be anything you like: it's just a placeholder 
for each element of the object you're using .each on.

=end


# Each Iterator
array = [1,2,3,4,5]
array.each do |x|
  x += 10
  print "#{x}\n"
end

# Times Iterator
10.times { print "Chunky bacon!\n" }