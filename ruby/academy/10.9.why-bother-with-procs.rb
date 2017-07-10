=begin 


Why Procs?

Why bother saving our blocks as procs? There are two main advantages:

    1. 	Procs are full-fledged objects, so they have all the powers and 
		abilities of objects. (Blocks do not.)

    2.	Unlike blocks, procs can be called over and over without rewriting 
		them. This prevents you from having to retype the contents of your 
		block every time you need to execute a particular bit of code.


Check out the code in the editor. Man, we had to rewrite that block a bunch of times! Remove the blocks and replace them with a proc called over_4_feet so that the code in the block only needs to be written once.

# Here at the amusement park, you have to be four feet tall
# or taller to ride the roller coaster. Let's use .select on
# each group to get only the ones four feet tall or taller.

group_1 = [4.1, 5.5, 3.2, 3.3, 6.1, 3.9, 4.7]
group_2 = [7.0, 3.8, 6.2, 6.1, 4.4, 4.9, 3.0]
group_3 = [5.5, 5.1, 3.9, 4.3, 4.9, 3.2, 3.2]

# Complete this as a new Proc
over_4_feet = ________________

# Change these three so that they use your new over_4_feet Proc
can_ride_1 = group_1.select { |height| height >= 4 }
can_ride_2 = group_2.select { |height| height >= 4 }
can_ride_3 = group_3.select { |height| height >= 4 }

=end


# Here at the amusement park, you have to be four feet tall
# or taller to ride the roller coaster. Let's use .select on
# each group to get only the ones four feet tall or taller.

group_1 = [4.1, 5.5, 3.2, 3.3, 6.1, 3.9, 4.7]
group_2 = [7.0, 3.8, 6.2, 6.1, 4.4, 4.9, 3.0]
group_3 = [5.5, 5.1, 3.9, 4.3, 4.9, 3.2, 3.2]

# Complete this as a new Proc
over_4_feet = Proc.new { |height| height >= 4 }

# Change these three so that they use your new over_4_feet Proc
can_ride_1 = group_1.select &over_4_feet  
can_ride_2 = group_2.select &over_4_feet
can_ride_3 = group_3.select &over_4_feet

puts can_ride_1 ; puts
puts can_ride_2 ; puts
puts can_ride_3 ; puts


