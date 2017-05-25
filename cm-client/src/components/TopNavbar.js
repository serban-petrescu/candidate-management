import React from 'react';
import msgLogo from '../assets/images/msgLogo.png'
import "roboto-fontface/css/roboto/roboto-fontface.css"
export default class TopNavbar extends React.Component {

    render() {

        return (
            <div>
                <nav  className="navbar navbar-default navbar-fixed-top">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="#">
                                <img alt="Brand" src={msgLogo} width={100} height={35}/>
                            </a>

                        </div>
                    </div>

                </nav>
                <div style={{color:'#786f6f',marginTop: '100px',fontWeight: 'bold',fontSize:25}} className="page-header">
                    <h1 style={{fontWeight: 'bold',fontSize:50}}>Candidate Management</h1>
                </div>
            </div>
        );

    }
}