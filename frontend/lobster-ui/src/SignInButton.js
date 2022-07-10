import React from "react";

class SignInButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
    };
  }

  render() {
    return (
      <div>
        <div id="signInDiv"></div>
      </div>
    );
  }
}

export default SignInButton;
