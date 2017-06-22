import axios from 'axios';

function fetchCandidates(url) {

    return axios.get(url).then(function (response) {
        return response.data._embedded.candidates;
    });
}

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

function addCandidate(url,candidate) {
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


export {updateCandidate, deleteCandidate, fetchEducationForCandidate, fetchCandidates, addCandidate, fetchSkillsForCandidate, fetchTagForCandidateSkill};
