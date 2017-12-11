import React from 'react';
import AddCandidate from './AddCandidate';

import {HashRouter as Router, Route} from 'react-router-dom';
import TopNavbar from './TopNavbar';
import Validation from './Validation';
import Home from './Home';
import Import from './Import';

/**
 * Each route will be identified in a <Route> component. The <Route> component will take two properties:
 * path and component. When a path matches the path given to the <Route> component, it will return
 * the component specified.
 */
class App extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <TopNavbar/>
                    <Route exact={true} path="/" component={Home}/>
                    <Route path="/addCandidate" component={AddCandidate}/>
                    <Route path="/validation" component={Validation}/>
                    <Route path="/import" component={Import}/>
                </div>
            </Router>
        );
    }
}

// <Route path="/addCandidateNote" component={AddNote}/>
export default App;
