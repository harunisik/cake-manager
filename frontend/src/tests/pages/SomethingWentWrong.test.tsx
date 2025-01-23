import { render, screen } from "@testing-library/react";
import SomethingWentWrong from "../../pages/SomethingWentWrong";
import "@testing-library/jest-dom/extend-expect";
import { BrowserRouter } from "react-router-dom";

const renderSomethingWentWrong = () => {
  return render(
    <BrowserRouter>
      <SomethingWentWrong />
    </BrowserRouter>
  );
};

const getSomethingWentWrongImg = () => {
  return screen.getByRole("img", { name: "Something went wrong." });
};

export const findSomethingWentWrongImg = () => {
  return screen.findByRole("img", { name: "Something went wrong." });
};

export const getGoBackLink = () => {
  return screen.getByRole("link", { name: "Go back to home page." });
};

describe("SomethingWentWrong", () => {
  describe("render", () => {
    it("should return a container", () => {
      const { container } = renderSomethingWentWrong();

      expect(container).toBeDefined();
    });

    it("should display PageNotFound elements correctly", () => {
      renderSomethingWentWrong();

      expect(getSomethingWentWrongImg()).toBeInTheDocument();
      expect(getGoBackLink()).toBeInTheDocument();
    });
  });
});
