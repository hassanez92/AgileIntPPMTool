import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  render(){
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 className="App-title">Welcome to PPM</h1>
        </header>
        <p className="App-intro">
         To get started, edit <code>src/App.js</code> and save to reload What is.
        </p>
    </div>
  );
  }
}

export default App;
