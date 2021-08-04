import { render, screen } from '@testing-library/react';
import About from '../../pages/About';
import '@testing-library/jest-dom/extend-expect';

export const getAboutHeading = () => {
  return screen.getByRole('heading', { name: 'About' });
};

export const findAboutHeading = () => {
  return screen.findByRole('heading', { name: 'About' });
};

const renderAbout = () => {
  return render(<About />);
};

describe('About', () => {
  describe('render', () => {
    it('should return a container', () => {
      const { container } = renderAbout();
      expect(container).toBeDefined();
    });

    it('should display about page elements correctly', () => {
      renderAbout();
      expect(getAboutHeading()).toBeInTheDocument();
    });
  });
});
