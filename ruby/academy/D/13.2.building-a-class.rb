
=begin 


Quick Review: Building a Class

Let's build a class!
	
	class ClassName
	    def MethodName( parameter )
	        @classVariable = parameter
	    end
	end

	1. 	First we tell ruby that we want to construct 
		our class ClassName. Don't forget the end to 
		end your class!
	
	2. 	Inside our ClassName is where we define our 
		methods. Here we have MethodName followed by 
		( parameter ), When we use this method it 
		will look like this MethodName("hello!")
	
	3. 	If our parameter above were "Hello!" it would 
		be assigned to our variable @classVariable on 
		the following line.
	
	4. Again, don't forget to end your MethodName!
	

Instructions:

	1.	First, create a class named Dog

	2. 	Create a method initialize within your class Dog

	3. 	Allow your initialize method to accept two parameters, 
		name and breed

	4.	Inside your initialize method assign those two parameters 
		to the variable @name and @breed respectively


=end



class Dog
   
    def initialize( name, breed )
        @name = name
        @breed = breed
    end   
    
end

