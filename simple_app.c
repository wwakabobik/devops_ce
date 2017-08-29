#include <stdio.h>
#include <stdlib.h>

int main(int argc, char * argv[])
{
    int i;
    switch(argc)
    {
	case 3:
	    printf ("%d\r\n", atoi(argv[1])+atoi(argv[2]));
	    return atoi(argv[1])+atoi(argv[2]);
	    break;
	default:
	    printf("Command line has no additional arguments, only %d", argc);
	    return -1;
	    break;
    }
}