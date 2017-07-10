=begin 


What if we wanted to sort the books by title, but from Z – A, 
or descending order? It appears that Ruby's sort method 
only works for A – Z, or ascending order.

The sort method assumes by default that you want to sort 
in ascending order, but it accepts a block as an optional 
argument that allows you, the programmer, to specify how t
wo items should be compared.


=end


books = [
	"Charlie and the Chocolate Factory", 
	"War and Peace", "Utopia", 
	"A Brief History of Time", 
	"A Wrinkle in Time"]

# To sort our books in ascending order, in-place
books.sort! { |firstBook, secondBook| firstBook <=> secondBook }

# Sort your books in descending order, in-place below

books.sort! { |firstBook, secondBook|  secondBook <=> firstBook }