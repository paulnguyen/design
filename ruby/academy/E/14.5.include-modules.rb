
=begin 


Feeling Included

We can do more than just require a module, however. We can also include it!

Any class that includes a certain module can use those module's methods!

A nice effect of this is that you no longer have to prepend your constants and methods with the module name. Since everything has been pulled in, you can simply write PI instead of Math::PI.
Instructions

In our case, we want to use Math::cos but we don't want to type Math::.

Please include Math on line 2.


=end

class Angle

  include Math			# Include Math Module
  
  attr_accessor :radians
  
  def initialize(radians)
    @radians = radians
  end
  
  def cosine
    cos(@radians)
  end
end

acute = Angle.new(1)
acute.cosine