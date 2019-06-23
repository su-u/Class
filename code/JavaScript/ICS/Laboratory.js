let Enumerable = require("linq");

module.exports = class Laboratory {
    constructor(number, name, max) {
        this.number = number;
        this.name = name;
        this.max = max;
        this.dt = 0;
        this.students = [];
        this.size = 0;
    }

    get Number() {
        return this.number;
    }

    get DT() {
        return this.dt;
    }

    set DT(value) {
        this.dt = value;
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
        return `number:${this.number} name:${this.name} DT:${this.dt} size:${this.Size} max:${this.max}`
    }


}

