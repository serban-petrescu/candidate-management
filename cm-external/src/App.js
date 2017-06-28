import React, { Component } from 'react';
import './App.css';
import CMRecaptcha from './Components/Recaptcha'


class App extends Component {

  render() {
    return (
      <div className="App">
         <CMRecaptcha/>
      </div>
    );
  }
}

export default App;
