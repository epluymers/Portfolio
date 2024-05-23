#include <iostream>
#include <stdio.h>

using namespace std;

int main()
{
  char ch;

  system("stty -echo"); // supress echo
  system("stty cbreak"); // go to RAW mode
  cin.get(ch); // or something like that
  
  
  
  system ("stty echo"); // Make echo work
  system("stty -cbreak");// go to COOKED mode

}