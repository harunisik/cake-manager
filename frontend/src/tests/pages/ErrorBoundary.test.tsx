import { ReactElement } from "react";
import { render, screen } from "@testing-library/react";
import ErrorBoundary from "../../components/ErrorBoundary";
import { BrowserRouter } from "react-router-dom";
import {
  findSomethingWentWrongImg,
  getGoBackLink,
} from "./SomethingWentWrong.test";

interface Props {
  username: string;
}

const TestComponent = ({ username }: Props) => {
  if (username.length > 5) {
    throw new Error("name length must not greater than 5");
  }
  return <div>{username}</div>;
};

const renderErrorBoundary = (children: ReactElement) => {
  return render(
    <BrowserRouter>
      <ErrorBoundary>{children}</ErrorBoundary>
    </BrowserRouter>
  );
};

describe("ErrorBoundary", () => {
  describe("render", () => {
    it("should return a container", () => {
      const { container } = renderErrorBoundary(
        <TestComponent username="test"></TestComponent>
      );
      expect(container).toBeDefined();
      expect(screen.getByText("test")).toBeInTheDocument();
    });

    it("should display the fallback UI", async () => {
      try {
        renderErrorBoundary(<TestComponent username="test 1"></TestComponent>);
      } catch (error) {}

      await findSomethingWentWrongImg();
      expect(getGoBackLink()).toBeInTheDocument();
    });
  });
});
