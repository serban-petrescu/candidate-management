import {
    fetchCandidates,
    deleteCandidate,
    updateCandidate,
    addCandidate as insertCandidate,
    addCandidateNote as insertCandidateNote
} from '../utils/CandidatesAPI';


export const
    LOAD_CANDIDATES = 'LOAD_CANDIDATES',
    ADD_CANDIDATE = 'ADD_CANDIDATE',
    EDIT_CANDIDATE = 'EDIT_CANDIDATE',
    REMOVE_CANDIDATE = 'DELETE_CANDIDATE',

    SELECT_CANDIDATE = 'SELECT_CANDIDATE',

    ADD_CANDIDATE_NOTE = 'ADD_CANDIDATE_NOTE';


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
        payload: fetchCandidates()
    }
}

export function removeCandidate(candidateId) {
    // deleteCandidate is an action creator, it needs to return an action
    // (an object with type property )
    return {
        type: REMOVE_CANDIDATE,
        payload: deleteCandidate(candidateId)
    }
}


export function editCandidate(candidate) {
    return {
        type: EDIT_CANDIDATE,
        payload: updateCandidate(candidate)
    }
}

export function addCandidate(candidate) {
    return {
        type: ADD_CANDIDATE,
        payload: insertCandidate(candidate)
    }
}

export function addCandidateNote(note, candidate) {
    return {
        type: ADD_CANDIDATE_NOTE,
        payload: insertCandidateNote(note, candidate)
    }
}
