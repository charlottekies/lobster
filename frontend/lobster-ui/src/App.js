import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

class App extends React.Component {
  state = {
    lobsterData: [],
    prices: [],
    dates: [],
    data: {},
  };

  // on page load
  componentDidMount() {
    let body = "";
    let newArray = this.state.prices;
    let monthsArray = this.state.dates;
    http
      .get("/lobsters/historical-price-data")
      .then((response) => {
        body = response.data.observations;
        this.setState({ lobsterData: body });

        this.state.lobsterData.forEach((month) => {
          let value = "";
          if (month.value !== ".") {
            value = parseFloat(month.value);
          } else {
            value = 0;
          }

          newArray.push(value);

          monthsArray.push(month.date);
        });
      })
      .catch((error) => console.error("Unable to get items.", error));
    this.setState({ prices: newArray });
    console.log(this.state.prices);
    this.setState({ dates: monthsArray });
    this.setData();
  }

  setData() {
    this.setState({
      data: {
        labels: this.state.dates,
        datasets: [
          {
            label: "First dataset",
            data: this.state.prices,
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)",
          },
        ],
      },
    });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Lobster Prices</h2>
            {/* <Line data={this.state.data} /> */}
            {this.state.data.datasets?.data ?? "Not loaded yet"}

            {/* {this.state.lobsterData.map((array) => (
              <div key={array.date}>
                {array.date} <span>value: </span>
                {array.value}
              </div>
            ))} */}
          </div>
        </header>
      </div>
    );
  }
}
export default App;
