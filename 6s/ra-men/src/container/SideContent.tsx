import React from 'react';
import styled from 'styled-components';
import { Link } from "react-router-dom";
import { DATA } from "@/DataBase";


const Wrapper = styled.div`
    width: 70%;
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