import "./App.css";
import React, { Component } from "react";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";

class App extends Component {
  render() {
  return (
    <Router>
    <div className="App dark-overlay">
      <Header />
      <Route exact path="/dashboard" component={Dashboard} />
      <Route exact path="/addProject" component={AddProject} />
    </div>
    </Router>
    );
  }
}

export default App;
