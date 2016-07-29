=begin 


Becoming More Selective

We know how to grab a specific value from a hash by specifying 
the associated key, but what if we want to filter a hash for 
values that meet certain criteria? For that, we can use .select.

=end

grades = { 
	alice: 100,
  	bob: 92,
  	chris: 95,
  	dave: 97
}

grades.select { |name, grade| grade < 97}
# ==> {:bob=>92, :chris=>95}

grades.select { |k, v| k == :alice }
# ==> {:alice=>100}
