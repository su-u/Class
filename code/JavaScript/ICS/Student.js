let Enumerable = require('linq');

module.exports = class Student {
    constructor(name, number, gpa, _satisfaction) {
        this.name = name;
        this.number = number;
        this.gpa = gpa;
        this.satisfaction = [];


        for (let i = 0, l = _satisfaction.length; i < l; i++) {
            this.satisfaction[i] = [i + 1, _satisfaction[i]]
        }
        this.satisfaction = Enumerable.from(this.satisfaction).orderByDescending(x => x[1]).toArray();
    }

    get Gpa() {
        return this.gpa;
    }
    set Gpa(value) {
        this.gpa = value;
    }

    GetSati(index) {
        if (index >= 0 && index < this.satisfaction.length) return undefined;
        return this.satisfaction[index][1];
    }

    get SatisfactionToString() {
        let text = "";
        this.satisfaction.forEach(element => {
            text += ` ${element}`;
        });
        return text;
    }


    get ToString() {
        return `number:${this.number} name:${this.name} gpa:${this.gpa} ` + this.SatisfactionToString;
    }


}

