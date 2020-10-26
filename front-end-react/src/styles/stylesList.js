import styled from 'styled-components';
import { darken } from 'polished';

export const Container = styled.div`
  width: 100%;
  max-width: 900px;
  margin: 30px;
`;
export const Headers = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  strong {
    font-size: 24px;
  }
`;

export const Tabela = styled.table`
  width: 100%;
  margin-top: 30px;
  background: #fff;
  border-radius: 4px;
  thead th {
    color: #333;
    text-align: left;
    padding: 12px;
  }
  tbody td {
    padding: 12px;
    border-bottom: 1px solid #eee;
    cursor: pointer;
  }
  button {
    color: red;
    padding-left: 10px;
    background: none;
  }
`;

export const ButtonRetirar = styled.button`
  display: block;
  margin-top: 2px;
  font-size: 14px;
  color: #d71245;
  background: none;
  font-weight: bold;
  &:hover {
    color: ${darken(0.1, '#d71245')};
  }
`;
