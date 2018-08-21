import {LOAD_CANDIDATES, REMOVE_CANDIDATE, EDIT_CANDIDATE, ADD_CANDIDATE, ADD_CANDIDATE_NOTE, FETCH_CANDIDATE_SKILLS, FETCH_CANDIDATE_EDUCATION} from '../actions/CandidateActions';

/**
 * Match the actions dispatched to this reducer. The constants are imported from
 * ../actions/CandidateActions.js so the action name will be consistent throughout the calls.
 * @param state
 * @param action
 * @returns {*}
 */
export default function (state = [], action) {

    switch (action.type) {
        case ADD_CANDIDATE:
            return [action.payload.response.data, ...state];
        case EDIT_CANDIDATE:
        case ADD_CANDIDATE_NOTE:
            return null;
        case REMOVE_CANDIDATE:
            const responseStatus = action.payload.response.status;
            if (responseStatus >= 200 && responseStatus < 300) {
                return state.filter((candidate) => {
                    return candidate.id !== action.payload.candidateId
                });
            }
            break;
        case LOAD_CANDIDATES:
        case FETCH_CANDIDATE_SKILLS:
        case FETCH_CANDIDATE_EDUCATION:
            if (action.error) {
                return null;
            } else {
                return action.payload;
            }
    }
    return state;
}

