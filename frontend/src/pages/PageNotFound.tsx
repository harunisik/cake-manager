import { Link } from 'react-router-dom';
import pageNotFoundImg from '../images/404.jpg';

function PageNotFound() {
  return (
    <>
      <div>
        <img src={pageNotFoundImg} alt="Page not found." className="img-center" />
      </div>
      <div className="line"></div>

      <Link to="/">
        <i className="fas fa-arrow-left fa-fw" />
        Go back to home page.
      </Link>
    </>
  );
}

export default PageNotFound;
