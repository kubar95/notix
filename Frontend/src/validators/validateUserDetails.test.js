import * as validator from './validateUserDetails';

describe("Testing validateEmail", () => {
    it('should return true when email is test@test.test', () => {
        expect(validator.validateEmail("test@test.test")).toBe(true)
    })
    it('should return false when email is test@test@test.test', () => {
        expect(validator.validateEmail("test@test@test.test")).toBe(false)
    })
    it('should return false when email is @.', () => {
        expect(validator.validateEmail("@.")).toBe(false)
    })
    it('should return false when email is test@test', () => {
        expect(validator.validateEmail("test@test")).toBe(false)
    })
    it('should return false when email is @test.', () => {
        expect(validator.validateEmail("@test.")).toBe(false)
    })
    it('should return false when email is test.test', () => {
        expect(validator.validateEmail("@test.")).toBe(false)
    })

})

describe("Testing validatePassword", () => {
    it("should return true when password is 1234567", () => {
        expect(validator.validatePassword("1234567")).toBe(true);
    })
    it("should return false when password is 123456", () => {
        expect(validator.validatePassword("123456")).toBe(false);
    })
    it("should return false when password is 123", () => {
        expect(validator.validatePassword("123")).toBe(false);
    })
})

describe("Testing validatePasswordRepeat", () => {
    it("should return true when password is okok and password repeat is okok", () => {
        expect(validator.validatePasswordRepeat("okok", "okok")).toBe(true);
    })
    it("should return false when password is okok and password repeat is xx", () => {
        expect(validator.validatePasswordRepeat("okok", "xx")).toBe(false);
    })
})