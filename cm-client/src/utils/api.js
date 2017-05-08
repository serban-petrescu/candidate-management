import axios from 'axios';

function fetchCandidates() {
    let url = "http://localhost:8080/api/candidates";
    return axios.get(url).then( function (response) {
        return response.data._embedded.candidates;
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

export {fetchCandidates, addCandidate};
