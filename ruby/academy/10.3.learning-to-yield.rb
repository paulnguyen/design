=begin 


Learning to Yield

Why do some methods accept a block and others don't? 
It's because methods that accept blocks have a way 
of transferring control from the calling method 
to the block and back again. 

We can build this into the methods 
we define by using the yield keyword.


Check out the code in the editor. We've set up a series 
of puts statements so you can see when we're in the 
block_test method and when we're in the block passed to it. 


=end 


def block_test
  puts "We're in the method!"
  puts "Yielding to the block..."
  yield
  puts "We're back in the method!"
end

block_test { puts ">>> We're in the block!" }