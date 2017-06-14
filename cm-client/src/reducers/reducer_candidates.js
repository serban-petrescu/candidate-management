import { LOAD_CANDIDATES, REMOVE_CANDIDATE } from '../actions'

export default function (state = [], action) {

    switch (action.type) {
        case LOAD_CANDIDATES:
            return action.payload;
        case REMOVE_CANDIDATE:
            const responseStatus = action.payload.response.status;
            if (responseStatus >= 200 &&  responseStatus < 300) {
                return state.filter((candidate) => {
                    return candidate.id !== action.payload.candidateId
                });
            }

    }

    return state;
}

