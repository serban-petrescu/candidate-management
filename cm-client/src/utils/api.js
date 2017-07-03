import axios from 'axios';
/**
 * Returns all the candidates available from the backend
 * @param url to which the GET request should be made
 * @returns {Promise}
 */
function fetchCandidates(url) {

    return axios.get(url).then(function (response) {
        return response.data._embedded.candidates;
    });
}
/**
 * Delete the candidate based on the candidate ID. Return
 * a Promise containing the response and the candidate's id.
 * Response will be used by the reducer to verify the response status
 * candidateId will be used by the reducer to modify the state of the program.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param url to which the DELETE request should be made
 * @param candidateId - id of the candidate which will be deleted
 * @returns {Promise}
 */
function deleteCandidate(url, candidateId) {
    return axios.delete(url)
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
 * Update the candidate with the object passed as a paramenter.
 * Return a Promise containing the response and the updated candidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param url to which the PUT request should be made
 * @param candidate - object of type candidate containing the update information
 * @returns {Promise}
 */
function updateCandidate(url, candidate) {
    return axios.put(url, candidate)
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
 * Add a candidate to the list of available candidates.
 * Return a Promise containing the response and the added candidate.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param url to which the POST request should be made
 * @param candidate - object of type candidate containing the new candidate
 * @returns {Promise}
 */
function addCandidate(url, candidate) {
    return axios.post(url, candidate)
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
 * Get a list of all the skills a candidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param url to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a tagLink, rating and a certifier
 */
function fetchSkillsForCandidate(url) {
    return axios.get(url).then(function (response) {
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
 Get a list of all the educations information a candidate has. Iterate over each response using
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
        }

    }).catch((error) => {
        console.log(error);
    });
}
/**
 *Get a list of all the tags a candidate has. Iterate over each response item using
 * the map function and for every object, return a newly create object containing the
 * relevant information. Append this created object to a list.
 * @param url to which the GET request should be made
 * @returns {Promise} containing a list of objects each having an id, description: and a tagType
 */
function fetchTagForCandidateSkill(url) {
    return axios.get(url).then(function (response) {

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
 * Get a list of all the notes a candidate has. Iterate over each response using
 * the map function and for every object return a newly create object containing the
 * relevant information.
 * @param url to which the GET request should be made
 * @returns {Promise} containing a list of objects each having a status and a note
 */
function fetchNotesForCandidate(url) {
    return axios.get(url).then(function (response) {
        return response.data._embedded.candidateNoteses.map(function (key) {
            return {
                status: key.status,
                note: key.note,
                date:key.date
            }
        });
    }).catch((error) => {
        console.log(error);
    });
}

/**
 * Add a candidate note to the list of available candidate notes.
 * Return a Promise containing the response and the added note.
 * The promise will be red by middleware module and sent to reducer as an Object.
 * @param url to which the POST request should be made
 * @param note - object of type note containing the new candidate
 * @returns {Promise}
 */
function addCandidateNote(url, note) {
    console.log(note);
    return axios.post(url, note)
        .then((response) => {
            return {
                response,
                note
            };
        })
        .catch((error) => {
            return error;
        });
}


export {updateCandidate, deleteCandidate, fetchEducationForCandidate, fetchCandidates, addCandidate, fetchSkillsForCandidate, fetchTagForCandidateSkill,fetchNotesForCandidate,addCandidateNote};
