import React, {Component} from 'react';
import AddCandidate from './AddCandidate';
import {HashRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import TopNavbar from './TopNavbar';
import Validation from './Validation';
import Home from './Home';
import Import from './Import';
import Login from './Login';

/**
 * Each route will be identified in a <Route> component. The <Route> component will take two properties:
 * path and component. When a path matches the path given to the <Route> component, it will return
 * the component specified.
 *
 * When a random input in inserted in the URL, it will redirect to Login page
 */
class App extends React.Component {

    render() {
        let PrivateRoute = ({ component: Component, ...rest }) => (
          <Route {...rest} render={(props) => (
            sessionStorage.getItem('userLogged') && sessionStorage.getItem('userLogged') === "true"
              ? <Component {...props} />
              : <Redirect to='/' />
          )} />
        );

        return (
            <Router>
                <div>
                    <TopNavbar/>
                    <Switch>
                        <Route exact path='/' component={Login}/>
                        <PrivateRoute path='/home' component={Home}/>
                        <PrivateRoute path='/validation' component={Validation}/>
                        <PrivateRoute path='/addCandidate' component={AddCandidate}/>
                        <PrivateRoute path='/import' component={Import}/>
                        <Redirect push from='*' to='/'/>
                    </Switch>

                </div>
            </Router>
        );
    }
}

export default App;
