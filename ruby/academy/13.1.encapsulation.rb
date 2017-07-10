
=begin 


Need-to-Know Basis

You might be wondering why we need to hide information in Ruby. Isn't it okay for every part of your Ruby program to know what every other part is doing?

Possibly, if you're the only one writing and using your software. But when other people are working on or using your programs, they may attempt to muck around with the way different parts of your program do their jobs. You don't want that!

For this reason, Ruby allows you to explicitly make some methods public and others private. Public methods allow for an interface with the rest of the program; they say, "Hey! Ask me if you need to know something about my class or its instances."

Private methods, on the other hand, are for your classes to do their own work undisturbed. They don't want anyone asking them anything, so they make themselves unreachable!
Instructions

Check out the code in the editor. We've created a public about_me method and a private bank_account_number method. Click Save & Submit Code to see what happens when we try to call the bank_account_number method from outside the class!

=end


class Person
  def initialize(name, age)
    @name = name
    @age = age
  end
  
  public    # This method can be called from outside the class.
  
  def about_me
    puts "I'm #{@name} and I'm #{@age} years old!"
  end
  
  private   # This method can't!
  
  def bank_account_number
    @account_number = 12345
    puts "My bank account number is #{@account_number}."
  end
end

eric = Person.new("Eric", 26)
eric.about_me
eric.bank_account_number

