

## http://awaxman11.github.io/blog/2013/08/05/what-is-the-difference-between-a-block/

# Block Examples

[1,2,3].each { |x| puts x*2 }   # block is in between the curly braces

[1,2,3].each do |x|
  puts x*2                    # block is everything between the do and end
end

# Proc Examples  
           
p = Proc.new { |x| puts x*2 }
[1,2,3].each(&p)              # The '&' tells ruby to turn the proc into a block 

proc = Proc.new { puts "Hello World" }
proc.call                     # The body of the Proc object gets executed when called

# Lambda Examples     
       
lam = lambda { |x| puts x*2 }
[1,2,3].each(&lam)

lam = lambda { puts "Hello World" }
lam.call

# Clousure: Example of Proc objects preserving local context

n=10
def counter
  n = 0
  return Proc.new { n+= 1 }
end

a = counter
puts a.call            # returns 1
puts a.call            # returns 2

b = counter
puts b.call            # returns 1

puts a.call            # returns 3


=begin 

Summary Differences

    Procs are objects, blocks are not
    At most one block can appear in an argument list
    Lambdas check the number of arguments, while procs do not
    Lambdas and procs treat the ‘return’ keyword differently

=end