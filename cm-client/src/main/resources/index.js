import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import ReduxPromise from 'redux-promise';
import App from './components/App';
import reducers from './reducers';
import $ from 'jquery';
window.$ = $;
import 'bootstrap';
import './less/main.less'

/**
 * Middleware with ReduxPromise applied means that the promise sent from actions
to the reducer will be intercepted by ReduxPromise module and the payload will be red from the promise
 and sent to the reducer as an object and not as a Promise.
 */
const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

ReactDOM.render(
    /***
     * Provider is used to pass the store down from react-redux
     * The Provider exposes the store you pass through as a prop
     * on the context, so the component can specify the context-type and then use
     * this context store to subscribe to the store  updates and dispatch actions.
     * We render the App component in the place of div element having the id of root
     */
    <Provider store={createStoreWithMiddleware(reducers)}>
        <App/>
    </Provider>,
    document.getElementById('root')
);