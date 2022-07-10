import React from "react";

class VoteButtons extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
    };
    this.handleVoteUp = this.handleVoteUp.bind(this);
    this.handleVoteDown = this.handleVoteDown.bind(this);
  }

  handleVoteUp() {
    console.log("You voted up");
  }

  handleVoteDown() {
    console.log("You voted down");
  }
  render() {
    return (
      <div>
        {/* <div id="signInDiv"></div> */}
        <button onClick={() => this.handleVoteUp()}>Up</button>
        <button onClick={() => this.handleVoteDown()}>Down</button>{" "}
      </div>
    );
  }
}

export default VoteButtons;
