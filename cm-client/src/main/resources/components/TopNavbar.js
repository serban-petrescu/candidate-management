import React from 'react';
import msgLogo from '../assets/images/msgLogo.png';
import "roboto-fontface/css/roboto/roboto-fontface.css";
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
        if (window.location.href.indexOf('validation') !== -1 ) {
            this.state = {
                activeKey: 2
            };
        } else {
            this.state = {
                activeKey: 1
            };
        }

    }

    handleSelect = (selectedKey) => {
        this.setState({activeKey:selectedKey});
        if(selectedKey === 1) {
            window.location.href = '#/';
        } else {
            window.location.href = '#/validation';
        }
    };

    render() {
        return (
            <div>
                <NotificationContainer/>
                <Navbar fluid>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <img href="/" alt="Brand" src={msgLogo} width={100} height={35}/>
                        </Navbar.Brand>
                        <Navbar.Toggle/>
                    <ButtonLogout/>
                    </Navbar.Header>

                    <Navbar.Collapse>
                        <Nav activeKey={this.state.activeKey} onSelect={this.handleSelect} className="navBar">
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