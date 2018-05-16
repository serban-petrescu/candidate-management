import React from 'react';
import {FormGroup, FormControl, ControlLabel, HelpBlock, Button, Grid, Row, Col} from 'react-bootstrap';
import {addCandidate} from '../actions/CandidateActions';
import '../less/addCandidate.less';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import ButtonAddCandidate from './ButtonAddCandidate';
import {showNotification} from '../utils/ApiNotification.js';

function FieldGroup({id, label, validationState, help, ...props}) {
    return (
        <FormGroup controlId={id} validationState={validationState}>
            <ControlLabel>{label}</ControlLabel>
            <FormControl {...props}/>
            { help && <HelpBlock>{help}</HelpBlock>}
        </FormGroup>
    )
}

let fieldsInitialValues = {
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
    phoneNumberValidationStatus: null
};

/**
 * Component handling the add operation of a candidate
 */
class AddCandidate extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            ...fieldsInitialValues,
            remainOnPage: false
        }
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
            const regexCheckResult = this.checkRegexAndGetMessage(phoneNumber, /^(0|(0040|004\s0)|(\+40|\+4\s0))([0-9]{3}\s?|[0-9]{2}\s[0-9])(([0-9]{3}\s?){2}|([0-9]{2}\s?){3})$/);
            validationMessage = regexCheckResult.validationMessage;
            validationStatus = regexCheckResult.validationStatus;
        }

        this.setState({
            phoneNumber: phoneNumber,
            phoneNumberValidationMsg: validationMessage,
            phoneNumberValidationStatus: validationStatus
        })
    };

    handleCheckbox = () => {
        this.setState({
            remainOnPage: !this.state.remainOnPage
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
        let HTTP_STATUS_CREATED = 201;
        let result = this.props.addCandidate(candidate);
        showNotification(result, HTTP_STATUS_CREATED, "create");

        return result;
    };

    afterSubmit = () => {
        if (this.state.remainOnPage) {
            this.setState({
                ...fieldsInitialValues
            });
        } else {
            window.location.href = '#/home';
        }
    };

    formValid = () => {
        return (this.state.emailAddressValidationStatus === 'success' && this.state.firstNameValidationStatus === 'success'
        && this.state.lastNameValidationStatus === 'success' && this.state.phoneNumberValidationStatus === 'success');
    };

    render() {
        return (
        <div>
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
                    <input id="checkbox_stay_on_page" type="checkbox" checked={this.state.remainOnPage} onClick={this.handleCheckbox}/>
                    <label for="checkbox_stay_on_page" className="checkbox-label">Remain on the current page to add another candidate</label>
                </form>
                {/* Buttons section */}
                <Row>
                    <Col xs={4} md={3}>
                        <ButtonAddCandidate formValid={this.formValid()} submitCandidate={this.submitCandidate} />
                    </Col>
                    <Col xs={14} md={9}>
                        <Button id="btn-home" className="float-right candidateCustomButton" href="/">Home</Button>
                    </Col>
                </Row>
            </Grid>
            </div>
        )
    }
}

function mapDispatchToProps(dispatch) {

    return bindActionCreators({addCandidate: addCandidate}, dispatch);
}
export default connect(null, mapDispatchToProps)(AddCandidate);