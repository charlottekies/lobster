import React from "react";
import { Line } from "react-chartjs-2";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

class Button extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
      name: "",
    };
  }
  render() {
    // Define two constants from state variables
    const {
      isLoading,
      data,
      dollarValues,
      inflationValues,
      lobsterPricesDown,
      lobsterPricesUp,
      buyLevel,
    } = this.state;

    return (
      <div>
        <div id="chart-container-container">
          <div id="chart-container">
            <Line id="chart" data={data} />
            <h2>Recommendation:</h2>
            <p></p>
            <p style={{ color: this.color(), fontWeight: 800 }}>
              {this.whatShouldYouDo().buyLevel}
            </p>
            <p>{this.whatShouldYouDo().recommendation}</p>
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
              Last Month's Rate of Inflation: {this.getLastMonthInflationRate()}
              %
            </p>
            <p>Current Rate of Inflation: {this.getCurrentInfationRate()}%. </p>
            <p> {this.isInflationUp()}</p>
          </div>
        </div>
      </div>
    );
  }
}

export default Button;
