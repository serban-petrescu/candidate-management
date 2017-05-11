import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import injectTapEventPlugin from 'react-tap-event-plugin';
// Importing jQuery in ES6 style
import $ from 'jquery';
window.$ = $;
// bootstrap
import 'bootstrap';
// bootstrap css
import 'bootstrap/dist/css/bootstrap.css'
injectTapEventPlugin();
ReactDOM.render(
  <App />,
  document.getElementById('root')
);
