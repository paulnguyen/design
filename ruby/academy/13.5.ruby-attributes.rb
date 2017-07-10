
=begin 


attr_reader, attr_writer

We saw in the lesson on classes that Ruby needs methods in order to access attributes. For instance, if we want to access a @name instance variable, we had to write something like

def name
  @name
end

Well, no longer! We can use attr_reader to access a variable and attr_writer to change it. If we write

class Person
  attr_reader :name
  attr_writer :name
  def initialize(name)
    @name = name
  end
end

Ruby does something like this for us automatically:

def name
  @name
end

def name=(value)
  @name = value
end

Like magic, we can read and write variables as we please! We just pass our instance variables (as symbols) to attr_reader or attr_writer.

(That name= might look funny, but you're allowed to put an = sign in a method name. That's just a Ruby convention saying, "hey, this method sets a value!")
Instructions

We've brought back our Person class with a method for getting @name and a method for changing his or her @job. Go ahead and remove the name and job= methods and add an attr_reader for :name and an attr_writer for :job.

class Person
  def initialize(name, job)
    @name = name
    @job = job
  end
  
  def name
    @name
  end
  
  def job=(new_job)
    @job = new_job
  end
end

=end

class Person
 
  attr_reader :name
  attr_writer :job
 
  def initialize(name, job)
    @name = name
    @job = job
  end
 
end



=begin 


attr_accessor

If we want to both read and write a particular variable, there's an even shorter shortcut than using attr_reader and attr_writer. We can use attr_accessor to make a variable readable and writeable in one fell swoop.
Instructions

Here we have an attr_reader and an attr_writer for :job. Go ahead and replace these with an attr_accessor!

class Person
  attr_reader :name
  attr_reader :job
  attr_writer :job

  def initialize(name, job)
    @name = name
    @job = job
  end
end

=end


class Person
  attr_reader :name
  attr_accessor :job

  def initialize(name, job)
    @name = name
    @job = job
  end
end



