const minPasswordLength = 6
export const validateEmail = (email) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
}

export const validatePassword = (password) => password.length > minPasswordLength

export const validatePasswordRepeat = (password, passwordRepeat) => password === passwordRepeat