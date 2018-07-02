import React from 'react';
import msgLogo from '../assets/images/msgLogo.png';
import "roboto-fontface/css/roboto/roboto-fontface.css";
import "../less/candidateTopNavbar.less";
import {Navbar, Nav, NavItem} from 'react-bootstrap';
import {NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import ButtonLogout from './ButtonLogout';

/**
 * Component containing the msg logo and the top navigation bar
 */
export default class TopNavbar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            activeKey: window.location.href.indexOf('validation') !== -1 ? 2 : 1
        };
    }

    handleSelect = (selectedKey) => {
        this.setState({activeKey:selectedKey});
        window.location.href = selectedKey === 1 ? '#/home' : '#/validation';
    };

    render() {
        let userActionsClass = '';
        if (!sessionStorage.getItem('userLogged')  || sessionStorage.getItem('userLogged') === "false") {
            userActionsClass = 'hidden';
        }
        return (
            <div>
                <NotificationContainer/>
                <Navbar fluid>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <img href="#/home" alt="Brand" src={msgLogo} width={100} height={35}/>
                        </Navbar.Brand>
                        <Navbar.Toggle/>
                        <ButtonLogout className={userActionsClass}/>
                    </Navbar.Header>

                    <Navbar.Collapse>
                        <Nav activeKey={this.state.activeKey} onSelect={this.handleSelect} className={"navBar " + userActionsClass}>
                            <NavItem eventKey={1}>Home</NavItem>
                            <NavItem eventKey={2}>Validation</NavItem>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>

                <div className="page-header candidate-top-navbar">
                    <h1>Candidate Management</h1>
                </div>
            </div>
        );

    }
}