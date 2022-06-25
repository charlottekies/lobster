import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";
// you MUST register your chart in order to use it, to prevent Category error
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

// create an Axios instance, in order to call the backend
const http = axios.create({
  baseURL: "http://localhost:8080",
});

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      lobsterData: {},
      data: "",
      dates: [],
      prices: [],
    };
  }

  //After mount, this hook runs
  componentDidMount() {
    console.debug("After mount! Let's load data from API...");

    // get Historical Lobster Prices from the server
    http.get("/lobsters/historical-price-data").then((response) => {
      this.setState({ lobsterData: response.data.observations });

      // set Line Data with returned historical price data
      this.setData(response.data.observations);
      // set loading to false, so the correct div will display on the screen
      this.setState({ isLoading: false });
    });
  }

  // set Line graph data with returned historical lobster prices
  setData(data) {
    // initialize state arrays for lobster Prices and Dates
    this.setPricesAndDates(data);
    // use the state arrays for prices and dates to initialize the chart data
    this.setState({
      data: {
        labels: this.state.dates,
        datasets: [
          {
            label: "Historical Lobster Prices",
            data: this.state.prices,
            fill: true,
            backgroundColor: "rgba(75,192,192,0.2)",
            borderColor: "rgba(75,192,192,1)",
          },
        ],
      },
    });
  }

  setPricesAndDates(data) {
    let value = "";
    let values = [];
    let dates = [];
    data.forEach((month) => {
      if (month.value !== ".") {
        value = parseFloat(month.value);
        values.push(value);
      } else {
        values.push(".");
      }
      dates.push(month.date);
    });
    this.setState({ dates: dates });
    this.setState({ prices: values });
  }

  // This is what displays on the page
  render() {
    // Define two constants from state variables
    const { isLoading, data } = this.state;

    // condition 1. If data has not been returned, show this div
    if (isLoading) {
      return (
        <div className="App">
          <p>Lobsters coming soon...</p>
        </div>
      );

      // otherwise, condition 2: Load the data in a chart
    } else {
      return (
        <div className="App">
          <h1>Lobster Data</h1>
          {/* data is a constant defined using state in Render() */}
          <Line data={data} />
        </div>
      );
    }
  }
}
