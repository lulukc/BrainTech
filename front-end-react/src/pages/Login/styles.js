import styled from 'styled-components';
import { darken } from 'polished';

export const Container = styled.div`
  width: auto;
  height: auto;
  padding: 10px;
  background-color: #edf1fe;
  border-radius: 10px;
  box-shadow: 9px 7px 5px rgba(50, 50, 50, 0.5);
  display: flet;
  align-items: center;

  img {
    margin: 30px;
    width: 300px;
    height: 300px;
  }
`;

export const LoginForm = styled.form`
  align-items: center;

  input {
    border: 1px solid #d71245;
    border-radius: 10px;
    height: 44px;
    margin: 0 5px 10px;
    padding: 10px;
  }
  h1 {
    text-align: center;
    font-weight: bold;
    color: #d71245;
    margin: 10px;
  }
`;

export const DivButton = styled.div`
  button {
    margin: 10px 0;
    width: 100%;
    color: #fff;
    background: #d71245;
    border-radius: 4px;
    padding: 10px;
    font-weight: bold;
    font-size: 18px;
    &:hover {
      background: ${darken(0.3, '#d71245')};
    }
  }
`;
