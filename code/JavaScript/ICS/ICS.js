const Enumerable = require("linq");
const Student = require("./Student");
const Laboratory = require("./Laboratory");

class Assignmment {
    constructor() {
        this.CanAssignLaboCount = 10;

        this.students = [];
        this.laboratories = [];
        for (let index = 0; index < 5; index++) {
            let list = [9, 10, 10, 7, 6, 5, 4, 3, 2, 1];
            // let list = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
            this.students[index] = new Student("test", index + 1, 3.3, list);
        }

        for (let index = 0; index < 10; index++) {
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

    GetAssignmentlaboratory(array) {
        return Enumerable.from(array).take(this.CanAssignLaboCount)
            .orderByDescending(x => x[1]).thenBy(y => this.laboratories[y[0] - 1]).toArray();
    }

    Run() {
        this.students = Enumerable.from(this.students).orderByDescending(x => x.Gpa).toArray();

        this.laboratories.forEach(element => {
            element.DT = Enumerable.from(this.students).sum(x => x.GetSati(element.Number));
        });

        this.PrintLabolatories();
        this.students.forEach(student => {
            let l = Enumerable.from(this.GetAssignmentlaboratory(student.Satisfaction)).firstOrDefault();
            this.laboratories[l[0] - 1].AddStudent(student);
        });

        this.PrintLabolatories();

    }

}

let app = new Assignmment();
app.Run();