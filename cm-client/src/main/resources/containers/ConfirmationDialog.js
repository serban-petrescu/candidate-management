import React from 'react';
import {Button, Modal} from 'react-bootstrap';
import {connect} from 'react-redux';
import {removeCandidate} from '../actions/index';
import {bindActionCreators} from 'redux'

class ConfirmationDialog extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
        };
        this._open = () => this.open();
        this._close = () => this.close();
        this._removeCandidate = () => this.removeCandidate();
    }

    close() {
        this.setState({showModal: false});
    }

    open() {
        this.setState({showModal: true});
    }

    removeCandidate = () => {
        this.setState({showModal: false});
        let result = this.props.removeCandidate(this.props.activeCandidate.id);
        let HTTP_STATUS_NO_CONTENT = 204;

        showNotification(result, HTTP_STATUS_NO_CONTENT, "delete");
    };

    render() {

        let activeCandidate = this.props.activeCandidate;

        return (
            <div style={{display: "inline"}}>
                <button onClick={this._open } type="button" className="btn-defaultCustom btn btn-default">
                    <span style={{color: "red"}} className="glyphicon glyphicon-remove"/>
                </button>

                <Modal show={this.state.showModal} onHide={this._close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Delete candidate</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <FormGroup>
                            <Col sm={10}>
                                Are you sure you want to delete candidate
                                <strong>{activeCandidate != null ? ' ' + activeCandidate.lastName + ' ' + activeCandidate.firstName : ''}</strong>?
                            </Col>
                            <Col>
                                <Button onClick={this._removeCandidate} className="margin-right2">Yes</Button>
                                <Button onClick={this._close}>No</Button>
                            </Col>
                        </FormGroup>
                    </Modal.Body>
                </Modal>
            </div>
        )
    }
}


function mapStateToProps(state) {
    return {
        activeCandidate: state.activeCandidate
    }
}
/**
 *  Hook components up to redux actions without having a dependency on redux using
 * bindActionsCreators function.  Anything returned from this function will end up as props
 * on the ConfirmationDialog component
 * @param dispatch
 * @returns {{removeCandidate: removeCandidate}|B|N}
 */

function mapDispatchToProps(dispatch) {
    // whenever deleteCandidate is called, the result should be passed
    // to all our reducers
    return bindActionCreators({removeCandidate: removeCandidate}, dispatch);
}
/**
 * Connect components to the redux store of the application
 */
export default connect(mapStateToProps, mapDispatchToProps)(ConfirmationDialog);
