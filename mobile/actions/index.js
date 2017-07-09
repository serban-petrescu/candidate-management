import {addCandidate as insertCandidate} from '../utils/api';

export const ADD_CANDIDATE = 'ADD_CANDIDATE';
export const ROOT_URL = "http://localhost";
export const PORT = 8080;
export const CANDIDATES_URL = `${ROOT_URL}:${PORT}/api/candidates`;

export function addCandidate(candidate) {
    console.log(candidate);
    return {
        type: ADD_CANDIDATE,
        payload: insertCandidate(CANDIDATES_URL, candidate)
    }
}
