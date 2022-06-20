import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

class App extends React.Component {
  state = {
    lobsterData: [],
  };

  // on page load
  async componentDidMount() {
    let body = "";
    http
      .get("/lobsters/historical-price-data")
      .then((response) => {
        body = response.data.observations;
        this.setState({ lobsterData: body });
        console.log(response.data.observations);
      })
      .catch((error) => console.error("Unable to get items.", error));
  }

  render() {
    const { lobsterData } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Lobster Prices</h2>
            {lobsterData.map((array) => (
              <div key={array.date}>
                {array.date} <span>value: </span>
                {array.value}
              </div>
            ))}
            {/* <div>{lobsterData.observations[0]}</div> */}
          </div>
        </header>
      </div>
    );
  }
}
export default App;
