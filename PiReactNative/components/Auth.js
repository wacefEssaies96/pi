var Auth = {
    state:true,
    name:'',
    email:'',
    password:'',
    picture: '',
    connectionMode: 'classic'
  };
export function getAuthData() {
  if(Auth.connectionMode === 'classic'){
    return {
      username: Auth.email,
      password:Auth.password
     };
  }
  //Pour les utilisateurs FB et google
  return {
    username: 'Salhiamani20@gmail.com',
    password: 'admin'
    };
}
export default Auth;