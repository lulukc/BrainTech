import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { reloadDataRequest } from '../../store/modules/data/actions';

import { Container, Headers, Tabela } from '../../styles/stylesList';
import Modal from '../../components/Modal';

import { formatPrice } from '../../util/format';

export default function Pedidos() {
  const dispatch = useDispatch();
  const pedidos = useSelector((state) => state.data.pedidosLista);
  const loading = useSelector((state) => state.data.loading);

  useEffect(() => {
    dispatch(reloadDataRequest());
  }, [dispatch]);

  const [mostrarModal, setMostrarModal] = useState(false);
  const [itemPedidos, setItemPedidos] = useState([]);

  function handleOpenModal(itemPedidosParametro) {
    setItemPedidos(itemPedidosParametro);
    setMostrarModal(true);
  }

  if (loading) {
    return <h1>caregando</h1>;
  }
  return (
    <Container>
      <Headers>
        <strong>Todos pedidos</strong>
      </Headers>
      <Tabela>
        <thead>
          <tr>
            <th>Numero</th>
            <th>Status</th>
            <th>Valor</th>
            <th>Empresa solicitante</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {pedidos.map((pedido) => (
            <tr
              key={pedido.id}
              onClick={() => handleOpenModal(pedido.itemPedidos)}
            >
              <td>{pedido.id}</td>
              <td>{pedido.perfil}</td>
              <td>{formatPrice(pedido.total)}</td>
              <td>{pedido.empresa.nomeEmpresa}</td>
            </tr>
          ))}
        </tbody>
      </Tabela>
      {mostrarModal ? (
        <Modal onClose={() => setMostrarModal(false)}>
          <Headers>
            <strong>Detalhe do pedidos</strong>
          </Headers>
          <Tabela>
            <thead>
              <tr>
                <th>Nome do produto</th>
                <th>Quantidade</th>
                <th>Subtotal</th>
                <th>Tipo pedido</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemPedidos.map((itemPedido) => (
                <tr key={itemPedido.id}>
                  <td>{itemPedido.produto.nome}</td>
                  <td>{`${itemPedido.quantidade}Kg`}</td>
                  <td>{formatPrice(itemPedido.subTotal)}</td>
                  <td>{itemPedido.tipoPedido}</td>
                </tr>
              ))}
            </tbody>
          </Tabela>
        </Modal>
      ) : null}
    </Container>
  );
}
