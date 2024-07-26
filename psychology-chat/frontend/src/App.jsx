import React from "react";
import { Redirect, Route, BrowserRouter as Router, Switch, useHistory } from "react-router-dom";
import { Login } from "./Layout/Login";
import { ChatPage2 } from "./Layout/ChatPage2";

const App = () => {
  return (
    <Router>
      <RoutesWithHistoryCheck />
    </Router>
  );
};

const RoutesWithHistoryCheck = () => {
  const history = useHistory();

  React.useEffect(() => {
    if (localStorage.getItem("chat-username")) {
      history.push("/chat");
    } else {
      history.push("/login");
    }
  }, [history]);

  return (
    <Switch>
      <Route exact path="/">
        <Redirect to="/login" />
      </Route>
      <Route path="/login">
        <Login />
      </Route>
      <Route path="/chat">
        <ChatPage2 />
      </Route>
    </Switch>
  );
};

export default App;
