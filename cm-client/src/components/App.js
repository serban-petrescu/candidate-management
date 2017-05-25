// React imports
import React, {Component} from 'react';

import CandidatesTable from './CandidatesTable';
import TopNavbar from './TopNavbar';
const divStyle = {
    backgroundColor: "b"
};

class App extends Component {
    render() {
        return (
            <div style={divStyle}>
                 <TopNavbar/>
                <CandidatesTable/>
            </div>
        );
    }
}

export default App;
