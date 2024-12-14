#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define ARRAY_SIZE      256
#define BUFFER_SIZE     1024
#define FILENAME        "input.txt"

/*
    Intcode program: List of integers separated by commas
                     Position 0 has valid codes 1, 2, 99

                     A command has 4 numbers
                     0: opcode
                     1: Position of first num
                     2: Position of second num
                     3: Location to save

                     Code 1: Takes positions 1 and 2, adds them together, and
                        saves them to position 3
                     Code 2: Takes positions 1 and 2, multiplies them together,
                        and saves them to position 3
                     Code 99: Halt
*/

// Get each command and execute until 99 is reached
int runProgram(int* program, int* results) {
    int commandCounter = 0;
    int command = program[commandCounter + 0];
    int pos1 = program[commandCounter + 1];
    int pos2 = program[commandCounter + 2];
    int saveIndex = program[commandCounter + 3];
    
    while (command != 99) {
        if (command == 1) {
            results[saveIndex] = results[pos1] + results[pos2];
        } else if (command == 2) {
            results[saveIndex] = results[pos1] * results[pos2];
        } else {
            printf("Invalid command... Exiting\n");
            exit(EXIT_FAILURE);
        }

        ++commandCounter;
        command = results[4 * commandCounter + 0];
        pos1 = results[4 * commandCounter + 1];
        pos2 = results[4 * commandCounter + 2];
        saveIndex = results[4 * commandCounter + 3];
    }

    return 0;
}

int runTests() {
    int test1[] = {1, 0, 0, 0, 99}; 
        // {2, 0, 0, 0, 99}
    int test2[] = {2, 3, 0, 3, 99}; 
        // {2, 3, 0, 6, 99}
    int test3[] = {2, 4, 4, 5, 99, 0}; 
        // {2, 4, 4, 5, 99, 9801}
    int test4[] = {1, 1, 1, 4, 99, 5, 6, 0, 99}; 
        // {30, 1, 1, 4, 2, 5, 6, 0, 99}
}

int main() {
   FILE* file;

    file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Error opening file %s\n", FILENAME);
        return EXIT_FAILURE;
    }

    // Input comes as CSV of ints
    char buffer[BUFFER_SIZE];
    fgets(buffer, sizeof(buffer), file) != NULL;
    
    // Tokenizing input
    char* tokens = strtok(buffer, ",");
    int program[ARRAY_SIZE];
    int results[ARRAY_SIZE];
    int curIndex = 0;
    while (tokens) {
        int code = atoi(tokens);
        program[curIndex] = code;
        results[curIndex] = code;
        tokens = strtok(NULL, ",");
        ++curIndex;
    }

    // Part 1
    program[1] = 12;
    results[1] = 12;
    program[2] = 2;
    results[2] = 2;
    runProgram(program, results);
    printf("Index 0 after running part 1: %d\n", results[0]);

    printf("%d codes written to program...\n", curIndex);

    return EXIT_SUCCESS;
}