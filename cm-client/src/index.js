import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';

import App from './components/App';
import AddCandidate from './components/AddCandidate';



ReactDOM.render((
    <Router>
        <div>
            <Route exact={true} path="/" component={App}/>
            <Route path="/addCandidate" component={AddCandidate}/>
        </div>
    </Router>
    ), document.getElementById('root')
);