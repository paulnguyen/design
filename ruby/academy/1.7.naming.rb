
name="Eric"  # local varables in lower case
first_name="John" # underscore separation (not camel case)

# avoid starting vars with special chars like: @ or $ (Ruby uses these)

$global_variable = 10		# Global Variable

class ClassExample
  def print_global
     puts "Global variable is #$global_variable"
  end
end

class Customer
   def initialize(id, name, addr)
      @cust_id=id					# Instance Variable
      @cust_name=name				# Instance Variable
      @cust_addr=addr				# Instance Variable
   end
   def display_details()
      puts "Customer id #@cust_id"
      puts "Customer name #@cust_name"
      puts "Customer address #@cust_addr"
    end
end

# Create Objects
object1=ClassExample.new()
cust1=Customer.new("1", "John", "Wisdom Apartments, Ludhiya")
cust2=Customer.new("2", "Poul", "New Empire road, Khandala")

# Call Methods
object1.print_global()
cust1.display_details()
cust2.display_details()