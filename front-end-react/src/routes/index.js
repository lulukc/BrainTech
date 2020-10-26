import React from 'react';
import { Switch } from 'react-router-dom';
import Route from './Route';

import Login from '../pages/Login';
import Agendas from '../pages/Agendas';
import Pedidos from '../pages/Pedidos';
import Estoque from '../pages/Estoque';

export default function Routes() {
  return (
    <Switch>
      <Route path="/" exact component={Login} />
      <Route path="/pedidos" exact component={Pedidos} isPrivate />
      <Route path="/agendas" exact component={Agendas} isPrivate />
      <Route path="/estoque" exact component={Estoque} isPrivate />
    </Switch>
  );
}
