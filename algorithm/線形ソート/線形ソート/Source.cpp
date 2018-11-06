#include "DxLib.h"
#include<iostream>

void sort(int[], const size_t);

int main(void) {
    const size_t array_size = 20;
    int array[array_size];

    for (int i = 0; i < array_size; i++) {
        array[i] = array_size - i;
    }
    
    for (auto& e : array) {
        std::cout << e << ",";
    }
    std::cout << "\n";
    sort(array, array_size);

    for (auto& e : array) {
        std::cout << e << ",";
    }
    std::cout << "\n";

    return 0;
}   

void sort(int array[],const size_t size) {
    int min;
    int temp = 0;
    for (int i = 0; i < size - 2; i++) {
        min = i;
        for (int j = i + 1; j < size; j++) {
            if(array[j] < array[min])min = j;
        }
        temp = array[i];
        array[i] = array[min];
        array[min] = temp;
    }
}