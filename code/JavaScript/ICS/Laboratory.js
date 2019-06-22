let Enumerable = require("linq");

module.exports = class Laboratory {
    constructor(number, name, max) {
        this.number = number;
        this.name = name;
        this.max = max;
        this.Dt = 0;
        this.students = [];
    }

    get Number() {
        return this.number;
    }

    get DT() {
        return this.Dt;
    }

    set DT(value) {
        this.Dt = value;
    }

    get Size() {
        return this.students.length;
    }

    IsMax() {
        if (this.Size >= this.max) return true;
        return false;
    }

    AddStudent(student) {
        this.students[this.students.length] = student;
    }

    get ToString() {
        return `number:${this.number} name:${this.name} DT:${this.Dt} max:${this.max}`
    }


}

