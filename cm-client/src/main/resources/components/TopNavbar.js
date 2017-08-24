import React from 'react';
import msgLogo from '../assets/images/msgLogo.png';
import "roboto-fontface/css/roboto/roboto-fontface.css";
import '../less/candidateTopNavbar.less';
import {Navbar, Nav, NavItem} from 'react-bootstrap';

/**
 * Component containing the msg logo and the top navigation bar
 */
export default class TopNavbar extends React.Component {

    render() {

        return (
            <div>
                <Navbar fluid>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <img href="/" alt="Brand" src={msgLogo} width={100} height={35}/>
                        </Navbar.Brand>
                        <Navbar.Toggle/>
                    </Navbar.Header>

                    <Navbar.Collapse>
                        <Nav>
                            <NavItem href="/">Home</NavItem>
                            <NavItem href="/validation">Validation</NavItem>
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