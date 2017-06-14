import {fetchCandidates, deleteCandidate} from '../utils/api';

export const LOAD_CANDIDATES = 'LOAD_CANDIDATES';
export const SELECT_CANDIDATE = 'SELECT_CANDIDATE';
export const REMOVE_CANDIDATE = 'DELETE_CANDIDATE';


export function selectCandidate(candidate) {
    // selectCandidate is an ActionCreator, it needs to return an action,
    // an object with a type property.
    return {
        type: SELECT_CANDIDATE,
        payload: candidate
    }
}

export function loadCandidates() {

    //payload is a promise that will be unwrapped by the redux-promise middleware
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
        payload: deleteCandidate(candidateId),
    }
}
