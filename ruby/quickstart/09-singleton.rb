
# REF:  http://ruby-doc.com/docs/ProgrammingRuby/html/tut_classes.html


# Singletons and Other Constructors

# Sometimes you want to override the default way in which Ruby creates objects. 
# As an example, let's look at our jukebox. Because we'll have many jukeboxes, 
# spread all over the country, we want to make maintenance as easy as possible. 
# Part of the requirement is to log everything that happens to a jukebox: the songs 
# that are played, the money received, the strange fluids poured into it, and so on. 
# Because we want to reserve the network bandwidth for music, we'll store these 
# logfiles locally. This means we'll need a class that handles logging. However, 
# we want only one logging object per jukebox, and we want that object to be shared 
# among all the other objects that use it.

# Enter the Singleton pattern, documented in Design Patterns . We'll arrange things 
# so that the only way to create a logging object is to call Logger.create, and we'll 
# ensure that only one logging object is ever created. 

class Logger
  private_class_method :new
  @@logger = nil
  def Logger.create
    @@logger = new unless @@logger
    @@logger
  end
end

# By making Logger's method new private, we prevent anyone from creating a logging 
# object using the conventional constructor. Instead, we provide a class method, 
# Logger.create. This uses the class variable @@logger to keep a reference to a single 
# instance of the logger, returning that instance every time it is called.
# [The implementation of singletons that we present here is not thread-safe; if multiple 
# threads were running, it would be possible to create multiple logger objects. Rather 
# than add thread safety ourselves, however, we'd probably use the Singleton mixin supplied 
# with Ruby, which is documented on page 468.] We can check this by looking at the object 
# identifiers the method returns. 

a = Logger.create
b = Logger.create
a.inspect
b.inspect