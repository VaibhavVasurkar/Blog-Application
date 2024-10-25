import { myAxios } from "./helper";

export const signUp=(user)=>{
    return myAxios.post('/users/signup', user)
    .then((response)=>response.data)
}

export const loginUser=(loginDetails)=>{
    return myAxios.post('/users/signin', loginDetails).then((response)=>response.data)
}