import React from "react";
import axios from "axios";

// create an Axios instance, in order to call the backend
const http = axios.create({
  baseURL: "http://localhost:8080",
});

export const foo = (token) => {
  http
    .get("/users", { headers: { Authorization: "Bearer " + token } })
    .then((response) => {
      console.log(response);
    });
};

export const bar = () => [
  // ... the code is omitted for the brevity
];

export const vote = (token, vote) => {
  http
    .post(`/vote/${vote}`, {
      headers: { Authorization: "Bearer " + token },
    })
    .then((response) => {
      console.log(response);
    });
  // console.log("Your token: " + token);
  // console.log("Your vote: " + vote);
  // code
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  foo,
  bar,
  vote,
};
