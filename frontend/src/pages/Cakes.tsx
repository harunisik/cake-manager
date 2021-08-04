import { useEffect, useState } from 'react';
import { Button, Col, Container, Row, Alert } from 'react-bootstrap';
import { useHistory } from 'react-router';
import CakesTable from '../components/CakesTable';
import { getCakes } from '../api/CakeApi';
import { LoadStatus } from '../util/PageUtils';

function Cakes() {
  const [cakes, setCakes] = useState([]);
  const [tableStatus, setTableStatus] = useState(LoadStatus.IDLE);
  const [errorMessage, setErrorMessage] = useState('');
  const history = useHistory();

  useEffect(() => {
    loadCakesData();
  }, []);

  const loadCakesData = () => {
    setTableStatus(LoadStatus.LOADING);

    getCakes()
      .then(({ cakeList }) => {
        setTableStatus(LoadStatus.IDLE);
        setCakes(cakeList);
      })
      .catch((error) => {
        setTableStatus(LoadStatus.FAILED);
        setErrorMessage(error.message);
      });
  };

  const isFailed = () => {
    return tableStatus === LoadStatus.FAILED;
  };

  return (
    <Container fluid>
      <Row>
        <Col>
          <h3>Cakes</h3>
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
            <Alert.Link variant="link" onClick={loadCakesData} className="ml-1">
              Refresh table.
            </Alert.Link>
          </Alert>
        </Col>
      </Row>
      <Row className="mb-3">
        <Col className="text-right">
          <Button variant="outline-info" size="sm" onClick={() => history.push('/cake')}>
            <i className="fas fa-plus fa-fw" aria-hidden="true" />
            Add Cake
          </Button>
        </Col>
      </Row>
      <Row>
        <Col>
          <CakesTable cakeList={cakes} status={tableStatus} />
        </Col>
      </Row>
    </Container>
  );
}

export default Cakes;
