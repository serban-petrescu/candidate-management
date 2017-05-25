import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
// Importing jQuery in ES6 style
import $ from 'jquery';
window.$ = $;
// bootstrap
import 'bootstrap';
// bootstrap css
import 'bootstrap/dist/css/bootstrap.css'


ReactDOM.render((<App/>), document.getElementById('root'));