import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import App from './components/App';
import AddCandidate from './components/AddCandidate';
// Importing jQuery in ES6 style
import $ from 'jquery';
window.$ = $;
// bootstrap
import 'bootstrap';
// bootstrap css
import 'bootstrap/dist/css/bootstrap.css'


ReactDOM.render((
    <Router>
        <div>
            <Route exact={true} path="/" component={App}/>
            <Route path="/addCandidate" component={AddCandidate}/>
        </div>
    </Router>
    ), document.getElementById('root')
);