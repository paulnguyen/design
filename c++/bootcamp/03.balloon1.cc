
#include <iostream>
using namespace std;

// Class declaration; often in .h file 

class Balloon { 

	public:
        Balloon ();
        Balloon (string colour);
        virtual ~Balloon();
        void speak() const;  
    	// ref: http://www.cprogramming.com/tutorial/const_correctness.html
    	
    private:
        string colour;
};

// Method definitions; often in a different file (.cc) 

Balloon::Balloon () { 
	// ctors (better way: use initializers)
	this->colour = "clear"; 
}

Balloon::Balloon (string colour) { 
	this->colour = colour;
}

Balloon::~Balloon (){}  // dtor

void Balloon::speak() const {
	cout << "I'm a " << this->colour << " balloon!\n"; 
}

// What happens here?

int main (int argc, char* argv[]) {

    Balloon rb ("red");
    rb.speak();
    
    Balloon cb;
    cb.speak();
    
    Balloon* gb = new Balloon ("green");
    // gb->speak();  // private
    
    Balloon* ob = gb;
    ob->speak();
    // ob->colour = "blue"; // private
    
    delete ob;
	// delete gb;  // error

}