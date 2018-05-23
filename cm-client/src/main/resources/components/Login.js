import React from 'react';
import ButtonLogin from './ButtonLogin';
import {Grid, FormGroup, FormControl, ControlLabel, HelpBlock, Row, Col} from 'react-bootstrap';
import {verifyUser} from '../actions/LoginLogout';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {showNotification} from '../utils/ApiNotification';
import {logoutUser} from '../actions/LoginLogout';

function FieldGroup({id, label, validationState, help, ...props}) {
    return (
        <FormGroup controlId={id} validationState={validationState}>
            <ControlLabel>{label}</ControlLabel>
            <FormControl {...props}/>
            { help && <HelpBlock>{help}</HelpBlock>}
        </FormGroup>
    );
}

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            usernameValidationMsg: '',
            usernameValidationState: null,

            password: '',
            passwordValidationMsg: '',
            passwordValidationState: null
        };
    }

    handleChangeUsername = (e) => {

        const username = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (!username) {
            validationMessage = 'Username required!';
        } else {
            const regex = /^\w{3,}$/;
            if (regex.test(username)) {
                validationStatus = 'success';
            } else {
                validationMessage = 'Username must contain at least 3 characters, all alphanumeric';
            }
        }

        this.setState({
            username,
            usernameValidationMsg: validationMessage,
            usernameValidationState: validationStatus
        });
    };

    handleChangePassword = (e) => {

        const password = e.target.value;
        let validationMessage = '';
        let validationStatus = 'error';

        if (!password) {
            validationMessage = 'Password required!';
        } else {
            const regex = /^[a-zA-Z0-9 \W].{7,}$/;
            if (regex.test(password)) {
                validationStatus = 'success';
            } else {
                validationMessage = 'Password must contain at least 8 characters';
            }
        }
        this.setState({
            password,
            passwordValidationMsg: validationMessage,
            passwordValidationState: validationStatus
        });
    };
    handleLogin = () => {

        let HTTP_STATUS_OK = 200;
        let result = this.props.verifyUser(this.state.username, this.state.password);
        result.then((success) => {
            if (success.payload.response.status === HTTP_STATUS_OK) {
                sessionStorage.setItem('userLogged', true);
                window.location = '#/home';
            } else {
                sessionStorage.setItem('userLogged', false);
            }
        });

        showNotification(result, HTTP_STATUS_OK, "login");
    };

    formValid = () => {
        return (this.state.usernameValidationState === 'success' && this.state.passwordValidationState === 'success');
    };

    render() {
        return (
            <div>
                <Grid className="loginGrid">
                    {/* Login info section */}
                    <form>
                        <header>Login</header>
                        <FieldGroup id="username"
                                    label="Username"
                                    validationState={this.state.usernameValidationState}
                                    type="text"
                                    value={this.state.username}
                                    help={this.state.usernameValidationMsg}
                                    placeholder="Enter username"
                                    onChange={this.handleChangeUsername}>
                        </FieldGroup>

                        <FieldGroup id="password"
                                    label="Password"
                                    validationState={this.state.passwordValidationState}
                                    type="password"
                                    value={this.state.password}
                                    help={this.state.passwordValidationMsg}
                                    placeholder="Enter password"
                                    onChange={this.handleChangePassword}>
                        </FieldGroup>

                    </form>
                    {/* Buttons section */}
                    <Row>
                        <Col className='float-right paddingRight16'>
                            <ButtonLogin formValid={this.formValid()} handleLogin={this.handleLogin}/>
                        </Col>
                    </Row>

                </Grid>
            </div>
        );
    }
}

function mapDispatchToProps(dispatch) {

    return bindActionCreators({verifyUser}, dispatch);
}

export default connect(null, mapDispatchToProps)(Login);