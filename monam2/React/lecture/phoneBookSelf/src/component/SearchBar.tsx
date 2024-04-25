import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
const SearchBar = () => {
  const [keyword, setKeyword] = useState("");
  const contactList = useSelector((state) => state.contactList);
    const dispatch = useDispatch();

  const onClick = () => {
    dispatch({
        type: "ADD_KEYWORD",
        payload: {
            keyword,
        }
    })
  };
  return (
    <div>
      <Form.Control
        type="text"
        onChange={(event) => {
          setKeyword(event.target.value);
        }}
        placeholder="Enter id"
      />
      <Button variant="primary" onClick={onClick}>
        검색
      </Button>
    </div>
  );
};

export default SearchBar;
