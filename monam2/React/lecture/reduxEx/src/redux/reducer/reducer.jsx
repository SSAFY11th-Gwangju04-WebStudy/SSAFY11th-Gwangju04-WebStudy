let initState = {
  count: 0,
  id: "oldId",
};

const reducer = (state = initState, action) => {
  switch (action.type) {
    case "INCREASE":
      return { ...state, count: state.count + action.payload.num };
    case "GETID":
      return { ...state, id: action.payload.newId };
    default:
      return { ...state };
  }
};

export default reducer;
