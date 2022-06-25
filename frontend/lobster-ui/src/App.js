import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

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
      pokemon: "my pokemon",
    };
  }

  componentDidMount() {
    console.debug("After mount! Let's load data from API...");
    // axios.get("https://pokeapi.co/api/v2/pokemon/4").then((response) => {
    //   this.setState({ pokemon: response.data });
    //   this.setState({ isLoading: false });
    // });
    http.get("/lobsters/historical-price-data").then((response) => {
      this.setState({ lobsterData: response.data.observations });
      this.setData(response.data.observations);
      this.setState({ isLoading: false });
    });
  }

  setData(data) {
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
    const { isLoading } = this.state;

    if (isLoading) {
      return <div className="App">Loading...</div>;
    }

    return (
      <div className="App">
        <h1>Lobster Data</h1>
        <Line data={this.state.data} />
      </div>
    );
  }
}
