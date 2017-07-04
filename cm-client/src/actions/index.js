import {fetchCandidates, deleteCandidate,updateCandidate, addCandidate as insertCandidate, addCandidateNote as insertCandidateNote} from '../utils/api';

export const LOAD_CANDIDATES = 'LOAD_CANDIDATES';
export const SELECT_CANDIDATE = 'SELECT_CANDIDATE';
export const REMOVE_CANDIDATE = 'DELETE_CANDIDATE';
export const EDIT_CANDIDATE = 'EDIT_CANDIDATE';
export const ADD_CANDIDATE = 'ADD_CANDIDATE';
export const ADD_CANDIDATE_NOTE = 'ADD_CANDIDATE_NOTE';
export const ROOT_URL = "http://localhost";
export const PORT = 8080;
export const CANDIDATES_URL = `${ROOT_URL}:${PORT}/api/candidates`;
export const CANDIDATES_NOTES_URL = `${ROOT_URL}:${PORT}/api/candidateNoteses`;

/**
 * Actions are plain JavaScript objects. Actions must have a type property that indicates the type of action being performed.
 * Actions are payloads of information that send data from your application to your store. They are the only source of information for the store.
 * The constant exported in this file are then imported in the reducers so that the names will keep their consistencies
 * Payload is a promise that will be unwrapped by the redux-promise middleware
 * @param candidate
 * @returns {{type: string, payload: *}}
 */

// Select active candidate
export function selectCandidate(candidate) {
    // selectCandidate is an ActionCreator, it needs to return an action,
    // an object with a type property.
    return {
        type: SELECT_CANDIDATE,
        payload: candidate
    }
}


export function loadCandidates() {
    return {
        type: LOAD_CANDIDATES,
        payload: fetchCandidates(CANDIDATES_URL)
    }
}


export function removeCandidate(candidateId) {
    // deleteCandidate is an action creator, it needs to return an action
    // (an object with type property )
    let DELETE_URL = CANDIDATES_URL +"/"+ candidateId;
    return {
        type: REMOVE_CANDIDATE,
        payload: deleteCandidate(DELETE_URL,candidateId)
    }
}


export function editCandidate(candidate) {
    let EDIT_URL = CANDIDATES_URL +"/"+ candidate.id;
    return {
        type: EDIT_CANDIDATE,
        payload: updateCandidate(EDIT_URL,candidate)
    }
}

export function addCandidate(candidate) {
    let ADD_URL = CANDIDATES_URL;
    return {
        type: ADD_CANDIDATE,
        payload: insertCandidate(ADD_URL, candidate)
    }
}

export function     addCandidateNote(note) {
    let ADD_URL = CANDIDATES_NOTES_URL;
    return {
        type: ADD_CANDIDATE_NOTE,
        payload: insertCandidateNote(ADD_URL, note)
    }
}


