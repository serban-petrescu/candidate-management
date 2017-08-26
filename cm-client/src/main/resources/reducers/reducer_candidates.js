import {LOAD_CANDIDATES, REMOVE_CANDIDATE, EDIT_CANDIDATE, ADD_CANDIDATE, ADD_CANDIDATE_NOTE} from '../actions/CandidateActions'
/**
 * Updates the candidates list and return a newly created object containing the state
 * with the update candidate
 * state.slice(0, index) - create a list from state list containing items from 0 to index
 * state.slice(index + 1) - create a list from state list containing items from index to the end of the list
 * ...state.slice - spread operator allows an expression to be expanded in places where multiple elements
 * (for array literals) are expected. Example [...[1,2,3],4] = [1,2,3,4];  [[1,2,3],4]  = [[1,2,3],4]
 * Note: slice is non-destructive
 * @param state
 * @param candidate
 * @returns a new state by concatenating  state.slice(0, index), @param candidate,  state.slice(index+1)
 */
const updateCandidate = (state, candidate) => {

    //find the index of the element that should be updated based on the element's id
    const index = state.findIndex(item => {
        return item.id === candidate.id
    });

    return [
        ...state.slice(0, index),
        candidate,
        ...state.slice(index + 1)
    ];
};

/**
 * Update the Candidate Note List with the newly added List*/
const updateCandidateNotes = (state, note)=>{
    //find the index of the element that should be updated based on the element's id
    const index = state.findIndex(item => {
        return item.id === note.candidate_id
    });
    let candidate = state[index];
    candidate.candidateNotesList=[note, ...candidate.candidateNotesList];
    return [
        ...state.slice(0, index),
       candidate,
        ...state.slice(index + 1)
    ];

}

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
            return [action.payload.candidate, ...state];
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
        case ADD_CANDIDATE_NOTE:
            console.log(action.payload);
            return updateCandidateNotes(state, action.payload.note);

    }
    return state;
}

