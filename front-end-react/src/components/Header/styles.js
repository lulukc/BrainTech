import styled from 'styled-components';
import { darken } from 'polished';

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;
`;

export const Links = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  padding: 10px;

  img {
    border-right: 1px solid #999;
    padding-right: 10px;
    margin-right: 10px;
    width: 50px;
    height auto;
  }
  a {
    margin: 10px;
    color: #d71245;
    font-size: 18px;
    font-weight: bold;
    &:hover {
      color: ${darken(0.1, '#d71245')};
    }
  }
`;

export const User = styled.div`
  text-align: right;
  align-items: right;
  margin-right: 20px;

  button {
    display: block;
    margin-top: 2px;
    font-size: 14px;
    color: #d71245;
    background: none;
    font-weight: bold;
    &:hover {
      color: ${darken(0.1, '#d71245')};
    }
  }
`;
