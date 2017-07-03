import React from 'react';
import {FormGroup, FormControl, ControlLabel, HelpBlock, Button, Grid, Row, Col} from 'react-bootstrap';
import TopNavbar from './TopNavbar';
import '../less/addCandidate.less';
import ButtonAddCandidateNote from './ButtonAddCandidateNote';
import {addCandidateNote} from '../actions/index';

import {bindActionCreators} from "redux";
import {connect} from "react-redux";

function FieldGroup({id, label, validationState, help, ...props}) {
    return (
        <FormGroup controlId={id} validationState={validationState}>
            <ControlLabel>{label}</ControlLabel>
            <FormControl {...props}/>
            { help && <HelpBlock>{help}</HelpBlock>}
        </FormGroup>
    )
}
class AddNote extends React.Component {
    constructor(props) {
        super(props);
        var today = new Date(),
            date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
        this.state = {
            candidate:props.candidate,
            date:date,
            status: '',
            note: '',
            confirmationMessage: '',
            confirmationStatus: ''
        };
    }
    handleChangeNoteDate = (e) => {
        this.setState({
            date: e.target.value,
        })
    };
    handleChangeNoteStatus = (e) => {
        this.setState({
            status: e.target.value,
        })
    };
    handleChangeNoteNote = (e) => {
        this.setState({
            note: e.target.value,
        })
    };

    submitCandidateNote = () => {
        let candidateNote = {
            candidate: this.state.candidate,
            date: this.state.date,
            status: this.state.status,
            note: this.state.note
        };
        return this.props.addCandidateNote(candidateNote);
    };


    setConfirmationStatus = (confirmationStatus) => {

        let message = '';
        if (confirmationStatus === 'pending') {
            message = '...';
        }
        else {
            message = 'Candidate status' + this.state.status+ ' ' +  this.state.note + ' ' ;
            message = confirmationStatus === 'success' ? message + ' succesfully added!' : message + "couldn't be added!";
        }

        this.setState({
            confirmationMessage: message,
            confirmationStatus: confirmationStatus
        })
    };

    render() {

        return (<div><TopNavbar/>
            <Grid>
                {/* Note info section */}
                <form>
                    <FieldGroup id="formDate"
                                label="Date"
                                validationState="success"
                                type="text"
                                value={this.state.date}
                                placeholder="Change date"
                                onChange={this.handleChangeNoteDate}>
                    </FieldGroup>
                    <FieldGroup id="formStatus"
                                label="Status"
                                validationState="success"
                                type="text"
                                value={this.state.status}
                                placeholder="Enter status"
                                onChange={this.handleChangeNoteStatus} >
                    </FieldGroup>

                    <FieldGroup id="formNote"
                                label="Note"
                                validationState="success"
                                type="text"
                                value={this.state.note}
                                placeholder="Enter note"
                                onChange={this.handleChangeNoteNote}>
                    </FieldGroup>
                </form>
                {/* Buttons section */}
                <Row>
                    <Col xs={4} md={3}>
                        <ButtonAddCandidateNote submitCandidateNote={this.submitCandidateNote}
                                            setConfirmationStatus={this.setConfirmationStatus}/>
                    </Col>
                    <Col xs={14} md={9}>
                        <Button id="btn-home" className="float-right candidateCustomButton" href="/">Home</Button>
                    </Col>
                </Row>

                {/* Confirmation message section */}
                <Row>
                    <Col xs={18} md={12}>
                        <h2 className={(this.state.confirmationStatus ? 'success-message' : 'error-message') + ' text-center'}>{this.state.confirmationMessage}</h2>
                    </Col>
                </Row>
            </Grid></div>);}
}

function mapDispatchToProps(dispatch) {

    return bindActionCreators({addCandidateNote: addCandidateNote}, dispatch);
}
export default connect(null, mapDispatchToProps)(AddNote);