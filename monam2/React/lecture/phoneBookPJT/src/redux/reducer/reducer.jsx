const initialState = {
  contactList: [
    {
      name: "김두열",
      phoneNumber: "01011111111",
    },
  ],
};

const reducer = (state = initialState, action) => {
  const { type, payload } = action;
  switch (type) {
    case "ADD_CONTACT":
      return {
        ...state,
        contactList: [
          ...state.contactList,
          {
            name: payload.name,
            phoneNumber: payload.phoneNumber,
          },
        ],
      };
    default:
      return { ...state };
  }
};

export default reducer;
