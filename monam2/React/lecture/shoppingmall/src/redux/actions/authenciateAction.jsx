const login=(id, password)=>{
    return (dispatch, getState) => {
        dispatch({type: "LOGIN_SUCCESS", payload: {id, password}});
    }
}

export const authenticateAction ={login};