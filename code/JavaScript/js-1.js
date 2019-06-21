import Enumerable from 'linq';

class Student{
    constructor (name, number, gpa, _satisfaction){
        this.name = name;
        this.number = number;
        this.gpa = gpa;
        this.satisfaction = []


        for(let i = 0, l = _satisfaction.length;i < l;i++){
            this.satisfaction[i] = [i + 1, _satisfaction[i]]
        }
        this.satisfaction =  Enumerable.from(this.satisfaction).orderByDescending(x => x[1]);
    }

    get Gpa() {
        return this.gpa;
    }
    set Gpa(value) {
        this.gpa = value;
    }
}

s = new Student("a", 1, 2.5, [1,6,10])
console.log(s.satisfaction)