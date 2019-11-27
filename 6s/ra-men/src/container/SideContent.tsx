import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { DATA } from '@/DataBase';
import Heading from '@/components/Heading';

const Wrapper = styled.div`
    position: sticky;
    margin-bottom: 0;
    top: 10px; 
    z-index: 1;
    margin-left: 20px;
     ul {
        width: 100%;
        & > li {
            width: 100%;
        }
    }
`;

const SideContent = () => {
    return (
        <>
            <Wrapper>
                <Heading.h2>ラーメンリスト</Heading.h2>
                <ul>
                    {
                        DATA.map((x, i) => (
                            <li key={i}>
                                <Link to={`/${x.name}`}>{x.name}</Link>
                            </li>
                        ))
                    }
                </ul>
            </Wrapper>
        </>
    );
};

export default SideContent;