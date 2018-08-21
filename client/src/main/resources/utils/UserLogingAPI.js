import axios from 'axios';
import getBaseURL from './BasePath';

/**
 * Search a user with username and password
 * Return a Promise containing the response and the added note.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param username - the username to be found
 * @param password - the given encrypted password
 * @returns {Promise}
 */
function searchUser(username, password) {
    let postUrl = `${getBaseURL()}/authentication`;
    return axios.post(postUrl, 'username=' + username + '&password=' + password)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {return error;});
}

function logout() {
    let postUrl = `${getBaseURL()}/logout`;
    return axios.post(postUrl)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {return error;});
}

axios.interceptors.response.use((response) => {
    return response;
}, function (error) {
    // Do something with response error
    if (error.status === 403) {
        console.log('unauthorized, logging out ...');
        logout();
        window.location.href='#/';
    }
    return Promise.reject(error.response);
});

export {
    searchUser,
    logout
    };