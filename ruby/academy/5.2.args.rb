
def square(n)
  puts n ** 2
end
square(12)
# ==> prints "144"


=begin

Splat arguments are arguments preceded by a *, 
which signals to Ruby: 

"Hey Ruby, I don't know how many arguments 
there are about to be, but it could be 
more than one."

=end

def what_up(greeting, *bros)
  bros.each { |bro| puts "#{greeting}, #{bro}!" }
end
 
what_up("What up", "Justin", "Ben", "Kevin Sorbo")