import {searchUser, logout} from '../utils/UserLogingAPI.js';

export const USER_LOGIN = 'USER_LOGIN';
export const USER_LOGOUT = 'USER_LOGOUT';

export function verifyUser(username, password) {
    return {
        type: USER_LOGIN,
        payload: searchUser(username, password)
    };
}

export function logoutUser() {
    return {
        type: USER_LOGOUT,
        payload: logout()
    };
}