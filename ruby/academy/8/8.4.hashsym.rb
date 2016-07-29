=begin 


All Aboard the Hash Rocket!

The hash syntax you've seen so far 
(with the => symbol between keys and values) 
is sometimes nicknamed the hash rocket style



=end

numbers = {
  :one => 1,
  :two => "two",
  :three => 3,
}


=begin 

However, the hash syntax has changed in Ruby 1.9. 
Just when you were getting comfortable!

The two changes are:

1. You put the colon at the end of the symbol, not at the beginning;
2. You don't need the hash rocket anymore.

=end

new_hash = { 
  one: 1,
  two: "two",
  three: 3
}