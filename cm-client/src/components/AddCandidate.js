import React from 'react';
import {FormGroup, FormControl, ControlLabel, HelpBlock, Button, Grid, Row, Col} from 'react-bootstrap';

import {addCandidate} from '../utils/api';

import './AddCandidate.css';

function FieldGroup({id, label, validationState, help, ...props}) {
    return (
        <FormGroup controlId={id} validationState={validationState}>
            <ControlLabel>{label}</ControlLabel>
            <FormControl {...props}/>
            { help && <HelpBlock>{help}</HelpBlock>}
        </FormGroup>
    )
}

class ButtonAddCandidate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    handleClick = () => {
        this.setState({isLoading: true});

        this.props.submitCandidate()
            .then((response) => {
                this.setState({isLoading: false});
                console.log(response.status);

                if (response.status === 201) {
                    console.log('Candidate added successfully!');
                }
                else {
                    console.log('An error has occured!')
                }
            })
    };

    render() {
        let isLoading = this.state.isLoading;
        let isFormValid = this.props.formValid;

        return (
            <div>
                <Button
                    bsStyle="primary"
                    disabled={isLoading || !isFormValid}
                    onClick={!isLoading ? this.handleClick : null}>
                    { isLoading ? 'Loading...' : 'Add Candidate'}
                </Button>
            </div>
        )
    }
}

class AddCandidate extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            emailAddress: '',
            emailAddressValidationMsg: '',
            emailAddressValidationStatus: '',
            firstName: '',
            firstNameValidationMsg: '',
            firstNameValidationStatus: '',
            lastName: '',
            lastNameValidationMsg: '',
            lastNameValidationStatus: ''
        };

        this.handleChangeEmail = this.handleChangeEmail.bind(this);
    }

    handleChangeEmail = (e) => {

        const emailAddress = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (emailAddress === '') {
            validationMessage = 'Email required!';
        }
        else {
            const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            let regexCheckResult = this.checkRegexAndGetMessage(emailAddress, regex);
            validationMessage = regexCheckResult.validationMessage;
            validationStatus = regexCheckResult.validationStatus;
        }

        this.setState({
            emailAddress: emailAddress,
            emailAddressValidationMsg: validationMessage,
            emailAddressValidationStatus: validationStatus
        })
    };

    handleChangeFirstName = (e) => {

        const firstName = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (firstName === '') {
            validationMessage = 'First name required!';
        }

        else {
            const regexCheckResult = this.checkRegexAndGetMessage(firstName, /^[a-zA-Z ]+$/);
            validationMessage = regexCheckResult.validationMessage;
            validationStatus = regexCheckResult.validationStatus;
        }

        this.setState({
            firstName: firstName,
            firstNameValidationMsg: validationMessage,
            firstNameValidationStatus: validationStatus
        })
    };

    handleChangeLastName = (e) => {
        const lastName = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (lastName === '') {
            validationMessage = 'Last name required!';
        }

        else {
            const regexCheckResult = this.checkRegexAndGetMessage(lastName, /^[a-zA-Z ]+$/);
            validationMessage = regexCheckResult.validationMessage;
            validationStatus = regexCheckResult.validationStatus;
        }

        this.setState({
            lastName: lastName,
            lastNameValidationMsg: validationMessage,
            lastNameValidationStatus: validationStatus
        })
    };

    checkRegexAndGetMessage = (value, regex) => {

        let validationMessage = '';
        let validationStatus = '';

        if (regex.test(value)) {
            validationMessage = 'Valid!';
            validationStatus = 'success';
        }
        else {
            validationMessage = 'Invalid!';
            validationStatus = 'error';
        }

        return {
            validationMessage: validationMessage,
            validationStatus: validationStatus
        }
    };

    submitCandidate = () => {
        let candidate = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.emailAddress
        };
        return addCandidate(candidate);
    };

    formValid = () => {
        return (this.state.emailAddressValidationStatus === 'success' && this.state.firstNameValidationStatus === 'success' && this.state.lastNameValidationStatus === 'success')
    };

    render() {
        return (
            <Grid>
                <form>
                    <FieldGroup id="formFirstName"
                                label="First Name"
                                validationState={this.state.firstNameValidationStatus}
                                help={this.state.firstNameValidationMsg}
                                type="text"
                                value={this.state.firstName}
                                placeholder="Enter first name"
                                onChange={this.handleChangeFirstName}>
                    </FieldGroup>

                    <FieldGroup id="formLastName"
                                label="Last Name"
                                validationState={this.state.lastNameValidationStatus}
                                help={this.state.lastNameValidationMsg}
                                type="text"
                                value={this.state.lastName}
                                placeholder="Enter last name"
                                onChange={this.handleChangeLastName}>
                    </FieldGroup>

                    <FieldGroup id="formEmailAddress"
                                label="Email address"
                                validationState={this.state.emailAddressValidationStatus}
                                help={this.state.emailAddressValidationMsg}
                                type="text"
                                value={this.state.emailAddress}
                                placeholder="Enter email"
                                onChange={this.handleChangeEmail}>
                    </FieldGroup>

                </form>
                <Row>
                    <Col xs={4} md={3}>
                        <ButtonAddCandidate formValid={this.formValid()} submitCandidate={this.submitCandidate}/>
                    </Col>
                    <Col xs={14} md={9}>
                        <Button className="btn-float-right" bsStyle="primary" href="/">Table Return</Button>
                    </Col>
                </Row>
            </Grid>


        )
    }
}

export default AddCandidate;