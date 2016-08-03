
=begin 


Quick Review

All this talk of blocks, procs, and lambdas might have your head spinning. 
Let's take a minute to clarify exactly what each one is:

    1. 	A block is just a bit of code between do..end or {}. 
		It's not an object on its own, but it can be passed 
		to methods like .each or .select.
    
	2. 	A proc is a saved block we can use over and over.

    3.	A lambda is just like a proc, only it cares about 
		the number of arguments it gets and it returns 
		to its calling method rather than returning immediately.

There are obviously lots of cases in which blocks, procs, and lambdas 
can do similar work, but the exact circumstances of your program 
will help you decide which one you want to use.


=end


=begin

The odds_n_ends array is full of all sorts of objects, 
but we only want the integers (numbers without decimal bits).

Create a new variable, ints, and store the result of calling 
odds_n_ends.select method with a block that checks if 
the element .is_a? Integer.

=end

odds_n_ends = [:weezard, 42, "Trady Blix", 3, true, 19, 12.345]
ints = odds_n_ends.select { |p| p.is_a? Integer }
# => [42, 3, 19]


=begin


Creating a Proc

Now let's tackle procs. First step: create one!
Instructions

Create a proc called under_100 that checks if a 
number it's passed is less than 100. 

(We'll handle passing this proc the .select method 
in the next exercise—we won't need to do anything 
with the ages array just yet.)

=end

ages = [23, 101, 7, 104, 11, 94, 100, 121, 101, 70, 44]

under_100 = Proc.new { |p| p < 100 }


=begin 


Passing Your Proc to a Method

Perfect! Now let's pass our proc to a method.
Instructions

Now we'll be using the ages array. Create a variable 
called youngsters and set it equal to calling .select on ages, 
and pass in your under_100 proc to filter for the ages that 
are less than one hundred. Remember to pass &under_100 
to convert your proc to a block!

=end


ages = [23, 101, 7, 104, 11, 94, 100, 121, 101, 70, 44]

under_100 = Proc.new { |p| p < 100 }
youngsters = ages.select &under_100 


=begin 

Creating a Lambda

Second verse: same as the first! Let's go ahead and make ourselves a lambda.
Instructions

Create a lambda called first_half that checks if a hash value is less 
than (that is, earlier in the alphabet than) "M". (No need to do anything 
with the crew hash yet.) Make sure to use a capital "M," since we'll 
be checking capitalized names!

Because it will be checking a hash, your lambda should 
include |key, value| instead of just |value|.

=end

crew = {
  captain: "Picard",
  first_officer: "Riker",
  lt_cdr: "Data",
  lt: "Worf",
  ensign: "Ro",
  counselor: "Troi",
  chief_engineer: "LaForge",
  doctor: "Crusher"
}

first_half = lambda { |k,v| v < 'M' }


=begin 


Passing Your Lambda to a Method

Home stretch! Now let's pass our lambda to a method, as well.
Instructions

To finish up, let's go ahead and create a variable called 
a_to_m and set it equal to calling .select on crew, and 
pass in your first_half lambda to filter for the names 
that are before "M" in the alphabet. Remember to pass 
&first_half to convert your lambda to a block!

=end

crew = {
  captain: "Picard",
  first_officer: "Riker",
  lt_cdr: "Data",
  lt: "Worf",
  ensign: "Ro",
  counselor: "Troi",
  chief_engineer: "LaForge",
  doctor: "Crusher"
}

first_half = lambda { |k,v| v < 'M' }
a_to_m = crew.select &first_half 


