import React from 'react';
import msgLogo from '../assets/images/msgLogo.png'
import "roboto-fontface/css/roboto/roboto-fontface.css"
import '../less/candidateTopNavbar.less'
/**
 * Component containing the msg logo and the top navigation bar
 */
export default class TopNavbar extends React.Component {

    render() {

        return (
            <div>
                <nav  className="navbar navbar-default navbar-fixed-top">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="#">
                                <img href="${ROOT_URL}:3000" alt="Brand" src={msgLogo} width={100} height={35}/>
                            </a>

                        </div>
                    </div>

                </nav>
                <div className="page-header candidate-top-navbar">
                                    <h1>Candidate Management</h1>
                </div>
            </div>
        );

    }
}