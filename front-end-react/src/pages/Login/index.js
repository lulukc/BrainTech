import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import logo from '../../assets/logo.png';
import { loginRequest } from '../../store/modules/auth/actions';

import { Container, LoginForm, DivButton } from './styles';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const loading = useSelector((state) => state.auth.loading);

  function handleSubmit(e) {
    e.preventDefault();
    dispatch(loginRequest(username, password));
  }

  return (
    <>
      <Container>
        <img src={logo} alt="Logo" />
        <LoginForm onSubmit={handleSubmit}>
          <div>
            <h1>Login</h1>
            <input
              name="username"
              type="text"
              placeholder="Seu usuario"
              required
              onChange={(e) => setUsername(e.target.value)}
              value={username}
            />
            <input
              name="password"
              type="password"
              placeholder="Sua senha"
              required
              onChange={(e) => setPassword(e.target.value)}
              value={password}
            />
          </div>
          <DivButton>
            <button type="submit">
              {loading ? 'caregando...' : 'Acessar'}
            </button>
          </DivButton>
        </LoginForm>
      </Container>
    </>
  );
}
