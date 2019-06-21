var Enumerable = require('linq');

class Laboratory {
    constructor (number, name, max){
        this.number = number;
        this.name = name;
        this.max = max;
        this.DT = 0;
        this.students = [];
    }

    get Number(){
        return this.number;
    }

    get DT(){
        return this.DT;
    }

    set DT(value){
        this.DT = value;
    }

    get Size(){
        return this.students.length;
    }

    IsMax() {
        if(this.Size >= this.max)return true;
        return false;
    }

    AddStudent(student){
        this.students[this.students.length - 1] = student;
    }
}


var t1 = new Laboratory(1,"a", 1);
var t2 = new Laboratory(2,"fwf", 5);

console.log(t1.number);
console.log(t2.number);
