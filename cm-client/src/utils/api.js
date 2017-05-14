import axios from 'axios';

function fetchCandidates() {
    let url = "http://localhost:8080/api/candidates";
    return axios.get(url).then(function (response) {
        return response.data._embedded.candidates;
    });
}

function fetchSkillsForCandidate(url) {
    return axios.get(url).then(function (response) {
        return response.data._embedded.candidateSkillses.map(function (key) {
            return {
                tagLink: key._links.tag.href,
                rating :   key.rating,
                certifier: key.certifier
            }
        });
    }).catch((error)=> {
        console.log(error);
    });
}
function fetchEducationForCandidate(url) {
    return axios.get(url).then(function (response) {
            return {
                educationType: response.data.educationType,
                provider :   response.data.provider,
                description: response.data.description
            }

    }).catch((error)=> {
        console.log(error);
    });
}
function fetchTagForCandidateSkill(url) {
    return axios.get(url).then(function (response) {

        return{
            id: response.data.id,
            description:response.data.description,
            tagType: response.data.tagType
        }
    }).catch((error)=> {
        console.log(error);
    });
}
function addCandidate(candidate) {
    let url = "http://localhost:8080/api/candidates";
    return axios.post(url, candidate)
        .then((response) => {
            return response;
        })
        .catch((error) => {
            return error;
        });
}

export {fetchEducationForCandidate,fetchCandidates, addCandidate,fetchSkillsForCandidate,fetchTagForCandidateSkill};
