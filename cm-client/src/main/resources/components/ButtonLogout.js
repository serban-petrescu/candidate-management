import React from 'react';
import {Button} from 'react-bootstrap';

export default
class ButtonLogout extends React.Component {
    constructor(props) {
        super(props);
    }

    handleClick = () => {
        sessionStorage.setItem('userLogged', false);
        window.location='/';
    };

    render() {
        let displayButton = '';
        if (sessionStorage.getItem('userLogged') === "false") {
            displayButton = 'hidden';
        }
        return (
            <div>
                <Button
                    className={'candidateCustomButton logoutButton ' + displayButton}
                    onClick={this.handleClick}
                    title={'Logout'}>
                    {'Logout'}
                </Button>
            </div>
        )
    }
}