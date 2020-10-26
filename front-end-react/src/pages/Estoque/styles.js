import styled from 'styled-components';
import { darken } from 'polished';

export const FormEstoque = styled.form`
  div {
    padding: 30px;
  }

  small {
    font-size: 14px;
    margin-right: 15px;
    font-weight: bold;
  }

  input {
    border: 1px solid #d71245;
    border-radius: 10px;
    padding: 5px;
    margin-right: 15px;
  }

  button {
    color: #fff;
    background: #d71245;
    border-radius: 4px;
    padding: 5px;
    font-weight: bold;
    font-size: 14px;
    &:hover {
      background: ${darken(0.3, '#d71245')};
    }
  }
`;
