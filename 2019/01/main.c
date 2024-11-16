#include <math.h>
#include <stdlib.h>
#include <stdio.h>

#define BUFFER_SIZE   256
#define FILENAME      "input.txt"

/*
    Fuel required for a given module: floor(mass / 3) - 2
    Ex: mass = 12; floor(12/3) - 2 = 2
        mass = 14; floor(14/3) - 2 = 2
        mass = 1969; floor(1969/3) - 2 = 654

    Part 1: Need to know total fuel. Calculate and sum fuel requirements based
            on file

    Part 2: Calculate the fuel needed for the fuel. Calculate fuel costs until
            a negative number is needed, this fuel runs on wishes and is not
            included.
*/

int calculateFuel(int mass) {
    if (mass > 6) {
        int fuelNeeded = floor(mass / 3) - 2;
        return fuelNeeded + calculateFuel(fuelNeeded);
    }

    return 0;
}

int runTests() {
    printf("%d\n", calculateFuel(12)); // 2
    printf("%d\n", calculateFuel(14)); // 2
    printf("%d\n", calculateFuel(1969)); // 966
    printf("%d\n", calculateFuel(100756)); // 50346

    return 1;
}

int main() {
    FILE* file;

    file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Error opening file %s\n", FILENAME);
        return EXIT_FAILURE;
    }

    // Get each mass, convert to integer, and calculate fuel
    int totalFuelNeeded = 0;
    char buffer[BUFFER_SIZE];
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        int mass = atoi(buffer);
        int fuelNeeded = calculateFuel(mass);
        totalFuelNeeded += fuelNeeded;
    }

    printf("Total fuel needed: %d\n", totalFuelNeeded);

    // Cleaning up
    fclose(file);

    return EXIT_SUCCESS;
}