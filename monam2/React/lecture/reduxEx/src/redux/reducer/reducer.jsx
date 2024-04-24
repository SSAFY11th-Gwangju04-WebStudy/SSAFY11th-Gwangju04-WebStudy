let initialState = {
  count: 0,
};

function reducer(state = initialState, action) {
  if (action.type === "INCREMENT") {
    return { ...state, count: state.count + 1 };
  } else {
    return state;
  }
}

export default reducer;
