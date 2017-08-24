import getBaseURL from './BasePath';
import axios from 'axios';

const CANDIDATES_URL = `${getBaseURL()}/api/candidates`,
      CANDIDATES_NOTES_URL = `${getBaseURL()}/api/candidateNoteses`,
      CANDIDATES_VALIDATION_URL = `${getBaseURL()}/api/validation`;


function getCandidateURLById(sId) {
    return `${CANDIDATES_URL}/${sId}`;
}

/**
 * Returns all the candidates available from the backend
 * @returns {Promise}
 */
function fetchCandidates() {
    return axios.get(CANDIDATES_URL).then(function (response) {
        return response.data._embedded.candidates;
    });
}


/**
 * Add a oCandidate to the list of available candidates.
 * Return a Promise containing the response and the added oCandidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param oCandidate - object of type oCandidate containing the new oCandidate
 * @returns {Promise}
 */
function addCandidate(oCandidate) {
    return axios.post(CANDIDATES_URL, oCandidate)
        .then((response) => {
            return {
                response,
                oCandidate
            };
        })
        .catch((error) => {
            return error;
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
                response,
                oCandidate
            };
        })
        .catch((error) => {
            return error;
        });
}

/**
 * Delete the oCandidate based on the oCandidate ID. Return
 * a Promise containing the response and the oCandidate's id.
 * Response will be used by the reducer to verify the response status
 * sCandidateId will be used by the reducer to modify the state of the program.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param sCandidateId - id of the oCandidate which will be deleted
 * @returns {Promise}
 */
function deleteCandidate(sCandidateId) {
    return axios.delete(getCandidateURLById(sCandidateId))
        .then((response) => {
            return {
                response,
                sCandidateId
            };
        })
        .catch((error) => {
            return error;
        });
}


/**
 * Get a list of all the skills a oCandidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param sURL to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a tagLink, rating and a certifier
 */
function fetchSkillsForCandidate(sURL) {
    return axios.get(sURL).then(function (response) {
        return response.data._embedded.candidateSkillses.map(function (key) {
            return {
                tagLink: key._links.tag.href,
                rating: key.rating,
                certifier: key.certifier
            }
        });
    }).catch((error) => {
        console.log(error);
    });
}

/**
 Get a list of all the educations information a oCandidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information. Append this created object to a list.
 * @param sURL to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a educationType, provider: and a description
 */
function fetchEducationForCandidate(sURL) {
    return axios.get(sURL).then(function (response) {
        return {
            educationType: response.data.educationType,
            provider: response.data.provider,
            description: response.data.description
        }

    }).catch((error) => {
        console.log(error);
    });
}

/**
 *Get a list of all the tags a oCandidate has. Iterate over each response item using
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
        }
    }).catch((error) => {
        console.log(error);
    });
}

/**
 * Get a list of all the notes a oCandidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param sURL to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a status and a note
 */
function fetchNotesForCandidate(sURL) {
    return axios.get(sURL).then(function (response) {
        return response.data._embedded.candidateNoteses.map(function (key) {
            return {
                status: key.status,
                note: key.note,
                date: key.date
            }
        });
    }).catch((error) => {
        console.log(error);
    });
}


/**
 * Add a oCandidate note to the list of available oCandidate notes.
 * Return a Promise containing the response and the added note.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param sNotesURL to which the POST request should be made
 * @param oNote - object of type note containing the new oCandidate
 * @param oCandidate - object of type oCandidate whose note it is
 * @returns {Promise}
 */
function addCandidateNote(oNote, oCandidate) {

    // first, create note
    let axiosResponse = axios.post(CANDIDATES_NOTES_URL, oNote)
        .then((response) => {
            let sURL = `${CANDIDATES_NOTES_URL}/${response.data.id}/candidate`;
            // this put request need text/uri-list as content type
            axios.defaults.headers.put['Content-Type'] = 'text/uri-list';
            // then, bind the oCandidate entity to the note's oCandidate
            return axios.put(sURL, getCandidateURLById(oNote.candidate_id))
                .then((response) => {
                    return {response, oNote, oCandidate};
                }).catch((error) => {
                    return error;
                });
        })
        .catch((error) => {
            return error;
        });

    // revert content type header of put requests to the default value
    axios.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';

    return axiosResponse;
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
    updateCandidate,
    deleteCandidate,
    fetchEducationForCandidate,
    fetchCandidates,
    addCandidate,
    fetchSkillsForCandidate,
    fetchTagForCandidateSkill,
    fetchNotesForCandidate,
    addCandidateNote,
    validateCandidates
};
