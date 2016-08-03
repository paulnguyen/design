=begin 

Define your own method, double, that accepts 
a single parameter and yields to a block. 

Then call it with a block that multiplies 
the number parameter by 2. 

You can double any number you like!

=end

def double ( p )
    yield(p)    
end

double (10) { |n| n*2 }
