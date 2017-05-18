import React from 'react';
import {Button, Modal, Form, FormGroup, Col, FormControl, ControlLabel} from 'react-bootstrap';
import {updateCandidate} from "../utils/api";

class EditCandidate extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            candidate: this.props.candidate
        };
        this._open = () => this.open();
        this._close = ()=> this.close();
    }

    close() {
        this.setState({showModal: false});
    }

    open() {
        this.setState({showModal: true});
    }

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
        updateCandidate(this.state.candidate).then(response => {
            this.props.refreshCandidateTable();
            this.setState({showModal: false});
        });
    };

    render() {

        return (
            <div>
                <Button
                    bsStyle="primary"
                    onClick={this._open}>
                    Edit Candidate
                </Button>

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
                                    <FormControl type="text" name='firstName' placeholder={this.state.candidate.firstName} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalLastName">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Last Name
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="text" name='lastName' placeholder={this.state.candidate.lastName} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalEmail">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Email
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="email" name='email' placeholder={this.state.candidate.email} onChange={this.handleChange}/>
                                </Col>
                            </FormGroup>

                            <FormGroup controlId="formHorizontalPhone">
                                <Col componentClass={ControlLabel} sm={4}>
                                    Phone
                                </Col>
                                <Col sm={6}>
                                    <FormControl type="tel" name='phone' placeholder={this.state.candidate.phone} onChange={this.handleChange}/>
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

export default EditCandidate;