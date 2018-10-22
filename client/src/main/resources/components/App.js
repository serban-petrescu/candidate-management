import React from 'react';
import AddCandidate from './AddCandidate';
import {HashRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import Validation from './Validation';
import Home from './Home';
import Import from './Import';
import Login from './Login';
import {NotificationContainer} from "react-notifications";

/**
 * Each route will be identified in a <Route> component. The <Route> component will take two properties:
 * path and component. When a path matches the path given to the <Route> component, it will return
 * the component specified.
 *
 * When a random input in inserted in the URL, it will redirect to Login page
 */
class App extends React.Component {

    render() {
        return (
            <Router>
                <div>
                    <NotificationContainer/>
                    <Switch>
                        <Route exact path='/' component={Login}/>
                        <Route path='/home' component={Home}/>
                        <Route path='/validation' component={Validation}/>
                        <Route path='/addCandidate' component={AddCandidate}/>
                        <Route path='/import' component={Import}/>
                        <Redirect push from='*' to='/'/>
                    </Switch>

                </div>
            </Router>
        );
    }
}

export default App;
