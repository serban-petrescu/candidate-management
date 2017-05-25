import React from 'react';
import AddCandidate from './AddCandidate';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Home from './Home'

class App extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <Route exact={true} path="/" component={Home}/>
                    <Route path="/addCandidate" component={AddCandidate}/>
                </div>
            </Router>
        );
    }
}

export default App;
