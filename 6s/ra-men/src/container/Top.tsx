import React from 'react';
import { Link } from 'react-router-dom';
import { DATA } from '@/DataBase';

const Top = () => {
    return (
        <>
            <h1>top</h1>
            <ul>
            {
                DATA.map((x, i) => (
                    <li key={i}>
                        <Link to={`/${x.name}`}>{x.name}</Link>
                    </li>
                ))
            }
            </ul>
        </>
    );
};

export default Top;