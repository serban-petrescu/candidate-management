// React imports
import React, {Component} from 'react';

// Material-UI imports
import {deepOrange500} from 'material-ui/styles/colors';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

// Component imports
import CandidatesTableMUI from './CandidatesTableMUI'


import './App.css';


const muiTheme = getMuiTheme({
    palette: {
        accent1Color: deepOrange500
    }
});


class App extends Component {
    render() {
        return (
            <MuiThemeProvider muiTheme={muiTheme}>
                <div>
                    <CandidatesTableMUI/>
                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;
