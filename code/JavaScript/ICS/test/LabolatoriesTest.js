const assert = require("assert");
const Student = require("../Student");
const Laboratory = require("../Laboratory");
const Enumerable = require("linq");


describe("LaboratoryTest", function () {
    it("Number", function () {
        laboratory = new Laboratory(1, "Test", 14);
        assert.equal(laboratory.Number, 1);
    });
    it("DT", function () {
        let list = [10, 4, 10, 7, 1, 2, 9, 8, 1, 10];
        student = new Student("test", 1, 3.3, list);
        laboratory = new Laboratory(1, "Test", 14);

        laboratory.DT = student.GetSati(laboratory.Number);

        assert.equal(laboratory.DT, 10);
    });
    it("DT-dtudents", function () {
        let students = [];
        for (let index = 0; index < 123; index++) {
            let list = [10, 4, 10, 7, 1, 2, 9, 8, 1, 10];
            students[index] = new Student("test", index + 1, 3.3, list);
        }
        laboratory = new Laboratory(1, "Test", 14);
        laboratory.DT = Enumerable.from(this.students).sum(x => x.GetSati(element.Number));
        assert.equal(laboratory.DT, 1230);
    });
})