import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
// import { Line } from "react-chartjs-2";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

class App extends React.Component {
  state = {
    lobsterData: [],
    prices: [],
    dates: [],
  };

  // on page load
  componentDidMount() {
    let body = "";
    http
      .get("/lobsters/historical-price-data")
      .then((response) => {
        body = response.data.observations;
        this.setState({ lobsterData: body });
        console.log(response.data.observations);
        let newArray = this.state.prices;
        let monthsArray = this.state.dates;
        this.state.lobsterData.forEach((month) => {
          let value = parseFloat(month.value);
          newArray.push(value);
          monthsArray.push(month.date);
        });
        this.setState({ prices: newArray });
        this.setState({ dates: monthsArray });
      })
      .catch((error) => console.error("Unable to get items.", error));
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Lobster Prices</h2>

            {this.state.lobsterData.map((array) => (
              <div key={array.date}>
                {array.date} <span>value: </span>
                {array.value}
              </div>
            ))}
          </div>
        </header>
      </div>
    );
  }
}
export default App;
