let Enumerable = require("linq");

module.exports = class Laboratory {
    constructor(number, name, max) {
        this.number = number;
        this.name = name;
        this.max = max;
        this.Dt = 0;
        this.students = [];
        this.size = 0;
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
        return this.size;
    }

    IsMax() {
        if (this.size >= this.max) return true;
        return false;
    }

    AddStudent(student) {
        if (!this.IsMax()) {
            this.students[this.size] = student;
            this.size++;
        }
    }

    get ToString() {
        return `number:${this.number} name:${this.name} DT:${this.Dt} max:${this.max}`
    }


}

