const Enumerable = require('linq');
const Student = require('./Student')
const Laboratory = require('./Laboratory')

class Assignmment {
    constructor() {
        this.students = [];
        this.laboratories = [];
        for (let index = 0; index < 123; index++) {
            let list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            this.students[index] = new Student("test", index + 1, 3.3, list)
        }

        for (let index = 0; index < 7; index++) {
            this.laboratories[index] = new Laboratory(index + 1, "Test", 14)

        }
    }


    Run() {
        this.students.forEach(element => {
            console.log(element.ToString)
        });
    }


}

let app = new Assignmment();
app.Run();