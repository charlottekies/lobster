import React from "react";
import service from "./service";

class VoteButtons extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      token: "",
    };
    // this.handleVoteUp = this.handleVoteUp.bind(this);
    // this.handleVoteDown = this.handleVoteDown.bind(this);
    this.vote = this.vote.bind(this);
  }

  //   handleVoteUp() {
  //     console.log("You voted up");
  //   }

  //   handleVoteDown() {
  //     console.log("You voted down");
  //   }

  vote() {
    service.vote(this.props.token, document.getElementById("vote").value);
  }
  render() {
    return (
      <div>
        {/* <div id="signInDiv"></div> */}
        {/* <button onClick={() => this.handleVoteUp()}>Up</button>
        <button onClick={() => this.handleVoteDown()}>Down</button>{" "} */}
        <label for="vote">Enter a dollar amount</label>

        <div>
          $
          <input
            id="vote"
            name="vote"
            type="number"
            step="0.01"
            max="1999.99"
            min="0"
          ></input>
        </div>
        <button onClick={() => this.vote()}>Submit</button>
      </div>
    );
  }
}

export default VoteButtons;
