import React, { useState, useEffect } from "react";
import { useHistory, useLocation } from "react-router-dom";
import "./button.css";

export const Login = () => {
  const history = useHistory();
  const location = useLocation();
  const [username, setUsername] = useState("");

  // Extract the query parameter
  useEffect(() => {
    const query = new URLSearchParams(location.search);
    const name = query.get("name");
    localStorage.setItem("chat-username", name);
    history.push("/chat");
  }, [location.search]);

  const handleLogin = () => {
  };

  return (
    <div
      className=" d-flex align-items-center justify-content-center"
      style={{
        height: "100vh",
        backgroundImage: `url("https://picsum.photos/1536/735?grayscale")`,
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
      }}
    >
      <div className="container form-group w-25  d-flex align-items-center justify-content-center gap-2">
        <input
          type="text"
          name=""
          id=""
          className="rounded-3 form-control"
          placeholder="Name"
          onChange={(e) => setUsername(e.target.value)}
          onKeyUp={(e) => {
            console.log(e.key);
            if (e.key == "Enter" || e.key == 13) handleLogin();
          }}
        />
        <button type="button" value={"Connect"} onClick={handleLogin}>
          <span>Connect</span>
        </button>
      </div>
    </div>
  );
};
