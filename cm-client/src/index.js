import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';

import App from './components/App';
import AddCandidate from  './components/AddCandidate';

import logo from './img/logo.svg';

ReactDOM.render((
        <Router>
            <div className="App">
                <div className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h2>Welcome to Candidate-Management</h2>
                </div>

                <Route exact={true} path="/" component={App}/>
                <Route path="/addCandidate" component={AddCandidate}/>

            </div>
        </Router>
    ), document.getElementById('root')
);
