import React from 'react';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';

import { Container, Links, User } from './styles';
import logo from '../../assets/logo.png';
import { logoff } from '../../store/modules/auth/actions';

export default function Header() {
  const dispatch = useDispatch();

  function handleLogoff() {
    dispatch(logoff());
  }

  return (
    <Container>
      <Links>
        <img src={logo} alt="BRAIN TECH" />
        <Link to="/pedidos">Pedidos</Link>
        <Link to="/agendas">Agenda</Link>
        <Link to="/estoque">Estoque</Link>
      </Links>
      <User>
        <button type="button" onClick={handleLogoff}>
          Sair
        </button>
      </User>
    </Container>
  );
}
