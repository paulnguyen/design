
# REF:  http://ruby-doc.com/docs/ProgrammingRuby/html/tut_classes.html

# Specifying Access Control
#
# You specify access levels to methods within class or module definitions using 
# one or more of the three functions public, protected, and private. Each function 
# can be used in two different ways.
#
# If used with no arguments, the three functions set the default access control 
# of subsequently defined methods. This is probably familiar behavior if you're 
# a C++ or Java programmer, where you'd use keywords such as public to achieve 
# the same effect. 

class MyClass

      def method1    # default is 'public'
        #...
      end

  protected          # subsequent methods will be 'protected'
      def method2    # will be 'protected'
        #...
      end

  private            # subsequent methods will be 'private'
      def method3    # will be 'private'
        #...
      end

  public             # subsequent methods will be 'public'
      def method4    # and this will be 'public'
        #...
      end
end


# Alternatively, you can set access levels of named methods by listing them as arguments 
# to the access control functions. 

class MyClass

  def method1
  end

  # ... and so on
  public    :method1, :method4
  protected :method2
  private   :method3
end

# A class's initialize method is automatically declared to be private. 
