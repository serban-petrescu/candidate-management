import {combineReducers} from 'redux';
import CandidatesReducer from './reducer_candidates';
import ActiveCandidateReducer from './reducer_active_candidate';
import CandidateValidationReducer from './reducer_validation';

/**
 * This reducer calls every child reducer, and gathers their results into a single
 * state object. Each reducer manages an independent part of the state.
 * @type {Reducer<S>} A reducer that invokes every reducer inside the reducers object,
 * and constructs a state object with the same shape.
 */
const rootReducer = combineReducers({
    candidates: CandidatesReducer,
    activeCandidate: ActiveCandidateReducer,
    candidatesNYValidated: CandidateValidationReducer
});

export default rootReducer;