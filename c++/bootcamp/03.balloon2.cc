
#include <iostream>
using namespace std;

// Class declaration; often in .h file 

class Balloon { 

	public:
	
		// constructors
        Balloon () {
				this->colour = "clear" ; 
        }
        
        Balloon (string c) {
        	this->colour = c ;
        }
        
        // destructors
        virtual ~Balloon() {} 
        
       // public methods
        void speak() const {
        	cout << "I'm a " << this->colour << " balloon!\n"; 
        }  
    
    private:
        string colour ;
};


int main (int argc, char* argv[]) {

    Balloon rb ("red");
    rb.speak();
    
    Balloon cb;
    cb.speak();
    
    Balloon* gb = new Balloon ("green");
    
    Balloon* ob = gb;
    ob->speak();
    
    delete ob;

}