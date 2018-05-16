import React from 'react';
import {Button, Modal, Form, FormGroup, Col, FormControl, ControlLabel} from 'react-bootstrap';
import {bindActionCreators} from "redux";
import {editCandidate} from "../actions/CandidateActions";
import {connect} from "react-redux";
import {showNotification} from '../utils/ApiNotification.js';
/**
 * Component used when the used clicks on the edit button.
 * An internal state containing a candidate will be used.
 * We do not keep the candidate in the redux global state because whenever
 * the user will type a letter the candidate will be updated and maybe at the end
 * the user will not press save but still the candidate will be changed.
 * Using an internal state lets us only update the global redux state when the user pressed
 * on save.
 */
class EditCandidate extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            candidate: this.props.candidate
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

    /**
     * Whenever a user types something on one of the inputs, handle change will update the candidate info
     * based on the field name taken from the event. For example when typing something into email input:
     * fieldName will be taken from inputs name attribute --> event.target.name = email
     * fieldVal will be take from inputs value attribute --> event.target.value = whatever user typed;
     * {...this.state.candidate, [fieldName]: fieldVal} a new candidate object created from the old candidate's fields
     * having their fieldName(email) update with the fieldVal(whatever user typed)
     * @param event
     */
    handleChange = (event) => {
        let fieldName = event.target.name;
        let fieldVal = event.target.value;
        this.setState(
            {
                candidate: {...this.state.candidate, [fieldName]: fieldVal}
            }
        );
    };
    updateCandidatePersonalInfo = () => {
        let result = this.props.editCandidate(this.state.candidate);
        result.then (() => {
            this.props.onEdit();
        });
        let HTTP_STATUS_OK = 200;
        showNotification(result, HTTP_STATUS_OK, "update");
        this.setState({showModal: false});
    };

    render() {
        return (
            <div style={{display: "inline"}}>
                <button onClick={this._open} type="button" className="btn-defaultCustom btn btn-default">
                    <span style={{color: "blue"}} className="glyphicon glyphicon-pencil"/>
                </button>

                <Modal show={this.state.showModal} onHide={this._close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Edit Candidate Information</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form horizontal>
                            <FormGroup controlId="formHorizontalFirstName">
                                <Col componentClass={ControlLabel} sm={4}>
                                    First Name
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='firstName' value={this.state.candidate.firstName} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalLastName">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Last Name
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='lastName' value={this.state.candidate.lastName} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalEmail">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Email
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="email" name='email' value={this.state.candidate.email} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalPhone">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Phone
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="tel" name='phone' value={this.state.candidate.phone} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this._close}>Close</Button>
                        <Button type="submit" onClick={this.updateCandidatePersonalInfo}>Save</Button>
                    </Modal.Footer>

                </Modal>
            </div>
        )
    }
}

/**
 * Hook components up to redux actions without having a dependency on redux using
 * bindActionsCreators function.
 * @returns {{editCandidate: editCandidate}|B|N}
 */
function mapDispatchToProps(dispatch) {

    return bindActionCreators({editCandidate: editCandidate}, dispatch);
}
/**
 * Connect components to the redux store of the application
 */
export default connect(null, mapDispatchToProps)(EditCandidate);