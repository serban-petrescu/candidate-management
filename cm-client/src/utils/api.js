import axios from 'axios';

export function fetchCandidates() {
    var url = "http://localhost:8080/api/candidates";
    return axios.get(url).then( function (response) {
        return response.data._embedded.candidates;
    });
}

