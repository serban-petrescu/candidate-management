import {LOAD_CANDIDATES, REMOVE_CANDIDATE, EDIT_CANDIDATE,ADD_CANDIDATE} from '../actions'
const updateCandidate = (state, candidate) => {

    const index = state.findIndex(item => {
        return item.id === candidate.id
    });
    //console.log("in REDUCER update candidate index", index);
    //console.log("in REDUCER update candidate candidate", candidate);
    //console.log("in REDUCER update candidate state", state);
    return [
        ...state.slice(0, index),
        candidate,
        ...state.slice(index + 1)
    ];
};

export default function (state = [], action) {

    switch (action.type) {
        case ADD_CANDIDATE:
            return [action.payload.candidate,...state];
        case EDIT_CANDIDATE:
            return updateCandidate(state, action.payload.candidate);
        case LOAD_CANDIDATES:
            return action.payload;
            
        case REMOVE_CANDIDATE:
            const responseStatus = action.payload.response.status;
            if (responseStatus >= 200 && responseStatus < 300) {
                return state.filter((candidate) => {
                    return candidate.id !== action.payload.candidateId
                });
            }
            break;
        default:
            
    }
    return state;
}

