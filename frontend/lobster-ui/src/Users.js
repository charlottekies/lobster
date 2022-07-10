import React from "react";
import Button from "./Button.js";
import VoteButtons from "./VoteButtons.js";
import axios from "axios";
import jwt_decode from "jwt-decode";

// create an Axios instance, in order to call the backend
const http = axios.create({
  baseURL: "http://localhost:8080",
});

// name of the class, its constructor, and state
class Users extends React.Component {
  constructor(props) {
    super(props);
    this.handleCallbackResponse = this.handleCallbackResponse.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.state = {
      token: "",
      name: "",
    };
  }

  // functions
  handleCallbackResponse(response) {
    console.log("Token: " + response.credential);
    let googleUser = jwt_decode(response.credential);
    this.setState({ name: googleUser.name });
    let token = response.credential;
    this.setState({ token: token });
    http
      .post(`/google/signin/${token}`, googleUser, {
        headers: { Authorization: "Bearer " + token },
      })
      .then((response) => {
        console.log(response);
      });
  }

  //After mount, this hook runs
  componentDidMount() {
    /* global google */
    google.accounts.id.initialize({
      client_id: process.env.REACT_APP_CLIENT_ID,
      callback: this.handleCallbackResponse,
    });

    google.accounts.id.renderButton(document.getElementById("signInDiv"), {
      theme: "outline",
      size: "large",
    });
  }

  getUsers() {
    http.get("/users", {
      headers: { Authorization: "Bearer " + this.state.token },
    });
  }

  handleClick() {
    http
      .get("/users", {
        headers: { Authorization: "Bearer " + this.state.token },
      })
      .then((response) => {
        console.log(response.data);
      });
  }

  render() {
    return (
      <h1>
        <Button name={this.state.name} token={this.state.token}></Button>
        {/* In order to fire button only on click, use this syntax and bind above */}
        {/* <button onClick={() => this.handleClick()}>
          Click this button to view ALL users
        </button> */}

        <h2>Do you think that prices will go up or down next month?</h2>

        {/* Add voting buttons */}
        {this.state.token !== "" ? <VoteButtons /> : "Login to vote."}
      </h1>
    );
  }
}

export default Users; // Don’t forget to use export default!
