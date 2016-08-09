=begin 

We've often found we only want the key or value 
associated with a key/value pair, and it's kind 
of a pain to put both into our block and only 
work with one. Can we iterate over just keys 
or just values?

This is Ruby. Of course we can.

Ruby includes two hash methods, 

	.each_key and 
	.each_value, 

that do exactly what you'd expect:

=end

my_hash = { one: 1, two: 2, three: 3 }

my_hash.each_key { |k| print k, " " }
# ==> one two three

my_hash.each_value { |v| print v, " " }
# ==> 1 2 3