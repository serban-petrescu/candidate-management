import React, {Component} from 'react';
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';

import CandidatesTable from './CandidatesTable'

import './App.css';

injectTapEventPlugin();

class App extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <div>
                    <CandidatesTable/>
                    <FloatingActionButton className="fab-button"
                                          label="Add Candidate"
                                          href="addCandidate">
                        <ContentAdd/>
                    </FloatingActionButton>
                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
