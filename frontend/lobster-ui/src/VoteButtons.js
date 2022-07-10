import React from "react";

class VoteButtons extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
    };
    this.handleVoteUp = this.handleVoteUp.bind(this);
  }

  handleVoteUp() {
    console.log("You voted up");
  }
  render() {
    return (
      <div>
        <div id="signInDiv"></div>
        <button onClick={() => this.handleVoteUp()}>Up</button>
        Click this button to view ALL users
        <button>Down</button>
      </div>
    );
  }
}

export default VoteButtons;
