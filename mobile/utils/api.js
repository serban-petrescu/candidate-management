import axios from 'axios';
/*
* Add a candidate to the list of available candidates.
* Return a Promise containing the response and the added candidate.
* The promise will be red by middleware module and sent to reducer as an Object.
* @param url to which the POST request should be made
* @param candidate - object of type candidate containing the new candidate
* @returns {Promise}
*/
export function addCandidate(url, candidate) {
    console.log(axios);
    return axios.post(url, candidate)
        .then((response) => {
        console.log(response);
            return {
                response,
                candidate
            };
        })
        .catch((error) => {
            return error;
        });
}