import React from 'react';
import styled from 'styled-components';
import { Link } from "react-router-dom";
import { DATA } from "@/DataBase";


const Wrapper = styled.div`
    position: sticky;
    margin-bottom: 0;
     top: 10px;
     z-index: 1;
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