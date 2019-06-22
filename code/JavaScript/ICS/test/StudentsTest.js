const assert = require("assert");
const Student = require("../Student");

describe("StudentTest", function () {
    let list = [4, 4, 10, 7, 1, 2, 9, 8, 1, 10];
    let s = new Student("testName", 1, 2.5, list)
    it("Gpa", function () {
        assert(s.gpa === 2.5);
        assert.equal(s.gpa, 2.5);
    });
    it("name", function () {
        assert(s.Name === "testName");
        assert.equal(s.Name, "testName");
    });
    it("satisfaction", function () {
        assert(s.SatisfactionToString === " 3,10 10,10 7,9 8,8 4,7 1,4 2,4 6,2 5,1 9,1");
        assert.equal(s.SatisfactionToString, " 3,10 10,10 7,9 8,8 4,7 1,4 2,4 6,2 5,1 9,1");
    });
    it("satisfactionSum", function () {
        assert(s.SatisfactionSum === 56);
        assert.equal(s.SatisfactionSum, 56);
    });
})