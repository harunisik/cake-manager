import React from "react";
import ReactDOM from "react-dom";
import App from "../pages/App";
import { BrowserRouter } from "react-router-dom";
import ErrorBoundary from "../components/ErrorBoundary";
import "@testing-library/jest-dom";

jest.mock("react-dom", () => ({ render: jest.fn() }));

describe("Application root", () => {
  it("should render without crashing", () => {
    const div = document.createElement("div");
    div.id = "root";
    document.body.appendChild(div);

    require("../index.tsx");

    expect(ReactDOM.render).toHaveBeenCalledWith(
      <React.StrictMode>
        <BrowserRouter>
          <ErrorBoundary>
            <App />
          </ErrorBoundary>
        </BrowserRouter>
      </React.StrictMode>,
      div
    );
  });
});
