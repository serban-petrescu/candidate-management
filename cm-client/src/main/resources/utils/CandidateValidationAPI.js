import getBaseURL from './BasePath';
import axios from 'axios';

const CANDIDATES_VALIDATION_URL = `${getBaseURL()}/api/validation`;


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
 * Update the oCandidate with the object passed as a parameter.
 * Return a Promise containing the response and the updated oCandidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param oCandidate - object of type oCandidate containing the update information
 * @returns {Promise}
 */
function updateCandidate(oCandidate) {

    return axios.put(getCandidateURLById(oCandidate.id), oCandidate)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {
            return error;
        });
}

function validateCandidates(oCandidates) {
    return axios.post(CANDIDATES_VALIDATION_URL, oCandidates)
        .then((response) => {
            return {
                response
            };
        })
        .catch((error) => {
            return error;
        });
}

export {
    fetchCandidates,
    updateCandidate,
    validateCandidates
};
