import { Button, Modal } from 'react-bootstrap';

interface Props {
  show: boolean;
  onHide: () => void;
  onSave: () => void;
}

function ModalComp({ show, onHide, onSave }: Props) {
  return (
    <Modal show={show} onHide={onHide} aria-labelledby="contained-modal-title-vcenter" centered>
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">Confirm</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p>Do you want to save ?</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Close
        </Button>
        <Button variant="primary" onClick={onSave}>
          Save Changes
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ModalComp;
