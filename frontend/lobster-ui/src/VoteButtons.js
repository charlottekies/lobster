import React from "react";

class VoteButtons extends React.Component {
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
        <button>Up</button>
        <button>Down</button>
      </div>
    );
  }
}

export default VoteButtons;
