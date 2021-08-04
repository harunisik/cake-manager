import { Form, Button, Row, Col, Spinner } from 'react-bootstrap';
import { ChangeEventHandler, FormEventHandler } from 'react';
import { LoadStatus, FormSubmitStatus, PageMode } from '../util/PageUtils';
import { CakeData } from '../api/CakeApi';

interface Props {
  pageMode: PageMode;
  formSubmitStatus: FormSubmitStatus;
  loadStatus: LoadStatus;
  cakeData: CakeData;
  onChange: ChangeEventHandler<HTMLInputElement>;
  onSubmit: FormEventHandler<HTMLFormElement>;
}

function CakeForm({ pageMode, formSubmitStatus, loadStatus, cakeData, onChange, onSubmit }: Props) {
  return (
    <Form noValidate validated={formSubmitStatus !== FormSubmitStatus.IDLE} onSubmit={onSubmit} data-testid="cakeForm">
      {pageMode === PageMode.EDIT && (
        <Form.Group as={Row}>
          <Form.Label column sm={3}>
            ID:
          </Form.Label>
          <Col sm={4}>
            {loadStatus === LoadStatus.LOADING ? (
              <Spinner size="sm" animation="border" />
            ) : (
              <Form.Control name="id" value={cakeData.id} readOnly />
            )}
          </Col>
        </Form.Group>
      )}

      <Form.Group as={Row}>
        <Form.Label column sm={3}>
          Name:
        </Form.Label>
        <Col sm={4}>
          {loadStatus === LoadStatus.LOADING ? (
            <Spinner size="sm" animation="border" />
          ) : (
            <Form.Control name="name" value={cakeData.name} onChange={onChange} placeholder="Enter Name" required />
          )}
          <Form.Control.Feedback type="invalid" className="text-left">
            Please provide a valid name.
          </Form.Control.Feedback>
        </Col>
      </Form.Group>

      <Form.Group as={Row}>
        <Form.Label column sm={3}>
          Description:
        </Form.Label>
        <Col sm={4}>
          {loadStatus === LoadStatus.LOADING ? (
            <Spinner size="sm" animation="border" />
          ) : (
            <Form.Control
              name="description"
              value={cakeData.description}
              onChange={onChange}
              placeholder="Enter Description"
              required
            />
          )}
          <Form.Control.Feedback type="invalid" className="text-left">
            Please provide a valid description.
          </Form.Control.Feedback>
        </Col>
      </Form.Group>

      <Form.Group as={Row}>
        <Form.Label column sm={3}>
          Created By:
        </Form.Label>
        <Col sm={4}>
          {loadStatus === LoadStatus.LOADING ? (
            <Spinner size="sm" animation="border" />
          ) : (
            <Form.Control
              name="createdBy"
              value={cakeData.createdBy}
              onChange={onChange}
              placeholder="Enter Created By"
              required
            />
          )}
          <Form.Control.Feedback type="invalid" className="text-left">
            Please provide a valid Created By.
          </Form.Control.Feedback>
        </Col>
      </Form.Group>

      {pageMode === PageMode.EDIT && (
        <Form.Group as={Row}>
          <Form.Label column sm={3}>
            Created Date:
          </Form.Label>
          <Col sm={4}>
            {loadStatus === LoadStatus.LOADING ? (
              <Spinner size="sm" animation="border" />
            ) : (
              <Form.Control name="createdDate" value={cakeData.createdDate} readOnly />
            )}
          </Col>
        </Form.Group>
      )}

      <Form.Group as={Row} className="mb-3">
        <Col sm={{ span: 2, offset: 3 }}>
          <Button
            className="btn-block"
            variant="primary"
            size="sm"
            type="submit"
            disabled={formSubmitStatus === FormSubmitStatus.SUBMITTING || loadStatus !== LoadStatus.IDLE}>
            {formSubmitStatus === FormSubmitStatus.SUBMITTING && (
              <Spinner size="sm" animation="border" className="mr-1" />
            )}
            <i className="fas fa-save mr-2" aria-hidden="true" />
            Save
          </Button>
        </Col>
      </Form.Group>
    </Form>
  );
}

export default CakeForm;
