import React from 'react';
import {FormGroup, FormControl, ControlLabel, HelpBlock, Button, Grid, Row, Col} from 'react-bootstrap';
import {addCandidate} from '../utils/api';
import TopNavbar from './TopNavbar';
import './AddCandidate.css';

let btnStyle = {color: 'white', backgroundColor: '#841439'};

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
        this.props.setConfirmationStatus('pending');
        this.props.submitCandidate()
            .then((response) => {
                this.setState({isLoading: false});
                this.props.setConfirmationStatus(response.status === 201 ? 'success' : 'failed');
            })
    };

    render() {
        let isLoading = this.state.isLoading;
        let isFormValid = this.props.formValid;

        return (

            <div>

                <Button
                    style={btnStyle}
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
            emailAddressValidationStatus: null,
            firstName: '',
            firstNameValidationMsg: '',
            firstNameValidationStatus: null,
            lastName: '',
            lastNameValidationMsg: '',
            lastNameValidationStatus: null,
            phoneNumber: '',
            phoneNumberValidationMsg: '',
            phoneNumberValidationStatus: null,
            confirmationMessage: '',
            confirmationStatus: null
        };
    }

    handleChangeEmail = (e) => {

        const emailAddress = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (emailAddress === '') {
            validationMessage = 'Email required!';
        }
        else {
            // regex for testing the allowed email formats
            // http://jsfiddle.net/ghvj4gy9/embedded/result,js/
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

    handleChangePhoneNumber = (e) => {
        const phoneNumber = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (phoneNumber === '') {
            validationMessage = 'Phone number required';
        }

        else {
            const regexCheckResult = this.checkRegexAndGetMessage(phoneNumber, /^(\+)?[0-9]{10,}$/);
            validationMessage = regexCheckResult.validationMessage;
            validationStatus = regexCheckResult.validationStatus;
        }

        this.setState({
            phoneNumber: phoneNumber,
            phoneNumberValidationMsg: validationMessage,
            phoneNumberValidationStatus: validationStatus
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
            email: this.state.emailAddress,
            phone: this.state.phoneNumber
        };
        return addCandidate(candidate);
    };

    setConfirmationStatus = (confirmationStatus) => {

        let message = '';
        if (confirmationStatus === 'pending') {
            message = '...';
        }
        else {
            message = 'Candidate ' + this.state.firstName + ' ' +  this.state.lastName + ' ' ;
            message = confirmationStatus === 'success' ? message + ' succesfully added!' : message + "couldn't be added!";
        }

        this.setState({
            confirmationMessage: message,
            confirmationStatus: confirmationStatus
        })
    };

    formValid = () => {
        return (this.state.emailAddressValidationStatus === 'success' && this.state.firstNameValidationStatus === 'success' && this.state.lastNameValidationStatus === 'success')
    };

    render() {
        return (
        <div>
        <TopNavbar/>
            <Grid>
                {/* Personal info section */}
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

                    <FieldGroup id="formPhoneNumber"
                                label="Phone number"
                                validationState={this.state.phoneNumberValidationStatus}
                                help={this.state.phoneNumberValidationMsg}
                                type="text"
                                value={this.state.phoneNumber}
                                placeholder="Enter phone number"
                                onChange={this.handleChangePhoneNumber}>
                    </FieldGroup>

                </form>
                {/* Buttons section */}
                <Row>
                    <Col xs={4} md={3}>
                        <ButtonAddCandidate formValid={this.formValid()} submitCandidate={this.submitCandidate}
                                            setConfirmationStatus={this.setConfirmationStatus}/>
                    </Col>
                    <Col xs={14} md={9}>
                        <Button id="btn-home" className="float-right" style={btnStyle} href="/">Home</Button>
                    </Col>
                </Row>

                {/* Confirmation message section */}
                <Row>
                    <Col xs={18} md={12}>
                        <h2 className={(this.state.confirmationStatus ? 'success-message' : 'error-message') + ' text-center'} style={{color: '#841439'}}>{this.state.confirmationMessage}</h2>
                    </Col>
                </Row>
            </Grid>
            </div>
        )
    }
}

export default AddCandidate;