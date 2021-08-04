import { Route, Switch } from 'react-router-dom';
import Cakes from './Cakes';
import About from './About';
import NavbarComp from '../components/Navbar';
import PageNotFound from './PageNotFound';
import { ToastContainer } from 'react-toastify';
import '../styles/App.css';
import Cake from './Cake';

function App() {
  return (
    <div className="container fluid">
      <div id="content">
        <NavbarComp />

        <Switch>
          <Route path="/" exact component={Cakes} />
          <Route path="/cake/:id" component={Cake} />
          <Route path="/cake" component={Cake} />
          <Route path="/about" component={About} />
          <Route component={PageNotFound} />
        </Switch>
      </div>

      <ToastContainer autoClose={3000} hideProgressBar />
    </div>
  );
}

export default App;
