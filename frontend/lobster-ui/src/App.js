import logo from "./logo.svg";
import "./App.css";
import React from "react";
import axios from "axios";
// import { Line } from "react-chartjs-2";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      data: {},
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
      console.log(response);
      this.setState({ lobsterData: response.data });
      this.setState({ isLoading: false });
    });
  }

  render() {
    const { isLoading, lobsterData } = this.state;

    if (isLoading) {
      return <div className="App">Loading...</div>;
    }

    return (
      <div className="App">
        <h1>Lobster Data</h1>
        {/* <img alt={pokemon.name} src={pokemon.sprites.front_default} /> */}
        {this.state.lobsterData.observations[0].value}
      </div>
    );
  }
}

// this.setState(
//   {
//     data: {
//       labels: this.state.dates,
//       datasets: [
//         {
//           label: "First dataset",
//           data: this.state.prices,
//           fill: true,
//           backgroundColor: "rgba(75,192,192,0.2)",
//           borderColor: "rgba(75,192,192,1)",
//         },
//       ],
//     },
//   },
//   []
// );
