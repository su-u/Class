import React from 'react';
import { RouteComponentProps, useParams } from 'react-router-dom'
import { ramenByName } from '@/DataBase';

interface Props extends RouteComponentProps<{}> {
    name: string;
}

const App = (props: Props) => {
    const { id } = useParams();
    const selectedData = ramenByName(id);

        return(
        <>
            {id}
            {selectedData !== undefined && (
               <h1>{selectedData.name}</h1>
            )}
        </>
    );
};

export default  App;