
=begin 


Setting Your Own Default

You don't have to settle for nil as a default value, 
however. If you create your hash using the Hash.new 
syntax, you can specify a default like so:

my_hash = Hash.new("Trady Blix")

Now if you try to access a nonexistent key in my_hash, 
you'll get "Trady Blix" as a result.

REF:  	http://ruby-doc.org/core-1.9.3/Hash.html
		http://ruby-doc.org/core-2.2.3/Hash.html

=end


my_hash = Hash.new("Trady Blix")
puts my_hash["foo"]