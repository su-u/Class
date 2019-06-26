const assert = require("assert");
const Student = require("../Student");

describe("StudentTest", function () {
    let list = [4, 4, 10, 7, 1, 2, 9, 8, 1, 10];
    let s = new Student("testName", 1, 2.5, list)
    it("Gpa", function () {
        assert.equal(s.gpa, 2.5);
    });
    it("name", function () {
        assert.equal(s.Name, "testName");
    });
    it("satisfaction", function () {
        assert.equal(s.SatisfactionToString, " 3,10 10,10 7,9 8,8 4,7 1,4 2,4 6,2 5,1 9,1");
    });
    it("satisfactionSum", function () {
        assert.equal(s.SatisfactionSum, 56);
    });
    it("get satifaction", function () {
        const n = s.GetSati(1);
        assert.equal(n, 4);
    });
    it("SumStudentsSatisfaction", function () {
        let _students = [];
        for (let index = 0; index < 123; index++) {
            let list = [4, 4, 10, 7, 1, 2, 9, 8, 1, 10];
            _students[index] = new Student("test", index + 1, 3.3, list);
        }
        for (let index = 0; index < _students.length; index++) {
            const element = _students[index];
            assert.equal(element.SatisfactionSum, 56);
        }
    });
})