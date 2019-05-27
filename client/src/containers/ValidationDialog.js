import React from 'react';
import {Button, Modal, FormGroup, Col} from 'react-bootstrap';
import {connect} from 'react-redux';
import {validateCandidate as validateAction} from '../actions/CandidateValidationActions';
import {bindActionCreators} from 'redux';
import {showNotification} from '../utils/ApiNotification';

class ValidationDialog extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false
        };
        this._open = () => this.open();
        this._close = () => this.close();
        this._validateCandidate = () => this.validateCandidate();
    }

    close() {
        this.setState({showModal: false});
    }

    open() {
        this.setState({showModal: true});
    }

    validateCandidate = () => {
        this.setState({showModal: false});
        let result = this.props.validateAction(this.props.activeCandidate.id);
        result.then(() => {
            this.props.onValidate();
        });
        let HTTP_STATUS_OK = 200;
        showNotification(result, HTTP_STATUS_OK, "validate");
    };

    render() {

        let candidate = this.props.activeCandidate;

        return (
            <div style={{display: "inline"}}>
                <button onClick={this._open } type="button" className="btn-defaultCustom btn btn-default">
                    <span style={{color: "green"}} className="glyphicon glyphicon-ok"/>
                </button>

                <Modal show={this.state.showModal} onHide={this._close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Validate candidate</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <FormGroup>
                            <Col sm={10}>
                                Are you sure you want to validate candidate
                                <strong>{candidate != null ? ' ' + candidate.firstName + ' ' +  candidate.lastName: ''}</strong>?
                            </Col>
                            <Col>
                                <Button onClick={this._validateCandidate} className="margin-right2">Yes</Button>
                                <Button onClick={this._close}>No</Button>
                            </Col>
                        </FormGroup>
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        activeCandidate: state.activeCandidate
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({validateAction}, dispatch);
}

/**
 * Connect components to the redux store of the application
 */
export default connect(mapStateToProps, mapDispatchToProps)(ValidationDialog);
