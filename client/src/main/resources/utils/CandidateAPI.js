import getBaseURL from './BasePath';
import axios from 'axios';
import {logout} from './UserLogingAPI';

const CANDIDATES_URL = `${getBaseURL()}/api/candidates`,
      CANDIDATES_NOTES_URL = `${getBaseURL()}/api/candidateNotes`;


function getCandidateURLById(sId) {
    return `${CANDIDATES_URL}/${sId}`;
}

/**
 * Returns all the candidates available from the backend
 * @returns {Promise}
 */
function fetchCandidates() {
    return axios.get(CANDIDATES_URL)
        .then((response) => {
            return response.data._embedded.candidateList;
        });
}

/**
 * Add a candidate to the list of available candidates.
 * Return a Promise containing the response and the added candidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param candidate - object of type candidate containing the new candidate
 * @returns {Promise}
 */
function addCandidate(candidate) {
    return axios.post(CANDIDATES_URL, candidate)
        .then((response) => {
            return {
                response,
                candidate
            };
        })
        .catch((error) => {
            return error;
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

    return axios.put(getCandidateURLById(candidate.id), candidate)
        .then((response) => {
            return {
                response,
                candidate
            };
        })
        .catch((error) => {
            return error;
        });
}

/**
 * Delete the candidate based on the candidate ID. Return
 * a Promise containing the response and the candidate's id.
 * Response will be used by the reducer to verify the response status
 * sCandidateId will be used by the reducer to modify the state of the program.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param candidateId - id of the candidate which will be deleted
 * @returns {Promise}
 */
function deleteCandidate(candidateId) {
    return axios.delete(getCandidateURLById(candidateId))
        .then((response) => {
            return {
                response,
                candidateId
            };
        })
        .catch((error) => {
            return error;
        });
}

/**
 * Get a list of all the notes a candidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param sURL to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a status and a note
 */
function fetchNotesForCandidate(sURL) {
    return axios.get(sURL).then(function (response) {
        if(response.data._embedded) {
            return response.data._embedded.candidateNotesList.map(function (key) {
                return {
                    status: key.status,
                    note: key.note,
                    date: key.date
                };
            });
        } else {
            return [];
        }
    }).catch((error) => {
        console.log(error);
    });
}

/**
 * Add a candidate note to the list of available candidate notes.
 * Return a Promise containing the response and the added note.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param note - object of type note containing the new candidate
 * @returns {Promise}
 */
function addCandidateNote(note) {

    // first, create note
    return axios.post(CANDIDATES_NOTES_URL, note)
        .then((response) => {
            return response;
        })
        .catch((error) => {
            return error;
        });

}

/**
 * Get a list of all the skills a candidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param url to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a tagLink, rating and a certifier
 */
function fetchSkillsForCandidate(url) {
    return axios.get(url).then(function (response) {
        if(response.data._embedded) {
            return response.data._embedded.candidateSkillsList.map(function (key) {
                        return {
                            tagLink: key._links.tag.href,
                            rating: key.rating,
                            certifier: key.certifier
                        };
                    });
        } else {
            return [];
        }

    }).catch((error) => {
        console.log(error);
    });
}

/**
 Get the educations information a candidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information. Append this created object to a list.
 * @param url to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a educationType, provider: and a description
 */
function fetchEducationForCandidate(url) {
    return axios.get(url).then(function (response) {
        return {
            educationType: response.data.educationType,
            provider: response.data.provider,
            description: response.data.description
        };

    }).catch((error) => {
        console.log(error);
    });
}

/**
 *Get a list of all the tags a candidate has. Iterate over each response item using
 * the map function and for every object, return a newly create object containing the
 * relevant information. Append this created object to a list.
 * @param sURL to which the GET request should be made
 * @returns {Promise} containing a list of objects each having an id, description: and a tagType
 */
function fetchTagForCandidateSkill(sURL) {
    return axios.get(sURL).then(function (response) {
        return {
            id: response.data.id,
            description: response.data.description,
            tagType: response.data.tagType
        };
    }).catch((error) => {
        console.log(error);
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
        return Promise.reject(error.response);
    } else {
        console.log(error);
    }
});

export {
    addCandidate,

    fetchCandidates,
    fetchEducationForCandidate,
    fetchSkillsForCandidate,
    fetchTagForCandidateSkill,
    fetchNotesForCandidate,

    updateCandidate,
    deleteCandidate,

    addCandidateNote,
};