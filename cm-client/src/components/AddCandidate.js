import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TextField from 'material-ui/TextField';
import injectTapEventPlugin from 'react-tap-event-plugin';
import {Card, CardHeader} from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import {addCandidate} from '../utils/api';

injectTapEventPlugin();

import './AddCandidate.css';

const cardStyle = {
    textAlign: 'center'
};

const titleText = {

    paddingRight: 0
};

const title = {
    fontSize: '24px'
};

const nameRegex = /^[a-z ,.'-]+$/i;

class AddCandidate extends React.Component {

    fnIsValid = false;
    lnIsValid = false;

    constructor(props) {
        super(props);
        this.state = {
            candidate: {
                firstName: '',
                lastName: ''
            },
            fnErrorText: 'First name is required',
            lnErrorText: 'Last name is required'
        };

        this.handlefnChange = this.handlefnChange.bind(this);
        this.handlelnChange = this.handlelnChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handlefnChange = (e) => {
        if (e.target.value === '') {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    firstName: e.target.value
                },
                lnErrorText: this.state.lnErrorText,
                fnErrorText: 'First name is required'
            })
        } else if (!e.target.value.match(nameRegex)) {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    firstName: e.target.value
                },
                lnErrorText: this.state.lnErrorText,
                fnErrorText: 'Invalid first name (should only contain letters)'
            })
        } else {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    firstName: e.target.value
                },
                lnErrorText: this.state.lnErrorText,
                fnErrorText: ''
            });
            this.fnIsValid = true;
        }
    };

    handlelnChange = (e) => {
        if (e.target.value === '') {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    lastName: e.target.value
                },
                fnErrorText: this.state.fnErrorText,
                lnErrorText: 'Last name is required'
            })
        } else if (!e.target.value.match(nameRegex)) {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    lastName: e.target.value
                },
                fnErrorText: this.state.fnErrorText,
                lnErrorText: 'Invalid last name (should only contain letters)'
            })
        } else {
            this.setState({
                candidate: {
                    ...this.state.candidate,
                    lastName: e.target.value
                },
                fnErrorText: this.state.fnErrorText,
                lnErrorText: ''
            });
            this.lnIsValid = true;
        }
    };

    handleSubmit() {
        addCandidate(this.state.candidate);
    }

    formIsValid() {
        return this.fnIsValid && this.lnIsValid;
    }

    render() {
        return (
            <MuiThemeProvider>
                <div>
                    <Card style={cardStyle}>
                        <CardHeader
                            title="Add New Candidate"
                            titleStyle={title}
                            textStyle={titleText}
                        />
                        <form onSubmit={this.handleSubmit}>
                            <TextField
                                floatingLabelText="First Name"
                                errorText={this.state.fnErrorText}
                                value={this.state.candidate.firstName}
                                onChange={this.handlefnChange}
                            />
                            <br/>
                            <TextField
                                floatingLabelText="Last Name"
                                errorText={this.state.lnErrorText}
                                value={this.state.candidate.lastName}
                                onChange={this.handlelnChange}
                            />
                            <br/>
                            <RaisedButton
                                className="submit-button"
                                label="Add Candidate"
                                primary={true}
                                disabled={!this.formIsValid()}
                                onTouchTap={this.handleSubmit}
                                href="/"
                            />
                        </form>
                    </Card>
                </div>
            </MuiThemeProvider>
        );
    }
}
export default AddCandidate;