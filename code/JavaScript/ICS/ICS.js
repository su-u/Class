const Enumerable = require("linq");
const Student = require("./Student");
const Laboratory = require("./Laboratory");

class Assignmment {
    constructor() {
        const LaboCount = 10
        this.CanAssignLaboCount = LaboCount;

        this.students = [];
        this.laboratories = [];
        for (let index = 0; index < 10; index++) {
            let list = [9, 10, 10, 7, 6, 5, 4, 3, 2, 1];
            // let list = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1];
            this.students[index] = new Student("test", index + 1, 3.3, list);
        }

        for (let index = 0; index < LaboCount; index++) {
            this.laboratories[index] = new Laboratory(index + 1, "Test", 3);
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
        const labolist = Enumerable.from(this.laboratories).take(this.CanAssignLaboCount).select(x => new {x});
        return Enumerable.from(array).where(a => Enumerable.from(this.labolist).contains(a))
            .orderByDescending(x => x[1]).thenBy(y => this.laboratories[y[0] - 1]).toArray();
    }

    CheckMaxLaboratories() {
        for (let index = 0; index < this.CanAssignLaboCount; index++) {
            if (this.laboratories[index].IsMax()) {
                console.log(`maxLabo:${this.laboratories[index].Number}`);
                const tmp = this.laboratories[this.CanAssignLaboCount - 1];
                this.laboratories[this.CanAssignLaboCount - 1] = this.laboratories[index];
                this.laboratories[index] = tmp;
                this.CanAssignLaboCount--;
            }
        }
    }

    Run() {
        this.students = Enumerable.from(this.students).orderByDescending(x => x.Gpa).toArray();

        this.laboratories.forEach(element => {
            element.DT = Enumerable.from(this.students).sum(x => x.GetSati(element.Number));
        });

        this.PrintLabolatories();
        this.students.forEach(student => {
            this.CheckMaxLaboratories();
            const l = Enumerable.from(this.GetAssignmentlaboratory(student.Satisfaction)).firstOrDefault();
            Enumerable.from(this.laboratories).single(x => x.Number == l[0]).AddStudent(student);
            console.log(`select:${Enumerable.from(this.laboratories).single(x => x.Number == l[0]).Number}`);
        });

        this.PrintLabolatories();
    }
}

let app = new Assignmment();
app.Run();