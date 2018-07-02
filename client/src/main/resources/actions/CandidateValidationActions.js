import {
    fetchCandidates as fetchNotValidated,
    updateCandidate as updateNotValidated,
    validateCandidate as validateOne,
    validateCandidates as validateAll,
    deleteCandidates as deleteAll

    } from '../utils/CandidateValidationAPI';

export const
    LOAD_NOT_VALIDATED_CANDIDATES = 'LOAD_NOT_VALIDATED_CANDIDATES',
    VALIDATE_CANDIDATE = 'VALIDATE_CANDIDATE',
    VALIDATE_CANDIDATES = 'VALIDATE_CANDIDATES',
    DELETE_CANDIDATES = 'DELETE_CANDIDATES',
    EDIT_NOT_VALIDATED_CANDIDATE = 'EDIT_NOT_VALIDATED_CANDIDATE';

export function loadNotValidatedCandidates() {
    return {
        type: LOAD_NOT_VALIDATED_CANDIDATES,
        payload: fetchNotValidated()
    };
}

export function updateNotValidatedCandidate(candidate) {
    return {
        type: EDIT_NOT_VALIDATED_CANDIDATE,
        payload: updateNotValidated(candidate)
    };
}

export function validateCandidate(candidateId) {
    return {
        type: VALIDATE_CANDIDATE,
        payload: validateOne(candidateId)
    };
}

export function validateCandidates(candidates) {
    return {
        type: VALIDATE_CANDIDATES,
        payload: validateAll(candidates)
    };
}

export function deleteCandidates(candidates) {
    return {
        type: DELETE_CANDIDATES,
        payload: deleteAll(candidates)
    };
}