import styled from 'styled-components';
import { darken } from 'polished';

export const ModalStyle = styled.div`
  width: 100%;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 10;
  background-color: rgba(50, 50, 50, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Content = styled.div`
  width: auto;
  height: auto;
  padding: 10px;
  background-color: #fff;
  border-radius: 10px;
`;

export const Button = styled.button`
  float: right;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0;
  width: 100px;
  color: #fff;
  background-color: #d71245;
  border-radius: 4px;
  padding: 10px;
  font-weight: bold;
  font-size: 18px;
  &:hover {
    background: ${darken(0.3, '#d71245')};
  }
`;
