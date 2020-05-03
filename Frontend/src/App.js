import React, { useEffect } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from './containers/Login/Login';
import Index from './containers//Index/Index';
import { useCookies } from 'react-cookie';
import './styles/main.scss';
import Subject from './containers/Subject/Subject';
import ErrorPage from './containers/ErrorPage/ErrorPage';


const AUTHORIZATION = 'Authorization';

function App() {

  const [cookie] = useCookies();
  useEffect(() => { console.log('ok') }, [])
  let routes =
    <Switch>
      <Route component={Login} />
    </Switch>
  if (cookie[AUTHORIZATION] != null) {
    routes = <Switch>
      <Route path="/login">
        <Redirect to="/" />
      </Route>
      <Route path="/przedmiot/:id" component={Subject} />
      <Route path="/" exact component={Index} />
      <Route component={ErrorPage} />
    </Switch>

  }
  return (
    <BrowserRouter>
      {routes}
    </BrowserRouter>

  );
}

export default (App);
