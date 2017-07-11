import {addCandidate as insertCandidate} from '../utils/api';

export const ADD_CANDIDATE = 'ADD_CANDIDATE';
export const ROOT_URL = "http://localhost";
export const PORT = 8080;
export const CANDIDATES_URL = `${ROOT_URL}:${PORT}/api/candidates`;

/**
 * Actions are plain JavaScript objects. Actions must have a type property that indicates the type of action being performed.
 * Actions are payloads of information that send data from your application to your store. They are the only source of information for the store.
 * The constant exported in this file are then imported in the reducers so that the names will keep their consistencies
 * Payload is a promise that will be unwrapped by the redux-promise middleware
 * @param candidate
 * @returns {{type: string, payload: *}}
 */

export function addCandidate(candidate) {
    return {
        type: ADD_CANDIDATE,
        payload: insertCandidate(CANDIDATES_URL, candidate)
    }
}
