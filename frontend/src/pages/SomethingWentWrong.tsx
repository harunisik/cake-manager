import { Link } from 'react-router-dom';
import somethingWentWrongImg from '../images/something-went-wrong.jpg';

function SomethingWentWrong() {
  const center = {
    display: 'block',
    marginLeft: 'auto',
    marginRight: 'auto',
    width: '50%',
  };

  return (
    <div className="text-center">
      <div>
        <img src={somethingWentWrongImg} alt="Something went wrong." style={center} />
      </div>
      <div className="line"></div>

      <Link to="/">
        <i className="fas fa-arrow-left fa-fw" />
        Go back to home page.
      </Link>
    </div>
  );
}

export default SomethingWentWrong;
