import {LOAD_NOT_VALIDATED_CANDIDATES, VALIDATE_CANDIDATE, VALIDATE_CANDIDATES, DELETE_CANDIDATES, EDIT_NOT_VALIDATED_CANDIDATE} from '../actions/CandidateValidationActions';

/**
 * Match the actions dispatched to this reducer. The constants are imported from
 * ../actions/CandidateActions.js so the action name will be consistent throughout the calls.
 * @param state
 * @param action
 * @returns {*}
 */
export default function (state = [], action) {

    switch (action.type) {
        case LOAD_NOT_VALIDATED_CANDIDATES:
            return action.payload;
        case VALIDATE_CANDIDATE:
            return null;
        case VALIDATE_CANDIDATES:
            return null;
        case DELETE_CANDIDATES:
            return null;
        case EDIT_NOT_VALIDATED_CANDIDATE:
            return action.payload;
    }
    return state;
}
