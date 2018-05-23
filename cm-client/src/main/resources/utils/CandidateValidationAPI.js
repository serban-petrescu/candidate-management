import getBaseURL from "./BasePath";
import axios from "axios";
import {logout} from './UserLogingAPI';

const CANDIDATES_VALIDATION_URL = `${getBaseURL()}/api/candidate-validation`;

function getCandidateURLById(sId) {
    return `${CANDIDATES_VALIDATION_URL}/${sId}`;
}

/**
 * Returns all the candidates available from the backend
 * @returns {Promise}
 */
function fetchCandidates() {
    return axios.get(CANDIDATES_VALIDATION_URL).then(function (response) {
        return response.data._embedded.candidates;
    });
}

/**
 * Update the candidate with the object passed as a parameter.
 * Return a Promise containing the response and the updated candidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param candidate - object of type candidate containing the update information
 * @returns {Promise}
 */
function updateCandidate(candidate) {

    return axios.put(getCandidateURLById(candidate.id) + '/update', candidate)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {
            return error;
        });
}

function validateCandidate(candidateId) {
    return axios.put(getCandidateURLById(candidateId))
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {
            return error;
        });
}

function validateCandidates(candidates) {
    return axios.put(CANDIDATES_VALIDATION_URL, candidates)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {
            return error;
        });
}

function deleteCandidates(candidates){
    return axios.delete(CANDIDATES_VALIDATION_URL, {data: candidates})
            .then((response) => {
                return {
                    response
                };
            })
            .catch((error) => {
                return error;
            });
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
    fetchCandidates,
    updateCandidate,
    validateCandidate,
    validateCandidates,
    deleteCandidates
};