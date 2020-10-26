import React from 'react';
import PropTypes from 'prop-types';

import { ModalStyle, Content, Button } from './styles';

export default function Modal({ onClose = () => {}, children }) {
  function clickClose(e) {
    if (e.target.id === 'modal') onClose();
  }

  return (
    <ModalStyle id="modal" onClick={clickClose}>
      <Content>
        {children}
        <Button type="button" onClick={onClose}>
          Fechar
        </Button>
      </Content>
    </ModalStyle>
  );
}

Modal.propTypes = {
  onClose: PropTypes.func.isRequired,
  children: PropTypes.element.isRequired,
};
