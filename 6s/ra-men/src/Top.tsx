import React from 'react';
import { Link } from 'react-router-dom';
import {DATA} from '@/DataBase';

const Top = () => {
    return (
        <>
            <h1>top</h1>
            {
                DATA.map(x => (
                    <Link key={x.name} to={`/${x.name}`}>{x.name}</Link>
                ))
            }
        </>
    );
};

export default Top;