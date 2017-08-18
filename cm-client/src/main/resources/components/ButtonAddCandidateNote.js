import React from "react";
import {Button} from 'react-bootstrap';
/**
 *  Add the candidate note to the candidate note list based on the
 *  candidate received as props.
 */
export default class ButtonAddCandidateNote extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    handleClick = () => {
        this.props.submitCandidateNote()
            .then((response) => {
                this.props.setConfirmationStatus(response.payload.response.status === 201 ? 'success' : 'failed');
            })
    };

    render() {
        let isLoading = this.state.isLoading;

        return (

            <div>

                <Button
                    className={'candidateNoteCustomButton'}
                    onClick={!isLoading ? this.handleClick : null}>
                    { isLoading ? 'Loading...' : 'Add Note'}
                </Button>
            </div>
        )
    }
}