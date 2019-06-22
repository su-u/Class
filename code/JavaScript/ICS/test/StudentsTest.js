const assert = require("assert");
const Student = require("../Student");

describe("StudentTest", function () {
    let list = [4, 4, 10, 7, 1, 2, 9, 8, 1, 10];
    let s = new Student("testName", 1, 2.5, list)
    it("Gpa", function () {
        assert(s.gpa === 2.5);
        assert.equal(s.gpa, 2.5);
    })
    it("name", function () {
        assert(s.Name === "testName");
        assert.equal(s.Name, "testName");
    })
})