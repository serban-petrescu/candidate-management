import {combineReducers} from 'redux';
import CandidatesReducer from './reducer_candidates';
import ActiveCandidateReducer from './reducer_active_candidate';

const rootReducer = combineReducers({
    candidates: CandidatesReducer,
    activeCandidate: ActiveCandidateReducer
});

export default rootReducer;