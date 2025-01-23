import React from "react";
import { RouteProps } from "react-router-dom";
import SomethingWentWrong from "../pages/SomethingWentWrong";

interface State {
  hasError: boolean;
}

class ErrorBoundary extends React.Component<RouteProps, State> {
  unlisten: any;

  constructor(props: RouteProps) {
    super(props);
    this.state = { hasError: false };
  }

  componentDidMount() {
    // const { history } = this.props;
    // this.unlisten = history.listen((location, action) => {
    //   if (this.state.hasError) {
    //     this.setState({ hasError: false });
    //   }
    // });
  }

  static getDerivedStateFromError(error: any) {
    return { hasError: true };
  }

  componentWillUnmount() {
    this.unlisten();
  }

  componentDidCatch(error: any, errorInfo: any) {
    console.log(error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return <SomethingWentWrong />;
    }

    return this.props.children;
  }
}

export default ErrorBoundary;
