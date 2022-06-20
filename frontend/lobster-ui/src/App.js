import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";
import Chart from "chart.js/auto";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

class App extends React.Component {
  state = {
    lobsterData: [],
    data: {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
      datasets: [
        {
          label: "First dataset",
          data: [33, 53, 85, 41, 44, 65],
          fill: true,
          backgroundColor: "rgba(75,192,192,0.2)",
          borderColor: "rgba(75,192,192,1)",
        },
      ],
    },
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
        let data = response.data.observations;
        let dates = "";
        let price = "";
        let i;
        for (i = 0; i < data.length; i++) {
          dates += data[i].date + ",";
          price += data[i].value + ",";
        }

        this.setState({ dates: dates });
        this.setState({ dates: this.state.dates.split(",") });
        this.setState({ price: price });
        this.setState({ price: this.state.price.split(",") });
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
            <Line data={this.state.data} />
            {lobsterData.map((array) => (
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
