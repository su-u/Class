#pragma once
class Object {
public:
    Object();
    Object(int data);
    ~Object();

    void View();

    private:
        int data;
        
};

