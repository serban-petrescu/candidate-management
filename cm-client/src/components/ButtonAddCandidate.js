import React from "react";
import {Button} from 'react-bootstrap';
/**
 *  Add the candidate to the candidate list based on the
 *  candidate received as props.
 */
export default class ButtonAddCandidate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    handleClick = () => {
        this.setState({isLoading: true});
        this.props.setConfirmationStatus('pending');
        this.props.submitCandidate()
            .then((response) => {
                this.setState({isLoading: false});
                this.props.setConfirmationStatus(response.payload.response.status === 201 ? 'success' : 'failed');
            })
    };

    render() {
        let isLoading = this.state.isLoading;
        let isFormValid = this.props.formValid;

        return (

            <div>

                <Button
                    className={'candidateCustomButton'}
                    disabled={isLoading || !isFormValid}
                    onClick={!isLoading ? this.handleClick : null}>
                    { isLoading ? 'Loading...' : 'Add Candidate'}
                </Button>
            </div>
        )
    }
}