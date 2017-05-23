import React from 'react';
import CandidatesTable from './CandidatesTable';
import AddCandidate from './AddCandidate';
import {BrowserRouter as Router, Route} from 'react-router-dom';

class App extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <Route exact={true} path="/" component={CandidatesTable}/>
                    <Route path="/addCandidate" component={AddCandidate}/>
                </div>
            </Router>
        );
    }
}

export default App;
