import {USER_LOGIN} from '../actions/index';

/**
 * Reducer responsible for returning and passing down as props a selected candidate.
 * For example when a row is expanded the active candidate will be sent down to Skill Tab
 * and Education Tab. This reducer only return a payload and does not modify the state.
 */
export default function (state = null, action) { // if state is undefined set it to null
    switch (action.type) {
        case USER_LOGIN:
            return action.payload;
        default:
    }

    return state;
}