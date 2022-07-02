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
      pricesInDollars: [],
      inflationData: [],
      inflationValues: [],
      inflationDates: [],
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

      // get Historical Inflation Data
      http.get("/inflation/historical-inflation-rates").then((response) => {
        this.setState({ inflationData: response.data.observations });
        this.setInflationValues(this.state.inflationData);
      });

      // set loading to false, so the correct div will display on the screen
      this.setState({ isLoading: false });
    });
    this.main();
  }

  getLobsterData(path) {
    return new Promise(function (resolve, reject) {
      http.get(path).then(
        (response) => {
          let result = response.data;
          console.log("Lobster Data " + result);
          resolve(result);
        },
        (error) => {
          reject("no response" + error);
        }
      );
    });
  }

  getHistoricalInflationData(path) {
    return new Promise(function (resolve, reject) {
      http.get(path).then(
        (response) => {
          let result = response.data;
          // gets inflation data
          console.log("inflation data" + response.data);

          resolve(result);
        },
        (error) => {
          reject("no response" + error);
        }
      );
    });
  }

  async main() {
    await this.getLobsterData(
      "http://127.0.0.1:8080/lobsters/historical-price-data"
    );
    let result2 = await this.getHistoricalInflationData(
      "http://127.0.0.1:8080/inflation/historical-inflation-rates"
    );
  }

  setInflationValues(inflationData) {
    let values = [];
    if (inflationData?.length) {
      inflationData.forEach((observation, index) => {
        values.push(observation.value);
      });
    }
    this.setState({ inflationValues: values });
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
    let dollarValues = [4.2147303699496];

    data.forEach((month, index) => {
      if (month.value !== ".") {
        value = parseFloat(month.value);
        values.push(value);
        // if index > 0,
        if (index > 0) {
          // Calculate percentage increase/decrease from value at index-1 to value
          if (values[0] > value) {
            let percentDecrease = (values[0] - value) / values[0];
            let newDollarValue =
              dollarValues[0] + dollarValues[0] * percentDecrease;
            dollarValues.push(newDollarValue);
            // if the price of lobster is less than what it was in December, 1991:
          } else {
            let percentIncrease = (value - values[0]) / values[0];
            let newDollarValue =
              dollarValues[0] + dollarValues[0] * percentIncrease;
            dollarValues.push(newDollarValue);
          }
        }

        // Calculate dollar amount increase/decrease from dollarValue at index-1
        // add result ^^ to dollarValues
      } else {
        values.push(".");
        dollarValues.push(".");
      }
      dates.push(month.date);
    });
    this.setState({ dates: dates });
    this.setState({ prices: values });
    this.setState({ dollarValues: dollarValues });
  }
  getPercentChange(currentValue, lastMonthValue) {
    if (currentValue > lastMonthValue) {
      let percentIncrease =
        ((currentValue - lastMonthValue) / lastMonthValue) * 100;
      return (
        "This is a " +
        percentIncrease.toFixed(2) +
        "% increase from last month's price"
      );
    } else {
      let percentDecrease =
        ((lastMonthValue - currentValue) / lastMonthValue) * 100;
      return (
        "This is a " +
        percentDecrease.toFixed(2) +
        "% decrease from last month's price."
      );
    }
  }

  // This is what displays on the page
  render() {
    // Define two constants from state variables
    const { isLoading, data, dollarValues, inflationValues } = this.state;

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
          <div id="chart-container-container">
            <div id="chart-container">
              <Line id="chart" data={data} />
              <p>
                The current average price of a 1.25-pound lobster is $
                {dollarValues[dollarValues.length - 1].toFixed(2)}
              </p>
              <p>
                {this.getPercentChange(
                  dollarValues[dollarValues.length - 1],
                  dollarValues[dollarValues.length - 2]
                )}
              </p>
              <p>
                Inflation is 7 percent Inflation increased 1% since last month
                In comparison, lobster prices decreased 27% since last month
                Your dollar doesn't go as for So find the thing that is on sale
                Lobster prices are down.
              </p>
              <p>
                Current Rate of Inflation:{" "}
                {parseFloat(
                  inflationValues[inflationValues.length - 1]
                ).toFixed(2)}{" "}
                %
              </p>
              <p>
                Last Month's Rate of Inflation:{" "}
                {parseFloat(
                  inflationValues[inflationValues.length - 2]
                ).toFixed(2)}{" "}
                %
              </p>
            </div>
          </div>
        </div>
      );
    }
  }
}
