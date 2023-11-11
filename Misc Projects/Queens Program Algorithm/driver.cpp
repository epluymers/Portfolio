#include "Queens.h"
#include <iostream>
using namespace std;

int main()
{
	Queens newgame = Queens();
	newgame.print();
	
	if(newgame.findSolution() == true)
	{
		newgame.print();
		cout << endl;
	}
	else
	{
		cout << "Min Conflict Algorithm is in infinite loop. Ending Program." << endl;
	}
	
	//End Program
	return 0;
}