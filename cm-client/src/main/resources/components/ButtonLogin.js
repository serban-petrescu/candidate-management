import React from 'react';
import {Button} from 'react-bootstrap';

export default
class ButtonLogin extends React.Component {
    constructor(props) {
        super(props);
    }

    handleSubmit = () => {
        this.props.handleLogin();
    };

    render() {
        let isFormValid = this.props.formValid;
        return (
            <div>
                <Button
                    disabled={!isFormValid}
                    className={'candidateCustomButton'}
                    onClick={this.handleSubmit}
                    title={!isFormValid ? 'Please fill all fields' : 'Login'}>
                    {'Login'}
                </Button>
            </div>
        )
    }
}