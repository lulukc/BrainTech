import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { reloadDataRequest } from '../../store/modules/data/actions';

import { Container, Headers, Tabela } from '../../styles/stylesList';
import { FormEstoque } from './styles';
import api from '../../services/api';

import Modal from '../../components/Modal';

export default function Estoque() {
  const dispatch = useDispatch();
  const estoques = useSelector((state) => state.data.estoqueLista);
  const loading = useSelector((state) => state.data.loading);

  useEffect(() => {
    dispatch(reloadDataRequest());
  }, [dispatch]);

  const [mostrarModal, setMostrarModal] = useState(false);
  const [quantidade, setQuantidade] = useState('');
  const [estoqueAdd, setEstoqueAdd] = useState({});

  function handleOpenModal(estoque) {
    setEstoqueAdd(estoque);
    setMostrarModal(true);
  }

  async function handleSubmit() {
    try {
      await api.post('estoques/atualizar', {
        produto: estoqueAdd.produto.id,
        quantidade,
      });
      console.tron.log('ate aqui');
      dispatch(reloadDataRequest());
      console.tron.log('ate aqui 2');
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
        <strong>Estoque</strong>
      </Headers>
      <Tabela>
        <thead>
          <tr>
            <th>Produto</th>
            <th>Quantidade</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {estoques.map((estoque) => (
            <tr key={estoque.id}>
              <td>{estoque.produto.nome}</td>
              <td>{`${estoque.quantidade} Kg`}</td>
              <td>
                <button type="button" onClick={() => handleOpenModal(estoque)}>
                  Adicionar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </Tabela>
      {mostrarModal ? (
        <Modal onClose={() => setMostrarModal(false)}>
          <Headers>
            <strong>Adicionar estoque</strong>
          </Headers>
          <FormEstoque
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit();
            }}
          >
            <div>
              <small>{estoqueAdd.produto.nome}</small>
              <input
                name="quantidade"
                type="text"
                required
                onChange={(e) => setQuantidade(e.target.value)}
                value={quantidade}
              />
              <button type="submit">Adicionar</button>
            </div>
          </FormEstoque>
        </Modal>
      ) : null}
    </Container>
  );
}
