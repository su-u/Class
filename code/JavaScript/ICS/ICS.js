const Enumerable = require("linq");
const Student = require("./Student");
const Laboratory = require("./Laboratory");

class Assignmment {
    constructor() {
        this.students = [];
        this.laboratories = [];
        for (let index = 0; index < 123; index++) {
            let list = [4, 4, 10, 7, 1, 2, 9, 8, 1, 10];
            this.students[index] = new Student("test", index + 1, 3.3, list);
        }

        for (let index = 0; index < 7; index++) {
            this.laboratories[index] = new Laboratory(index + 1, "Test", 14);
        }
    }

    Print() {
        this.PrintStudents();
        this.PrintLabolatories();
    }

    PrintStudents() {
        console.log("Students");
        this.students.forEach(element => {
            console.log(element.ToString);
        });
    }

    PrintLabolatories() {
        console.log("Labo");
        this.laboratories.forEach(element => {
            console.log(element.ToString);
        });
    }

    StudentSatisfactionLaboratories(student) {
        let s = Enumerable.from(student.Satisfaction).orderByDescending(x => x[1]).thenBy(y => this.laboratories[y[0]].DT).toArray();
    }

    Run() {
        this.students = Enumerable.from(this.students).orderByDescending(x => x.Gpa).toArray();

        this.laboratories.forEach(element => {
            element.DT = Enumerable.from(this.students).sum(x => x.GetSati(element.Number));
        });

        this.students.forEach(student => {

        });

    }

}

let app = new Assignmment();
app.Run();