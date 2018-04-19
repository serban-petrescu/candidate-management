import {
    fetchCandidates as fetchNotValidated,
    validateCandidates as validate,
    updateCandidate as updateNotValidated
} from '../utils/CandidateValidationAPI';

export const
    LOAD_NOT_VALIDATED_CANDIDATES = 'LOAD_NOT_VALIDATED_CANDIDATES',
    VALIDATE_CANDIDATES = 'VALIDATE_CANDIDATES',
    EDIT_NOT_VALIDATED_CANDIDATE = 'EDIT_NOT_VALIDATED_CANDIDATE';

export function updateNotValidatedCandidate(candidate) {
    return {
        type: EDIT_NOT_VALIDATED_CANDIDATE,
        payload: updateNotValidated(candidate)
    }
}

export function validateCandidates(aCandidates) {
    return {
        type: VALIDATE_CANDIDATES,
        payload: validate(aCandidates)
    }
}

export function loadNotValidatedCandidates() {
    return {
        type: LOAD_NOT_VALIDATED_CANDIDATES,
        payload: fetchNotValidated()
    }
}