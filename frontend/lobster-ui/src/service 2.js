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
  // code
};

export const bar = () => [
  // ... the code is omitted for the brevity
];

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  foo,
  bar,
};
