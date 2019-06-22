const assert = require("assert");
const Student = require("../Laboratory");

describe("LaboratoryTest", function () {
    let students = [];
    for (let index = 0; index < 123; index++) {
        let list = [10, 4, 10, 7, 1, 2, 9, 8, 1, 10];
        students[index] = new Student("test", index + 1, 3.3, list);
    }

    laboratory = new Laboratory(1, "Test", 14);
    it("DT", function () {
        laboratory.forEach(element => {
            element.DT = Enumerable.from(this.students).sum(x => x.GetSati(element.Number));
        });
        assert.equal(laboratory.DT, 1230)
    });
})