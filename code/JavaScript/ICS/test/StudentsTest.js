const assert = require('assert')

describe('StudentTest', function () {
    it('Equality', function () {
        assert(1 + 1 === 2)
        assert.equal(1 + 1, 2)
    })
})