import React from 'react';
import { Table } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import { LoadStatus } from '../util/PageUtils';
import { EditCell, HeaderCell, NoDataRow, SpinnerRow } from '../util/TableUtil';

interface Props {
  status: LoadStatus;
  cakeList: {
    id: number;
    name: string;
    description: string;
    createdBy: string;
    createdDate: string;
  }[];
}

const CakesTable = ({ cakeList, status }: Props) => {
  const history = useHistory();

  return (
    <Table bordered hover size="sm" style={{ minHeight: '100px' }}>
      <caption style={{ captionSide: 'top', paddingTop: 0 }}>List of cakes</caption>

      <thead className="thead-light">
        <tr>
          <HeaderCell width="5%" title="ID" />
          <HeaderCell width="20%" title="Name" />
          <HeaderCell width="20%" title="Description" />
          <HeaderCell width="20%" title="Created By" />
          <HeaderCell width="20%" title="Created Date" />
          <HeaderCell title="Edit" className="text-right" />
        </tr>
      </thead>
      {status === LoadStatus.LOADING ? (
        <tbody>
          <SpinnerRow />
        </tbody>
      ) : !cakeList || cakeList.length === 0 ? (
        <tbody>
          <NoDataRow />
        </tbody>
      ) : (
        cakeList.map((cake: any, index: number) => {
          return (
            <React.Fragment key={cake.id}>
              <tbody>
                <tr>
                  <td>{cake.id}</td>
                  <td>{cake.name}</td>
                  <td>{cake.description}</td>
                  <td>{cake.createdBy}</td>
                  <td>{cake.createdDate}</td>
                  <EditCell onClick={() => history.push(`/cake/${cake.id}`)} />
                </tr>
              </tbody>
            </React.Fragment>
          );
        })
      )}
    </Table>
  );
};

export default CakesTable;
