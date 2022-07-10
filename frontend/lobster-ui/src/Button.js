import React from "react";

class Button extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
      name: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.name !== "" ? <h1>Hello, {this.props.name}</h1> : ""}
      </div>
    );
  }
}

export default Button;
