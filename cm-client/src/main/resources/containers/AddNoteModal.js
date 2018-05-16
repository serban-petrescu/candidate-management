import React from 'react';
import {Button, Modal, Form, FormGroup, Col, FormControl, ControlLabel} from 'react-bootstrap';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {addCandidateNote} from '../actions/CandidateActions';

/**
 * Component used when the used clicks on the edit button.
 * An internal state containing a candidate will be used.
 * We do not keep the candidate in the redux global state because whenever
 * the user will type a letter the candidate will be updated and maybe at the end
 * the user will not press save but still the candidate will be changed.
 * Using an internal state lets us only update the global redux state when the user pressed
 * on save.
 */
class AddNoteModal extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            candidate: this.props.candidate,
            candidate_id:this.props.candidate.id,
            date:getPrettyDate(),
            status: '',
            note: '',
            confirmationMessage: '',
            confirmationStatus: ''
        };
        this._open = () => this.open();
        this._close = () => this.close();
    }

    /**
     * Close the edit dialog
     */
    close() {
        this.setState({showModal: false});
    }

    /**
     * Open the edit dialog
     */
    open() {
        this.setState({showModal: true});
    }

    handleChangeNoteDate = (e) => {
        this.setState({
            date: e.target.value
        })
    };
    handleChangeNoteStatus = (e) => {
        this.setState({
            status: e.target.value
        })
    };
    handleChangeNoteNote = (e) => {
        this.setState({
            note: e.target.value
        })
    };

    submitCandidateNote = () => {
        let candidateNote = {
            candidateId:this.state.candidate_id,
            date: this.state.date,
            status: this.state.status,
            note: this.state.note
        };
        let result = this.props.addCandidateNote(candidateNote);
        result.then (() => {
            this.props.onAdd();
        });
        this.setState({showModal: false});
    };

    render() {
        return (
            <div style={{display: "inline"}}>
                <button onClick={this._open} type="button" className="candidateCustomButton btn btn-default">
                  Add Note
                </button>

                <Modal label="Add Note Candidate" show={this.state.showModal} onHide={this._close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add Candidate Note</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form horizontal>
                            <FormGroup controlId="formHorizontalDate">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Date
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='date' value={this.state.date} onChange={this.handleChangeNoteDate}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalStatus">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Status
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='status' value={this.state.status} onChange={this.handleChangeNoteStatus}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalnote">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Note
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='Note' value={this.state.note} onChange={this.handleChangeNoteNote}/>
                                </Col>
                            </FormGroup>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this._close}>Close</Button>
                        <Button type="submit" onClick={this.submitCandidateNote}>Save</Button>
                    </Modal.Footer>

                </Modal>
            </div>
        )
    }
}

function getPrettyDate(){
    let today = new Date();
    let prettyMonth = ((today.getMonth() + 1)<10)?'-0':'-';
    let prettyDay = (today.getDate()<10)?'-0':'-';
    return today.getFullYear() + prettyMonth + (today.getMonth() + 1) + prettyDay + today.getDate();
}
/**
 * Hook components up to redux actions without having a dependency on redux using
 * bindActionsCreators function.
 * @returns {{addCandidateNote: addCandidateNote}}
 */
function mapDispatchToProps(dispatch) {

    return bindActionCreators({addCandidateNote: addCandidateNote}, dispatch);
}

/**
 * Connect components to the redux store of the application
 */
export default connect(null, mapDispatchToProps)(AddNoteModal);