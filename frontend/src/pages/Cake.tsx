import { ChangeEventHandler, FormEventHandler, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useHistory } from 'react-router';
import { getCake, saveCake, updateCake } from '../api/CakeApi';
import { Col, Container, Row, Alert } from 'react-bootstrap';
import CakeForm from '../components/CakeForm';
import { toast } from 'react-toastify';
import { LoadStatus, FormSubmitStatus, PageMode } from '../util/PageUtils';
import ModalComp from '../components/Modal';

interface PageParams {
  id: string;
}

function Cake() {
  const [cakeData, setCakeData] = useState({ id: '', name: '', description: '', createdBy: '', createdDate: '' });
  const [formSubmitStatus, setFormSubmitStatus] = useState(FormSubmitStatus.IDLE);
  const [formLoadStatus, setFormLoadStatus] = useState(LoadStatus.IDLE);
  const [errorMessage, setErrorMessage] = useState('');
  const { id } = useParams<PageParams>();
  const pageMode = id ? PageMode.EDIT : PageMode.NEW;
  const history = useHistory();

  useEffect(() => {
    if (pageMode === PageMode.EDIT) {
      loadCakeData();
    }
  }, []);

  const loadCakeData = () => {
    setFormLoadStatus(LoadStatus.LOADING);

    getCake(id)
      .then((cake) => {
        setFormLoadStatus(LoadStatus.IDLE);
        setCakeData(cake);
      })
      .catch((error) => {
        setFormLoadStatus(LoadStatus.FAILED);
        setErrorMessage(error.message);
      });
  };

  const handleDataChange: ChangeEventHandler<HTMLInputElement> = ({ target }) => {
    const { name, value } = target;
    setCakeData((previousData) => {
      return { ...previousData, [name]: value };
    });
  };

  const handleFormSubmit: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();

    setFormSubmitStatus(FormSubmitStatus.VALIDATED);

    const form = event.currentTarget;

    if (form.checkValidity()) {
      setFormSubmitStatus(FormSubmitStatus.CONFIRM);
    }
  };

  const handleModalOnSave = () => {
    setFormSubmitStatus(FormSubmitStatus.SUBMITTING);

    const data = {
      name: cakeData.name,
      description: cakeData.description,
      createdBy: cakeData.createdBy,
    };

    if (pageMode === PageMode.NEW) {
      saveCake(data)
        .then((cake) => {
          processSuccessResponse();
        })
        .catch((error) => {
          setFormSubmitStatus(FormSubmitStatus.FAILED);
          setErrorMessage(error.message);
        });
    } else if (pageMode === PageMode.EDIT) {
      updateCake(id, data)
        .then((cake) => {
          processSuccessResponse();
        })
        .catch((error) => {
          setFormSubmitStatus(FormSubmitStatus.FAILED);
          setErrorMessage(error.message);
        });
    }
  };

  const processSuccessResponse = () => {
    toast.info('Cake saved successfully.', { position: 'bottom-right' });
    history.push('/');
  };

  const isFailed = () => {
    return formSubmitStatus === FormSubmitStatus.FAILED || formLoadStatus === LoadStatus.FAILED;
  };

  return (
    <>
      <ModalComp
        show={formSubmitStatus === FormSubmitStatus.CONFIRM}
        onHide={() => setFormSubmitStatus(FormSubmitStatus.VALIDATED)}
        onSave={handleModalOnSave}
      />
      <Container fluid>
        <Row>
          <Col>
            <h3>{pageMode === PageMode.EDIT ? 'Edit ' : 'New '}Cake</h3>
          </Col>
        </Row>
        <Row>
          <Col>
            <div className="line" />
          </Col>
        </Row>
        <Row>
          <Col>
            <Alert variant="danger" show={isFailed()} transition={false}>
              <i className="fas fa-exclamation-triangle mr-1" aria-hidden="true" />
              {errorMessage}
            </Alert>
          </Col>
        </Row>
        <Row>
          <Col>
            <CakeForm
              pageMode={pageMode}
              formSubmitStatus={formSubmitStatus}
              loadStatus={formLoadStatus}
              cakeData={cakeData}
              onChange={handleDataChange}
              onSubmit={handleFormSubmit}
            />
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default Cake;
