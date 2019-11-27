import React from 'react';
import { useParams } from 'react-router-dom'
import { ramenByName, defaultData } from '@/DataBase';

const App = () => {
    const { name } = useParams();
    const selectedData = name === undefined ? defaultData : ramenByName(name);

        return(
        <>
            {selectedData !== undefined && (
               <h1>{selectedData.name}</h1>
            )}
        </>
    );
};

export default  App;