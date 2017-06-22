import React from 'react';
import {Button, Modal} from 'react-bootstrap';
import { connect } from 'react-redux';
import {removeCandidate} from '../actions/index';
import {bindActionCreators} from 'redux'

class ConfirmationDialog extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
        };
        this._open = () => this.open();
        this._close = ()=> this.close();
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
        this.props.removeCandidate(this.props.activeCandidate.id);
    };

    render() {

        let activeCandidate = this.props.activeCandidate;

        return (
            <div style={{display: "inline"}}>
                <button  onClick={this._open } type="button" className="btn-defaultCustom btn btn-default">
                                 <span style={{color:"red"}} className="glyphicon glyphicon-remove" />
                </button>

                <Modal show={this.state.showModal} onHide={this._close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Are you sure you want to delete candidate <strong>{activeCandidate!= null ? activeCandidate.lastName + ' ' + activeCandidate.firstName : ''}</strong>?</Modal.Title>
                    </Modal.Header>
                    <Modal.Footer>
                        <Button onClick={this._removeCandidate}>Yes</Button>
                        <Button onClick={this._close}>No</Button>
                    </Modal.Footer>
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

// Anything returned from this function will end up as props
// on the ConfirmationDialog component
function mapDispatchToProps(dispatch) {
    // whenever deleteCandidate is called, the result should be passed
    // to all our reducers
    return bindActionCreators({ removeCandidate: removeCandidate}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ConfirmationDialog);
