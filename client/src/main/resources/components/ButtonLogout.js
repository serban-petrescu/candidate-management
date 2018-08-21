import React from 'react';
import {Button} from 'react-bootstrap';
import {logoutUser} from '../actions/LoginLogout';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class ButtonLogout extends React.Component {

    handleClick = () => {
        let HTTP_STATUS_OK = 200;
        let result = this.props.logoutUser();
        result.then((success) => {
            window.location = '#/';
            sessionStorage.setItem('userLogged', false);
        });
    };

    render() {
        return (
            <div>
                <Button
                    className={'candidateCustomButton logoutButton ' + this.props.className}
                    onClick={this.handleClick}
                    title={'Logout'}>
                    {'Logout'}
                </Button>
            </div>
        );
    }
}

function mapDispatchToProps(dispatch) {

    return bindActionCreators({logoutUser}, dispatch);
}

export default connect(null, mapDispatchToProps)(ButtonLogout);