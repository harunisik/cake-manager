import { Spinner } from 'react-bootstrap';

interface HeaderCellProps {
  title?: string;
  width?: string;
  className?: string;
}

export const HeaderCell = ({ width = '', title = '', className = 'text-left' }: HeaderCellProps) => {
  return (
    <th style={{ width }} className={className}>
      {title}
    </th>
  );
};

interface CollapseCellProps {
  onClick: () => void;
  direction: string;
  size?: number;
}

export const CollapseCell = ({ onClick, direction, size = 0 }: CollapseCellProps) => {
  return size > 0 ? (
    <td onClick={onClick} className="text-left" style={{ cursor: 'pointer' }}>
      {direction === 'down' ? (
        <i className="fas fa-chevron-circle-down" />
      ) : (
        <i className="fas fa-chevron-circle-right" />
      )}
      {` (${size})`}
    </td>
  ) : (
    <td></td>
  );
};

interface StatusCellProps {
  status: 'green' | 'red' | 'grey';
}

export const StatusCell = ({ status }: StatusCellProps) => {
  return (
    <td className="text-center">
      <i className="fas fa-circle" style={{ color: status }} />
    </td>
  );
};

interface EditCellProps {
  onClick: () => void;
}

export const EditCell = ({ onClick }: EditCellProps) => {
  return (
    <td className="text-right">
      <i className="far fa-edit" style={{ cursor: 'pointer' }} onClick={onClick} />
    </td>
  );
};

export const MinusCell = () => {
  return (
    <td className="text-center">
      <i className="fas fa-minus" />
    </td>
  );
};

export const SpinnerRow = () => {
  return (
    <tr className="text-center">
      <td colSpan={7} style={{ verticalAlign: 'middle' }}>
        <Spinner size="sm" animation="border" className="mr-1" />
        Data Loding...
      </td>
    </tr>
  );
};

export const NoDataRow = () => {
  return (
    <tr className="text-center">
      <td colSpan={7} style={{ verticalAlign: 'middle' }}>
        <i className="fas fa-database mr-1" />
        No Data.
      </td>
    </tr>
  );
};
