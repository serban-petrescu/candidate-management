import {searchUser} from '../utils/UserLogingAPI.js';

export const USER_LOGIN = 'USER_LOGIN';

export function verifyUser(username, password) {
    return {
        type: USER_LOGIN,
        payload: searchUser(username, password)
    }
}