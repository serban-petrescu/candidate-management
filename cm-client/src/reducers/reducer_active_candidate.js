import { SELECT_CANDIDATE } from '../actions';

// State argument is not application state, only the state
// this reducer is responsible for
export default function (state=null, action) { // if state is undefined set it to null
    switch (action.type) {
        case SELECT_CANDIDATE:
            return action.payload;
    }

    return state;
}