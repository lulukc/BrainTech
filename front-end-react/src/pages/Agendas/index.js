import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { reloadDataRequest } from '../../store/modules/data/actions';

import {
  Container,
  Headers,
  Tabela,
  ButtonRetirar,
} from '../../styles/stylesList';
import { DivButton } from '../Login/styles';
import Modal from '../../components/Modal';
import api from '../../services/api';

export default function Agenda() {
  const dispatch = useDispatch();
  const agendas = useSelector((state) => state.data.agendaLista);
  const loading = useSelector((state) => state.data.loading);

  useEffect(() => {
    dispatch(reloadDataRequest());
  }, [dispatch]);

  const [mostrarModal, setMostrarModal] = useState(false);
  const [idPedido, setIdPedido] = useState('');

  function handleOpenModal(id) {
    setIdPedido(id);
    setMostrarModal(true);
  }

  async function handleSubmit() {
    try {
      await api.get(`pedidos/retirada/${idPedido}`);

      dispatch(reloadDataRequest());

      setMostrarModal(false);
    } catch (error) {
      console.tron.log(error);
    }
  }

  if (loading) {
    return <h1>caregando</h1>;
  }
  return (
    <Container>
      <Headers>
        <strong>Agenda</strong>
      </Headers>
      <Tabela>
        <thead>
          <tr>
            <th>Pedido</th>
            <th>Hora Agendada</th>
            <th>Status</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {agendas.map((agenda) => (
            <tr key={agenda.id}>
              <td>{agenda.pedido.id}</td>
              <td>{agenda.startDateFormated}</td>
              <td>{agenda.retirado ? 'Retirado' : 'Pendente'}</td>
              <td>
                <ButtonRetirar
                  onClick={() => handleOpenModal(agenda.pedido.id)}
                >
                  Retirar
                </ButtonRetirar>
              </td>
            </tr>
          ))}
        </tbody>
      </Tabela>
      {mostrarModal ? (
        <Modal onClose={() => setMostrarModal(false)}>
          <Headers>
            <strong>Confirmar retirada</strong>
          </Headers>
          <DivButton>
            <button
              type="button"
              onClick={(e) => {
                e.preventDefault();
                handleSubmit();
              }}
            >
              Sim
            </button>
          </DivButton>
        </Modal>
      ) : null}
    </Container>
  );
}
